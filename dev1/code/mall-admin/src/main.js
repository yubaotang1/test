import Vue from 'vue'

import VueRouter from 'vue-router'
import router from './router'
import axios from 'axios'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'

// 安装路由
Vue.use(VueRouter);

Vue.use(ElementUI)

Vue.prototype.$axios = axios

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
