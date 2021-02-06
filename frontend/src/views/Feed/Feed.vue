<template>
    <div class="fiddiv">
      <div>
        <Navi class="detail__navi"/>
      </div>
    <div class="newsNav" v-on:scroll.passive="handleScroll"  data-aos-duration="1500" v-if="navType==1" >
    <div id="feednav1" >
        <p id="txt">NEWSFEED</p>
        
        <div id="nav" class="nav2">
            <router-link to="/feed" class="div2" id="follow">팔로우 피드 </router-link> | 
            <router-link to="/feed/bookmark" class="div2" id="mark">북마크 </router-link>
        </div>
        <div>
          <img class="bu" src="../../assets/newsfeedPicture.png" alt="">
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
      // console.log("스크롤위치 : "+this.scrollY); 
      // console.log("navType : "+this.navType);

      if(this.scrollY > 250) this.navType = 2;
      else if(this.scrollY <= 120) this.navType =1;
    }
  }
}
</script>

<style>
.fiddiv {
  width: 760px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}
.newsNav{
  position: sticky;
  top: 70px;
  background-image : url(../../assets/ngngz.png); 
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
/* ------------------------------- nav css ------------------------------- */

#feednav1{
    text-align: center;
    margin: 0 auto;
    height: 300px;
    margin-bottom: 17px;
}
#feednav1>#txt {
  clear: both;
  float: left;
  font-size: 35px;
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
    height: 120px;
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
    padding-top: 130px;
    clear: both;
    float: left;
    margin-left: 50px;
    color: #CCCCCC;
}
.bu{
    padding-top: 60px;
    margin-left: 120px;
    width: 300px;
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
  .bu{
    padding-top: 75px;
    width: 160px;
    margin-left: 0px;
  }
  #feednav1{
    height: 210px;
  }
  .nav2{
    padding-top: 60px;
  }
  #feednav1>#txt{
    font-size: 28px;
  }
  #feednav2{
    height: 115px;
  }
}
</style>