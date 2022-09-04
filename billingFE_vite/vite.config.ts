import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'

import { resolve } from 'path'


//配置路径
const pathResolve = (dir: string): any => {
    return resolve(__dirname, ".", dir)
}

const alias: Record<string, string> = {
    '@': pathResolve("src")
}

// https://vitejs.dev/config/
export default ({ mode }) => {
    const env = loadEnv(mode, __dirname)   // 获取当前环境下的.env文件内容
    console.log(env);
    return defineConfig({
        plugins: [vue()],
        server: {
            port: 80,
            proxy: {
                "/api": {
                    target: env.VITE_APP_API,
                    changeOrigin: true, //将主机头的来源更改为目标 URL
                    ws: false, //如果你想代理 websockets
                    rewrite: (path) => path.replace(/^\/api/, '')
                }
            }
        },
        resolve: {
            alias
        }
    })
}