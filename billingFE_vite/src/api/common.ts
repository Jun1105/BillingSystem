import request from '@/utils/request'

function getMenu(userId) {
  return request({
    url: `/getMenu/${userId}`,
    method: 'post'
  })
}

export { getMenu }
