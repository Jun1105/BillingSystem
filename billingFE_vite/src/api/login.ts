/** @format */

import request from '@/utils/request'

function login() {
  return request({
    url: '/login',
    method: 'post',
  })
}

export { login }
