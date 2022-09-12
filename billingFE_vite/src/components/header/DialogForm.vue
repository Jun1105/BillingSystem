<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- eslint-disable vue/require-explicit-emits -->
<script setup lang="ts">
  import { reactive, ref } from 'vue'
  import { ElMessage, FormInstance, FormRules } from 'element-plus'
  import { userStore } from '@/stores/user'
  import loading from '@/utils/loading'
  import { updatePassword } from '@/api/login'
  const user = userStore()

  interface Props {
    dialogFormVisible: boolean
  }

  const props = defineProps<Props>()
  const $emit = defineEmits(['closeDialog'])

  let form = ref({
    password: null,
    repassword: null
  })

  const formRef = ref<FormInstance>()

  const checkPassword = (rule: any, value: any, callback: any) => {
    if (value === '' || value === null) {
      callback(new Error('Please input password'))
    } else if (value !== form.value.password) {
      callback(new Error('两次密码不一致'))
    } else {
      callback()
    }
  }

  const rules = reactive<FormRules>({
    password: [
      { required: true, message: 'Please input password', trigger: 'blur' }
    ],
    repassword: [{ validator: checkPassword, trigger: 'blur' }]
  })

  const clearForm = () => {
    form.value = {
      password: null,
      repassword: null
    }
  }

  const handleClose = (formEl: FormInstance | undefined) => {
    clearForm()
    if (formEl) {
      formEl.resetFields()
    }
    $emit('closeDialog', false)
  }

  const handleConfirm = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
      if (valid) {
        loading(true)
        const res: any = await updatePassword({
          id: user.$state.userId,
          password: form.value.password
        })
        if (res.code === 0 && res.data) {
          ElMessage({
            type: 'success',
            message: '修改密码成功！'
          })
        } else {
          ElMessage({
            type: 'error',
            message: '修改密码失败！'
          })
        }
        loading(false)
        clearForm()
        $emit('closeDialog', false)
      } else {
        console.log('error submit!', fields)
      }
    })
  }
</script>
<template>
  <el-dialog :model-value="props.dialogFormVisible" title="添加账单">
    <el-form ref="formRef" :model="form" :rules="rules">
      <el-form-item label="修改密码：" prop="password">
        <el-input
          type="password"
          v-model="form.password"
          placeholder="Please input password"
          clearable
          maxlength="18"
          show-password
        />
      </el-form-item>
      <el-form-item label="确认密码：" prop="repassword">
        <el-input
          type="password"
          v-model="form.repassword"
          placeholder="Please input password again"
          clearable
          maxlength="18"
          show-password
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="handleClose(formRef)">Cancel</el-button>
        <el-button type="primary" @click="handleConfirm(formRef)">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>
>
<style lang="scss"></style>
