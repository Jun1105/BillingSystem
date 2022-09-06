/** @format */

import App from '@/view/app'

const page = [
  {
    path: '/',
    redirect: 'app'
  },
  {
    path: '/app',
    name: 'app',
    component: async () => await App,
    redirect: '/app/home',
    children: [
      {
        path: '/app/home',
        name: 'home',
        title: '首页',
        menu_icon: '',
        component: () => import('@/view/home/Home.vue')
      }
    ]
  },
  {
    path: '/404',
    component: () => import('@/view/error/404.vue')
  },
  {
    path: '/:pathMatch(.*)',
    redirect: '/404'
  }
]

export const error404 = {
  path: '/:pathMatch(.*)',
  redirect: '/404'
}

export default page
