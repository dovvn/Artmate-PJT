<template>
    <div class="fiddiv">
    <div class="newsNav" v-on:scroll.passive="handleScroll"  data-aos-duration="1500" v-if="navType==1" >
    <div id="feednav1" >
      <Navi class="detail__navi"/>
        <p id="txt">NEWSFEED</p>
        <div id="nav" class="nav2">
            <router-link to="/feed" class="div2" id="all"> 전체 피드 </router-link> | 
            <router-link to="/feed/follow" class="div2" id="follow">팔로우 피드 </router-link> | 
            <router-link to="/feed/bookmark" class="div2" id="mark">북마크 </router-link>
        </div>
    </div>
 </div>

 <!-- 스크롤 내리면 변하는 메뉴바  -->
 <div class="newsNav" v-on:scroll.passive="handleScroll" v-if="navType==2" >
    <div id="feednav2"   >
      <Navi class="detail__navi"/>
      <p id="txt2">NEWSFEED</p>
        <div id="nav" class="nav">
          <router-link to="/feed" class="div2" id="all"> 전체 피드 </router-link> | 
            <router-link to="/feed/follow" class="div2" id="follow">팔로우 피드 </router-link> | 
            <router-link to="/feed/bookmark" class="div2" id="mark">북마크 </router-link>
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
      // console.log("스크롤위치 : "+this.scrollY); 
      // console.log("navType : "+this.navType);

      if(this.scrollY > 250) this.navType = 2;
      else if(this.scrollY <= 120) this.navType =1;
    }
  }
}
</script>

<style >
.fiddiv {
  width: 760px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}
.newsNav{
  position: sticky;
  top: 0px;
  background-image : url(../../assets/ngngz.png); 
  background-repeat: no-repeat;
  background-position: fixed;
  background-size: cover;
  box-shadow: 0px 4px 7px #99979725;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
}
.detail__navi{
  position: sticky!important;
  /* background-color: white; */
}
/* ------------------------------- nav css ------------------------------- */

#feednav1{
    text-align: center;
    margin: 0 auto;
    height: 270px;
    margin-bottom: 17px;
}
#feednav1>#txt {
  clear: both;
  float: left;
  font-size: 35px;
  font-weight: bold;
  margin-left: 50px;
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
    height: 150px;
    margin-bottom: 17px;
}
#feednav2>#txt2 {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 0px;
}
.nav2{
    padding-top: 40px;
    clear: both;
    float: left;
    margin-left: 50px;
    color: #CCCCCC;
}
/* ------------------------------ 반응형 ------------------------------ */
@media screen and (max-width: 1024px) {
  .fiddiv {
    width: 380px;
  }
  .newsNav{
    background-image : url(../../assets/ngng.png); 
    background-repeat: no-repeat;
    background-position: fixed;
    background-size: cover;
  }
  #feednav1{
    height: 250px;
  }
  #feednav1>#txt{
    font-size: 28px;
  }
  #feednav2{
    height: 150px;
  }
}
</style>