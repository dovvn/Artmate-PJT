<template>
  <div class="feedadd">
    
    <button @click="goBack()" class="back__button">
      <font-awesome-icon icon="chevron-left"/>
    </button>
    <div class="title">게시글 작성</div>
    <input ref="imageInput" type="file" hidden @change="onChangeImages">
    <div class="img__empty" v-if="!imageUrl">
      <font-awesome-icon icon="camera"/>
    </div>
    <img v-if="imageUrl" :src="imageUrl" class="img__upload" alt="">
    <div class="img__buttons">
      <button @click = "registerImg" class="changeimg__button">사진 변경</button>
      <button @click = "deleteImg" class="deleteimg__button">삭제</button>
    </div>
    <!-- <div class="input__container">
      <label class="label__date" for="date">달력</label>
      <input class="input__date" id="date" placeholder="방문 날짜" type="text">
    </div>
    <div class="line"></div> -->
    <div class="input__container">
      <label class="label__location" for="location">
        <font-awesome-icon icon="map-marker-alt" class="location__icon"/>
      </label>
      <input v-model="feed.location" class="input__location" id="location" placeholder="위치" type="text" autocomplete="off">
    </div>
    <div class="line"></div>
    <div class="input__container">
      <label class="label__exhibition" for="exhibition">
        <font-awesome-icon :icon="['fab', 'envira']" class="exhibition__icon"/>
      </label>
      <input class="input__exhibition" id="exhibition" placeholder="전시회 이름" type="text" autocomplete="off">
    </div>
    <div class="line"></div>
    <textarea v-model="feed.feedText" class="input__content"></textarea>
    <button @click="$bvModal.show('pos-check-modal')" class="addfeed__button">등록</button>
    <b-modal id="pos-check-modal" modal-class="pos-check-modal" hide-header hide-footer centered size="sm">
      <div class="pos-check-modal-body">
        <div class="pos-check-title">
          글을 등록하시겠습니까?
        </div>
        <button class="pos-check-yes-button" @click="addFeed">예</button>
        <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal')">아니오</button>
      </div>
    </b-modal>
  </div>
</template>

<script>
import {mapState} from "vuex";
import {addFeed} from '@/api/myfeed.js';
import http from "@/util/http-common";

export default {
  data() {
    return {
      imageUrl: null,
      imageFile: null,
      feed: {
        location: '',
        feedText: '',

        userId: '',
        userImg: '',
        userName: '',
      },
      exhibitions: []
        
    }
  },
  computed: {
    ...mapState(["user", 'stompClient'])
  },
  created() {
    this.feed.userId = this.user.userId;
    this.feed.userImg = this.user.userImg;
    this.feed.userName = this.user.userName;
    http
    .get(`/api/exhibit/name`)
    .then((response)=>{
      console.log(response);
    })
    .catch((error)=>{
      console.error(error);
    })
  },
  methods: {
    goBack() {
      this.$router.replace({
        name: "MyFeedList",
        // params: {status: "added"}
      });
    },
    addFeed() {
      // 먼저 알림창 함 띄우고 동의하면
      // axios로 백에 요청
      console.log(this.imageFile);
      console.log(this.feed.location);
      console.log(this.feed.feedText);
      const formData = new FormData();
      formData.append("file", this.imageFile)
      formData.append("feed", new Blob([JSON.stringify(this.feed)], { type: "application/json" }));
      addFeed(formData, (response) => {
        console.log(response);
        if (this.stompClient && this.stompClient.connected) {
          //소켓이 연결되어있을 때만 알림 전송
          console.log('피드 알림보냄~~')
          this.stompClient.send(`/send/feed/${this.user.userId}`, {});
        }
        // const feedno = response.data.id;
        this.$router.replace({
          name:"MyFeedList",
          // name: "MyFeedView",
          params: {status: "added"},
        });
      }, (error) => {
        console.error(error);
      })

      
      
    },
    registerImg() {
      this.$refs.imageInput.click();
    },
    deleteImg() {
      this.imageUrl = null;
    },
    onChangeImages(e) {
      console.log(e.target.files);
      const file = e.target.files[0];
      this.imageFile = file;
      this.imageUrl = URL.createObjectURL(file);
    }
  },
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
.feedadd {
  display:flex;
  flex-direction: column;
  width:380px;
  margin:auto;
}
.line {
  width: 100%;
  height:1px;
  background-color: #CCCCCC;
  margin-bottom:20px;
}
.img__upload,
.img__empty,
.input__container,
.input__content {
  /* margin:30px 20px 0px 20px; */
  margin-left:20px;
  margin-right:20px;
}
.back__button {
  position:fixed;
  top:30px;
  margin-left:20px;
  font-weight:700;
}
.title {
  font-size:20px;
  font-weight:700;
  margin-top:30px;
  text-align:center;
}
.img__upload {
  margin-top:20px;
  width:90%;
  height:220px;
}
.img__empty {
  margin-top:20px;
  background-color: #CCCCCC;
  width:90%;
  height:220px;
  color:white;
  font-size:72px;
  display: flex;
  justify-content: center;
  align-items:center;
}
.img__buttons {
  margin-top:15px;
  text-align:center;
  height:50px;
  
}
.changeimg__button,
.deleteimg__button {
  border: 1px solid#999999;
  padding: 6px 10px 6px 10px;
  font-size:16px;
}
.changeimg__button {
  font-weight:700;
  margin-right:11px;
  margin-bottom:35px;
}
label {
  margin-right:10px;
  font-size:16px;
}
.input__container {
  display:flex;
  margin-bottom:5px;
  align-items: flex-start;
  font-size:16px;
}
.label__date,
.input__date,
.label__location,
.input__location,
.input__content {
  color: #999999;
  font-weight: 700;
}
.label__exhibition,
.input__exhibition {
  color: #A593DF;
  font-weight: 700;
}
.input__exhibition,
.input__location {
  width:90%;
}
.input__location::placeholder,
.input__date::placeholder,
.input__content::placeholder {
  color: #999999;
  font-weight: 700;
}
.input__exhibition::placeholder{
  color: #A593DF;
  font-weight: 700;
}
.input__content {
  margin-bottom:20px;
  color: black;
  border:1px solid #CCCCCC;
  height:120px;
}
.addfeed__button {
  border-radius:20px;
  color:white;
  background: linear-gradient(270deg,#A593DF,#9279E9,#7D5BF4,#6D44FD);
  font-size: 18px;
  width:160px;
  height:40px;
  align-self:center;
}
/* 긍정알림 */
.pos-check-yes-button {
  color:white;
  background-color:#9279e9;
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
/* 반응형 */
@media screen and (min-width: 1024px) {
  .feedadd {
    width:760px;
  }
  .img__upload,
  .img__empty {
    height:320px;
  }
}

/* 반응형 */
</style>