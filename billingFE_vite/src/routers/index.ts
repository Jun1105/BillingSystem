/** @format */

import { createRouter, createWebHistory } from 'vue-router'
import login from './routes/login'
import page from './routes/page'
import { userStore } from '@/stores'

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const routes: any = [...page, login]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(to => {
  const user = userStore()
  // ...
  // 返回 false 以取消导航
  // return false
  if (user.userId) {
    return true
  } else {
    if (to.path === '/login') {
      return true
    } else {
      return { path: '/login' }
    }
  }
})

export default router
