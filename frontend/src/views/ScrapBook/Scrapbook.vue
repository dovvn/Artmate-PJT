<template>
  <div class="scrap_app">
    <Navi class="scrap_nav" />

    <b-modal
      id="pos-check-modal"
      modal-class="pos-check-modal"
      hide-header
      hide-footer
      centered
      size="sm"
    >
      <div class="pos-check-modal-body">
        <div class="pos-check-title">
          스크랩북에서 제거하시겠습니까?
        </div>
        <button class="pos-check-yes-button" @click="deleteScrap()">예</button>
        <button
          class="pos-check-no-button"
          @click="$bvModal.hide('pos-check-modal')"
        >
          아니오
        </button>
      </div>
    </b-modal>

    <div id="container">
      <div id="ttl">
        <p class="title">Scrapbook</p>
        <div class="bookLogo">
          <div class="book__page"></div>
          <div class="book__page"></div>
          <div class="book__page"></div>
        </div>
      </div>
      <p class="txt">당신만의 '아트 스크랩북'을 만들어보세요😉</p>

      <!--스크랩북 리스트-->
      <ul id="bookList" class="list-inline" v-if="scrap_list.length>0" >
        <li class="book" v-for="(item, idx) in scrap_list" :key="idx">
          <font-awesome-icon
            :icon="['fas', 'bookmark']"
            class="bookmark"
            @click="showCheckmodal(item.id)"
          />
          <img :src="item.exImg" alt="" @click="seeDetail(item.id)" />
          <div class="exName">
            <p>{{ item.name }}</p>
          </div>
        </li>
      </ul>
      <div v-else class="scrap_text">스크랩 된 내역이 없습니다</div>
      <!--스크랩북 리스트-->
    </div>
  </div>
</template>

<script>
import { getScrapBook } from "@/api/exhibit.js";
import { deleteScrapBook } from "@/api/exhibit.js";
import Navi from "@/components/Common/Navi.vue";
import anime from "animejs";

export default {
  name: "Scrapbook",
  components: {
    Navi,
  },
  data: () => {
    return {
      scrap_list: [],
      deleteId: "",
    };
  },
  created() {
    this.getScrapBook();
    document.getElementsByClassName(".navi_item").style.color="#FFF";
  },
  watch: {},
  methods: {
    shred() {
      this.shredding = anime({
        targets: "#original",
        height: 0,
        duration: 10000,
        easing: "linear",
      });

      this.dropping = anime({
        targets: "#painting",
        translateY: "101%",
        duration: 10000,
        easing: "linear",
      });
    },
    artSelected() {
      this.shredding.pause();
      this.dropping.pause();
    },
    seeDetail(exno) {
      const vue=this;
      setTimeout(function(){
        vue.$router.replace({
        name: "ExhibitionDetail",
        params: { id: exno },
      });
      },500);
      // this.$router.replace({
      //   name: "ExhibitionDetail",
      //   params: { exno: exno },
      // });
    },
    showCheckmodal(id) {
      this.deleteId = id;
      this.$bvModal.show("pos-check-modal");
    },
    deleteScrap() {
      //스크랩 제거
      const user = this.$store.getters.getUser;

      deleteScrapBook(
        user.userId,
        this.deleteId,
        () => {
          // console.log("북마크 삭제 완료" + res.data);
          this.getScrapBook();
          this.$bvModal.hide("pos-check-modal");
        },
        (err) => {
          console.error(err);
        }
      );
    },
    getScrapBook() {
      //스크랩한 전시회리스트 가져오기
      const user = this.$store.getters.getUser;
      getScrapBook(
        user.userId,
        (res) => {
          this.scrap_list = res.data;
        },
        (err) => {
          console.error(err);
        }
      );
    },
  },
  mounted: function() {
    this.shred();
  },
};
</script>

<style scoped>
@import "../../components/css/style.css";
@import '../../components/css/navi.css'; 

/* nav */
.scrap_nav {
  background-color: #313030;
}

.scrap_nav>>>.bm-burger-bars {
  background-color: white;
}

.scrap_nav>>>.navi_item  {
  color: white;
}

#container {
  float: left;
  margin-top: 70px;
  width: 100%;
  height: 100vh;
  background-color: #313030;
}

#container .txt {
  padding: 0 0 65px 30px;
  font-size: 16px;
  color: #fff;
  opacity: 0.7;
}

.flipbook .viewport {
  width: 90vw;
  height: 90vh;
}

#ttl {
  float: left;
  width: 100%;
  height: auto;
}

#ttl .title {
  display: inline;
  float: left;
  font-size: 2em;
  padding: 32px 0 0px 30px;
  color: #fff;
}

/* 책 로고 */
#ttl .bookLogo {
  float: left;
  display: inline;
  margin: 55px 0 0 15px;
  transform: translateY(-50%);
  border: 3px solid #ecf0f1;
  width: 48px;
  height: 35px;
  background-color: #313030;
}

