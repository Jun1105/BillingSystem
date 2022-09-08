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

export { getMenu, getType }
