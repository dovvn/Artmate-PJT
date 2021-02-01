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
import { faHeart as farHeart, faCommentAlt as farCommentAlt, faBookmark as farBookmark} from '@fortawesome/free-regular-svg-icons'
import { faEnvira } from '@fortawesome/free-brands-svg-icons'
import { faCamera , faPenFancy, faTrashAlt} from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faSearch, faBars, faBell, faTimes, faCheck, faChevronRight,faPallet,faLeaf } from '@fortawesome/free-solid-svg-icons'

library.add(faMapMarkerAlt,faEnvira,fasHeart,farHeart, fasBookmark, farBookmark, faShareAlt,faPaperPlane,faChevronLeft, faUserCircle, faChevronCircleRight, fasCommentAlt, farCommentAlt,faCheckCircle, faEdit)
library.add(faCamera,faPenFancy, faTrashAlt)
library.add(faSearch, faBars, faBell, faTimes, faCheck,faChevronRight,faPallet,faLeaf)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

new Vue({
  vuetify,
  store,
  router,
  render: h => h(App)
}).$mount('#app')
