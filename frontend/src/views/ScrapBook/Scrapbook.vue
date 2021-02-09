<template>
  <div id="app" class="scrapdiv">
    <link rel="stylesheet" href="vue-booklet.min.css">
    <div>
      <Navi class="scrap__navi" />
    </div>
    <div class="container">
      <h1>Scrapbook</h1>
      <p>당신만의 "아트 스크랩북"을 만들어보세요😉</p>

 <Book>
    <div class="page cover">
      <article class="content">
        <h1>My first book</h1>
      </article>
    </div>
    <div class="page">
      <article class="content">
        <h1>Hello World !</h1>
        <p>Using vue-booklet to create a book which can fliped.</p>
        <p>Please feel free to use it.</p>
      </article>
    </div>
  </Book>

    </div>
  </div>
</template>

<script>
import Navi from "@/components/Common/Navi.vue";
import booklet from 'vue-booklet';



export default {
  name: "Scrapbook",
  components: {
    Navi,
    Book: booklet
  },
  data: () => {
    return {

    };
  },
  methods: {

  },
  mounted: function() {

  },
};
</script>

<style>
@import "../../components/css/style.css";


* {
  font-family: NanumBarunGothic;
  box-sizing: border-box;
}
.scrapdiv {
  width: 380px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}

.scrap__navi {
  width: 380px;
  height: auto;
}

.container {
  float: left;
  margin-top: 70px;
  width: 380px;
  height: auto;
}

.container h1 {
  float: left;
  font-size: 24px;
  padding: 32px 0 0 30px;
  font-size: 26px;
  color: #151515;
}

.container p {
  float: left;
  padding: 0 0 20px 30px;
  font-size: 12px;
  color: #666;
}
</style>
