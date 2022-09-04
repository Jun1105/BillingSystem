<!-- @format -->

<template>
  <div class="box-card">
    <el-card shadow="always">
      <template #header>
        <h3>Welecom to Billing System!</h3>
      </template>
      <el-form
        ref="formRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        status-icon
      >
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" show-password v-model="ruleForm.password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(formRef)">
            Submit
          </el-button>
          <el-button @click="resetForm(formRef)">Reset</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script lang="ts" setup>
  import { reactive, ref } from 'vue'
  import type { FormInstance, FormRules } from 'element-plus'
  const formRef = ref<FormInstance>()
  const ruleForm = reactive({
    username: '',
    password: ''
  })

  const rules = reactive<FormRules>({
    username: [
      { required: true, message: 'Please input username', trigger: 'blur' },
      { min: 3, message: 'Min Length min 3', trigger: 'blur' },
    ],
    password: [
      { required: true, message: 'Please input password', trigger: 'blur' },
      { min: 3, message: 'Min Length is 3', trigger: 'blur' },
    ]
  })

  const submitForm = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
      if (valid) {
        console.log(ruleForm)
        console.log('submit!')
      } else {
        console.log('error submit!', fields)
      }
    })
  }

  const resetForm = (formEl: FormInstance | undefined) => {
    if (!formEl) return
    formEl.resetFields()
  }
</script>
<style lang="scss">
  .box-card {
    width: 30%;
    margin: 0 auto;
  }
</style>
