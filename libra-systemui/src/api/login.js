import request from '@/utils/request'

export function login(account, password) {
  return request({
    url: '/admin/sysUser/login',
    method: 'post',
    data: {
      account,
      password
    }
  })
}

export function logout() {
  return request({
    url: '/admin/sysUser/logout',
    method: 'post'
  })
}
