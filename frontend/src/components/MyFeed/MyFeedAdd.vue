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
      <label class="label__exhibition" for="exhibition">
        <font-awesome-icon :icon="['fab', 'envira']" class="exhibition__icon"/>
      </label>
      <input class="input__exhibition" id="exhibition" placeholder="전시회 이름" type="text" autocomplete="off" @input="onInput" v-model="keyword">
      <ul
      v-if="isShowAuto" 
      class="keyword_list"
      >
      <li
        class="keyword_item"
        v-for="(item,idx) in autocomplete"
        :key="idx"
        @click="onClickAuto"
        :data-keyword="item.name"
        :data-location="item.location"
        :data-id="item.id"
      >
        <span class="auto_search_text" :data-keyword="item.name" :data-location="item.location" :data-id="item.id">{{item.name}}</span> 
      </li>
    </ul>
    </div>
    <div class="line"></div>
    <div class="input__container" v-if="isSetExhibit">
      <label class="label__location" for="location">
        <font-awesome-icon icon="map-marker-alt" class="location__icon"/>
      </label>
      <input v-model="feed.location" class="input__location" id="location" placeholder="위치" type="text" autocomplete="off">
    </div>
    <div class="line" v-if="isSetExhibit"></div>

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
import * as Hangul from 'hangul-js';

export default {
  data() {
    return {
      isSetExhibit:false,
      isShowAuto:false,
      keyword: "",
      imageUrl: null,
      imageFile: null,
      feed: {
        location: '',
        feedText: '',
        exName: '',
        exId: 0,
        userId: '',
        userImg: '',
        userName: '',
      },
      keywordList: [],
        
    }
  },
  computed: {
    ...mapState(["user", 'stompClient']),
    autocomplete(){
      const search = this.keyword;
      const search1 = Hangul.disassemble(search).join("");
      // console.log(search1)
      let arr=[];
      this.keywordList
      .filter(function (item) {
          return item.name.includes(search)|| item.diassembled.includes(search1);
      })
      .forEach(function (item) {
        arr.push(item);
      });
      // console.log(arr);
      return arr.slice(0,10);
    }
  },
  created() {
    this.feed.userId = this.user.userId;
    this.feed.userImg = this.user.userImg;
    this.feed.userName = this.user.userName;
    http
    .get(`/api/exhibit/name`)
    .then((response)=>{
      // console.log(response);
      this.keywordList=response.data;
      this.keywordList.forEach(function (item) {
            var dis = Hangul.disassemble(item.name, true);
            var cho = dis.reduce(function (prev, elem) {
                elem = elem[0] ? elem[0] : elem;
                return prev + elem;
            }, "");
            item.diassembled = cho;
        });
    })
    .catch((error)=>{
      console.error(error);
    })
  },
  methods: {
    goBack() {
      this.$router.replace({
        name: "UserFeedList",
        params: {userId: this.user.userId}
      });
    },
    addFeed() {
      // 먼저 알림창 함 띄우고 동의하면
      // axios로 백에 요청
      
      // console.log(this.feed);
      const formData = new FormData();
      formData.append("file", this.imageFile)
      formData.append("feed", new Blob([JSON.stringify(this.feed)], { type: "application/json" }));
      addFeed(formData, () => {
        // console.log(response);
        if (this.stompClient && this.stompClient.connected) {
          //소켓이 연결되어있을 때만 알림 전송
          // console.log('피드 알림보냄~~')
          this.stompClient.send(`/send/feed/${this.user.userId}`, {});
        }
        // const feedno = response.data.id;
        this.$router.replace({
          name:"UserFeedList",
          // name: "MyFeedView",
          params: {status: "added",userId:this.user.userId},
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
      // console.log(e.target.files);
      const file = e.target.files[0];
      this.imageFile = file;
      this.imageUrl = URL.createObjectURL(file);
    },
    handleAuto(e){
      const x=['keyword_list','keyword_item','auto_search_icon','auto_search_text']
      if(!x.includes(e.target.className)){
        this.isShowAuto=false;
      }
    },
    onClickAuto(e){
        this.keyword=e.target.dataset.keyword;
        // console.log(e.target.dataset);
        this.feed.location = e.target.dataset.location;
        this.feed.exId = e.target.dataset.id;
        this.feed.exName = e.target.dataset.keyword;
        this.isSetExhibit = true;
        // this.search();
        
        this.isShowAuto=false;
      },
    onInput(e){
        this.keyword=e.target.value;
        this.isSubmit=false;
        if(this.keyword.length===0) {
          this.isShowAuto=false;
          this.isSetExhibit = false;
          }
        else this.isShowAuto=true;
    },
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
  position: relative;
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

.keyword_list {
  width: 100%;
  z-index: 100;
  border: 1px solid var(--color-light-purple);
  list-style: none;
  position:absolute;
  top:37px;
  background-color: white;
}

.keyword_item:hover {
  cursor:pointer;
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