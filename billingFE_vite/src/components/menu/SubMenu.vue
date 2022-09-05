<!-- @format -->

<script setup lang="ts">
  import { Menu as IconMenu } from '@element-plus/icons-vue'
  const routerMenu = [
    {
      title: '用户功能',
      name: '用户功能',
      children: [
        {
          path: '/app/billing',
          title: '账单',
          menu_icon: ''
        }
      ]
    },
    {
      title: '系统功能',
      name: '系统功能',
      children: [
        {
          path: '/app/addUser',
          title: '添加用户',
          menu_icon: ''
        },
        {
          path: '/app/addRouter',
          title: '添加路由',
          menu_icon: ''
        },
        {
          path: '/app/dataList',
          title: '数据字典',
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
        <h5 class="mb-2">Billing System</h5>
        <el-menu default-active="2" class="el-menu-vertical-demo" router>
          <el-menu-item index="0" :route="{ path: '/app' }">
            <el-icon>
              <House />
            </el-icon>
            <span>首页</span>
          </el-menu-item>
          <el-sub-menu
            v-for="(item, index) in routerMenu"
            :key="index"
            :index="index + ''"
          >
            <template #title>
              <el-icon>
                <icon-menu />
              </el-icon>
              <span>{{ item.title }}</span>
            </template>
            <template v-if="item.children">
              <el-menu-item
                v-for="(ite, i) in item.children"
                :key="`${index}-${i}`"
                :index="`${index}-${i}`"
                :route="ite.path"
              >
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
