import Vue from 'vue'
import Router from 'vue-router'
import Cookies from "js-cookie"
import Register from '@/views/Register'
import NotFound from '@/views/Error/404'
import Home from '@/views/Home'
import MainPage from '@/views/MainPage'
import Login from '@/views/Login'
import Cardhospital from '@/views/Sys/Cardhospital'
import Preview from '@/views/Sys/Preview'
import PrivacyExplain from '@/views/Sys/PrivacyExplain'
import EndPage from '@/views/EndPage'
import Submit from '@/views/Submit'
import VG70caiye from '@/views/VG70caiye'
import caiye510S from '@/views/caiye510S'
import caiye590P from '@/views/caiye590P'
import Notlogin from '@/views/Notlogin'
import Scanning from '@/views/Scanning'
import BeforeScanning from '@/views/BeforeScanning'
import Operation510S from '@/views/Operation510S'
import Operation510S1 from '@/views/Operation510S1'
import Operation510S2 from '@/views/Operation510S2'
import Operation510S3 from '@/views/Operation510S3'
import VG70Operation from '@/views/VG70Operation'
import VG70Operation1 from '@/views/VG70Operation1'
import VG70Operation2 from '@/views/VG70Operation2'
import VG70Operation3 from '@/views/VG70Operation3'
import VG70Operation4 from '@/views/VG70Operation4'


import api from '@/http/api'
import store from '@/store'
import { getIFramePath, getIFrameUrl } from '@/utils/iframe'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/register',
      name: '注册',
      meta: {
                requireAuth: true, // 判断是否需要登录
            },
      component: resolve => require(['@/views/Register'],resolve)
    } ,
    {
      path: '/vg70operation',
      name: '去扫码',
      component: VG70Operation
    },
    {
      path: '/vg70operation1',
      name: '去扫码',
      component: VG70Operation1
    },
    {
      path: '/vg70operation2',
      name: '去扫码',
      component: VG70Operation2
    },{
      path: '/vg70operation3',
      name: '去扫码',
      component: VG70Operation3
    },{
      path: '/vg70operation4',
      name: '去扫码',
      component: VG70Operation4
    },
    {
      path: '/operation510S',
      name: '去扫码',
      component: Operation510S
    },
    {
      path: '/operation510S1',
      name: '去扫码',
      component: Operation510S1
    },
    {
      path: '/operation510S2',
      name: '去扫码',
      component: Operation510S2
    },
    {
      path: '/operation510S3',
      name: '去扫码',
      component: Operation510S3
    },
    {
      path: '/scanning',
      name: '去扫码',
    
      component: Scanning
    },
    {
      path: '/beforescanning',
      name: '登陆之前',
    
      component:BeforeScanning
    },
  
    {
      path: '/caiye510S',
      name: '彩页',
    
      component: caiye510S
    },
    {
      path: '/notlogin',
      name: '未登录',
      component: Notlogin
    },
    {
      path: '/vg70caiye',
      name: '彩页',
      component: VG70caiye
    },
    {
      path: '/caiye590P',
      name: '彩页',
      component: caiye590P
    },
    {
     path: '/',
     name: '登录',
    component: Login
  },

    {
      path: '/mainpage',
      name: '主页面',
      meta: {
        
         requireAuth: true, // 判断是否需要登录
    },
    
      component: resolve => require(['@/views/MainPage'],resolve)
    },
    
    {
      path: '/submit',
      name: '提交',
      meta: {
        requireAuth: true, // 判断是否需要登录
    },
     component: resolve => require(['@/views/Submit'],resolve)
    },
   
    {
      path: '/privacyexplain',
      name: '隐私说明',
      meta: {
        requireAuth: true, // 判断是否需要登录
    },
      component: resolve => require(['@/views/Sys/PrivacyExplain'],resolve)
    },

    {
      path: '/404',
      name: 'notFound',
      component: NotFound
    }
  ]
  
})

//    router.beforeEach((to, from, next) => {
//   // 登录界面登录成功之后，会把用户信息保存在会话
//   // 存在时间为会话生命周期，页面关闭即失效。
//   let token = Cookies.get('token')
//   let userName = sessionStorage.getItem('user')

//   // if (to.path === '/') {
//   //   //next({ path: '/' })   
//   //   // 如果是访问登录界面，如果用户会话信息存在，代表已登录过，跳转到主页
//   // } else {
//     if (!token) {
//          next({ path: '/' })
//     } else {
//         next()    
//         //next()
//     }
//  // }
// })


// 路由判断登录 根据路由配置文件的参数
   router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requireAuth)){ // 判断该路由是否需要登录权限
  
     let token = Cookies.get('token')

     next();
     
   if (token) { // 判断当前的token是否存在 ； 登录存入的token
    next();
   }
else {
    next({
     path: '/',
     //query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
    })
   }
  }
  else {
   next();
  }
 });

export default router
