<template>
  <div class="myfeed">
    
    
    <Navi class="list__navi" @seeFeedList = "seeFeedList"/>
    <div class="writer__info">
      
      <div class="writer__info__header">
        <div class="writer__info__feedname">
          {{userInfo.feedName}}
        </div>
        <button @click="Follow" v-if="following==-1 && this.user.userId != this.userInfo.userId" class="writer__follow__button">
          팔로우
        </button>
        <button v-if="following==0 && this.user.userId != this.userInfo.userId" class="writer__followed__button">
          요청됨
        </button>
        <button @click="unFollow" v-if="following==1 && this.user.userId != this.userInfo.userId" class="writer__followed__button">
          팔로잉
        </button>
      </div>
      <!-- 반응형 -->
      <div class="writer__info__responsive">
      <!-- <img class="writer__info__img" :src="userInfo.userImg" alt=""> -->
        <div class="writer__info__left">
          <img class="writer__info__img" v-if="imageUrl==null||imageUrl==''" src="../../assets/person.jpg"/>
          <img class="writer__info__img" v-else :src="imageUrl"/>
        </div>
        <div class="writer__info__right">
          <div class="writer__info__nickname">{{userInfo.userName}}</div>
          <div class="writer__info__intro">{{userInfo.introduction}}</div>
          <div class="writer__3d__buttons">
            <button class="edit3D__button" @click="goEdit()" v-if="user.userId == userInfo.userId">3D 전시회 편집</button>
            <button class="view3D__button" @click="goView()">3D 전시회 보기</button>
          </div>
          <div class="writer__info__cntboxes">
            <div class="writer__info__cntbox">
              <div class="writer__info__cnt">{{feeds.length}}</div>
              <div class="writer__info__name">게시물</div>
            </div>
            <div class="writer__info__cntbox" @click="seeFollow('Follower')">
              <div class="writer__info__cnt">{{userInfo.followerCnt}}</div>
              <div class="writer__info__name">팔로워</div>
            </div>
            <div class="writer__info__cntbox"  @click="seeFollow('Following')">
              <div class="writer__info__cnt">{{userInfo.followingCnt}}</div>
              <div class="writer__info__name">팔로잉</div>
            </div>
          </div>
        </div>
      </div>
      <button class="feed__writebutton" @click="goWrite" v-if="this.user.userId == this.userInfo.userId">
        <font-awesome-icon icon="pen-fancy"/>
      </button>
      <!-- 반응형 -->  
     
      <!-- <div class="writer__info__tags">
        <div class="writer__info__tag" v-for="(tag,idx) in userInfo.myTag" :key="idx">
          {{tag}}
        </div>
      </div> -->
    </div>
    <ul class="feeds">
      <li class="feed__image__container" v-for="(feed,idx) in feeds" :key="idx">
        <img 
        @click="seeDetail(feed.id)" 
        class= "feed__image" 
        :src="feed.feedImg" 
        alt=""
        >
      </li>
    </ul>
    
    <b-modal hide-footer hide-header scrollable id="FollowInfo" modal-class="FollowInfo">
      <button 
      class="detail__hide__button" 
      @click="$bvModal.hide('FollowInfo')">
      x
      </button>
      <FollowInfo :clicked="clicked" :userId="userInfo.userId" @seeFeedList = "seeFeedList"/>
    </b-modal>
    <b-modal id="pos-added-modal" modal-class="pos-added-modal" hide-header hide-footer centered size="sm">
      <div class="pos-added-modal-body">
        <div class="pos-added-title">
          피드 등록이 완료되었습니다.
        </div>
        <button class="pos-added-check-button" @click="$bvModal.hide('pos-added-modal')">확인</button>
      </div>
    </b-modal>
    <b-modal id="pos-modified-modal" modal-class="pos-modified-modal" hide-header hide-footer centered size="sm">
      <div class="pos-modified-modal-body">
        <div class="pos-modified-title">
          피드 수정이 완료되었습니다.
        </div>
        <div class="modal-line"></div>
        <button class="pos-modified-check-button" @click="$bvModal.hide('pos-modified-modal')">확인</button>
      </div>
    </b-modal>
  </div>
</template>

<script>
import Navi from '@/components/Common/Navi.vue';
import FollowInfo from './UserFollow.vue';
import {mapState} from "vuex";
import {listMyfeed} from '@/api/myfeed.js';
import http from "@/util/http-common";

