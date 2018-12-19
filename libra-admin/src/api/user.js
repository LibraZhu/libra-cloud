import request from '@/utils/request'

export function getInfo() {
  return request({
    url: '/admin/user/info',
    method: 'post'
  })
}

export function userList() {
  return request({
    url: '/admin/user/list',
    method: 'post'
  })
}

export function userAdd(user) {
  return request({
    url: '/admin/user/add',
    method: 'post',
    data: user
  })
}

export function userEdit(user) {
  return request({
    url: '/admin/user/update',
    method: 'post',
    data: user
  })
}

export function userDelete(user) {
  return request({
    url: '/admin/user/delete',
    method: 'post',
    data: user
  })
}

export function userAddRole(userId, roleId) {
  return request({
    url: '/admin/user/addRole',
    method: 'post',
    data: { "userId": userId, "roleId": roleId }
  })
}

export function userDeleteRole(userId, roleId) {
  return request({
    url: '/admin/user/deleteRole',
    method: 'post',
    data: { "userId": userId, "roleId": roleId }
  })
}

export function userRoleList(userId) {
  return request({
    url: '/admin/user/roleList',
    method: 'post',
    data: { "userId": userId }
  })
}
