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
                <span class="scrapCnt"> {{exhibit.name}}</span>
              </div>
              <div class="exInfo">
                  <div class="ex__date">
                    <div class="day"> 날짜 : </div>
                    <div class="days"> {{exhibit.startDate}} ~ {{exhibit.endDate}} </div>
                  </div>
                  <div class="ex__area">
                    <div class="area"> 장소 : </div>
                    <div class="areas"> {{exhibit.location}} </div>
                  </div>
                  <div class="ex__artist">
                    <div class="artist"> 작가 : </div>
                    <div class="artist" v-if="exhibit.artist != null"> {{exhibit.artist}} </div>
                    <div class="artists" v-else> 작가없음 '[총 41명/팀] 강상우, 고등어, 김민, 라이프 오브 어 크랩헤드 (에이미 램, 존 맥컬리)[Life of a Craphead (Amy Lam and John McCurley)], 류한솔, 리랴오(Li Liao), 리우추앙(Liu Chuang), 리처드 벨(Richard Bell), 림기옹(Lim Giong), 무니라 알 카디리(Mounira Al Qadiri), 미네르바 쿠에바스(Minerva Cuevas), 밍 웡(Ming Wong), 바니 아비디(Bani Abidi), 브리스 델스페제(Brice Dellsperger), 사라 라이(Srah Lai), 샤론 헤이즈(Sharon Hayes), 쉬쩌위(Hsu Che-Yu), 씨씨 우(Cici Wu), 아마츄어 증폭기, 아이사 혹슨(Eisa Jocson), 야마시로 치카코(Chikako Yamashiro), 올리버 라릭(Oliver Laric), 왕하이양(Want Haiyang), 요한나 빌링(Johanna Billing), 유리 패티슨(Yuri Pattison), 장영혜중공업, 장윤한(Chang Yun-Han), 정금형, 취미가X워크스, 치호이(Chihoi), 탈라 마다니(Tala Madani), 토비아스 칠로니(Tobias Zielony), 폴 파이퍼(Paul Pfeiffer), 폴린 부드리/레나테 로렌츠(Pauline Boudry / Renate Lorenz), 필비 타칼라(Pilvi Takala), 하오징반(Hao Jingban), 합정지구, 헨리케 나우만(Henrike Naumann), 홍진훤, DIS, ONEROOM' </div>
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
            userInfo:{
                userId:"",
            },
        };
    },
    created() {
        this.userInfo =  this.$store.getters.getUser;
        this.id = this.$route.params.id;
        console.log(this.id);
        http
        .get(`/api/exhibit/${this.userInfo.userId}/${this.id}`) 
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
        clear: both;
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
    }
    .days, .areas, .artists, .texts{
        padding-left: 55px;
        color: #FFFFFF;
        font-size: 14px;
        height: auto;
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