import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
<<<<<<< HEAD
=======
    isLogin: false,
    user: {
      userId : '',
      userPw : '',
      userName : '',
      feedName : '',
      introduction : '',
      userImg : '',
      accessKey : '',
      myTag: [],
    },
    message : '',
  },
  getters: {
    getUser(state) {
      return state.user;
    },
    getMessage(state){
      return state.message;
    }
>>>>>>> develop
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
