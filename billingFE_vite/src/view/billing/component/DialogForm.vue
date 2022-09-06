<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- eslint-disable vue/require-explicit-emits -->
<script setup lang="ts">
  import { T } from 'unimport/dist/types-43c63a16'
  import { reactive, ref, watch } from 'vue'
  import type { FormInstance, FormRules } from 'element-plus'

  interface Props {
    dialogFormVisible: boolean
    typeList: Array<T>
    currentRow?: any
  }

  const props = defineProps<Props>()
  const $emit = defineEmits(['closeDialog', 'confirmDialog'])

  watch(
    () => props.dialogFormVisible,
    visible => {
      if (visible && props.currentRow) {
        form.value = { ...props.currentRow }
      }
    }
  )

  let form = ref({
    type: null,
    description: null,
    amount: 0,
    date: null
  })

  const formRef = ref<FormInstance>()

  const rules = reactive<FormRules>({
    type: [{ required: true, message: 'Please input type', trigger: 'blur' }],
    amount: [
      { required: true, message: 'Please input amount', trigger: 'blur' }
    ],
    date: [{ required: true, message: 'Please input date', trigger: 'blur' }]
  })

  const clearForm = () => {
    form.value = {
      type: null,
      description: null,
      amount: 0,
      date: null
    }
  }

  const disabledDate = (time: Date) => {
    return time.getTime() > Date.now()
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
    await formEl.validate((valid, fields) => {
      if (valid) {
        console.log(form.value)
        clearForm()
        $emit('closeDialog', false)
      } else {
        console.log('error submit!', fields)
      }
    })
  }
</script>
<template>
  <el-dialog :model-value="props.dialogFormVisible" title="Shipping address">
    <el-form ref="formRef" :model="form" :rules="rules">
      <el-form-item label="类型：" prop="type">
        <el-select v-model="form.type" placeholder="please select type">
          <el-option
            v-for="item in props.typeList"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="描述：">
        <el-input
          type="text"
          v-model="form.description"
          placeholder="Such as breakfast"
          maxlength="20"
          show-word-limit
        />
      </el-form-item>
      <el-form-item label="金额：" prop="amount">
        <el-input-number
          v-model="form.amount"
          class="mx-4"
          :min="0"
          :precision="2"
          controls-position="right"
        />
      </el-form-item>
      <el-form-item label="时间：" prop="date">
        <el-date-picker
          v-model="form.date"
          type="date"
          placeholder="Pick a day"
          :disabled-date="disabledDate"
          value-format="YYYY-MM-DD"
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
