<template>
  <div class="exdiv_detail">
      <div>
        <Navi class="exDetial__navi"/>
      </div>
      <div class="detail">
          <!-- 전시회 포스터  -->
          <div class="exPoster">
              <img class="poster_img" :src="exhibit.exImg" alt="" >
          </div>
          <div class="bar"></div>
          <!-- 전시회내용 -->
          <div class="exContent">
              <div class="exName">
                <font-awesome-icon :icon="['fab', 'envira']" class="ex__icon" style="color:#A593DF"/>
                <span class="ex__name"> {{exhibit.name}}</span>
              </div>
              <div class="exInfo">
                  <div class="ex__date">
                    <span class="day"> 날짜 : </span>
                    <span class="day"> {{exhibit.startDate}} ~ {{exhibit.endDate}} </span>
                  </div>
                  <div class="ex__area">
                    <span class="area"> 장소 : </span>
                    <span class="area"> {{exhibit.location}} </span>
                  </div>
                  <div class="ex__artist">
                    <span class="artist"> 작가 : </span>
                    <span class="artist" v-if="exhibit.artist != null"> {{exhibit.artist}} </span>
                    <span class="artist" v-else> 작가없음 </span>
                  </div>
                  <div class="ex__text">
                    <div class="text"> 소개 : </div>
                    <div class="texts" v-if="exhibit.description != null">{{exhibit.description}} </div>
                    <div class="texts" v-else> 소개없음 </div>
                  </div>
              </div>
          </div>
          <div class="bar"></div>
          <!-- 함께 즐겨요 -->
          <div class="exReview">
              <div class="exFeeds">
                <font-awesome-icon :icon="['fas', 'globe-americas']" class="earth__icon" style="color:#5F9EA0"/>
                <span class="ex__name"> 함께 즐겨요 </span>
                <div class="mention">
                    <span class="feeds">6</span><span class="feeds">명의 회원님이 </span>
                <span class="feeds">"{{exhibit.name}}"</span><span class="feeds"> 을 먼저 다녀가셨어요 😃</span>
                </div>
                <div class="review__list">
                    <img class="feed_img" src="../../assets/sample.jpg" alt="" >
                    <img class="feed_img" src="../../assets/sample.jpg" alt="" >
                    <img class="feed_img" src="../../assets/sample.jpg" alt="" >
                    
                </div>
              </div>
          </div>
          <div class="bar"></div>
          <!-- 오시는 길 -->
          <div class="exRode">
            <font-awesome-icon icon="map-marker-alt" class="location__icon" style="color:gray"/>
            <span class="ex__name"> 오시는 길 </span>
            <div>
                 <img class="rode_img" src="../../assets/test_rode.jpg" alt="" >
            </div>
           
          </div>
      </div>
  </div>
</template>

<script>
import Navi from '@/components/Common/Navi.vue';
import http from "@/util/http-common";
export default {
    name: "ExhibitDetail",
    components: {
        Navi,
    },
    data() {
        return {
            id: 0,
            exhibit:{},
        };
    },
    created() {
        this.id = this.$route.params.id;
        console.log(this.id);
        http
        .get(`/api/exhibit/${this.id}`) 
        .then(res => {
            console.log(res.data);
            this.qna = res.data;
        })
        .catch(err => {
            console.log(err);
        });
    },

}
</script>

<style>
@import "../../components/css/style.css";
    .exDetial__navi{
        width: 380px;
    }
    .exdiv_detail{
        width: 380px;
        height: 100%;
        text-align: center;
        margin: 0 auto;
        padding-bottom: 40px;
        background-color: #313030;
    }
    .detail{
        padding-top: 70px;
        width: 340px;
        text-align: center;
        margin: 0 auto;
    }
    .bm-burger-bars {
        background-color: white;
    }
    .navi_item{
        color: white;
    }
    .exPoster{
        padding-top: 10px;
    }
    .poster_img{
        width: 280px;
        text-align: center;
        margin: 0 auto;
        box-shadow: 0px 4px 10px gray;
        margin-bottom: 25px;
    }
    .bar{
        padding-top: 10px;
        border-bottom: solid 0.1px #B9B9B9;
    }
    .exContent, .exReview, .exRode{
        padding-top: 10px;
        text-align: left;
        font-size: 20px;
    }
    .exName, .exFeeds{
        padding-bottom: 15px;
    }
    .ex__name{
        font-size: 20px;
        color: white;
    }
    .exContent, .exReview, .exRode{
        width: 320px;
        padding-top: 20px;
        margin: 0 auto;
    }
    .ex__date, .ex__area, .ex__artist {
        border-left: solid 3px #A593DF;
        height: 20px;
        margin-bottom: 12px;
        width: 100%;

    }
    .text{
        border-left: solid 3px #A593DF;
    }
    .day, .area, .artist, .text{
        color: #FFFFFF;
        font-size: 14px;
        float: left;
        padding-left: 10px;
        height: auto;
    }
    .texts{
        padding-left: 55px;
        color: #FFFFFF;
        font-size: 14px;
        
    }
    .feeds{
        color: #FFFFFF;
        font-size: 12px;
    }
    .mention{
        margin-left: 10px;
    }
    .review__list{
        margin-top: 10px;
        line-height: 100px;
    }
    .feed_img{
        width: 80px;
        height: 80px;
        border-radius: 12px;
        vertical-align: middle;
        margin-right: 13px;
    }
    .rode_img{
        display: block;
        width: 270px;
        text-align: center;
        margin: 0 auto;
        padding-top: 10px;
    }
</style>