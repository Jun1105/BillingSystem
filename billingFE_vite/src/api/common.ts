import request from '@/utils/request'

function getMenu(body) {
  return request({
    url: '/getMenu',
    method: 'post',
    data: {
      ...body
    }
  })
}

function getAllMenu() {
  return request({
    url: '/getAllMenu',
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

function searchRoleMenu(body) {
  return request({
    url: '/roleMenu',
    method: 'post',
    data: {
      ...body
    }
  })
}

function updateRole(body) {
  return request({
    url: '/updateRole',
    method: 'post',
    data: {
      ...body
    }
  })
}

export { getMenu, getAllMenu, getType, getRole, searchRoleMenu, updateRole }
