<template>
  <div id="newsfeed">
      <div class="feedLine">
        <div id="post"  v-for="nf in newsfeed" :key="nf.id" > 
          <div id="picture">
            <img class="feed_img" :src="nf.feedImg" alt=""  @click="postDetail(nf.id)">
          </div>
          <div id="contents">
            <div class="pro" > 
              <img class="profile_img" :src="nf.userImg" alt="">
              <p id="nick">{{nf.userName}}</p>
              <p id="date" >{{timeForToday(nf.writeDate)}}</p>
            </div>
            <div class="feedtext">
               {{nf.feedText}}
            </div>

            <div id="icons1">
              <font-awesome-icon v-if="nf.likemark == 0" @click="addHeart(nf.likemark,nf.id)"  :icon="['far', 'heart']" /> 
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
              <font-awesome-icon v-if="nf.bookmark == 0" @click="addBookmark(nf.bookmark, nf.id)" :icon="['far', 'bookmark']" size="sm" />
              <font-awesome-icon v-if="nf.bookmark == 1" @click="addBookmark(nf.bookmark, nf.id)" :icon="['fas', 'bookmark']" size="sm" />
            </div>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
export default {
  data() {
    return {
      userInfo:{
        userId:"",
      },
      newsfeed:[],
    };
  },
  created() {
    this.userInfo =  this.$store.getters.getUser;
    // this.changeDate = this.timeForToday(this.newsfeed.writeDate);
    console.log(this.userInfo.userId);
    http
      .get("/api/feed/allList")
      .then((res) => {
        console.log(res);
        this.newsfeed = res.data;
      })
      .catch((err) => console.log(err));

  },
  methods:{
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
        .catch((err) => console.log(err));
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
      .catch((err) => console.log(err));
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
          console.log(this.newsfeed[i].likemark,this.newsfeed[i].likeCnt)
          break;
        }
      }
      if(like == 0){ // 좋아요 안눌린 상태 
        http
        .put(`api/likemark/${this.userInfo.userId}/${feedid}`)
        .then((data) => {
          console.log(data); 
          if (data) {
            // alert('좋아요!❤');
          } else {
            alert('오류가 발생하였습니다.');
          }
        })
        .catch((err) => console.log(err));
      }else if(like == 1){ // 좋아요 눌린 상태 
        http
        .delete(`api/likemark/${this.userInfo.userId}/${feedid}`)
        .then((data) => {
          console.log(data); 
          if (data) {
            // alert('좋아요 취소..😢');
          } else {
            alert('오류가 발생하였습니다.');
          }
        })
        .catch((err) => console.log(err));
      }
    },
    timeForToday(value){
      const today=new Date();
      const timeValue = new Date(value);
      // console.log(today,timeValue);
      const betweenTime = Math.floor((today.getTime() - timeValue.getTime())/ 1000/ 60);
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
        params: {feedno: feedno, status:"feedlist"}
      });
    }
  }
  
}
</script>

<style>
.feedLine {
  width: 700px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
  padding-bottom: 80px;
  padding-top: 10px;
}
#post{
  width: 100%;
  height: 500px;
  box-shadow: 0px 0px 7px #00000029;
  border-radius: 25px;
  margin-top: 20px;
}
#post>#picture{
  height: 250px;
  width: 100%;
  border-top-left-radius: 25px;
  border-top-right-radius: 25px;
  overflow: hidden;
  width: 100%;
}
.feed_img{
  width: 100%;
  border-top-left-radius: 25px;
  border-top-right-radius: 25px;
  object-fit:fill;
}
#post>#contents{
  height: 250px;
  padding: 20px;
}
.profile_img{
  width: 78px;
  height: 78px;
  float: left;
  border-radius: 50%;
}
.pro{
  display: block;
  width: 650px;
  height: 80px;
  text-align: center;
  margin-bottom: 15px;
}
#nick{
  float: left;
  padding-left: 10px;
  padding-top:3.5%;
  font-size: 24px;
  font-weight: bold;
  margin: 0 auto;
}
#date{
  float: right;
  padding-top: 4%;
  font-size: 12px;
  color: #999999;
  font-size: 14px;
}
.feedtext{
  display: inline-block;
  width: 650px;
  padding: 5px;
  text-align: left;
  font-size: 16px;
  margin-bottom: 8px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: normal; 
  line-height: 1.25; 
  height: 5.8em; 
  text-align: left; 
  word-wrap: break-word; 
  display: -webkit-box; 
  -webkit-line-clamp: 3; 
  -webkit-box-orient: vertical;

}
#icons1{
  padding-left: 10px;
  float: left;
  font-size: 12px;
}
#icons2{
  margin-left: 20px;
  float: left;
  font-size: 12px;
}
#mark{
  float: right;
  margin-right: 5px;
}
/* ------------------------------ 반응형 ------------------------------ */
@media screen and (max-width: 1024px) {
  .feedLine {
    width: 340px;
  }
  #post{
    height: 300px;
  }
  #post>#picture{
   height: 150px;
  }
  #post>#contents{
    height: 150px;
    padding: 8px;
  }
  .profile_img{
    width: 50px;
    height: 50px;
  }
  .pro{
    width: 320px;
    height: 50px;
    margin-bottom: 7px;
  }
  #nick{
    padding-left: 6px;
    font-size: 18px;
  }
  #date{
    font-size: 12px;
  }
  .feedtext{
    width: 320px;
    padding: 2px;
    margin-left: 5px;
    margin-right: 5px;
    font-size: 12px;
    line-height: 1.25; 
    height: 4em; 
  }
}
</style>