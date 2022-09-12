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

function getAllUser() {
  return request({
    url: '/getAllUser',
    method: 'get'
  })
}

function addUser(body) {
  return request({
    url: '/addUser',
    method: 'post',
    data: {
      ...body
    }
  })
}

function updateUser(body) {
  return request({
    url: '/updateUser',
    method: 'post',
    data: {
      ...body
    }
  })
}

function updatePassword(body) {
  return request({
    url: '/updatePassword',
    method: 'post',
    data: {
      ...body
    }
  })
}

export { login, getAllUser, addUser, updateUser, updatePassword }
