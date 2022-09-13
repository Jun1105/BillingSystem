<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<script setup lang="ts">
  import { getAllMenu } from '@/api/common'
  import {
    ElMessage,
    ElMessageBox,
    FormInstance,
    FormRules
  } from 'element-plus'
  import { onMounted, reactive, ref } from 'vue'
  import { Edit, Delete } from '@element-plus/icons-vue'
  import loading from '@/utils/loading'
  import { addMenu, deleteMenu, updateMenu } from '@/api/menu'

  const formRef = ref<FormInstance>()
  const menuForm = reactive({
    id: null,
    pid: null,
    title: null,
    url: null
  })

  const parentList = ref([])

  const checkUrl = (rule: any, value: any, callback: any) => {
    if (menuForm.pid && !value) {
      callback(new Error('Please input url'))
    } else {
      callback()
    }
  }

  const menuRule = reactive<FormRules>({
    title: [
      { required: true, message: 'Please input menu name', trigger: 'blur' }
    ],
    url: [{ validator: checkUrl, trigger: 'blur' }]
  })

  const tableMenu = ref([])
  const tableColumn = ref([
    {
      prop: 'index',
      label: ''
    },
    {
      prop: 'title',
      label: '账号'
    },
    {
      prop: 'url',
      label: '路径地址'
    }
  ])

  const getMenuList = async () => {
    const menuData: any = await getAllMenu()
    if (menuData.code === 0) {
      parentList.value = menuData.data
      const tableData = menuData.data.reduce((acc, item) => {
        acc.push(item)
        if (item.children) {
          item.children.forEach(i => {
            acc.push(i)
          })
        }
        return acc
      }, [])
      tableMenu.value = tableData.map((item, index) => {
        return {
          index: index + 1,
          ...item
        }
      })
    }
  }

  onMounted(() => {
    getMenuList()
  })

  const handleUpdate = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
      if (valid) {
        loading(true)
        updateMenu(menuForm)
          .then(value => {
            const { data } = value
            if (data) {
              ElMessage({
                type: 'success',
                message: '修改成功！'
              })
              getMenuList()
              menuForm.id = null
              menuForm.pid = null
              isClick.value = true
              formEl.resetFields()
            } else {
              ElMessage({
                type: 'error',
                message: '修改失败！'
              })
              menuForm.id = null
              menuForm.pid = null
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
  const handleAdd = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate((valid, fields) => {
      if (valid) {
        loading(true)
        addMenu(menuForm)
          .then(value => {
            const { data } = value
            if (data) {
              ElMessage({
                type: 'success',
                message: '添加成功！'
              })
              getMenuList()
              formEl.resetFields()
            } else {
              ElMessage({
                type: 'error',
                message: '添加失败！类型已存在！'
              })
              menuForm.id = null
              menuForm.pid = null
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

  const handleDelete = async (formEl: FormInstance | undefined) => {
    ElMessageBox.confirm('确认要删除吗?', 'Warning', {
      confirmButtonText: 'Confirm',
      cancelButtonText: 'Cancel',
      type: 'warning'
    })
      .then(() => {
        loading(true)
        deleteMenu(currentRow.value.id)
          .then(value => {
            if (value.data) {
              getMenuList()
              ElMessage({
                type: 'success',
                message: 'Delete Success'
              })
            } else {
              ElMessage({
                type: 'error',
                message: 'Delete Failed'
              })
            }
            menuForm.id = null
            menuForm.pid = null
            isClick.value = true
            formEl.resetFields()
          })
          .catch(() => {
            ElMessage({
              type: 'error',
              message: 'Delete Failed'
            })
          })
          .finally(() => {
            loading(false)
          })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: 'Delete Canceled'
        })
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
      menuForm.id = val.id
      menuForm.title = val.title
      menuForm.url = val.url
      menuForm.pid = val.pid
      ElMessage({
        message: '已选中当前数据,可进行操作',
        type: 'success'
      })
    }
  }
</script>
<template>
  <el-card>
    <el-form ref="formRef" :model="menuForm" :rules="menuRule">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :md="12" :lg="8" :xl="6">
          <el-form-item label="父菜单：">
            <el-select
              v-model="menuForm.pid"
              clearable
              placeholder="please select parent menu"
            >
              <el-option
                v-for="(item, index) in parentList"
                :key="index"
                :label="item.title"
                :value="item.id"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="12" :md="10" :lg="8" :xl="6">
          <el-form-item label="子菜单：" prop="title">
            <el-input
              type="text"
              v-model="menuForm.title"
              placeholder="Please input menu name"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="12" :md="10" :lg="8" :xl="6">
          <el-form-item label="路径地址：" prop="url">
            <el-input
              type="text"
              v-model="menuForm.url"
              placeholder="Please input url"
              @keyup="menuForm.url = menuForm.url.replace(/[^a-zA-Z]/g, '')"
              clearable
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="6">
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
          <el-button
            type="danger"
            :icon="Delete"
            :disabled="isClick"
            @click="handleDelete(formRef)"
          >
            删除
          </el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
  <el-card :style="{ marginTop: '20px' }">
    <Table
      :table-data="tableMenu"
      :table-column="tableColumn"
      :handle-current-row-change="handleCurrentRowChange"
      :is-pagination="false"
    />
  </el-card>
</template>
<style lang="scss"></style>
