<script lang="ts" setup>
  import { userStore } from '@/stores/user'
  const user = userStore()
  const handleGitHub = () => {
    window.open('https://github.com/Jun1105/BillingSystem')
  }
  const handleExit = () => {
    user.removeUser()
    window.sessionStorage.clear()
    window.location.reload()
  }
</script>
<template>
  <el-row class="el-row-header" :gutter="20">
    <el-col class="el-row-left" :span="4"></el-col>
    <el-col class="el-row-middle" :span="18">
      <span>Welecom to BillingSystem!</span>
    </el-col>
    <el-col :span="2">
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
                <el-dropdown-item @click="handleExit">退出</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>
<style lang="scss">
  .el-row-header {
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
