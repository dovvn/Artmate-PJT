<template>
  <div class="alarmPage">

    <b-modal id="pos-added-modal" modal-class="pos-added-modal" hide-header hide-footer centered size="sm">
      <div class="pos-added-modal-body">
        <div class="pos-added-title">
          팔로우 신청을 수락했습니다.
        </div>
        <button class="pos-added-check-button" @click="$bvModal.hide('pos-added-modal')">확인</button>
      </div>
    </b-modal>

    <b-modal id="pos-check-modal" modal-class="pos-check-modal" hide-header hide-footer centered size="sm">
      <div class="pos-check-modal-body">
        <div class="pos-check-title">
          팔로우를 거절하시겠습니까?
        </div>
        <button class="pos-check-yes-button" @click="rejectFollow()">예</button>
        <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal')">아니오</button>
      </div>
    </b-modal>

    <div class="sticky-top">
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
    </div>
    
    
    
    <div class="feed__alarms" v-if="this.choosed === 'feed'">
      <div class="feed__alarm" v-for="(alarm,idx) in feeds" :key="idx">
        <button class="alarm__delete__button" @click="removeAlarm(alarm.id)">x</button>
        <div class="follow__alarm" v-if="alarm.subType === 0 && alarm.read === 0">
          
          <div class="alarm__left">
            <img v-if="alarm.img=='' || alarm.img==null" src="../../assets/person.jpg" class="follow__alarm__img" alt="">
            <img v-else :src="alarm.img"  class="follow__alarm__img" alt="">
          </div>
          <div class="alarm__middle">
            <div class="follow__alarm__body">
              <div class="follow__alarm__content">{{alarm.sendUserName}}님이 당신의 피드를 <br/> 팔로우했습니다.</div>
            </div>
            <div class="follow__alarm__buttons">
              <button class="follow__yes__button" @click="acceptFollow(alarm.sendUserId,alarm.id)">수락</button>
              <button class="follow__no__button" @click="showRejectModal(alarm.id,alarm.sendUserId)">거절</button>  
            </div>
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Feed")}}</div>
          </div>
        </div>

        <div class="follow__alarm read" v-if="alarm.subType === 0 && alarm.read === 1">
          <button class="alarm__delete__button__read" @click="removeAlarm(alarm.id)">x</button>
          <div class="alarm__left">
            <img v-if="alarm.img=='' || alarm.img==null" src="../../assets/person.jpg" class="follow__alarm__img" alt="">
            <img v-else :src="alarm.img"  class="follow__alarm__img" alt="">
          </div>
          <div class="alarm__middle">
            <div class="follow__alarm__body">
              <div class="follow__alarm__content">{{alarm.sendUserName}}님이 당신의 피드를 <br/> 팔로우했습니다.</div>
            </div>
            <div class="follow__alarm__buttons">
              <button class="follow__yes__button" @click="acceptFollow(alarm.sendUserId,alarm.id)">수락</button>
              <button class="follow__no__button" @click="showRejectModal(alarm.id,alarm.sendUserId)">거절</button>  
            </div>
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Feed")}}</div>
          </div>
        </div>

        <div class="like__alarm" v-if="alarm.subType === 2 && alarm.read === 0">
          <button class="alarm__delete__button" @click="removeAlarm(alarm.id)">x</button>
          <div class="alarm__left" @click="goFeedList(alarm.sendUserId, alarm.id)">
            <img v-if="alarm.img=='' || alarm.img==null" src="../../assets/person.jpg" class="like__alarm__img" alt="">
            <img v-else :src="alarm.img"  class="like__alarm__img" alt="">
          </div>
          <div class="like__alarm__middle">
            <div class="like__alarm__body">
              <div class="like__alarm__content">{{alarm.sendUserName}} 님이 당신의<br/> 게시글을 좋아합니다.</div>
            </div>
            
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Feed")}}</div>
          </div>
        </div>

        <div class="like__alarm read" v-if="alarm.subType === 2 && alarm.read === 1">
          <button class="alarm__delete__button__read" @click="removeAlarm(alarm.id)">x</button>
          <div class="alarm__left" @click="goFeedList(alarm.sendUserId, alarm.id)">
            <img v-if="alarm.img=='' || alarm.img==null" src="../../assets/person.jpg" class="like__alarm__img" alt="">
            <img v-else :src="alarm.img"  class="like__alarm__img" alt="">
          </div>
          <div class="like__alarm__middle">
            <div class="like__alarm__body">
              <div class="like__alarm__content">{{alarm.sendUserName}} 님이 당신의<br/> 게시글을 좋아합니다.</div>
            </div>
            
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Feed")}}</div>
          </div>
        </div>

        <div class="post__alarm" v-if="alarm.subType === 1 && alarm.read === 0">
          <button class="alarm__delete__button" @click="removeAlarm(alarm.id)">x</button>
          <div class="alarm__left" @click="goFeed(alarm.messageId, alarm.id)">
            <img v-if="alarm.img=='' || alarm.img==null" src="../../assets/person.jpg" class="like__alarm__img" alt="">
            <img v-else :src="alarm.img"  class="like__alarm__img" alt="">
          </div>
          <div class="like__alarm__middle">
            <div class="like__alarm__body">
              <div class="like__alarm__content">팔로워 {{alarm.sendUserName}} 님께서 후기를 게시하였습니다.</div>
            </div>
            
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Feed")}}</div>
          </div>
        </div>

        <div class="post__alarm read" v-if="alarm.subType === 1 && alarm.read === 1">
          <button class="alarm__delete__button__read" @click="removeAlarm(alarm.id)">x</button>
          <div class="alarm__left" @click="goFeed(alarm.messageId, alarm.id)">
            <img v-if="alarm.img=='' || alarm.img==null" src="../../assets/person.jpg" class="like__alarm__img" alt="">
            <img v-else :src="alarm.img"  class="like__alarm__img" alt="">
          </div>
          <div class="like__alarm__middle">
            <div class="like__alarm__body">
              <div class="like__alarm__content">팔로워 {{alarm.sendUserName}} 님께서 후기를 게시하였습니다.</div>
            </div>
            
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Feed")}}</div>
          </div>
        </div>

      </div>
    </div>
    
    <div class="exhibition__alarms" v-if="this.choosed === 'exhibition'">
      <div class="exhibition__alarm__container" v-for="(alarm,idx) in ex10" :key="idx">
        <div class="exhibition__alarm" v-if="!alarm.read">
          <button class="alarm__delete__button" @click="removeAlarm(alarm.id)">x</button>
          <div class="alarm__left">
            <img :src="alarm.img"  class="exhibition__alarm__img"  @click="goExhibit(alarm.messageId, alarm.id)">
          </div>
          <div class="alarm__middle">
            <div class="alarm__body">
              <div class="exhibition__alarm__title">{{alarm.exName}}</div>
              <div class="alarm__info">
                <!-- <div class="exhibition__alarm__content">{{alarm.description}}</div> -->
                <div class="exhibition__alarm__term" v-if="alarm.startDate">{{simpleDate(alarm.startDate)}}~<br>{{simpleDate(alarm.endDate)}}</div>
              </div>
              
            </div>
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Exhibit")}}</div>
          </div>
          
        </div>
        <div class="exhibition__alarm read" v-if="alarm.read">
          <button class="alarm__delete__button" @click="removeAlarm(alarm.id)">x</button>
          <div class="alarm__left">
            <img :src="alarm.img"  class="exhibition__alarm__img"  @click="goExhibit(alarm.messageId, alarm.id)">
          </div>
          <div class="alarm__middle">
            <div class="alarm__body">
              <div class="exhibition__alarm__title">{{alarm.exName}}</div>
              <div class="alarm__info">
                <!-- <div class="exhibition__alarm__content">{{alarm.description}}</div> -->
                <div class="exhibition__alarm__term" v-if="alarm.startDate">{{simpleDate(alarm.startDate)}}~<br>{{simpleDate(alarm.endDate)}}</div>
              </div>
            </div>
          </div>
          <div class="alarm__right">
            <div class="exhibition__alarm__date">{{timeForToday(alarm.sigDate,"Exhibit")}}</div>
          </div>
        </div>
      </div>
    </div>

  </div>
  
