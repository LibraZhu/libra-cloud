import request from '@/utils/request'

export function systemList() {
  return request({
    url: '/admin/system/list',
    method: 'post'
  })
}

export function systemAdd(system) {
  return request({
    url: '/admin/system/add',
    method: 'post',
    data: system
  })
}

export function systemDelete(system) {
  return request({
    url: '/admin/system/delete',
    method: 'post',
    data: system
  })
}
