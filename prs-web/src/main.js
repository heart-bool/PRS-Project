import Vue from 'vue'
import App from './App.vue'
import Axios from 'axios'
import qs from 'qs'

Vue.prototype.$axios = Axios;  //在Vue的原型上添加$axios方法



//设置baseURL
Axios.defaults.baseURL = 'http://gateway-server:8080';
//设置token值
Axios.defaults.headers.common['token'] = "9afefd93361ff2fbe368384fd142c6d4";
//请求头
Axios.defaults.headers.post['Content-Type'] = 'application/json';

new Vue({
  el: '#app',
  render: h => h(App)
})
