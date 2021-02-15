<template>
    <!-- 모바일  -->
    <div class="feed" v-if="isMobile()">
      <Navi class="detail__navi"/>
      <b-modal id="pos-check-modal" modal-class="pos-check-modal" hide-header hide-footer centered size="sm">
        <div class="pos-check-modal-body">
          <div class="pos-check-title">
            댓글을 삭제하시겠습니까?
          </div>
          <button class="pos-check-yes-button" @click="deleteMemo(delete_memoId)">예</button>
          <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal')">아니오</button>
        </div>
      </b-modal>
      <button class="goBack__button" @click="goBack()">
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </button>
      <div class="sticky-top">
        <div class="white">
          <div class="feed__info">
            <div class="feed__left">
              <font-awesome-icon icon="map-marker-alt" class="feed__location__icon"/>
              <span class="feed__location__name"> {{feed.location}}</span>
            </div>
            <div class="feed__right">
              <font-awesome-icon :icon="['fab', 'envira']" class="feed__exhibition__icon"/>
              <span class="feed__exhibition__name"> 간직해온 마음들</span>
            </div>
          </div>
          <img class="feed__img" :src="feed.feedImg" >
          <div class="creator__info">
            <div class="creator__info__left">
              <img class="creator__info__img" v-if="feed.userImg==''||feed.userImg==null" @click="goFeedList(feed.userId)" src="../../assets/person.jpg"/>
              <img class="creator__info__img" v-else :src="feed.userImg" @click="goFeedList(feed.userId)">
              <div class="creator__info__box">
                <div class="creator__info__name">{{feed.userName}}</div>
                <div class="creator__info__date">{{feed.writeDate}}</div>
              </div>
            </div>
            <div class="creator__info__right">
              <font-awesome-icon v-if="feed.likemark==0" :icon="['far','heart']" @click="addHeart(feed.likemark,feed.id)" class="feed__like__button"/>
              <font-awesome-icon v-if="feed.likemark==1" :icon="['fas','heart']" @click="addHeart(feed.likemark,feed.id)" class="feed__like__button"/>
              <span class="feed__like__cnt">{{feed.likeCnt}}</span>
              <font-awesome-icon v-if="feed.bookmark == 0" class="feed__interactions__bookmark" @click="addBookmark(feed.bookmark,feed.id)" :icon="['far','bookmark']"/>
              <font-awesome-icon v-if="feed.bookmark == 1" class="feed__interactions__bookmark" @click="addBookmark(feed.bookmark,feed.id)" :icon="['fas','bookmark']"/>

              <font-awesome-icon class="feed__interactions__share" icon="share-alt" />
            </div>
          </div>
          <div class="feed__content">
            {{feed.feedText}}
          </div>
          
          <div class="dotLine"></div>
          <div class="feed__memo__cnt">Comment ({{memos.length}})</div>
        </div>
      </div>
      <!-- 댓글 창은 import해서 쓰자 -->
      <div class="feed__memo">
        <div class="feed__memo__list">
          <div class="feed__memo__items" v-for="(memo,idx) in memos" :key="idx">
            <div class="memo__left">
              <img v-if="memo.userImg==''||memo.userImg==null" class="memo__writer__img" src="../../assets/person.jpg">
              <img
                v-else 
                :src="memo.userImg" 
                class="memo__writer__img"
                @click="goUserFeed(memo.userId)">
              
              <div class="memo__content"  >
                <span class="memo__writer__name">{{memo.userName}}</span> <span class="memo__text" v-if="!memos_modify[idx]">{{memo.content}}</span>
              </div>
              <div class="memo__modifier" v-if=memos_modify[idx]>
                <input v-focus class="memo__modifier__input" type="text" :value="memo.content">
                <button @click="modifyMemo(memo)" class="memo__modifier__btn">수정</button>
              </div>  
            </div>
            
            <div class="memo__buttons" v-if="memo.userId === user.userId">
              <button v-if="!memos_modify[idx]" @click="showModifier(idx)" class="memo__modify__button">수정</button>
              <button v-if="!memos_modify[idx]" @click="showCheckmodal(memo.id)"><font-awesome-icon icon="trash-alt"/></button>
            </div>
          </div>
          
        </div>
        <div class="feed__memo__write">
          <input v-model="memoInput.content" class="feed__memo__write__input" type="text">
          <button @click="addMemo" class="feed__memo__write__button">
            >
          </button>
        </div>
      </div>
    </div>
    <!-- PC -->
    <div class="feed" v-else>
      <Navi class="detail__navi"/>
      <b-modal id="pos-check-modal" modal-class="pos-check-modal" hide-header hide-footer centered size="sm">
        <div class="pos-check-modal-body">
          <div class="pos-check-title">
            댓글을 삭제하시겠습니까?
          </div>
          <button class="pos-check-yes-button" @click="deleteMemo(delete_memoId)">예</button>
          <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal')">아니오</button>
        </div>
      </b-modal>
      <button class="goBack__button" @click="goBack()">
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </button>
      <div class="feed__important">
        <div class="feed__left__part">
          
          <img class="feed__img" :src="feed.feedImg" alt="">
        </div>
        <div class="feed__right__part">
          <div class="creator__info">
            <div class="creator__info__left">
              <img class="creator__info__img" v-if="feed.userImg==''||feed.userImg==null" src="../../assets/person.jpg"/>
              <img class="creator__info__img" v-else :src="feed.userImg" alt="">
              <div class="creator__info__box">
                <div class="creator__info__name">{{feed.userName}}</div>
                <div class="creator__info__date">{{feed.writeDate}}</div>
              </div>
            </div>
            <div class="creator__info__right">
              <font-awesome-icon v-if="feed.likemark==0" :icon="['far','heart']" @click="addHeart(feed.likemark,feed.id)" class="feed__like__button"/>
              <font-awesome-icon v-if="feed.likemark==1" :icon="['fas','heart']" @click="addHeart(feed.likemark,feed.id)" class="feed__like__button"/>
              <span class="feed__like__cnt">{{feed.likeCnt}}</span>
              <font-awesome-icon v-if="feed.bookmark == 0" class="feed__interactions__bookmark" @click="addBookmark(feed.bookmark,feed.id)" :icon="['far','bookmark']"/>
              <font-awesome-icon v-if="feed.bookmark == 1" class="feed__interactions__bookmark" @click="addBookmark(feed.bookmark,feed.id)" :icon="['fas','bookmark']"/>

              <font-awesome-icon class="feed__interactions__share" icon="share-alt" />
            </div>
          </div>
          <div class="feed__info">
            <div class="feed__left">
              <font-awesome-icon icon="map-marker-alt" class="feed__location__icon"/>
              <span class="feed__location__name"> {{feed.location}}</span>
            </div>
            <div class="feed__right">
              <font-awesome-icon :icon="['fab', 'envira']" class="feed__exhibition__icon"/>
              <span class="feed__exhibition__name"> 간직해온 마음들</span>
            </div>
          </div>
          <div class="feed__content">
            {{feed.feedText}}
          </div>
          
          <div class="dotLine"></div>
          <div class="feed__memo__cnt">Comment ({{memos.length}})</div>
            
          <!-- 댓글 창은 import해서 쓰자 -->
          <div class="feed__memo">
            <div class="feed__memo__list">
              <div class="feed__memo__items" v-for="(memo,idx) in memos" :key="idx">
                <div class="memo__left">
                  <img v-if="memo.userImg==''||memo.userImg==null" class="memo__writer__img" src="../../assets/person.jpg">
                  <img
                    v-else 
                    :src="memo.userImg" 
                    class="memo__writer__img"
                    @click="goUserFeed(memo.userId)">
                  
                  <div class="memo__content"  >
                    <span class="memo__writer__name">{{memo.userName}}</span> <span class="memo__text" v-if="!memos_modify[idx]">{{memo.content}}</span>
                  </div>
                  <div class="memo__modifier" v-if=memos_modify[idx]>
                    <input v-focus class="memo__modifier__input" type="text" :value="memo.content">
                    <button @click="modifyMemo(memo)" class="memo__modifier__btn">수정</button>
                  </div>  
                </div>
                
                <div class="memo__buttons" v-if="memo.userId === user.userId">
                  <button v-if="!memos_modify[idx]" @click="showModifier(idx)" class="memo__modify__button">수정</button>
                  <button v-if="!memos_modify[idx]" @click="showCheckmodal(memo.id)"><font-awesome-icon icon="trash-alt"/></button>
                </div>
              </div>
              
            </div>
            <div class="feed__memo__write">
              <input v-model="memoInput.content" class="feed__memo__write__input" type="text">
              <button @click="addMemo" class="feed__memo__write__button">
                >
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>

