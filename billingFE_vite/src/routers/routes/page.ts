/** @format */

import App from '@/view/app'
const page = [
  {
    path: '/',
    redirect: 'app',
    children: [
      {
        path: '/app',
        name: 'App',
        component: () => App
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

export default page
