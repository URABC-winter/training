import Vue from 'vue'
import 'normalize.css/normalize.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import '@/styles/index.scss' // global css

import App from './App'

import store from './store'

import router from './router'

// 注册svg
import '@/icons'

// 结果：吧element-ui中导出的所有组件，注册成全局组件
Vue.use(ElementUI)

// 关闭生产模式下给出的提示
Vue.config.productionTip = false

// vue实例化
// 注入router 和 store 实例，方便在项目中使用 this.$router, this.$store
new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
})