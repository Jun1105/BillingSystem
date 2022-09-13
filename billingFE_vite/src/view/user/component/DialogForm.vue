<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- eslint-disable vue/require-explicit-emits -->
<script setup lang="ts">
  import { T } from 'unimport/dist/types-43c63a16'
  import { onMounted, reactive, ref } from 'vue'
  import { ElMessage, ElTree, FormInstance, FormRules } from 'element-plus'
  import loading from '@/utils/loading'
  import { useMenuStore } from '@/stores/menu'
  import { getAllMenu, getMenu, searchRoleMenu, updateRole } from '@/api/common'
  import { userStore } from '@/stores/user'

  interface Props {
    dialogFormVisible: boolean
    roleList: Array<T>
  }

  const user = userStore()
  const props = defineProps<Props>()
  const $emit = defineEmits(['closeDialog', 'confirmDialog'])

  const useMenu = useMenuStore()
  const menuList = ref([])
  const treeRef = ref<InstanceType<typeof ElTree>>()

  const defaultProps = {
    children: 'children',
    label: 'title'
  }

  let form = ref({
    roleId: null,
    menuIdList: []
  })

  const formRef = ref<FormInstance>()

  const rules = reactive<FormRules>({
    roleId: [{ required: true, message: 'Please select role', trigger: 'blur' }]
  })

  onMounted(() => {
    getMenuList()
  })

  const getMenuList = async () => {
    const menuData: any = await getAllMenu()
    if (menuData.code === 0) {
      menuList.value = menuData.data
    }
  }

  const handleChange = async val => {
    const res: any = await searchRoleMenu({ roleId: val })
    if (res.code === 0) {
      const nodeKeys = res.data.map(item => item.menu_id)
      nodeKeys.forEach(item => {
        treeRef.value?.setChecked(item, true, false)
      })
    }
  }

  const clearForm = () => {
    treeRef.value?.setCheckedKeys([])
    form.value = {
      roleId: null,
      menuIdList: []
    }
  }

  const handleClose = (formEl: FormInstance | undefined) => {
    clearForm()
    if (formEl) {
      form.value.menuIdList = []
      formEl.resetFields()
    }
    $emit('closeDialog', false)
  }

  const handleConfirm = async (formEl: FormInstance | undefined) => {
    if (!formEl) return
    await formEl.validate(async (valid, fields) => {
      if (valid) {
        form.value.menuIdList = [].concat(
          treeRef.value?.getCheckedKeys(),
          treeRef.value?.getHalfCheckedKeys()
        )
        loading(true)
        const res: any = await updateRole(form.value)
        if (res.code === 0 && res.data) {
          ElMessage({
            type: 'success',
            message: '权限修改成功!'
          })
        } else {
          ElMessage({
            type: 'error',
            message: '权限修改失败!'
          })
        }
        const result = await getMenu({ id: user.userId })
        useMenu.settingMenu(result.data)
        clearForm()
        $emit('closeDialog', false, 'confirm')
        loading(false)
      } else {
        console.log('error submit!', fields)
      }
    })
  }
</script>
<template>
  <el-dialog
    :model-value="props.dialogFormVisible"
    :close-on-click-modal="false"
    :close-on-press-escape="false"
    title="权限管理"
  >
    <el-form ref="formRef" :model="form" :rules="rules">
      <el-form-item label="角色：" prop="roleId">
        <el-select
          v-model="form.roleId"
          placeholder="please select role"
          @change="handleChange"
        >
          <el-option
            v-for="(item, index) in roleList"
            :key="index"
            :label="item.role_name"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="角色：">
        <el-tree
          ref="treeRef"
          :data="menuList"
          show-checkbox
          default-expand-all
          node-key="id"
          highlight-current
          :props="defaultProps"
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
