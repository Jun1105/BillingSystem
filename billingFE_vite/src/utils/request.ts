/** @format */

import router from '@/routers'
import axios, { AxiosRequestConfig } from 'axios'

type axiosRequestConfig = AxiosRequestConfig

// 创建请求
const service = axios.create({
  baseURL: '/api',
  timeout: 60 * 1000 // 超时
  // withCredentials: true, // 异步请求携带cookie
})
// 请求前
service.interceptors.request.use(
  (config: axiosRequestConfig) => {
    if (config.method === 'get') {
      if (config?.headers)
        config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
    } else {
      if (config?.headers) config.headers['Content-Type'] = 'application/json'
    }
    // 发送请求的相关逻辑
    const token = window.sessionStorage.getItem('token')
    // 判断token存在再做配置
    if (token) {
      config.headers.Authorization = token
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// 请求结果
service.interceptors.response.use(
  response => {
    //返回数据
    const res = response.data
    if (res.data?.token) {
      const token = res.data.token
      window.sessionStorage.setItem('token', token)
    }

    if (res.code < 0) {
      router.push('/404')
    }

    return res
  },
  error => {
    return Promise.reject(error)
  }
)
export default service
