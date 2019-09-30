import request from '@/utils/request'

export function getInfo() {
  return request({
    url: '/admin/sysUser/info',
    method: 'post'
  })
}

export function userList() {
  return request({
    url: '/admin/sysUser/list',
    method: 'post'
  })
}

export function userAdd(user) {
  return request({
    url: '/admin/sysUser/add',
    method: 'post',
    data: user
  })
}

export function userEdit(user) {
  return request({
    url: '/admin/sysUser/update',
    method: 'post',
    data: user
  })
}

export function userDelete(user) {
  return request({
    url: '/admin/sysUser/delete',
    method: 'post',
    data: user
  })
}

export function userAddRole(userId, roleId) {
  return request({
    url: '/admin/sysUser/addRole',
    method: 'post',
    data: { "userId": userId, "roleId": roleId }
  })
}

export function userDeleteRole(userId, roleId) {
  return request({
    url: '/admin/sysUser/deleteRole',
    method: 'post',
    data: { "userId": userId, "roleId": roleId }
  })
}

export function userRoleList(userId) {
  return request({
    url: '/admin/sysUser/roleList',
    method: 'post',
    data: { "userId": userId }
  })
}
