<template>
  <div class="exdiv_detail">
      <div>
        <Navi class="exDetial__navi"/>
      </div>
      <div class="detail">
          <!-- 전시회 포스터  -->
          <div class="exPoster">
              <img class="poster_img" src="../../assets/test_exPoster.jpg" alt="" >
          </div>
          <div class="bar"></div>
          <!-- 전시회내용 -->
          <div class="exContent">
              <div class="exName">
                <font-awesome-icon :icon="['fab', 'envira']" class="ex__icon" style="color:#A593DF"/>
                <span class="ex__name"> 서울의 전차</span>
              </div>
              <div class="exInfo">
                  <div class="ex__date">
                    <span class="day"> 날짜 : </span>
                    <span class="day"> 2021.04.05 ~ 2021.04.28 </span>
                  </div>
                  <div class="ex__area">
                    <span class="area"> 장소 : </span>
                    <span class="area"> 경기도 해움미술관 </span>
                  </div>
                  <div class="ex__artist">
                    <span class="artist"> 작가 : </span>
                    <span class="artist"> 김남주 </span>
                  </div>
                  <div class="ex__text">
                    <span class="text"> 소개 : </span>
                    <div class="texts"> 동양이나 서양이나 산업혁명 이전 도시들은 '성곽 도시' 라고 불리는 방어형 도시였다. 성곽도시는 전 통적으로 뚜렷한 경계와 영역을 갖는 폐쇄적 공간 구조를 갖는데 성벽은 군사행정을 위한 건조물인 동시에 안과 밖을 분리해 도시 안의 재화가 빠져나 가는 것을 막았으며, 성문은 그 흐름을 관리하는 수 단이었다. 우리가 마주하고 있는 도시 풍경과 도시 문화는 어떤 양상을 띠고 있는가? </div>
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
                <span class="feeds">"외관의 지층들"</span><span class="feeds"> 을 먼저 다녀가셨어요 😃</span>
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
export default {
    name: "ExhibitDetail",
    components: {
        Navi,
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