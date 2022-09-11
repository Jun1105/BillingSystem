<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- eslint-disable vue/require-explicit-emits -->
<script setup lang="ts">
  import { T } from 'unimport/dist/types-43c63a16'
  import { reactive, ref } from 'vue'
  import type { ElTree, FormInstance, FormRules } from 'element-plus'
  import loading from '@/utils/loading'
  import { useMenuStore } from '@/stores/menu'
  import { searchRoleMenu } from '@/api/common'

  interface Props {
    dialogFormVisible: boolean
    roleList: Array<T>
  }

  const props = defineProps<Props>()
  const $emit = defineEmits(['closeDialog', 'confirmDialog'])

  const useMenu = useMenuStore()
  const menuList = useMenu.menuList
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

  const handleChange = async val => {
    const res: any = await searchRoleMenu({ roleId: val })
    if (res.code === 0) {
      const nodeKeys = res.data.map(item => item.menu_id)
      treeRef.value?.setCheckedKeys(nodeKeys)
    }
  }

  const clearForm = () => {
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
        console.log(form.value)
        // loading(true)
        // loading(false)
        // clearForm()
        // $emit('closeDialog', false, 'confirm')
      } else {
        console.log('error submit!', fields)
      }
    })
  }
</script>
<template>
  <el-dialog :model-value="props.dialogFormVisible" title="权限管理">
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
