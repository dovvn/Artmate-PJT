<template>
  <div id="app">
    <link rel="stylesheet" href="vue-booklet.min.css" />
    <Navi class="scrap__navi" />
    <div id="container">
      <h1>Scrapbook</h1>
      <p>당신만의 "아트 스크랩북"을 만들어보세요😉</p>
    </div>
  </div>
</template>

<script>
import Navi from "@/components/Common/Navi.vue";

export default {
  name: "Scrapbook",
  components: {
    Navi,
  },
  data: () => {
    return {};
  },
  methods: {},
  mounted: function() {},
};
</script>

<style>
@import "../../components/css/style.css";

* {
  font-family: NanumBarunGothic;
  box-sizing: border-box;
}
#app {
  width: 760px;
  height: 320px;
  text-align: left;
  margin: 0 auto;
  background-color: #313030;
}

.scrap__navi{

}

#container {
  float: left;
  margin-top: 70px;
  width: 380px;
  height: auto;
}

#container > h1 {
  font-size: 24px;
  padding: 32px 0 0 30px;
  font-size: 26px;
  color: #151515;
}

#container > p {
  padding: 0 0 20px 30px;
  font-size: 12px;
  color: #666;
}

/* ------------------------------ 반응형 ------------------------------ */
@media screen and (max-width: 1024px) {
  #app {
    width: 380px;
  }
}

/*
@media screen and (max-width: 1024px) {

  }
}
*/
</style>
