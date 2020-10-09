import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import qs from 'qs'

import VCharts from 'v-charts'

Vue.use(VCharts)
Vue.config.productionTip = false;
Vue.prototype.$qs = qs;

axios.defaults.withCredentials=true;

//前端拦截器
router.beforeEach((to, from, next) => {
      if (to.meta.requireAuth) {
          let power=0;
          if (sessionStorage.getItem("user")!==null) {
              power=1;
          }
          if (sessionStorage.getItem("admin")!==null) {
              power=2;
          }
          if(to.meta.level<=power){
              next()
          }else {
              const result =power-to.meta.level
              next({
                  path: (result === -2 ? 'adminLogin':'/userLogin')
              });
              Vue.prototype.$message({
                  message: "权限不足！",
                  type: 'error'
              });
          }
      } else {
        next()
      }
    }
);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app');
