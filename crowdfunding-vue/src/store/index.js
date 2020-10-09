import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    active: 0
  },
  mutations: {
    SET_INDEX(state,active){
      state.active = active;
    }
  },
  actions: {
    setIndex({commit},index){
      commit('SET_INDEX',index);
    }
  },
  modules: {
  },
  getters:{
    getIndex: state => state.active
  }
})
