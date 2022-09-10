import request from '@/utils/request'

function addType(body) {
  return request({
    url: '/addType',
    method: 'post',
    data: {
      ...body
    }
  })
}

function updateType(body) {
  return request({
    url: '/updateType',
    method: 'post',
    data: {
      ...body
    }
  })
}

export { addType, updateType }
