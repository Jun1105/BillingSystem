/** @format */

import request from '@/utils/request'

function login(body: { username: string; password: string }) {
  return request({
    url: '/login',
    method: 'post',
    data: {
      ...body
    }
  })
}

export { login }
