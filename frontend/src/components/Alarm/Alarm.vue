<template>
  <div class="alarmPage">
    <div class="header">
      <button class="back__button" @click="goBack">
        <font-awesome-icon icon="chevron-left"/>
      </button>
      <div class="title">알림</div>
    </div>
    
    <div class="tabs">
      <div class="left__tab choosed" @click="showExhibition">전시회 알림</div>
      <span class="tab__divider">|</span>
      <div class="right__tab" @click="showFeed">피드 알림</div>
    </div>
    
    <div class="line"></div>
    <div class="feed__alarms" v-if="this.choosed === 'feed'">
      <div class="follow__alarm">
        <div class="alarm__left">
          <img src="../../assets/test_alarm/ex1.png"  class="follow__alarm__img" alt="">
        </div>
        <div class="alarm__middle">
          <div class="follow__alarm__body">
            <div class="follow__alarm__content">박싸피님이 당신의 피드를 <br/> 팔로우했습니다.</div>
          </div>
          <div class="follow__alarm__buttons">
            <button class="follow__yes__button">수락</button>
            <button class="follow__no__button">삭제</button>  
          </div>
        </div>
        <div class="alarm__right">
          <div class="exhibition__alarm__date">1시간 전</div>
        </div>
      </div>

      <div class="like__alarm">
        <div class="alarm__left">
          <img src="../../assets/test_alarm/ex1.png"  class="like__alarm__img" alt="">
        </div>
        <div class="like__alarm__middle">
          <div class="like__alarm__body">
            <div class="like__alarm__content">이연희님 외 3명이 당신의<br/> 게시글을 좋아합니다.</div>
          </div>
          
        </div>
        <div class="alarm__right">
          <div class="exhibition__alarm__date">3시간 전</div>
        </div>
      </div>

      <div class="post__alarm read">
        <div class="alarm__left">
          <img src="../../assets/test_alarm/ex1.png"  class="like__alarm__img" alt="">
        </div>
        <div class="like__alarm__middle">
          <div class="like__alarm__body">
            <div class="like__alarm__content">팔로워 김연우 님께서 후기를 게시하였습니다.</div>
          </div>
          
        </div>
        <div class="alarm__right">
          <div class="exhibition__alarm__date">7일 전</div>
        </div>
      </div>

    </div>
    <div class="exhibition__alarms" v-if="this.choosed === 'exhibition'">
      <div class="exhibition__alarm">
        <div class="alarm__left">
          <img src="../../assets/test_alarm/ex1.png"  class="exhibition__alarm__img" alt="">
        </div>
        <div class="alarm__middle">
          <div class="alarm__body">
            <div class="exhibition__alarm__title">OOO 전시회</div>
            <div class="alarm__info">
              <div class="exhibition__alarm__content">당신의 **** 취향에 맞는 전시회 놓치지마세요!!</div>
              <div class="exhibition__alarm__term">2021.04.01~04.23</div>
            </div>
            
          </div>
        </div>
        <div class="alarm__right">
          <div class="exhibition__alarm__date">6시간 전</div>
        </div>
        
      </div>
       <div class="exhibition__alarm read">
        <div class="alarm__left">
          <img src="../../assets/test_alarm/ex2.png"  class="exhibition__alarm__img" alt="">
        </div>
        <div class="alarm__middle">
          <div class="alarm__body">
            <div class="exhibition__alarm__title">Enjoy Every</div>
            <div class="alarm__info">
              <div class="exhibition__alarm__content">언택트 시대에도 즐기는 문화생활</div>
              <div class="exhibition__alarm__term">2021.03.03~03.10</div>
            </div>
          </div>
        </div>
        <div class="alarm__right">
          <div class="exhibition__alarm__date">3일 전</div>
        </div>
      </div>
    </div>

  </div>
  
</template>

