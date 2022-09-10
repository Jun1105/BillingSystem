<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<script setup lang="ts">
  import { getType } from '@/api/common'
  import { FormInstance, FormRules } from 'element-plus'
  import { onMounted, reactive, ref } from 'vue'
  import { Edit } from '@element-plus/icons-vue'
  import loading from '@/utils/loading'
  import { addType, updateType } from '@/api/type'

  const formRef = ref<FormInstance>()
  const typeForm = reactive({
    name: null,
    id: null
  })

  const getTypeList = async () => {
    const res: any = await getType()
    if (res.code === 0) {
      tableType.value = [...res.data]
    }
  }

  onMounted(() => {
    getTypeList()
  })

  const tableType = ref([])
  const tableColumn = ref([
    {
      prop: 'id',
      label: ''
    },
    {
      prop: 'name',
      label: '类型'
    }
  ])

  const typeRule = reactive<FormRules>({
    name: [
      { required: true, message: 'Please input type', trigger: 'blur' },
      { min: 2, message: 'Min Length min 2', trigger: 'blur' }
    ]
  })

  const handleUpdate = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
      if (valid) {
        loading(true)
        updateType(typeForm)
          .then(value => {
            const { data } = value
            if (data) {
              ElMessage({
                type: 'success',
                message: '修改成功！'
              })
              getTypeList()
              formEl.resetFields()
            } else {
              ElMessage({
                type: 'error',
                message: '修改失败！'
              })
              formEl.resetFields()
            }
          })
          .finally(() => {
            loading(false)
          })
      } else {
        console.log('error submit!', fields)
      }
    })
  }
  const handleAdd = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
      if (valid) {
        loading(true)
        addType(typeForm)
          .then(value => {
            const { data } = value
            if (data) {
              ElMessage({
                type: 'success',
                message: '添加成功！'
              })
              getTypeList()
              formEl.resetFields()
            } else {
              ElMessage({
                type: 'error',
                message: '添加失败！类型已存在！'
              })
              formEl.resetFields()
            }
          })
          .finally(() => {
            loading(false)
          })
      } else {
        console.log('error submit!', fields)
      }
    })
  }

  const isClick = ref(true)
  const currentRow = ref()
  const handleCurrentRowChange = val => {
    if (!val) {
      isClick.value = true
    } else {
      isClick.value = false
      currentRow.value = val
      typeForm.name = val.name
      typeForm.id = val.id
      ElMessage({
        message: '已选中当前数据,可进行操作',
        type: 'success'
      })
    }
  }
</script>
<template>
  <el-card>
    <el-form ref="formRef" :model="typeForm" :rules="typeRule">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :md="10" :lg="8" :xl="8">
          <el-form-item label="类型：" prop="name">
            <el-input
              type="text"
              v-model="typeForm.name"
              placeholder="Such as breakfast"
              maxlength="5"
              show-word-limit
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="10" :xl="6">
          <el-button type="success" @click="handleAdd(formRef)">
            <el-icon><CirclePlus /></el-icon>
            添加
          </el-button>
          <el-button
            type="success"
            :icon="Edit"
            :disabled="isClick"
            @click="handleUpdate(formRef)"
          >
            修改
          </el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
  <el-card :style="{ marginTop: '20px' }">
    <Table
      :table-data="tableType"
      :table-column="tableColumn"
      :handle-current-row-change="handleCurrentRowChange"
      :is-pagination="false"
    />
  </el-card>
</template>
<style lang="scss"></style>
