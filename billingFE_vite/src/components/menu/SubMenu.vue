<!-- @format -->

<script setup lang="ts">
  import {
    Document,
    Menu as IconMenu,
    Location,
    Setting
  } from '@element-plus/icons-vue'
  const routerMenu = [
    {
      title: '用户功能',
      name: '',
      children: [
        {
          path: '/billing',
          title: '记账',
          menu_icon: ''
        },
        {
          path: '/billing',
          title: '记账',
          menu_icon: ''
        }
      ]
    },
    {
      title: '系统功能',
      name: '',
      children: [
        {
          path: '/addUser',
          title: '添加用户',
          menu_icon: ''
        }
      ]
    }
  ]
</script>
<template>
  <el-scrollbar>
    <el-row class="tac">
      <el-col>
        <h5 class="mb-2">Default colors</h5>
        <el-menu default-active="2" class="el-menu-vertical-demo" router>
          <el-menu-item index="0" :route="{ path: '/' }">
            <el-icon>
              <House />
            </el-icon>
            <span>首页</span>
          </el-menu-item>
          <el-sub-menu v-for="(item, index) in routerMenu" :index="index">
            <template #title>
              <el-icon>
                <location />
              </el-icon>
              <span>{{ item.title }}</span>
            </template>
            <template v-if="item.children">
              <el-menu-item
                v-for="(ite, i) in item.children"
                :index="`${index}-${i}`"
                :route="ite.path"
              >
                <el-icon>
                  <icon-menu />
                </el-icon>
                <span>{{ ite.title }}</span>
              </el-menu-item>
            </template>
          </el-sub-menu>
        </el-menu>
      </el-col>
    </el-row>
  </el-scrollbar>
</template>
<style lang="scss"></style>
