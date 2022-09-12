import request from '@/utils/request'

function addMenu(body) {
  return request({
    url: '/addMenu',
    method: 'post',
    data: {
      ...body
    }
  })
}

function updateMenu(body) {
  return request({
    url: '/updateMenu',
    method: 'post',
    data: {
      ...body
    }
  })
}

function deleteMenu(menuId: number) {
  return request({
    url: '/deleteMenu',
    method: 'post',
    data: {
      id: menuId
    }
  })
}

export { addMenu, updateMenu, deleteMenu }
