import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './index.css'
import router from "./routers"
import i18n from './i18n'

const app = createApp(App)
const pinia = createPinia()

app.use(ElementPlus, { i18n: (key, value) => i18n.t(key, value) })
app.use(router)
app.use(pinia)
app.use(i18n)

app.mount('#app')