</template>

<script>
import {mapState} from "vuex";
import {deleteAlarm} from "@/api/alarm.js";
import http from "@/util/http-common";

export default {
  name: "alarm",
  data() {
    return {
      choosed: 'exhibition',
      exhibitions: [],
      feeds: [],
      deleteId:-1,
      sendFollowUserId: '',
    }
  },
  computed: {
    ...mapState(["user"]),
    ex10() {
      return this.exhibitions.slice(0,10);
    }
  },
  created() {
    // vuex로 유저정보 가져와서 걔의 알림 백에서 가져온다음 data에 담아준다.
    this.updateAlarms();
  },
  updated() {
    const alarms = document.querySelectorAll(".feed__alarm");
    console.log(alarms);
    
  },
  methods: {
    simpleDate(startDate){
      const items = startDate.split('-');
      const tp = items.join('.');
      // console.log(tp);
      // console.log(items);
      return tp.slice(0,10);
    },
    allowDrop(event) {
      event.preventDefault();
    },
    removeAlarm(id) {
      deleteAlarm(id, (response) => {
        console.log(response);
        this.updateAlarms();
      }, (error) => {
        console.error(error);
      })
    },
    goExhibit(messageId,id) {
      //읽음처리후 걔 피드목록으로 감
      http
      .put(`/api/signal/${id}`)
      .then((response) => {
        console.log(response);
        this.$router.replace({
        name: "ExhibitionDetail",
        params: {id: messageId,
        status: 'Alarm'}
      });
      })
      .catch((error)=>{
        console.error(error);
      })
      
    },
    goFeedList(userId,id) {
      //읽음처리후 걔 피드목록으로 감
      http
      .put(`/api/signal/${id}`)
      .then((response) => {
        console.log(response);
        this.$router.replace({
        name: "UserFeedList",
        params: {userId: userId}
      });
      })
      .catch((error)=>{
        console.error(error);
      })
      
    },
    goFeed(messageId,id) {
      //읽음처리후 걔 피드목록으로 감
      http
      .put(`/api/signal/${id}`)
      .then((response) => {
        console.log(response);
        this.$router.replace({
        name: "UserFeedDetail",
        params: {feedno: messageId,
        status: 'Alarm'}
      });
      })
      .catch((error)=>{
        console.error(error);
      })
      
    },
    showRejectModal(deleteId,sendUserId) {
      this.deleteId = deleteId; 
      this.sendFollowUserId = sendUserId;
      this.$bvModal.show('pos-check-modal');
    },
    acceptFollow(sendUserId,deleteId) {
      deleteAlarm(deleteId, (response) => {
        console.log(response);
        this.updateAlarms();
        //팔로우
        http
        .put(`/api/user/follow/accept/${sendUserId}/${this.user.userId}`)
        .then((response) => {
          console.log(response);
          //팔로우등록됐다 모달 띄우기
          this.$bvModal.show('pos-added-modal');
        })
        .catch((error) => {
          console.error(error);
        })
      }, (error) => {
        console.error(error);
      })
    },
    rejectFollow() {
      //알림 걍 지우고
      //전체 알람 업뎃
      deleteAlarm(this.deleteId, (response) => {
        console.log(response);
        this.updateAlarms();
        http
        .delete(`/api/user/follow/${this.sendFollowUserId}/${this.user.userId}`)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.error(error);
        })
        this.$bvModal.hide('pos-check-modal');
      }, (error) => {
        console.error(error);
      })
    },
    timeForToday(value,type) {
      const today=new Date();
      const timeValue = new Date(value);
      // console.log(today,timeValue);
      let betweenTime;
      if(type=="Exhibit") {
        betweenTime = Math.floor((today.getTime() - timeValue.getTime())/ 1000/ 60);
      } else if(type=="Feed") {
        betweenTime = Math.floor((today.getTime() - timeValue.getTime())/ 1000/ 60);
        betweenTime -= 540;
      }
      if(betweenTime < 1) return '방금전';
      if(betweenTime < 60) {
        return `${betweenTime}분전`;
      }
      const betweenTimeHour = Math.floor(betweenTime / 60);
      if(betweenTimeHour < 24) {
        return `${betweenTimeHour}시간전`;
      }

      const betweenTimeDay = Math.floor(betweenTime / 60/ 24);
      if(betweenTimeDay < 365) {
        return `${betweenTimeDay}일전`;
      }
      return `%{Math.floor(betweenTimeDay/ 365)}년전`;
    },
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
    },
    updateAlarms() {
      http
      .get(`/api/signal/feed/${this.user.userId}`)
      .then((response) => {
        this.feeds = response.data;
      })
      .then(() => {
        console.log(this.feeds);
      })
      .catch((error) => {
        console.error(error);
      })

      http
      .get(`/api/signal/exhibit/${this.user.userId}`)
      .then((response) => {
        this.exhibitions = response.data;
      })
      .then(() => {
        console.log(this.exhibitions);
      })
      .catch((error) => {
        console.error(error);
      })
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
  width:380px;
  margin:auto;
}
.sticky-top {
  position:sticky;
  top:0px;
  background-color: white;
}
.line {
  width:100%;
  height:1px;
  background-color:#CCCCCC;
  margin-bottom:30px;
}

