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
    component: async () => await App
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
