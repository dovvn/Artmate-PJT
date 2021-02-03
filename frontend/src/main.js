import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import store from './store'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
Vue.config.productionTip = false
import Carousel3d from 'vue-carousel-3d';
Vue.use(Carousel3d)

import { library } from '@fortawesome/fontawesome-svg-core'
import { faMapMarkerAlt, faHeart as fasHeart, faBookmark as fasBookmark,faShareAlt,faPaperPlane, faChevronLeft, faUserCircle, faChevronCircleRight, faCommentAlt as fasCommentAlt, faCheckCircle, faEdit } from '@fortawesome/free-solid-svg-icons'
import { faHeart as farHeart, faCommentAlt as farCommentAlt, faBookmark as farBookmark, faFlag as farFlag} from '@fortawesome/free-regular-svg-icons'
import { faEnvira } from '@fortawesome/free-brands-svg-icons'
import { faCamera , faPenFancy, faTrashAlt} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faSearch, faBars, faBell, faTimes, faCheck, faChevronRight,faPallet,faLeaf } from '@fortawesome/free-solid-svg-icons'

//구글 로그인
import GAuth from 'vue-google-oauth2'

library.add(faMapMarkerAlt,faEnvira,fasHeart,farHeart, fasBookmark, farBookmark, faShareAlt,faPaperPlane,faChevronLeft, faUserCircle, faChevronCircleRight, fasCommentAlt, farCommentAlt,faCheckCircle, faEdit)
library.add(faCamera,faPenFancy, faTrashAlt)
library.add(faSearch, faBars, faBell, faTimes, faCheck,faChevronRight,faPallet,faLeaf,farFlag)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

//구글 로그인
Vue.use(GAuth, {clientId: '586176925871-o9lo1kb9m1rgft73q8n4sicrh4knvlr6.apps.googleusercontent.com', scope: 'profile email https://www.googleapis.com/auth/plus.login'})

//카카오 로그인
window.Kakao.init("8ddb98b2ba952c3a17143df31ebbc92d");

new Vue({
  vuetify,
  store,
  router,
  render: h => h(App)
}).$mount('#app')
