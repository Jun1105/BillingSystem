module.exports = {
    devServer: {
        port: 80,
        proxy: {
            '/api': {
                target: process.env.VUE_APP_API,
                ws: false, //如果你想代理 websockets
                changeOrigin: true, //将主机头的来源更改为目标 URL
                pathRewrite: {// 重写路径: 去掉路径中开头的'/api'
                    '^/api': ''
                }
            },
        }
    },
}