<template>
  <div class="myfeed">
    
    
    <Navi class="list__navi"/>
    <div class="writer__info">
      
      <div class="writer__info__feedname">
        {{myInfo.feedName}}
      </div>
      
      <!-- <img class="writer__info__img" :src="myInfo.userImg" alt=""> -->
      <img class="writer__info__img" v-if="myInfo.userImg==null||myInfo.userImg==''" src="../../assets/person.jpg"/>
      <img class="writer__info__img" v-else :src="myInfo.userImg"/>
      <div class="writer__info__nickname">{{myInfo.userName}}</div>
      <div class="writer__info__intro">{{myInfo.introduction}}</div>
      
      <div class="writer__info__cntboxes">
        <div class="writer__info__cntbox">
          <div class="writer__info__cnt">{{feeds.length}}</div>
          <div class="writer__info__name">게시물</div>
        </div>
        <div class="writer__info__cntbox" @click="seeFollow('Follower')">
          <div class="writer__info__cnt">{{myInfo.followerCnt}}</div>
          <div class="writer__info__name">팔로워</div>
        </div>
        <div class="writer__info__cntbox"  @click="seeFollow('Following')">
          <div class="writer__info__cnt">{{myInfo.followingCnt}}</div>
          <div class="writer__info__name">팔로잉</div>
        </div>
      </div>
        
     
      <!-- <div class="writer__info__tags">
        <div class="writer__info__tag" v-for="(tag,idx) in myInfo.myTag" :key="idx">
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
    <button class="feed__writebutton" @click="goWrite">
      <font-awesome-icon icon="pen-fancy"/>
    </button>
    <b-modal hide-footer hide-header scrollable id="FollowInfo" modal-class="FollowInfo">
      <button 
      class="detail__hide__button" 
      @click="$bvModal.hide('FollowInfo')">
      x
      </button>
      <FollowInfo :clicked="clicked"/>
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
import FollowInfo from './MyPageFollower_ing_List';
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
      myInfo: {
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
      detailVisible: false
    };
  },
  computed: {
    ...mapState(["user"])
  },
  methods: {
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
      // console.log(feedno);
      // detail페이지로 이동하면서 라우터 안에 피드id를 같이 보냄
      this.$router.replace({
        name: "MyFeedView",
        params: {feedno: feedno}
      });
    },
    goWrite(){
      this.$router.replace({
        name: "MyFeedAdd"
      });
    },
    imgLocate(){
      // console.log(this.feeds);
      const imgs = document.querySelectorAll(".feed__image__container");
      let pos = "left";
      let left_width = 0;
      let left_short = true;
      let row_height = 0;
      let row_cnt = 0;
      let nums = [125,150,100]
      for(let i = 0; i < imgs.length; i++){
        if(pos === "left") {
          row_height = nums[row_cnt%3];
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
  },
  created() {
    //axios요청으로 내가 쓴 피드들 받아와서 feeds에 담아놓는다. 
    //내 정보(피드 소개, 프로필 이미지, 닉네임,소개글, 관심 태그 )
    //myInfo에 담는다.
    // console.log(this.user);
   
    http
    .get(`/api/user/${this.user.userId}`)
    .then((response) => {
      this.myInfo = response.data;
    })
    .then(() => {
      console.log(this.myInfo);
    })
    .catch((error)=> {
      console.error(error);
    })
    // this.myInfo.feedCnt = this.user.feedCnt;
    // this.myInfo.feedName = this.user.feedName;
    // this.myInfo.userImg = this.user.userImg;
    // this.myInfo.userName = this.user.userName;
    // this.myInfo.introduction = this.user.introduction;
    // this.myInfo.myTag = this.user.myTag;
    // this.myInfo.followingCnt = this.user.followingCnt;
    // this.myInfo.followerCnt = this.user.followerCnt;
    this.imageUrl = this.myInfo.userImg;
    // console.log(this.user.userId);
    listMyfeed(this.user.userId,
      (response) => {
        this.feeds=response.data;
        // this.imgLocate();
      },
      (error) => {
        console.error(error);
      }
    );
    
  },
  updated() {
    // const imgs = document.querySelectorAll(".feed__image__container");
        // console.log(this.feeds);
        // console.log(imgs);
    this.imgLocate();
  },
  mounted() {
    console.log(this.$route.params.status);
    
    if(this.$route.params.status == "added") {
      this.$bvModal.show('pos-added-modal')
    }
    if(this.$route.params.status == "modified") {
      this.$bvModal.show('pos-modified-modal')
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
.detail__navi {
  position:sticky;
}
.myfeed{ -ms-overflow-style: none; } 
.myfeed::-webkit-scrollbar{ display:none; }

.feed__detail > .modal-body {
  padding:0;
}

.myfeed {
  width:100%;
  max-width:380px;
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
.writer__info__feedname {
  font-size:20px;
  /* margin-top:20pt; */
  margin-left:20px;
  text-align:left;
  font-weight: 700;
  margin-top:10px;
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
  box-shadow: 0 1px 2px #00000029;
}
.writer__info__cntbox {
  /* margin:10pt; */
  margin-top:10px;
  /* margin-bottom:10px; */
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
  border-radius:10px;
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

::v-deep .FollowInfo > .modal-dialog >.modal-content {
  margin-top:200px;
  bottom:0px;
  border-radius: 20px 20px 0px 0px;
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
</style>