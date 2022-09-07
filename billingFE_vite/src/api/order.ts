import request from '@/utils/request'

function getAllOrder(body) {
  return request({
    url: '/getAllOrder',
    method: 'post',
    data: {
      page: 1,
      size: 10,
      ...body
    }
  })
}

function addOrder(body) {
  return request({
    url: '/addOrder',
    method: 'post',
    data: {
      ...body
    }
  })
}

function updateOrder(body) {
  return request({
    url: '/updateOrder',
    method: 'post',
    data: {
      ...body
    }
  })
}

export { getAllOrder, addOrder, updateOrder }
