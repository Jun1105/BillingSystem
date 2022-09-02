import axios from 'axios'

// 创建请求
const service = axios.create({
    baseURL: process.env.VUE_APP_API,//.env文件配置接口地址
    timeout: 60 * 1000, // 超时
    // withCredentials: true, // 异步请求携带cookie
    headers: {
        // 设置后端需要的传参类型
        'Content-Type': 'application/x-www-form-urlencoded'
    },
})

// 请求前
service.interceptors.request.use(
    (config) => {
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
