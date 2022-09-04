import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router"
import login from "./routes/login"
import page from "./routes/page"

const routes: Array<RouteRecordRaw> = [
    ...page,
    login
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from) => {
    // ...
    // 返回 false 以取消导航
    // return false
  })

export default router
