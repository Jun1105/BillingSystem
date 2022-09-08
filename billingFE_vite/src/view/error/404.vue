<!-- @format -->

<script setup lang="ts">
  import { useRouter } from 'vue-router'
  const router = useRouter()
  const handleClick = () => {
    router.push('/')
  }
</script>
<template>
  <div class="page404">
    <h1>404</h1>
    <el-button @click="handleClick">返回</el-button>
  </div>
</template>
<style>
  .page404 {
    text-align: center;
  }
</style>
