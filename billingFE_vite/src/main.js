/** @format */

import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './index.css'
import router from './routers'
import i18n from './i18n'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import piniaPluginPersist from 'pinia-plugin-persist'
import 'dayjs/locale/zh-cn'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

const app = createApp(App)
const pinia = createPinia()
pinia.use(piniaPluginPersist)

app.use(ElementPlus, {
  i18n: (key, value) => i18n.t(key, value),
  locale: zhCn
})
app.use(router)
app.use(pinia)
app.use(i18n)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.mount('#app')
