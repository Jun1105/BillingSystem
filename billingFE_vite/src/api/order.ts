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

function deleteOrder(orderId: number) {
  return request({
    url: '/deleteOrder',
    method: 'post',
    data: {
      orderId
    }
  })
}

function getOrderCount(body) {
  return request({
    url: '/getOrderCount',
    method: 'post',
    data: {
      ...body
    }
  })
}

function getTypeCount(body) {
  return request({
    url: '/getTypeCount',
    method: 'post',
    data: {
      ...body
    }
  })
}
function getWeekOrder(body) {
  return request({
    url: '/getWeekOrder',
    method: 'post',
    data: {
      ...body
    }
  })
}

export {
  getAllOrder,
  addOrder,
  updateOrder,
  deleteOrder,
  getOrderCount,
  getTypeCount,
  getWeekOrder
}