.back__button {
  position:absolute;
  top:28px;
  margin-left:20px;
  font-weight:700;
}
.title {
  font-size:20px;
  font-weight:700;
  padding-top:25px;
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
.feed__alarm,
.exhibition__alarm {
  position:relative;
}
.feed__alarms,
.exhibition__alarms {
  padding-bottom:20px;
}
.follow__alarm,
.like__alarm,
.post__alarm,
.exhibition__alarm {
  background-color:#FFFFFF;
  border: 1px solid #f3f2f2;
  border-radius:7px;
  box-shadow: 0 3px 4px #00000005;
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
  object-fit:cover;
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
  /* height:73px; */
  display:flex;
  flex-direction: column;
  justify-content: space-between;
}
.exhibition__alarm__content,
.exhibition__alarm__term {
  font-size:12px;
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
  font-size:16px;
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
.alarm__delete__button {
  position:absolute;
  right:10px;
  font-size:15px;

}
.alarm__delete__button__read {
  position:absolute;
  right:10px;
  font-size:15px;
  color: #FFFFFF;
}


/* 반응형 */
@media screen and (min-width: 1024px) {
  .alarmPage {
    width: 760px;
  }
  .exhibition__alarm__img {
    width:174px;
    height:246px;
  }
  .follow__alarm__img {
    width:120px;
    height:120px;
  }
  .like__alarm__img {
    width:160px;
    height:160px;
  }
  .exhibition__alarm__title {
    font-size:30px;
  }
  .exhibition__alarm__content {
    font-size:21px;
  }
  .exhibition__alarm__term {
    font-size:21px;
  }
  .exhibition__alarm__date {
    font-size:18px;
  }
  .follow__alarm__content {
    font-size:21px;
  }
  .alarm__info {
    width: 100%;
  }
}

/* 반응형 */

/* 거절 모달 */
.pos-check-yes-button {
  color:white;
  /* background-color:#CB3E47; */ /* 삭제, 탈되 확인 색 */
  background-color:#9279e9;  /* 수정, 등록 확인 색 */
  border-radius:10px;
  font-size:14px;
  width:100px;
  height:30px;
}
.pos-check-no-button {
  color:#F3F3F3;
  background-color:#707070;
  border-radius:10px;
  font-size:14px;
  width:100px;
  height:30px;
  margin-left:15px;
}
::v-deep .pos-check-modal > .modal-dialog >.modal-content{
  background-color: #E8E8E8;
  border: 1px solid #707070;
  border-radius:15px;
  font-size:14px;
  width:310px;
  margin:auto;
}
.pos-check-title {
  height:60px;
  line-height:60px;
  font-weight:700;
}
.pos-check-modal-body {
  text-align:center;
  
}
/* 거절 모달 */

/* 수락 모달 */

::v-deep .pos-added-modal > .modal-dialog >.modal-content{
  background-color: #E8E8E8;
  border: 1px solid #707070;
  border-radius:16px;
  font-size:14px;
  width:310px;
  margin:auto;
  box-shadow: #00000096 20px 20px 40px;
}


.pos-added-modal-body {
  text-align:center;
}

.pos-added-title {
  height:60px;
  line-height:60px;
  font-weight:500;
  /* text-align:center; */
}

.pos-added-check-button {
  color: #6D44FD;
  margin-top: 15px;
}

/* 수락 모달 */
</style>