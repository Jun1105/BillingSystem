const mentRouter = [
  {
    path: '/app/billing',
    name: 'billing',
    title: '账单',
    menu_icon: '',
    component: () => import('@/view/order/Order.vue')
  },
  {
    path: '/app/addUser',
    name: 'addUser',
    title: '添加用户',
    menu_icon: '',
    component: () => import('@/view/user/User.vue')
  },
  {
    path: '/app/addRouter',
    name: 'addRouter',
    title: '添加路由',
    menu_icon: '',
    component: () => import('@/view/add_router/AddRouter.vue')
  },
  {
    path: '/app/dataList',
    name: 'dataList',
    title: '数据字典',
    menu_icon: '',
    component: () => import('@/view/data_list/DataList.vue')
  }
]

export default mentRouter
