<template>
  <div class="myfeed">
    
    <!-- <b-modal hide-footer hide-header scrollable id="FollowInfo" modal-class="FollowInfo">
      <button 
      class="detail__hide__button" 
      @click="$bvModal.hide('FollowInfo')">
      x
      </button>
      <FollowInfo/>
    </b-modal> -->
    <Navi class="list__navi"/>
    <div class="writer__info">
      
      <div class="writer__info__feedname">
        {{myInfo.feedName}}
      </div>
      
      <!-- <img class="writer__info__img" :src="myInfo.userImg" alt=""> -->
      <img class="writer__info__img" v-if="imageUrl==null||imageUrl==''" src="../../assets/person.jpg"/>
      <img class="writer__info__img" v-else :src="imageUrl"/>
      <div class="writer__info__nickname">{{myInfo.userName}}</div>
      <div class="writer__info__intro">{{myInfo.introduction}}</div>
      
      <div class="writer__info__cntboxes">
        <div class="writer__info__cntbox">
          <div class="writer__info__cnt">{{feeds.length}}</div>
          <div class="writer__info__name">게시물</div>
        </div>
        <div class="writer__info__cntbox" @click="seeFollow('follower')">
          <div class="writer__info__cnt">{{myInfo.followerCnt}}</div>
          <div class="writer__info__name">팔로워</div>
        </div>
        <div class="writer__info__cntbox"  @click="seeFollow('following')">
          <div class="writer__info__cnt">{{myInfo.followingCnt}}</div>
          <div class="writer__info__name">팔로잉</div>
        </div>
        
      </div>
      <div class="writer__info__nickname">{{myInfo.userName}}</div>
      <div class="writer__info__intro">{{myInfo.introduction}}</div>
      <div class="writer__info__tags">
        <div class="writer__info__tag" v-for="(tag,idx) in myInfo.myTag" :key="idx">
          {{tag}}
        </div>
      </div>
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
    
    <button class="feed__writebotton">붓</button>
  </div>
</template>

<script>
import Navi from '@/components/Common/Navi.vue';
// import FollowInfo from './MyPageFollower_ing_List';
import {mapState} from "vuex";
import {listMyfeed} from '@/api/myfeed.js';

export default {
  components: {
    // FollowInfo,
    Navi,
  },
  data() {
    return {
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
      this.$router.replace({
        name: "MyFollow",
        params: {choosed: choosed}
      });
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
      console.log(this.feeds);
      const imgs = document.querySelectorAll(".feed__image__container");
      let pos = "left";
      let left_width = 0;
      let left_short = true;
      let row_height = 0;
      let nums = [150,125,100]
      for(let i = 0; i < imgs.length; i++){
        if(pos === "left") {
          row_height = nums[Math.floor(Math.random()*nums.length)];
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
    this.myInfo.feedCnt = this.user.feedCnt;
    this.myInfo.feedName = this.user.feedName;
    this.myInfo.userImg = this.user.userImg;
    this.myInfo.userName = this.user.userName;
    this.myInfo.introduction = this.user.introduction;
    this.myInfo.myTag = this.user.myTag;
    this.myInfo.followingCnt = this.user.followingCnt;
    this.myInfo.followerCnt = this.user.followerCnt;
    this.imageUrl = this.myInfo.userImg;
    console.log(this.user.userId);
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
        console.log(this.feeds);
        // console.log(imgs);
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
  margin-left:15pt;
  margin-right:15pt;
}
.writer__info {
  position: sticky;
  top:0pt;
  padding-top:55px;
  text-align:center;
  /* padding-top:20pt; */
  
  height:330px;
  background-color: #FFFFFF;
}
.writer__info__feedname {
  font-size:15pt;
  /* margin-top:20pt; */
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
  width:45pt;
  height:45pt;
  border-radius:100%;
}
.writer__info__cntboxes {
  display:flex;
  /* justify-content:space-around; */
  align-items: center;
  border-radius:30px;
  border: 1px solid #F5F5F5;
  margin-top:20px;
  margin-bottom:25px;
  box-shadow: 0 1px 2px #00000029;
}
.writer__info__cntbox {
  margin:10pt;
}
.writer__info__cnt {
  font-weight:900;
  font-size:13pt;
}
.writer__info__name {
  font-size:10pt;
  font-weight:600;
}
.writer__info__nickname,
.writer__info__intro {
  text-align:left;
  
}
.writer__info__intro {
  font-weight:500;
  font-size:12px;
  /* margin-top:1px; */
}
.writer__info__nickname {
  font-weight:800;
  font-size:13pt;
  margin-top:8pt;
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
  justify-content: center;
  padding-left:0;
  padding-right:0;
}
.feed__img__container {
  width: 50%;
}
.feed__img {
  width: 90%;
  border-radius:10px;
  margin:3%;
}
.feed__writebotton {
    position:fixed;
    bottom:40pt;
    right:25pt;
    background-color:#B8A4FD;
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

</style>