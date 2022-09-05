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
  }
]

export default page
