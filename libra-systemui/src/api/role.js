import request from '@/utils/request'

export function roleList() {
  return request({
    url: '/admin/role/list',
    method: 'post'
  })
}

export function roleAdd(role) {
  return request({
    url: '/admin/role/add',
    method: 'post',
    data: role
  })
}

export function roleUpdate(role) {
  return request({
    url: '/admin/role/update',
    method: 'post',
    data: role
  })
}

export function roleDelete(role) {
  return request({
    url: '/admin/role/delete',
    method: 'post',
    data: role
  })
}

export function roleResourceList(roleId) {
  return request({
    url: '/admin/role/resourceList',
    method: 'post',
    data: { "roleId": roleId }
  })
}

export function roleResourceModify(roleId, list) {
  return request({
    url: '/admin/role/modifyResourceList',
    method: 'post',
    data: { "roleId": roleId, "resourceList": list }
  })
}
