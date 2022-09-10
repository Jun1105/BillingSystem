import request from '@/utils/request'

function getMenu(userId) {
  return request({
    url: `/getMenu/${userId}`,
    method: 'post'
  })
}

function getType() {
  return request({
    url: '/getType',
    method: 'get'
  })
}

function getRole() {
  return request({
    url: '/getRole',
    method: 'get'
  })
}

export { getMenu, getType, getRole }