<script>
import Navi from '@/components/Common/Navi.vue';
import {detailFeed} from '@/api/myfeed.js';
import {listMemo, addMemo, modifyMemo, deleteMemo} from '@/api/memo.js';
import {mapState} from "vuex";
import http from "@/util/http-common";

export default {
  name: "FeedDetail",
  components: {
    Navi,
  },
  data() {
    return {
      observer:null,
      feed: {
        bookmark: 0,
        commentCnt: 0,
        feedImg: '',
        feedText: '',
        id: 0,
        likeCnt: 0,
        likemark: 0,
        location: '',
        userName: '',
        writeDate: '',
        
      },
      memos: [],
      delete_memoId: -1,
      memos_modify: [],
      memoInput: {
        content: '',
        feedId: 0,
        userId: '',
        userName: '',
      },
    };
  },
  methods: {
    goFeedList(userId) {
      this.$router.replace({
        name: "UserFeedList",
        params:{userId:userId}
      })
    },
    isMobile() {
      return window.innerWidth < 1024;
    },
    goUserFeed(userId) {
      this.$router.replace({
        name: "UserFeedList",
        params: {userId: userId}
      });
    },
    goBack() {
      // console.log('뒤로가')
      if(this.$route.params.status =="feedlist") {
        this.$router.replace({
        name: "FeedList",
        });
      }else if(this.$route.params.status == "bookmarklist") {
        this.$router.replace({
        name: "BookmarkList"
        });
      }else if(this.$route.params.status == "alllist") {
        this.$router.replace({
        name: "FeedAll"
        });
      }else if(this.$route.params.status == "ExhibitionDetail") {
        this.$router.replace({
        name: "ExhibitionDetail",
        params: {id: this.feed.exId }
        });
      }else if(this.$route.params.status == "Alarm") {
        this.$router.replace({
        name: "AlarmAll",
        params: {feedno: this.feed.id }
        });
      }else {
        this.$router.replace({
        name: "UserFeedList",
        params: {userId: this.feed.userId}
      });
    }
      
    },
    showCheckmodal(memoId) {
      this.delete_memoId = memoId;
      console.log(memoId);
      console.log(this.delete_memoId);
      this.$bvModal.show('pos-check-modal');
      
    },
    addMemo() {
      console.log(this.memoInput.content,'등록');
      addMemo(this.memoInput, (response) => {
        console.log(response);
        this.updateMemos();
        console.log(this.memos);
      }, (error) => {
        console.error(error); 
      })
      this.memoInput.content = '';
    },
    updateMemos() {
      listMemo(this.$route.params.feedno, (response) => {
      console.log(response);
      this.memos = response.data;
      const temp = []
      for(let i = 0; i < this.memos.length; i++){
        temp.push(false);
      }
      this.memos_modify = temp;
      console.log(this.memos);
    }, (error) => {
      console.error(error);
    });
    },
    deleteMemo(memoId) {
      deleteMemo(memoId, (response) => {
        console.log(response);
        this.updateMemos();
        this.$bvModal.hide('pos-check-modal');
      }, (error) => {
        console.error(error);
      });
    },
    modifyMemo(memo) {
      console.log(memo);
      const input = document.querySelector('.memo__modifier__input');
      console.log(input.value);
      memo.content = input.value;
      modifyMemo(memo,(response) => {
        console.log(response);
        this.updateMemos();
      }, (error) => {
        console.error(error);
      });
    },
    showModifier(idx) {
      // console.log('수정창 띄워');
      this.$set(this.memos_modify,idx,true);
      // const temp = this.memos_modify;
      // temp[idx] = true;
      // this.memos_modify = temp;
      // console.log(this.memos_modify);
    },
    timeForToday(value) {
      const today=new Date();
      const timeValue = new Date(value);
      // console.log(today,timeValue);
      const betweenTime = Math.floor((today.getTime() - timeValue.getTime())/ 1000/ 60);
      //배포에서는 betweenTime -= 480;
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
    addBookmark:function(bookmark,feedid){
      this.feed.bookmark = !this.feed.bookmark;
      if(bookmark == 0) {
        http
        .put(`/api/bookmark/${this.user.userId}/${feedid}`)
        .then((data) => {
          console.log(data);
        })
        .catch((error) => {
          console.error(error);
        })
      }
      else {
        http
        .delete(`/api/bookmark/${this.user.userId}/${feedid}`)
        .then((data) => {
          console.log(data);
        })
        .catch((error) => {
          console.error(error);
        })
      }
    },
    addHeart:function(like, feedid){
      this.feed.likemark = !this.feed.likemark;
      if(like == 0){ // 좋아요 안눌린 상태 
        this.feed.likeCnt += 1;
        http
        .put(`api/likemark/${this.user.userId}/${feedid}`)
        .then((data) => {
          console.log(data); 
          if (data) {
            // alert('좋아요!❤');
            if (this.stompClient && this.stompClient.connected) {
                console.log('알림보냄');
                //소켓이 연결되어있을 때만 알림 전송
                this.stompClient.send(
                  `/send/like/${this.user.userId}/${this.feed.userId}/${feedid}`,
                  {}
                );
              }
          } else {
            alert('오류가 발생하였습니다.');
          }
        })
        .catch((err) => console.log(err));
      }else if(like == 1){ // 좋아요 눌린 상태 
      this.feed.likeCnt -= 1;
        http
        .delete(`api/likemark/${this.user.userId}/${feedid}`)
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
  },
  created() {
    this.memoInput.feedId = this.$route.params.feedno;
    this.memoInput.userId = this.user.userId;
    this.memoInput.userName = this.user.userName;
    this.updateMemos();
    
    detailFeed(this.$route.params.feedno,this.user.userId, (response) => {
      console.log(response);
      this.feed = response.data;
      this.feed.writeDate = this.timeForToday(this.feed.writeDate);
      // console.log(this.feed);
    }, (error) => {
      console.error(error);
    });
    
  },
  destroyed() {
    this.observer.disconnect();
  },
  mounted() {
    const target=document.querySelector(".bm-menu");
    // console.log(target);
    this.observer = new MutationObserver((mutations)=>{
      mutations.forEach((mutation)=>{
        if(mutation.target.classList.contains('moveRight')){
          document.querySelector('.goBack__button').style.display = 'none';          
        } else {
          document.querySelector('.goBack__button').style.display = 'block';
        }

      })
    })
    const config = {attributes: true, childList: true, characterData: true};
    this.observer.observe(target,config);

    console.log(this.$route.params.feedno);
    // document.documentElement.style.overflowY = "hidden"; 
    const img = document.querySelector('.feed__img');
    // const memoList = document.querySelector('.feed__memo__list')
    const ori_h = img.style.height;
    // let last_csv = 0;
    //문제점: 사진이 작아지면 body크기가 작아져서
    //해결법: body크기를 고정하자
    //zoom event도 추가하자
    document.addEventListener('scroll', () => {
      // 반응형
      if(screen.height > 700) {
        return;
      }
      // 반응형
      const csv = document.documentElement.scrollTop;
      // console.log(csv);
      if(csv === 0)  {
        img.style.height = ori_h;
        // console.log('사진 원래로 변경')
        // memoList.style.marginBottom = "200px";
      } else {
        if(img.style.height === ori_h){
          img.style.height = "200px";
          // console.log('사진 작게 변경')
          // memoList.style.marginBottom = "60px";
        }
        // last_csv = csv;
      }
      // console.log(window.pageYOffset);
      // console.log(csv);
    })
  },
  computed: {
    ...mapState(["user", "stompClient"]),
  },
  directives: {
    focus: {
      inserted: function (el) {
        el.focus()
      }
    }
  }
}
</script>

<style scoped>
  @import "../../components/css/style.css";

  * {
    font-family: 'NanumBarunGothic', sans-serif;
    font-weight:500;
  }
  * {
  box-sizing: border-box;
  }
  .detail__navi {
    background-color:white;
  }
  
  .feed{ -ms-overflow-style: none; } 
  .feed::-webkit-scrollbar{ display:none; }
  .feed {
    width: 380px;
    /* height: 100%; */
    box-sizing: border-box;
    /* max-width:380px; */
    margin:auto;
    /* z-index:1; */
  }
  .feed__left {
    color:#999999;
  }
  .feed__right {
    color:#A593DF;
  }
  .creator__info {
    margin-top:15px;
    display:flex;
    justify-content: space-between;
    align-items: center;
    background:white;
  }

  .creator__info__img {
    width:40px;
    height:40px;
    border-radius:100%;
    /* margin-left:9pt; */
  }
   .creator__info__img:hover {
    cursor:pointer;
  }
  .creator__info__box {
    margin-left:7px;
    text-align:left;
  }

  .creator__info__name {
    font-size:15px;
    font-weight:900;
    /* line-height: 40px; */
  }

  .creator__info__date {
    font-size:13px;
    color: #999999;
    /* line-height:40px; */
    font-weight:700;
  }
  .creator__info__left {
    display:flex;
    /* flex-direction:column; */
    /* justify-content: center; */
    
  }
  .creator__info__right {
    font-size:11px;
    align-self: flex-start;
  }
  .feed__interactions__bookmark,
  .feed__interactions__share {
    color:#999999;
  }
  .feed__interactions__bookmark {
    margin-left:7px;
  }
  .feed__interactions__share {
    margin-left: 3px;
  }
  .feed__like__cnt {
    margin-left:3px;
  }
  .feed__info {
    display:flex;
    font-size:13px;
    justify-content: space-between;
    /* padding-top:70px; */
    margin-bottom:5px;
  }
  .feed__like__button {
    color:#EB4956;
    font-size:13px;
  }
  .feed__like__cnt {
    color:#999999;
  }
  .feed__img {
    transition: height 250ms ease;
    width:100%;
    height:275px;
    
    /* border-radius:24px 24px 0 0; */
  }

  
  .feed__content,
  .feed__memo,
  .feed__btns,
  .feed__info,
  .creator__info {
    margin-left:15px;
    margin-right:15px;
  }
  .feed__content {
    text-align:left;
    font-size:12px;
    font-weight: 500;
    margin-top:20px;
    /* line-height:1.3em; */
    color:#666666;
  }
  .feed__btns {
    color: #D43E3E;
    font-size:10px;
    text-align: right;
    margin-top: 15px;
  }
  .feed__modify__button {
    color:#70A6E3;
  }
  .feed__delete__button {
    margin-left:2px;
  }
  .dotLine {
    height:1px;
    /* background-color:#999999; */
    width:80%;
    border:1px dashed #999999;
    opacity:0.5;
    margin: 15px 10% 0px 10%;
    
  }
  /* 댓글 창은 import해서 쓰자 */
  .feed__memo {
    text-align:left;
    /* margin-top:15px; */
   
    font-size:11px;
  }

  .feed__memo__cnt {
    color:#A0A0A0;
    font-size:13px;
    font-weight:700;
    margin-top:15px;
    margin-left:15px;
    text-align: left;
  }

  .feed__memo__list {
    display:flex;
    flex-direction: column;
    align-items: flex-start;
    /* margin-top:15px; */
    margin-left:0px;
    /* padding-top:55px; */
    padding-bottom:70px;
    min-height:300px;
  }

  .feed__memo__items {
    margin-top:12px;
    display:flex;
    width:100%;
    justify-content: space-between;
    align-items: flex-start;
  }

  .memo__writer__img {
    height:38px;
    width:38px;
    border-radius:100%;
  }

  .memo__writer__name {
    font-size:12px;
    font-weight:700;
  }

  .memo__content {
    font-size:11px;
    margin-left:8px;
  }
  .memo__modify__button {
    margin-right:3px;
  }

  .memo__buttons {
    color: #A593DF;
    font-size:8px;
    align-self:center;
  }
  .memo__left {
    display:flex;
    align-items:center;
  }
  .memo__modifier {
    margin-left:3px;
  }
  .memo__modifier__btn {
    position: relative;
    right:-117px;
    color:#A593DF;
    font-size:8px;
  }
  .feed__memo__write {
    color: #999999;
    height:60px;
    /* margin-top:15pt; */
    position:fixed;
    background-color: white;
    width:380px;
    bottom:0px;
    padding-top:10px;
    padding-bottom:10px;
  }

  .feed__memo__write__input {
    /* background-color: #F5F5F5; */
    width:90%;
    border-radius:25px;
    border: 1px solid #A593DF;
    height:40px;
    
  }

  .feed__memo__write__button {
    width:30px;
    height:30px;
    font-size:15px;
    position:absolute;
    top:15px;
    right:45px;
    border-radius:100%;
    color:white;
    background-color: #A593DF;
    /* margin-left:10pt; */
  }
  .sticky-top {
    position: sticky;
    top:70px;
    z-index:1;
    /* padding-top:55px; */
    /* background-color:white; */
    padding-bottom:8px;
  }
  .pos-check-yes-button {
  color:white;
  background-color:#CB3E47;
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
.transparent {
  background: transparent;
  
  height:55px;
}
.white {
  background: white;
}
.goBack__button {
  font-size:22px;
  position:fixed;
  z-index:3;
  top:27px;
  margin-left:10px;
}

/* 반응형 */
@media screen and (min-width: 1024px) {
  .feed__important {
    margin-top:20px;
    display:flex;
    width:100%;
    height:600px;
  }
  .feed__left__part {
    flex-basis:50%;
    height:100%;
    
  }
  .feed__right__part {
    flex-basis:50%;
    height:100%;
    position:relative;
    /* overflow-y:scroll; */
    /* overflow-x:hidden; */
  }
  .feed {
    width: 760px;
  }
  .feed__memo {
    overflow-y:auto;
    height:300px;
  }
  .feed__memo {
    -ms-overflow-style: none; /* IE and Edge */
    scrollbar-width: none; /* Firefox */
  }
  .feed__memo::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }

  .feed__memo__write {
    position:absolute;
    width:360px;
  }
  .feed__memo__write__input {
    width: 95%;
  }
  .feed__memo__write__button {
    right:25px;
  }
  .feed__img {
    height:600px;
    /* object-fit: cover; */
  }
  /* 글씨 크기 변경 */
  .feed__info {
    margin-top:5px;
    font-size:19px;
  }
  .creator__info__name {
    font-size:22px;
  }
  .creator__info__date {
    font-size:19px;
  }
  .creator__info__img {
    width:60px;
    height:60px;
    margin-right:5px;
  }
  .creator__info__right {
    font-size:23px;
  }
  .feed__like__button {
    font-size:23px;
  }
  .feed__interactions__share,
  .feed__interactions__bookmark {
    margin-left:10px;
  }
  .feed__content {
    font-size:18px;
    margin-top:5px;
  }
  .feed__memo__cnt {
    font-size:20px;
  }
  .memo__writer__img {
    height:57px;
    width:57px;
  }
  .memo__writer__name {
    font-size:18px;
  }
  .memo__text {
    font-size: 12px;
  }
  /* 글씨 크기 변경 */
  /* 추가 */
  .memo__buttons,
  .memo__modifier__btn {
    font-size:15px;
  }
  .memo__modify__button {
    margin-right:5px;
  }
  /* 추가 */
 
}


/* 반응형 */


</style>