<template>
  <div id="scrap_app">
    <link rel="stylesheet" href="vue-booklet.min.css" />
    <Navi class="scrap__navi" />
    <div id="container">
      <div id="ttl">
        <p class="title">Scrapbook</p>
        <div class="bookLogo">
          <div class="book__page"></div>
          <div class="book__page"></div>
          <div class="book__page"></div>
        </div>
      </div>
      <p class="txt">당신만의 "아트 스크랩북"을 만들어보세요😉</p>

      <!--스크랩북 리스트-->
      <ul id="bookList" class="list-inline">
        <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>
         <li class="book">
          <div class="bookmark"></div>
          <img src="http://37signals.com/images/remote/remote_front.png" />
        </li>

      </ul>
    </div>
  </div>
</template>

<script>
import Navi from "@/components/Common/Navi.vue";
import anime from "animejs";

export default {
  name: "Scrapbook",
  components: {
    Navi,
  },
  data: () => {
    return {
      shredding: null,
      dropping: null,
    };
  },
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
    artSelected(e) {
      this.shredding.pause();
      this.dropping.pause();
    },
  },
  mounted: function() {
    this.shred();
  },
};

var colorList = ["red","orange","yellow","green","blue","purple"];

</script>

<style>
@import "../../components/css/style.css";



#container {
  float: left;
  margin-top: 70px;
  width: 100%;
  height: 100%;
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
  font-size: 2.5em;
  padding: 32px 0 0px 30px;
  color: #fff;
}

/* 책 로고 */
#ttl .bookLogo {
  float: left;
  display: inline;
  margin: 60px 0 0 15px;
  transform: translateY(-50%);
  border: 3px solid #ecf0f1;
  width: 50px;
  height: 40px;
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
  width: 25px;
  height: 40px;
  transform-origin: 0% 50%;
  -webkit-animation: flip 1.2s infinite linear;
  animation: flip 1.2s infinite linear;
  -webkit-animation-fill-mode: forwards;
  animation-fill-mode: forwards;
}
.book__page:nth-child(1) {
  z-index: -1;
  -webkit-animation-delay: 2.4s;
  animation-delay: 2.4s;
}
.book__page:nth-child(2) {
  z-index: -2;
  -webkit-animation-delay: 3.8s;
  animation-delay: 3.8s;
}
.book__page:nth-child(3) {
  z-index: -3;
  -webkit-animation-delay: 5.2s;
  animation-delay: 5.2s;
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
  top: 0;
  left: 0;
  z-index: 999;
  height: 70px;
  width: 50px;
  padding: 0px;
  -webkit-transform: rotate(0deg) skew(0deg);
  transform: rotate(0deg) skew(0deg);
  border-left: 25px solid rgb(153, 50, 204);
  border-right: 25px solid rgb(153, 50, 204);
  border-bottom: 25px solid transparent;
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
  margin-bottom: 5.5em;
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
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1), 0 9px 20px 0 rgba(0, 0, 0, 0.25);
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
#scrap_app {
  width: 760px;
  height: 100%;
  text-align: left;
  margin: 0 auto;
  background-color: #313030;
}

/* ------------------------------ 반응형 ------------------------------ */
@media screen and (max-width: 1024px) {
  #scrap_app {
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
