/** @format */

import { createRouter, createWebHistory } from 'vue-router'
import login from './routes/login'
import page, { error404 } from './routes/page'
import { userStore } from '@/stores/user'
import { useMenuStore } from '@/stores/menu'
import { getMenu } from '@/api/common'
import menu from './routes/menu'
import loading from '@/utils/loading'

// eslint-disable-next-line @typescript-eslint/no-explicit-any
const routes: any = [login, ...page]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(async (to, from, next) => {
  const user = userStore()
  const useMenu = useMenuStore()
  // ...
  // 返回 false 以取消导航
  // return false

  if (user.userId && to.path !== '/login') {
    loading(true)
    const { data } = await getMenu(user.userId)
    loading(false)
    if (data) {
      useMenu.settingMenu(data)
      const menuList = data.reduce((acc, item) => {
        return acc.concat(item.children)
      }, [])

      const newMenu = menu.filter(item =>
        menuList.find(i => i.url === item.path)
      )
      newMenu.forEach(v => {
        router.addRoute('app', v)
      })
      router.addRoute(error404)
    }
    if (to.matched.length === 0) {
      router.push(to.path)
    }
    next()
  } else {
    if (to.path !== '/login') {
      next('/login')
    } else {
      next()
    }
  }
})

export default router