<script>
export default {
  name: "alarm",
  data() {
    return {
      choosed: 'exhibition',
      exhibitions: [],
      feeds: [],
    }
  },
  created() {
    // vuex로 유저정보 가져와서 걔의 알림 백에서 가져온다음 data에 담아준다.
  },
  methods: {
    showExhibition() {
      const tab__left = document.querySelector(".left__tab");
      const tab__right = document.querySelector(".right__tab");
      
      if(tab__left.classList.contains("choosed")) {
        return;
      } else {
        tab__left.classList.add("choosed");
        tab__right.classList.remove("choosed");
        this.choosed = "exhibition";
      } 
    },
    showFeed() {
      const tab__left = document.querySelector(".left__tab");
      const tab__right = document.querySelector(".right__tab");
      if(tab__right.classList.contains("choosed")) {
        return;
      } else {
        tab__right.classList.add("choosed");
        tab__left.classList.remove("choosed");
        this.choosed = "feed";
      } 
    },
    goBack() {
      this.$router.go(-1);
    }
  }
}
</script>

<style scoped>
@import "../../components/css/style.css";

* {
    font-family: 'NanumBarunGothic', sans-serif;
}
* {
  box-sizing: border-box;
}
.alarmPage {
  max-width:380px;
}
.line {
  width:100%;
  height:1px;
  background-color:#CCCCCC;
  margin-bottom:30px;
}

.back__button {
  position:fixed;
  top:30px;
  left:20px;
  font-weight:700;
}
.title {
  font-size:20px;
  font-weight:700;
  margin-top:25px;
  text-align:center;
}
.left__tab,
.tab__divider {
  margin-right:5px;
}
.tabs {
  display:flex;
  font-size:16px;
  color:#CCCCCC;
  margin-bottom:10px;
  font-weight:700;
  margin-top:20px;
  /* opacity: 0.3; */
}
.choosed {
  color:#A593DF;
  
  /* opacity: 1.0; */
}
.feed__alarms,
.tabs,
.exhibition__alarms {
  margin-left:20px;
  margin-right:20px;
}
.follow__alarm,
.like__alarm,
.post__alarm,
.exhibition__alarm {
  background-color:#FFFFFF;
  border: 1px solid #D3D3D3;
  border-radius:10px;
  box-shadow: 0 3px 6px #00000029;
  margin-bottom:20px;
  color: #666666;
  display:flex;
}
.alarm__left {
  flex-basis:25%;
}
.alarm__middle {
  flex-basis:55%;
}
.alarm__right {
  flex-basis:20%;
  display:flex;
  flex-direction: column;
  justify-content: flex-end;
}
.exhibition__alarm__img {
  margin: 13px;
  width:87px;
  height:123px;
}
.follow__alarm__img {
  margin:15px;
  width: 60px;
  height: 60px;
  border-radius:100%;
}
.like__alarm__img {
  width:80px;
  height: 80px;
  margin: 5px;
  border-radius: 10px;
}
.follow__alarm__body {
  margin:15px 0px 15px 0px;
}
.like__alarm__middle{
  flex-basis:55%;
  display: flex;
  align-items: center;
}
.alarm__body {
  margin:15px 0px 15px 15px;
}
.alarm__info {
  width:137px;
  height:73px;
  display:flex;
  flex-direction: column;
  justify-content: space-between;
}
.exhibition__alarm__content,
.exhibition__alarm__term {
  font-size:14px;
}
.follow__alarm__content {
  font-size: 14px;
  margin-top:20px;
}
.like__alarm__content {
  font-size:14px;
  margin-left:12px;
}
.exhibition__alarm__date {
  /* position:absolute; */
  /* bottom:12px; */
  /* right:12px;  */
  font-size:12px;
  text-align:right;
  margin-right:12px;
  margin-bottom:12px;
}
.read {
  background-color: #A289F6;
  opacity: 0.41;
  color: #FFFFFF;
}
.exhibition__alarm__title {
  font-size:20px;
  margin-bottom:12px;
}
.follow__yes__button {
  width: 69px;
  height: 20px;
  background: linear-gradient(270deg,#A593DF,#9279E9,#7D5BF4,#6D44FD);
  border-radius:10px;
  color: white;
  font-size:12px;
  box-shadow: 0 3px 6px #00000029;
  margin-right:8px;
  margin-bottom:18px;
}
.follow__no__button {
  width: 69px;
  height: 20px;
  background-color: #FFFFFF;
  border-radius:10px;
  color: #666666;
  font-size:12px;
  box-shadow: 0 3px 6px #00000029;
  margin-bottom:18px;
}
</style>