.book__page {
  position: absolute;
  left: 50%;
  top: -3px;
  margin: 0 auto;
  border-top: 3px solid #ecf0f1;
  border-bottom: 3px solid #ecf0f1;
  border-right: 3px solid #ecf0f1;
  background-color: #313030;
  width: 24px;
  height: 35px;
  transform-origin: 0% 50%;
  -webkit-animation: flip 1.2s infinite linear;
  animation: flip 1.2s infinite linear;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
}
.book__page:nth-child(1) {
  z-index: -1;
  -webkit-animation-delay: 0.3s;
  animation-delay: 0.3s;
}
.book__page:nth-child(2) {
  z-index: -2;
  -webkit-animation-delay: 1.8s;
  animation-delay: 1.8s;
}
.book__page:nth-child(3) {
  z-index: -3;
  -webkit-animation-delay: 3.2s;
  animation-delay: 3.2s;
}

@keyframes flip {
  0% {
    transform: perspective(600px) rotateY(0deg);
  }
  20% {
    background: #080808;
  }
  29.9% {
    background: #080808;
  }
  30% {
    transform: perspective(200px) rotateY(-90deg);
    background: #313030;
  }
  54.999% {
    opacity: 1;
  }
  55% {
    opacity: 0;
  }
  60% {
    transform: perspective(200px) rotateY(-180deg);
    background: #313030;
  }
  100% {
    transform: perspective(200px) rotateY(-180deg);
    background: #313030;
  }
}

/* 북마크 아이콘 */
.bookmark:before,
.bookmark:after {
  box-sizing: border-box;
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
}

.bookmark {
  position: absolute;
  top: -5px;
  right: 15px;
  z-index: 999;
  padding: 0px;
  -webkit-transform: rotate(0deg) skew(0deg);
  transform: rotate(0deg) skew(0deg);
  color: #a593df;
  font-size: 60px;
}

/* 리스트  */
.book::after,
.book::before,
.book img,
.book {
  position: relative;
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.book:nth-child(2n) {
  top: 35px;
}

.book .exName {
  position: absolute;
  bottom: -10px;
  left: 0;
  width: 100%;
  background-color: #000;
  opacity: 0.6;
}

.book .exName p {
  padding-top: 10px;
  color: #fff;
  font-size: 12px;
}

#bookList {
  display: table-cell;
  width: 100%;
  height: auto;
  padding-left: 30px;
  background-color: #313030;
}

.list-inline {
  padding-left: 0;
  list-style: none;
}
.list-inline > li {
  display: inline-block;
  margin-right: 1.5em;
  margin-bottom: 4.5em;
}
.list-inline > li:last-child {
  margin-right: 0;
}

.book {
  float: left;
  cursor: pointer;
  display: block;
  width: 150px;
  height: 220px;
  position: relative;
  background: white;
  z-index: 1;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.45), 0 9px 20px 0 rgba(0, 0, 0, 0.55);
  overflow: hidden;
  transition: box-shadow 0.3s linear;
}

.book img {
  width: inherit;
  height: inherit;
  transform-origin: 0 50%;
  transform: rotateY(0);
  transition: all 0.45s ease;
}

.book:hover {
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.25), 0 9px 20px 0 rgba(0, 0, 0, 0.45);
}
.book:hover img {
  transform: rotateY(-25deg);
  box-shadow: 1px 1px 5px 5px rgba(0, 0, 0, 0.2);
}

.book::after,
.book::before {
  content: "";
  display: block;
  width: inherit;
  height: inherit;
  position: absolute;
  z-index: -1;
  top: 0;
  background: white;
  border: 1px solid #d9d9d9;
}

.book::before {
  left: -3px;
}

.book::after {
  left: -6px;
}

* {
  font-family: NanumBarunGothic;
  box-sizing: border-box;
}

.scrap_app {
  width: 760px;
  height: 100%;
  text-align: left;
  margin: 0 auto;
  background-color: #313030;
}

.scrap_text {
  text-align: center;
  font-size: var(--font-txt-lg);
  font-weight: var(--weight-bold);
  color: #fff;
  opacity: 0.7;
}

/* -------------------------모달 css ----------------------------- */
.pos-check-yes-button {
  color: white;
  background-color: #cb3e47;
  border-radius: 10px;
  font-size: 14px;
  width: 100px;
  height: 30px;
}
.pos-check-no-button {
  color: #f3f3f3;
  background-color: #707070;
  border-radius: 10px;
  font-size: 14px;
  width: 100px;
  height: 30px;
  margin-left: 15px;
}
::v-deep .pos-check-modal > .modal-dialog > .modal-content {
  background-color: #e8e8e8;
  border: 1px solid #707070;
  border-radius: 15px;
  font-size: 14px;
  width: 310px;
  margin: auto;
}
::v-deep .pos-check-modal2 > .modal-dialog > .modal-content {
  background-color: #e8e8e8;
  border: 1px solid #707070;
  border-radius: 15px;
  font-size: 14px;
  width: 310px;
  margin: auto;
}
.pos-check-title {
  height: 60px;
  line-height: 60px;
  font-weight: 700;
}
.pos-check-modal-body {
  text-align: center;
}
.transparent {
  background: transparent;
  height: 55px;
}
.white {
  background: white;
}
.goBack__button {
  font-size: 22px;
  position: fixed;
  z-index: 3;
  top: 30px;
  margin-left: 10px;
}

/* ------------------------------ 반응형 ------------------------------ */
@media screen and (max-width: 1024px) {
  .scrap_app {
    width: 380px;
    height: 100%;
    background-color: #313030;
  }
}

/*
@media screen and (max-width: 1024px) {

  }
}
*/
</style>
