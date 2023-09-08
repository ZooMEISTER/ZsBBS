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


//vantui
import { Popup } from 'vant';
import { Button } from 'vant';
import { Dialog } from 'vant';
import { Form, Field, CellGroup } from 'vant';
import { Uploader } from 'vant';
import { BackTop } from 'vant';
import { Divider } from 'vant';
import { Lazyload } from 'vant';
import { ActionBar, ActionBarIcon, ActionBarButton } from 'vant';
import { FloatingPanel } from 'vant';
import { Overlay } from 'vant';
import { FloatingBubble } from 'vant';

import 'vant/lib/index.css';


import './layout/breadcrumb.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

//axios.defaults.baseURL = "http://47.97.8.13:8088"
axios.defaults.baseURL = "http://localhost:8088"
//axios.defaults.baseURL = "http://192.168.5.13:8088"

const app = createApp(App)

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.config.globalProperties.$cookies = VueCookies
app.use(ElementPlus)
app.use(routes)
// 将 store 实例作为插件安装
app.use(store)
app.use(vuescroll)

app.use(Popup);
app.use(Button);
app.use(Dialog);
app.use(Form);
app.use(Field);
app.use(CellGroup);
app.use(Uploader);
app.use(BackTop);
app.use(Divider);
app.use(Lazyload);
app.use(ActionBar);
app.use(ActionBarIcon);
app.use(ActionBarButton);
app.use(FloatingPanel);
app.use(Overlay);
app.use(FloatingBubble);

app.component('font-awesome-icon', FontAwesomeIcon)
app.mount('#app')
// app.use(VueCookies)