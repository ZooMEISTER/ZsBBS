import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'
/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
/* import specific icons */
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'
/* add icons to the library */
library.add(faUserSecret)
import axios from 'axios'
import App from './App.vue'
import routes from './router/index_router'
import store from './store/index_vuex'
import VueCookies from 'vue-cookies'
import vuescroll from 'vuescroll';

import './layout/breadcrumb.css'

//axios.defaults.baseURL = "http://47.97.8.13:8088"
axios.defaults.baseURL = "http://localhost:8088"

const app = createApp(App)

app.config.globalProperties.$cookies = VueCookies
app.use(ElementPlus)
app.use(routes)
// 将 store 实例作为插件安装
app.use(store)
app.use(vuescroll)
app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
// app.use(VueCookies)