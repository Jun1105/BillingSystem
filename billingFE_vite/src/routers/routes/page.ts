/** @format */

import App from '@/view/app'
import menu from './menu'

const page = [
  {
    path: '/',
    redirect: 'app'
  },
  {
    path: '/app',
    name: 'app',
    component: async () => await App,
    children: [...menu]
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
