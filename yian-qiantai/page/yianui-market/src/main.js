import Vue from 'vue'
import App from './App'
import router from './router'
import api from './http'
import i18n from './i18n'
import store from './store'
import global from '@/utils/global'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'font-awesome/css/font-awesome.min.css'
import '@/assets/iconfont/iconfont.css'
import VideoPlayer from 'vue-video-player'
import VueCookies from 'vue-cookies'
import Mui from 'vue-awesome-mui'
Vue.use(Mui)
Vue.use(VueCookies)
Vue.use(ElementUI)
Vue.use(api)
Vue.use(VideoPlayer);
Vue.prototype.global = global

new Vue({
  el: '#app',
  i18n,
  router,
  store,
  render: h => h(App)
  
});

router.afterEach((to,from, next) => {
  
  window.scrollTo(0,0)
  
  })
// // 路由判断登录 根据路由配置文件的参数
//    router.beforeEach((to, from, next) => {
//   if (to.matched.some(record => record.meta.requireAuth)){ // 判断该路由是否需要登录权限
//    console.log('需要登录');
//    if (localStorage.token) { // 判断当前的token是否存在 ； 登录存入的token
//     next();
//    }
//    else {
//     next({
//      path: '/',
//      query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
//     })
//    }
//   }
//   else {
//    next();
//   }
//  });

//    router.beforeEach((to, from, next) => {
//   // 登录界面登录成功之后，会把用户信息保存在会话
//   // 存在时间为会话生命周期，页面关闭即失效。
//   let token = Cookies.get('token')
//   let userName = sessionStorage.getItem('user')

//   if (to.path === '/') {
//     // 如果是访问登录界面，如果用户会话信息存在，代表已登录过，跳转到主页
//     if(token) {
//          next({ path: '/mainpage' })    
//     } else {
         
//          next()    
//     }
//   } else {
//     if (!token) {
//          next({ path: '/' })
//     } else {
//         next()    
  
//     }
//   }
// })