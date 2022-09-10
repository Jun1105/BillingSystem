<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<script setup lang="ts">
  import { getRole } from '@/api/common'
  import { FormInstance, FormRules } from 'element-plus'
  import { onMounted, reactive, ref } from 'vue'
  import { Edit } from '@element-plus/icons-vue'
  import { addUser, getAllUser, updateUser } from '@/api/login'
  import loading from '@/utils/loading'

  const formRef = ref<FormInstance>()
  const userForm = reactive({
    id: null,
    username: null,
    roleId: null
  })

  const roleList = ref([])

  const getRoleList = async () => {
    const res: any = await getRole()
    if (res.code === 0) {
      roleList.value = [...res.data]
    }
  }

  const getAllUserRole = async () => {
    const res: any = await getAllUser()
    if (res.code === 0) {
      tableRole.value = [...res.data]
    }
  }

  onMounted(() => {
    getRoleList()
    getAllUserRole()
  })

  const tableRole = ref([])
  const tableColumn = ref([
    {
      prop: 'id',
      label: ''
    },
    {
      prop: 'username',
      label: '账号'
    },
    {
      prop: 'roleName',
      label: '角色'
    }
  ])

  const userRule = reactive<FormRules>({
    username: [
      { required: true, message: 'Please input username', trigger: 'blur' },
      { min: 3, message: 'Min Length min 3', trigger: 'blur' }
    ],
    roleId: [{ required: true, message: 'please select role', trigger: 'blur' }]
  })

  const handleUpdate = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
      if (valid) {
        loading(true)
        updateUser(userForm)
          .then(value => {
            const { data } = value
            if (data) {
              ElMessage({
                type: 'success',
                message: '修改成功！'
              })
              getAllUserRole()
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
        addUser(userForm)
          .then(value => {
            const { data } = value
            if (data) {
              ElMessage({
                type: 'success',
                message: '添加成功！'
              })
              getAllUserRole()
              formEl.resetFields()
            } else {
              ElMessage({
                type: 'error',
                message: '添加用户失败！该用户或已存在！'
              })
              userForm.id = null
              isClick.value = true
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
      userForm.username = val.username
      userForm.roleId = val.roleId
      userForm.id = val.id
      ElMessage({
        message: '已选中当前数据,可进行操作',
        type: 'success'
      })
    }
  }
</script>
<template>
  <el-card>
    <el-form ref="formRef" :model="userForm" :rules="userRule">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="8">
          <el-form-item label="账号：" prop="username">
            <el-input
              type="text"
              v-model="userForm.username"
              placeholder="please input name"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="12" :md="12" :lg="6" :xl="4">
          <el-form-item label="类型：" prop="roleId">
            <el-select
              v-model="userForm.roleId"
              placeholder="please select role"
            >
              <el-option
                v-for="(item, index) in roleList"
                :key="index"
                :label="item.role_name"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
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
      :table-data="tableRole"
      :table-column="tableColumn"
      :handle-current-row-change="handleCurrentRowChange"
      :is-pagination="false"
    />
  </el-card>
</template>
<style lang="scss"></style>
