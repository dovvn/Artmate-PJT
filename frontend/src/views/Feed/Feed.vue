<template>
    <div class="fiddiv">
      <div>
        <Navi class="detail__navi"/>
      </div>
    <div class="newsNav" v-on:scroll.passive="handleScroll" data-aos="fade-down"
     data-aos-easing="linear" data-aos-duration="1500" v-if="navType==1" >
    <div id="feednav1" >
        <p id="txt">NEWSFEED</p>
        
        <div id="nav" class="nav2">
            <router-link to="/feed" class="div2" id="follow">팔로우 피드 </router-link> | 
            <router-link to="/feed/bookmark" class="div2" id="mark">북마크 </router-link>
        </div>
    </div>
 </div>

 <!-- 스크롤 내리면 변하는 메뉴바  -->
 <div class="newsNav" v-on:scroll.passive="handleScroll" v-if="navType==2" >
    <div id="feednav2"   >
      <p id="txt2">NEWSFEED</p>
        <div id="nav" class="nav">
            <router-link to="/feed" class="div22" id="follow">팔로우 피드 </router-link> | 
            <router-link to="/feed/bookmark" class="div22" id="mark">북마크 </router-link>
        </div>
    </div>
    </div>
      <router-view></router-view>
  </div>

</template>

<script defer>
import Navi from '@/components/Common/Navi.vue';
import AOS from 'aos';
import "aos/dist/aos.css";
export default {
  name: "Feed",
  components: {
    Navi,
  },
  data(){
    return{
      scrollY: 0,
      navType:1
    }
  },
  created(){
    AOS.init(); 
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  destroyed () {
    window.removeEventListener('scroll', this.handleScroll);
  },
    methods:{
      back:function(){ 
       this.$router.push('/home');
    },
     handleScroll () {
      var _scrollTop = window.scrollY || document.documentElement.scrollTop;
      // console.log(_scrollTop);  
      this.scrollY = _scrollTop;
      console.log("스크롤위치 : "+this.scrollY); 
      console.log("navType : "+this.navType);

      if(this.scrollY > 250) this.navType = 2;
      else if(this.scrollY <= 120) this.navType =1;
    }
  }
}
</script>

<style>
.fiddiv {
  width: 380px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}
.newsNav{
  position: sticky;
  top: 70px;
  background-image : url(../../assets/ngng.png); 
  background-repeat: no-repeat;
  background-position: fixed;
  background-size: cover;
  box-shadow: 0px 4px 7px #00000029;
  border-bottom-right-radius: 40px;
  border-bottom-left-radius: 40px;
}
.detail__navi{
  background-color: white;
}
.nbg{
  width: 900px;
  height: 500px;
  background-position: center top;
  background-attachment: fixed;
  background-repeat: no-repeat;
  /* position: fixed;  */
  /* position: absolute;
  top: 0;
  left: 0; */
  z-index: -1;
  opacity: 0.6;
}
/* ------------------------------- nav css ------------------------------- */

#feednav1{
    text-align: center;
    margin: 0 auto;
    height: 210px;
    margin-bottom: 17px;
}
#feednav1>#txt {
  clear: both;
  float: left;
  font-size: 28px;
  font-weight: bold;
  margin-left: 50px;
  margin-top: 10px;
  padding-bottom: 30px;
}
.nav{
    padding-top: 20px;
    clear: both;
    float: left;
    margin-left: 50px;
    color: #CCCCCC;
}
#follow, #mark{
  text-decoration: none;
  color: #CCCCCC;
}

#feednav2{
    text-align: center;
    margin: 0 auto;
    height: 115px;
    margin-bottom: 17px;
}
#feednav2>#txt2 {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  padding-top: 30px;
  margin-bottom: 0px;
}
.nav2{
    padding-top: 60px;
    clear: both;
    float: left;
    margin-left: 50px;
    color: #CCCCCC;
}
</style>