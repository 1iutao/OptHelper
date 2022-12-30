import { createApp } from 'vue'
import App from './App.vue'
import installElementPlus from './plugins/element'
import store from './store'
import router from './router'
import 'element-plus/lib/theme-chalk/index.css'
import '@/styles/index.scss'

const app = createApp(App).use(router).use(store)
installElementPlus(app)
app.mount('#app')
