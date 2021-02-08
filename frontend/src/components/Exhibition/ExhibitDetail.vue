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
                <span class="scrapCnt"> 
                    <font-awesome-icon v-if="exhibit.scrapmark == 0"  :icon="['far', 'star']" style="color:white"/> 
                    <font-awesome-icon v-if="exhibit.scrapmark == 1"  :icon="['fas', 'star']" style="color:white"/>
                    {{exhibit.scrapCnt}}</span>
              </div>
              <div class="exInfo">
                  <div class="ex__date">
                    <div class="day"> 날짜 : </div>
                    <div class="days"> {{exhibit.startDate}} ~ {{exhibit.endDate}} </div>
                  </div>
                  <div class="ex__area">
                    <div class="area"> 장소 : </div>
                    <div class="areas"> {{exhibit.location}}</div>
                  </div>
                  <div class="ex__artist">
                    <div class="artist"> 작가 : </div>
                    <div class="artists" v-if="exhibit.artist != null && !showArtist"> {{exhibit.artist}} </div>
                    <div class="artistTogle" v-if="exhibit.artist != null && showArtist"> {{exhibit.artist}} </div>
                    <div class="artists" v-if="exhibit.artist == null"> 작가없음 </div>
                    <div class="btn">
                        <b-button class="more" pill variant="outline-secondary" v-if="!showArtist" @click="toggleArtistShow">더보기▼</b-button>
                        <b-button class="mores" pill variant="outline-secondary" v-if="showArtist" @click="toggleArtistShow">닫기 X</b-button>
                    </div>
                  </div>
                  <div class="ex__text">
                    <div class="text"> 소개 : </div>
                    <div class="texts" v-if="exhibit.description != null && !showDes">{{exhibit.description}} </div>
                    <div class="textTogle" v-if="exhibit.description != null && showDes">{{exhibit.description}} </div>
                    <div class="texts" v-if="exhibit.description == null"> 소개없음 </div>
                    <div class="btn">
                        <b-button class="more" pill variant="outline-secondary" v-if="exhibit.description != null && !showDes" @click="toggleDesShow">더보기▼</b-button>
                        <b-button class="mores" pill variant="outline-secondary" v-if="exhibit.description != null && showDes" @click="toggleDesShow">닫기 X</b-button>
                    </div>
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
                    <span class="feeds">{{exhibit.feedCnt}}</span><span class="feeds">명의 회원님이 </span>
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
            exhibit:{
                artist:"",
                description:"",
                startDate:"",
                endDate:"",
                exImg:"",
                feedCnt:0,
                location:"",
                name:"",
                scrapCnt:0,
                scrapmark:0
            },
            userInfo:{
                userId:"",
            },
            showArtist: false,
            showDes: false
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
    methods:{
        toggleArtistShow(){
            this.showArtist = !this.showArtist;
        },
        toggleDesShow(){
            this.showDes = !this.showDes;
        }
    }

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
    .ex__date, .ex__area, .ex__artist, .ex__text{
        height: auto;
        margin-bottom: 12px;
        
    }
    .day, .area, .artist, .text{
        color: #FFFFFF;
        font-size: 14px;
        float: left;
        padding-left: 10px;
        border-left: solid 3px #A593DF;
    }
    .days, .areas, .artists, .texts{
        padding-left: 10px;
        color: #FFFFFF;
        font-size: 14px;
        height: auto;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: normal; 
        line-height: 1.25; 
        text-align: left; 
        word-wrap: break-word; 
        display: -webkit-box; 
        -webkit-line-clamp: 3; 
        -webkit-box-orient: vertical;
    }
    /* .areas{
        clear: both;
    } */
    .artistTogle, .textTogle{
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
    .scrapCnt{
        float: right;
        font-size: 12px;
        color: white;
    }
    .more{
        color: #A593DF;
        border: 1px solid #A593DF;
        font-size: 11px;
        line-height: 13px;
        margin-top: 2px;
        margin-bottom: 10px;
        float: right;
    }
    .more:hover{
        background-color: #A593DF;
        color:whitesmoke;
    }
    .mores{
        background-color: #A593DF;
        color:whitesmoke;
        font-size: 11px;
        line-height: 13px;
        margin-top: 2px;
        margin-bottom: 10px;
        float: right;
    }
    .btn > .mores{
        margin-left: 126px;
    }
    .btn{
        height: auto;
        margin-left: 110px;
    }
</style>