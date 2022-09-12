<script lang="ts" setup>
  import { userStore } from '@/stores/user'
  import { ref } from 'vue'
  import DialogFormVue from './DialogForm.vue'
  const user = userStore()
  const handleGitHub = () => {
    window.open('https://github.com/Jun1105/BillingSystem')
  }
  const handleExit = () => {
    user.removeUser()
    window.sessionStorage.clear()
    window.location.reload()
  }
  const handleUpdatePassword = () => {
    dialogFormVisible.value = true
  }

  const dialogFormVisible = ref(false)
  const handleCloseDialog = value => {
    dialogFormVisible.value = value
  }
</script>
<template>
  <el-row class="el-row-header" :gutter="20">
    <el-col class="el-row-left" :span="4"></el-col>
    <el-col class="el-row-middle" :span="17">
      <span>Hello {{ user.$state.userName }}, Welecom to BillingSystem!</span>
    </el-col>
    <el-col :span="3">
      <el-row class="el-row-right">
        <el-col :span="8"></el-col>
        <el-col :span="8">
          <div class="icon-click" @click="handleGitHub">
            <el-tooltip
              class="box-item"
              effect="dark"
              content="git hub 地址"
              placement="bottom"
            >
              <el-avatar
                size="small"
                src="https://img0.baidu.com/it/u=1438527857,1168554193&fm=253&fmt=auto&app=138&f=PNG?w=500&h=500"
              />
            </el-tooltip>
          </div>
        </el-col>
        <el-col :span="8">
          <el-dropdown class="icon-click">
            <el-avatar
              size="small"
              src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
            />
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="handleUpdatePassword">
                  修改密码
                </el-dropdown-item>
                <el-dropdown-item @click="handleExit">退出</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
  <DialogFormVue
    :dialog-form-visible="dialogFormVisible"
    @close-dialog="handleCloseDialog"
  />
</template>
<style lang="scss">
  .el-row-header {
    text-align: center;
    height: 100%;
    .el-row-middle {
      line-height: 8vh;
      font-weight: 800;
    }
    .el-row-right {
      display: flex;
      justify-content: center;
      align-content: center;
      height: 100%;
      .icon-click {
        cursor: pointer;
      }
    }
  }
</style>
