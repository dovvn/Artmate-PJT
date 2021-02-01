<template>
  <div class="bgcircle">
    <!-- <img src="../../assets/nbg.png" alt="" class="nbg"> -->
    <div class="fiddiv">
     <!-- <img src="../../assets/circle11.png" alt="" class="circle1">
     <img src="../../assets/circle2.png" alt="" class="circle2" > -->
    <div class="newsNav">
      
      <feed-nav></feed-nav>
      
    </div>
      <router-view></router-view>
  </div>
  </div>
</template>

<script>
import FeedNav from "@/components/Feed/FeedNav.vue";
export default {
    components :{
        FeedNav
    }
}
</script>

<style>
/* @import '../../components/css/common.css'; */
/* .bgcircle{
  background-image:url(../../assets/circle11.png), url(../../assets/circle2.png),url(../../assets/circle3.png); background-size:450px 250px, 200px 200px, 100px 100px; background-position:70% 0px ,25% 65%, 40% 50%; background-repeat:no-repeat;
} */
.fiddiv {
  width: 380px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}
.newsNav{
  /* 그림자 반응형으로 해보기 🤍 */
  box-shadow: 0px 4px 7px #00000029;
  border-bottom-right-radius: 40px;
    border-bottom-left-radius: 40px;
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
/* .circle1{
  width: 450px;
  height: 250px;
  position: fixed;
  z-index: -1;
  opacity: 0.6;
}
.circle2{
  position: fixed;
  z-index: -2;
  opacity: 0.4;
  width: 180px;
  height: 180px;
  
} */

</style>