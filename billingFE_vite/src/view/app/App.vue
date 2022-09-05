<!-- @format -->

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header">Header</el-header>
      <el-container class="un_header">
        <el-aside class="aside"><SubMenu></SubMenu></el-aside>
        <el-main class="main">Main</el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
  import SubMenu from '@/components/menu/SubMenu.vue'
</script>

<style lang="scss">
  .common-layout {
    .header {
      height: 8vh;
      background: rgb(50, 44, 44);
    }
    .un_header {
      height: 92vh;
      .aside {
        width: 10vw;
        background: rgb(117, 137, 237);
      }
      .main {
        background: rgb(50, 155, 18);
      }
    }
  }
</style>
