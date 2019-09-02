import request from '@/request'

export function login(account, password) {
  const data = {
    account,
    password
  }
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}

export function getUserInfo() {
  return request({
    url: '/users/currentUser',
    method: 'get'
  })
}

export function register(account, nickname, password) {
  const data = {
    account,
    nickname,
    password
  }
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

/**
 * 修改用户信息
 */
export function alterUserInfo(account,nickname,sex,profession,birthday,hobby,education,introduction){
  const data = {
    account,
    nickname,
    sex,
    profession,
    birthday,
    hobby,
    education,
    introduction
  }
  return request({
    url: '/users/update',
    method: 'post',
    data
  })
}

/**
 * 修改用户密码
 */
 export function alterUserPassword(account,password){
   const data ={
     account,
     password
   }
   return{
     url:'/users/update',
     method:'post',
     data
   }
}
