import request from '@/utils/request'

function getAllOrder(body) {
  return request({
    url: '/getAllOrder',
    method: 'post',
    data: {
      page: 1,
      size: 5,
      ...body
    }
  })
}

export { getAllOrder }
