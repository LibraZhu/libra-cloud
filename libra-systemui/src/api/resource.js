import request from '@/utils/request'

export function resourceList() {
  return request({
    url: '/admin/resource/list',
    method: 'post'
  })
}
