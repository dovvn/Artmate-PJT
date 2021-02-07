<template>
  <div class="exdiv">
      <div>
        <Navi class="detail__navi"/>
      </div>
      <div class="detail">
          <!-- 전시회 포스터  -->
          <div class="exPoster">
              <img class="poster_img" src="../../assets/test_exPoster.jpg" alt="" >
          </div>
          <!-- 전시회내용 -->
          <div class="exContent">

          </div>
          <!-- 함께 즐겨요 -->
          <div class="exReview">

          </div>
          <!-- 오시는 길 -->
          <div class="exRode">

          </div>
      </div>
  </div>
</template>

<script>
import Navi from '@/components/Common/Navi.vue';
export default {
    name: "ExhibitDetail",
    components: {
        Navi,
    },

}
</script>

<style>
.exdiv{
    width: 380px;
    height: 100%;
  text-align: center;
  margin: 0 auto;
  padding-bottom: 40px;
  padding-top: 40px;
}
.detail{
    width: 340px;
    text-align: center;
  margin: 0 auto;
}
.poster_img{
width: 280px;
text-align: center;
  margin: 0 auto;
}
</style>