export default {
  components: {
    FollowInfo,
    Navi,
  },
  data() {
    return {
      clicked: '',
      feeds: [],
      userInfo: {
        feedCnt: 0,
        feedName: "",
        userImg: "",
        userName: "",
        introduction: "",
        myTag: [],
        followingCnt: 0,
        followerCnt: 0,
      },
      imageUrl:"",
      detailVisible: false,
      following: -2,
    };
  },
  computed: {
    ...mapState(["user","stompClient"])
  },
  methods: {
    goWrite(){
      this.$router.replace({
        name: "MyFeedAdd"
      });
    },
    goEdit() {
      this.$router.push({
        name: "3dCustomize"
      });
    },
    goView() {
      this.$router.replace({
        name: "3dWatch",
        params: {
          userId: this.$route.params.userId,
        }
      });
    },
    Follow() {
      this.following = 0;
      let params = {
        sendUserId: this.user.userId,
        getUserId:this.$route.params.userId, 
      }
      console.log(params);
      http
      .put(`/api/user/follow/${params.sendUserId}/${params.getUserId}`)
      .then((response) => {
        // console.log(response.data);
        if(response) {
          if (this.stompClient && this.stompClient.connected) {
              //소켓이 연결되어있을 때만 알림 전송
              console.log('팔로요청 보냄')
              this.stompClient.send(
                `/send/follow/${params.sendUserId}/${params.getUserId}`, //서버로 팔로우 알림을 보내야한다고 요청
                {}
              );
            }
        }
      })
      .catch((error) => {
        console.error(error);
      })
    },
    unFollow() {
      this.following = -1;
      this.userInfo.followerCnt -= 1;
       let params = {
        "sendUserId": this.user.userId,
        "getUserId":this.$route.params.userId, 
      }
      http
      .delete(`/api/user/follow/${params.sendUserId}/${params.getUserId}`)
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.error(error);
      })
    },
    seeFeedList(userId) {
      this.updateFeedList(userId);
      this.updateFollow(this.user.userId,userId)
      
      this.$bvModal.hide('FollowInfo');
       this.$router.replace({
        name: "UserFeedList",
        params: {userId: userId}
      });
    },
    seeFollow(choosed) {
      // console.log(choosed);
      this.clicked = choosed;
      // this.$router.replace({
      //   name: "MyFollow",
      //   params: {choosed: choosed}
      // });
      this.$bvModal.show('FollowInfo');
    },
    seeDetail(feedno){
      console.log(feedno);
      // detail페이지로 이동하면서 라우터 안에 피드id를 같이 보냄
      this.$router.replace({
        name: "UserFeedDetail",
        params: {feedno: feedno}
      });
    },
    imgLocate(){
      // console.log(this.feeds);
      const imgs = document.querySelectorAll(".feed__image__container");
      // 반응형
      if(window.innerWidth >= 1024) {
        // let width_cnt = 0;
        // let row_height = 0;
        // let row_cnt = 0;
        // let nums = [125,150,100];
        // let widths = [28,18,15,31];        
        for(let i = 0; i < imgs.length; i++) {
          // row_height=nums[row_cnt%3];
          imgs[i].style.width=`30%`

          // imgs[i].style.width=`${widths[width_cnt%4]}%`
          imgs[i].style.height = `125px`
          console.log(imgs[i].style.width,imgs[i].style.height);
          // width_cnt += 1;
          // if(width_cnt%4 == 0) {
          //   imgs[i].style.left="0";
          // }
          // else if(width_cnt%3 == 2) {
          //   row_cnt+=1;
          // }
        }
        return;
      }
      // 반응형
      let pos = "left";
      let left_width = 0;
      let left_short = true;
      let row_height = 0;
      let row_cnt = 0
      let nums = [125,150,100]
      for(let i = 0; i < imgs.length; i++){
        if(pos === "left") {
          row_height = nums[row_cnt%3];
          // console.log(row_height);
          row_cnt +=1;
          left_width = left_short? 38 : 58;
          imgs[i].style.width = `${left_width}%`
          imgs[i].style.height = `${row_height}px`
          imgs[i].style.left="0";
          pos = 'right';
        } else {
          let right_width = 96-left_width;
          imgs[i].style.width = `${right_width}%`
          imgs[i].style.height = `${row_height}px`
          // imgs[i].style.positionright="0";
          // console.log(imgs[i].style)
          pos='left'
          left_short= !left_short;
        }
        // console.log(imgs[i].style.width)
        // console.log(imgs[i].style.height)
      }

    },
    updateFeedList(userId) {
      http
      .get(`/api/user/${userId}`)
      .then((response) => {
        this.userInfo = response.data;
      })
      .then(() => {
        this.imageUrl = this.userInfo.userImg;
        console.log(this.userInfo.userId);
        listMyfeed(this.userInfo.userId,
          (response) => {
            this.feeds=response.data;
            // this.imgLocate();
          },
          (error) => {
            console.error(error);
          }
        );
      })
      .catch((error)=> {
        console.error(error);
      })
      },
      updateFollow(me,you) {
        http
        .get(`/api/user/follow/${me}/${you}`)
        .then((response) => {
          console.log(response.data);
          
          this.following = response.data;
          
        })
        .catch((error) => {
          console.error(error);
        })
      },
      updateUserInfo(userId) {
        http
        .get(`/api/user/${userId}`)
        .then((response) => {
          this.userInfo = response.data;
        }) 
        .catch((error) => {
          console.error(error);
        })
      }
  },
  created() {
    //axios요청으로 유저가 쓴 피드들 받아와서 feeds에 담아놓는다. 
    //유저 정보(피드 소개, 프로필 이미지, 닉네임,소개글, 관심 태그 )
    //userInfo에 담는다.
    // console.log(this.user);
    this.updateUserInfo(this.$route.params.userId);
    this.updateFeedList(this.$route.params.userId);
    //팔로우 정보 확인
    this.updateFollow(this.user.userId,this.$route.params.userId);
    
  },
  
  mounted() {
    if(this.$route.params.status == "added") {
      this.$bvModal.show('pos-added-modal')
    }
    if(this.$route.params.status == "modified") {
      this.$bvModal.show('pos-modified-modal')
    }
    // 반응형
    
    let prev_size = window.innerWidth;
    window.addEventListener('resize', () => {
      let now_size = window.innerWidth;
      if((now_size >= 1024 && prev_size < 1024) || (now_size<1024 && prev_size >= 1024)) {
        this.imgLocate();
      }
      prev_size=now_size;
    })
     // 반응형
  },
 
  updated() {
    // const imgs = document.querySelectorAll(".feed__image__container");
        // console.log(this.feeds);
        // console.log(imgs);
    // console.log('왜안대ㅐㅐ')
    this.imgLocate();
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
.detail__navi {
  position:sticky;
}
.myfeed{ -ms-overflow-style: none; } 
.myfeed::-webkit-scrollbar{ display:none; }

.feed__detail > .modal-body {
  padding:0;
}

.myfeed {
  width:380px;
  /* max-width:380px; */
  margin:auto;
}
.FollowInfo > div {
  position:absolute !important;
  bottom:0%;
}

.modal-content {
  border-radius:24px;
  border: none;
}
.detail__hide__button {
  position:absolute;
  top:10px;
  right:20px;
  font-size:22px;
  color:#666666;
}
.feeds {
  margin-left:20px;
  margin-right:20px;
}
.writer__info {
  position: sticky;
  top:0pt;
  padding-top:55px;
  padding-bottom: 20px;
  text-align:center;
  /* padding-top:20pt; */
  
  /* height:330px; */
  background-color: #FFFFFF;
}
.writer__info__header {
  display:flex;
  justify-content: space-between;
  align-items: flex-start;
  margin-left:20px;
  margin-right:20px;
  margin-top:15px;
}
.writer__follow__button {
  font-size: 12px;
  color:white;
  background: linear-gradient(270deg,#A593DF,#9279E9,#7D5BF4,#6D44FD);
  box-shadow: #00000029 0px 3px 6px;
  width: 69px;
  height:20px;
  margin-top:4px;
  margin-right:2px;
  border-radius:15px;
}
.writer__followed__button {
  font-size: 12px;
  color:white;
  background-color: #D3D3D3;
  /* box-shadow: #00000029 0px 3px 6px; */
  width: 69px;
  height:20px;
  margin-top:4px;
  margin-right:2px;
  border-radius:15px;
}
.writer__info__feedname {
  font-size:20px;
  /* margin-top:20pt; */
  /* margin-left:20px; */
  text-align:left;
  font-weight: 700;
  /* margin-top:10px; */
}
.writer__info__middle {
  display:flex;
  justify-content:space-between ;
  align-items: center;
  margin-top:14px;
}
.writer__info__img {
  width:95px;
  height:95px;
  border-radius:100%;
}
.writer__info__cntboxes {
  display:flex;
  justify-content:space-around;
  width:100%;
  align-items: center;
  border-radius:30px;
  border: 1px solid #F5F5F5;
  margin-top:20px;
  /* margin-bottom:25px; */
  box-shadow: 0 1px 2px #d4d4d425;
}
.writer__info__cntbox {
  /* margin:10pt; */
  margin-top:10px;
  /* margin-bottom:10px; */
}
.writer__info__cntbox:hover,
.feed__image__container:hover {
  cursor:pointer;
}
.writer__info__cnt {
  font-weight:900;
  font-size:16px;
}
.writer__info__name {
  font-size:14px;
  font-weight:600;
}
.writer__info__nickname,
.writer__info__intro {
  text-align:center;
  
}
.writer__info__intro {
  font-weight:500;
  font-size:12px;
  width:200px;
  margin:auto;
  /* margin-top:1px; */
}
.writer__info__nickname {
  font-weight:800;
  font-size:16px;
  margin-top:8px;
}
.writer__info__tags {
  margin-top:7pt;
  padding-bottom:12pt;
  display:flex;
  background-color:#FFFFFF;
  
}
.writer__info__tag {
  color:#FFFFFF;
  background-color: #A593DF;
  font-size:8pt;
  padding: 4pt 8pt 4pt 8pt;
  margin-right: 5pt;
  font-weight:300;
}
.feeds {
  display:flex;
  flex-wrap: wrap;
  list-style:none;
  justify-content: space-between;
  padding-left:0;
  padding-right:0;
}
.feed__image__container {
  /* display:flex; */
  /* justify-content: center; */
  /* align-items: center; */
  width: 50%;
  height: 200px;
  margin-bottom:13px;
  /* background-color:blue; */
}
.feed__image {
  /* position:relative; */
  display:flex;
  width: 100%;
  height:100%;
  border-radius:5px;
  object-fit:cover;
  /* padding:2px; */
}
.feed__writebutton {
    position:fixed;
    bottom:40pt;
    right:25pt;
    background-image:linear-gradient(270deg,#B8A4FD,#9275F2,#7953FF);
    background-color:#9275F2;
    border-radius:100%;
    height:64px;
    width:64px;
    color:#FFFFFF;
    font-size:28px;
    text-align:center;
    vertical-align: middle;
}

.feed__writebutton > font-awesome-icon {
  line-height:64px;
}

::v-deep .FollowInfo > .modal-dialog {
  position:absolute;
  bottom:0;
  width:100%;
  margin: 0;
  /* margin:auto; */
}

::v-deep .FollowInfo > .modal-dialog >.modal-content {
  /* margin-top:200px; */
  bottom:0px;
  border-radius: 10px 10px 0px 0px;
  /* min-height:490px; */
}
::v-deep .FollowInfo > .modal-dialog > .modal-content> .modal-body {
  min-height:498px;
}

.modal-line {
  width: 100%;
  margin:auto;
  height: 1px;
  border: 1px solid #707070;
  opacity:0.2;
}

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

::v-deep .pos-modified-modal > .modal-dialog >.modal-content{
  background-color: #E8E8E8;
  border: 1px solid #707070;
  border-radius:16px;
  font-size:14px;
  width:310px;
  margin:auto;
  box-shadow: #00000096 20px 20px 40px;
}

.pos-modified-modal-body {
  text-align:center;
}

.pos-modified-title {
  height:60px;
  line-height:60px;
  font-weight:500;
  /* text-align:center; */
}

.pos-modified-check-button {
  color: #6D44FD;
  margin-top: 15px;
}

/* 3d buttons */
.writer__3d__buttons {
  display:flex;
  /* justify-content: space-around; */
  margin-top: 5px;
  margin-left: 100px;
}

.view3D__button,
.edit3D__button {
  display: inline-block;
  border-radius:15px;
  border: 1px solid #9275F2;
  /* background: linear-gradient(270deg,#B8A4FD,#9275F2,#7953FF); */
  color:#9275F2;
  font-size:13px;
  padding:5px;
  margin-right: 5px;
  line-height: 14px;
  
  
}

/*  */
/* 반응형 */
@media screen and (min-width: 1024px) {
  .myfeed {
    width: 760px;
  }
  .writer__info__img {
    width:160px;
    height:160px;
  }
  .writer__info__responsive {
    display:flex;
    justify-content: space-around;
    margin-top:20px;
  }
  .writer__info__right {
    width:400px;
    
  }
  .writer__info__intro {
    width: 100%;
  }
  .writer__info__intro,
  .writer__info__nickname {
    text-align:left;
  }
  .writer__info__nickname {
    margin-top:20px;
  }
  .feeds {
    justify-content:initial;
  }
  ::v-deep .FollowInfo > .modal-dialog >.modal-content {
    border-radius: 10px 10px 0px 0px;
  }
  ::v-deep .FollowInfo > .modal-dialog {
    margin-top:200px;
    position:static;
  }
  /* 변경 */
  .feed__image__container {
    margin:1.5%;
  }
  /* 변경 */

  /* 글씨크기변경 */
  .writer__info__feedname {
    font-size:30px;
  }
  .writer__followed__button {
    font-size:18px;
    width:105px;
    height:30px;
    margin-top:8px;
    margin-right:4px;
  }
  .writer__info__nickname {
    font-size:24px;
  }
  .writer__info__intro {
    font-size:18px;
  }
  /* 글씨크기변경 */
}
/* 반응형 */
</style>