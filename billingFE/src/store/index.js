import { createStore } from 'vuex'

// 获得全部module
const files = require.context('./modules', false, /\.js$/)
const modules = {}
// 收集
files.keys().forEach((key) => {
  // 文件名为key
  modules[key.replace(/(\.\/|\.js)/g, '')] = files(key).default
})
Object.keys(modules).forEach((key) => {
  // 带命名空间，防止不唯一
  modules[key]['namespaced'] = true
})
const store = createStore({ modules })

export default store