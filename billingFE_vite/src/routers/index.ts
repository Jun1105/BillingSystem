/** @format */

import { createRouter, createWebHistory } from 'vue-router'
import login from './routes/login'
import page from './routes/page'
import { userStore } from '@/stores'
import { getMenu } from '@/api/common'
import menu from './routes/menu'

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const routes: any = [login, ...page]

const router = createRouter({
  history: createWebHistory(),
  routes
})

getMenu(1)
  .then(value => {
    const { data } = value
    const menuList = data.reduce((acc, item) => {
      return acc.concat(item.children)
    }, [])
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const newPage: any = page

    const newMenu = menu.filter(item => menuList.find(i => i.url === item.path))
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    newPage.forEach((item: any) => {
      if (item.path === '/app') {
        item.children = newMenu
      }
    })
    router.addRoute(newPage)
  })
  .catch(err => {
    console.log(err)
  })

router.beforeEach(to => {
  const user = userStore()
  // ...
  // 返回 false 以取消导航
  // return false
  if (!user.userId && to.path !== '/login') {
    return { path: '/login' }
  } else {
    return true
  }
})

export default router
