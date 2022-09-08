<!-- @format -->

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="header"><Header></Header></el-header>
      <el-container class="un_header">
        <el-aside class="aside"><SubMenu></SubMenu></el-aside>
        <el-main class="main">
          <el-scrollbar><router-view></router-view></el-scrollbar>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script setup lang="ts">
  import SubMenu from '@/components/menu'
  import Header from '@/components/header'
</script>

<style lang="scss">
  .common-layout {
    .header {
      height: 8vh;
      box-shadow: 0 6px 5px -5px rgba(0, 0, 0, 0.1);
    }
    .un_header {
      height: 92vh;
      .aside {
        width: 12vw;
        box-shadow: 6px 0 5px -5px rgba(0, 0, 0, 0.1);
      }
      .main {
        margin: 10px;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
      }
    }
  }
</style>
