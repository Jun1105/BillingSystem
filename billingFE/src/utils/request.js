import axios from 'axios'

// 创建请求
const service = axios.create({
    baseURL: '/api',
    timeout: 60 * 1000 // 超时
    // withCredentials: true, // 异步请求携带cookie
})
// 请求前
service.interceptors.request.use(
    (config) => {
        if (config.method === 'get') {
            config.headers['Content-Type'] = 'application/x-www-form-urlencoded'
        } else {
            config.headers['Content-Type'] = 'application/json'
        }
        return config
    },
    (error) => {
        return Promise.reject(error)
    }
)

// 请求结果
service.interceptors.response.use(
    (response) => {
        //返回数据
        const res = response.data
        return res
    },
    (error) => {
        return Promise.reject(error)
    }
)
export default service
