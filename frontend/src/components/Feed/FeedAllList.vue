<template>
  <div id="newsfeed">
      <div class="feedLine">
        <div id="post"  v-for="nf in newsfeed" :key="nf.id" > 
          <div class="pro"> 
            <img class="profile_img" :src="nf.userImg" @click="goUserFeed(nf.userId)">
            <span id="nick">{{nf.userName}}</span>
          </div>

          <div id="picture">
            <img class="feed_img" :src="nf.feedImg" alt=""  @click="postDetail(nf.id)">
          </div>
          <div id="contents">
            <div class="icon">
              <div id="icons1">
                <font-awesome-icon v-if="nf.likemark == 0" @click="addHeart(nf.likemark,nf.id)"  :icon="['far', 'heart']" :style="{ color: 'red' }"/> 
                <font-awesome-icon v-if="nf.likemark == 1" @click="addHeart(nf.likemark,nf.id)"  :icon="['fas', 'heart']" :style="{ color: 'red' }"/> 
                {{nf.likeCnt}}
            </div>
    
            <div id="icons2">
              <font-awesome-icon
            :icon="['far', 'comment-alt']"
            size="sm"
          /> {{nf.commentCnt}}
            </div>
            <div id="mark">
              <font-awesome-icon v-if="nf.bookmark == 0" @click="addBookmark(nf.bookmark, nf.id)" :icon="['far', 'bookmark']" />
              <font-awesome-icon v-if="nf.bookmark == 1" @click="addBookmark(nf.bookmark, nf.id)" :icon="['fas', 'bookmark']" />
              <ShareLinkModal v-bind:nf="nf"/>
            </div>
            </div>
            <div class="con">
              <span id="nicks">{{nf.userName}}</span>
              <div class="feedtext">{{nf.feedText}}</div>
            </div> 
            <div class="date">
              {{timeForToday(nf.writeDate)}}
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import ShareLinkModal from "@/components/Common/ShareLinkModal.vue";
import {mapState} from "vuex";
export default {
  components:{
    ShareLinkModal,
  },
  data() {
    return {
      userInfo:{
        userId:"",
      },
      newsfeed:[],
    };
  },
  computed: {
	  ...mapState(["isLogin"]),
  },
  created() {
    if(!this.isLogin) {
      this.$router.push({name:'Login'})
    }
    this.userInfo =  this.$store.getters.getUser;
    // this.changeDate = this.timeForToday(this.newsfeed.writeDate);
    // console.log(this.userInfo.userId);
    http
      .get(`/api/feed/allList/${this.userInfo.userId}`)
      .then((res) => {
        // console.log(res);
        this.newsfeed = res.data;
      })
      .catch((err) => console.error(err));

  },
  methods:{
    goUserFeed(userId) {
      this.$router.replace({
        name: "UserFeedList",
        params: {userId: userId}
      });
    },
    addBookmark:function(bookmark, feedid){
      // conosole.log(this.newf)
      for (let i = 0; i<this.newsfeed.length; i++){
        if(this.newsfeed[i].id===feedid){
          if(!this.newsfeed[i].bookmark){
            this.newsfeed[i].bookmark=1;
          }
          else{
            this.newsfeed[i].bookmark=0;
          }
          break;
        }
      }
      if(bookmark == 0){ //북마크 안눌려있음 
        http
        .put(`api/bookmark/${this.userInfo.userId}/${feedid}`)
        .then((data) => {
          if (data) {
            // alert('북마크 목록에 추가되었습니다.');
          } else {
            alert('추가하는데 오류가 발생했습니다.');
          }
         })
        .catch((err) => console.error(err));
      } else if(bookmark == 1){ // 북마크 눌려있음
        http
        .delete(`api/bookmark/${this.userInfo.userId}/${feedid}`)
        .then((data) => {
        if (data) {
          // alert('북마크 목록에서 삭제되었습니다.');
        } else {
          alert('삭제하는데 오류가 발생했습니다.');
        }
      })
      .catch((err) => console.error(err));
      }
      
    },
    addHeart:function(like, feedid){
      // console.log(this.newsfeed.data);
      for (let i = 0; i<this.newsfeed.length; i++){
        if(this.newsfeed[i].id===feedid){
          if(!this.newsfeed[i].likemark){
            this.newsfeed[i].likemark=1;
            this.newsfeed[i].likeCnt++;
          }
          else{
            this.newsfeed[i].likemark=0;
            this.newsfeed[i].likeCnt--;
          }
          // console.log(this.newsfeed[i].likemark,this.newsfeed[i].likeCnt)
          break;
        }
      }
      if(like == 0){ // 좋아요 안눌린 상태 
        http
        .put(`api/likemark/${this.userInfo.userId}/${feedid}`)
        .then((data) => {
          // console.log(data); 
          if (data) {
            // alert('좋아요!❤');
          } else {
            alert('오류가 발생하였습니다.');
          }
        })
        .catch((err) => console.error(err));
      }else if(like == 1){ // 좋아요 눌린 상태 
        http
        .delete(`api/likemark/${this.userInfo.userId}/${feedid}`)
        .then((data) => {
          // console.log(data); 
          if (data) {
            // alert('좋아요 취소..😢');
          } else {
            alert('오류가 발생하였습니다.');
          }
        })
        .catch((err) => console.error(err));
      }
    },
    timeForToday(value){
      const today=new Date();
      const timeValue = new Date(value);
      // console.log(today,timeValue);
      let betweenTime = Math.floor((today.getTime() - timeValue.getTime())/ 1000/ 60);
      //배포에서는 
      betweenTime -= 540;
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
    postDetail:function(feedno){ 
      this.$router.replace({
        name: "UserFeedDetail",
        params: {feedno: feedno, status:"alllist"}
      });
    }
  }
  
}
</script>

<style>
.feedLine {
  width: 370px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
  padding-bottom: 80px;
}
#post{
  margin-bottom: 30px;
}
.pro{
  display: inline-block;
  float: left;
  padding-left: 10px;
  padding-bottom: 7px;
}
.profile_img{
  width: 35px;
  height: 35px;
  border-radius: 50%;
  margin-right: 10px;
}
#nick{
  font-size: 16px;
  font-weight: bold;
}
.picture{
  width: 100%;
  height: 370px;
  overflow: hidden;
}
.feed_img{
  width: 100%;
  height: 370px;
  object-fit:cover;
  margin-bottom: 7px;
}
.icon{
  width: 100%;
  height: 24px;
}
#icons1{
  padding-left: 5px;
  float: left;
}
#icons2{
  margin-left: 14px;
  float: left;
}
#mark{
  float: right;
  margin-right: 5px;
}
.share{
  margin-left: 5px;
  margin-right: 5px;
}
#nicks{
  font-size: 14px;
  font-weight: bold;
  margin-right: 5px;
  float: left;
}
.con{
  width: 250px;
}
.feedtext{
  font-size: 14px;
  text-align: left;
  width: 200px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap; 
  line-height: 1.25; 
  height: 17.5px; 
  word-wrap: normal; 
}
.feed_img:hover{
  cursor:pointer;
}
.profile_img:hover{
  cursor:pointer;
}
.date{
  clear: both;
   height: 18px; 
   font-size: 12px;
   text-align: left;
   margin-right: 300px;
   margin-left: 2px;
}

/* ------------------------------ 커질때반응형 ------------------------------ */
@media screen and (min-width: 1024px) {
  .feedLine {
    width: 760px;
  }
}
</style>