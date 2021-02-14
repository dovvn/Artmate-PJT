<template>
  <div class="customize">
    <div class="header">
      <button class="goBack__button">
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </button>
      <div class="title">전시회 커스터마이징</div>
    </div>
    

    <div class="themes">
      <div class="themes__title">전시회 테마</div>
      <div class="themes__list">
        <!-- v-for로 테마들 이미지 띄움, 피드들이랑 똑같이 -->
        <div class="themes__item">
          <div class="themes__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="themes__item__check">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img src="../../assets/test_myfeed/feedImg.png" class="themes__item__img">
        </div>
        <div class="themes__item">
          <div class="themes__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="themes__item__check">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img src="../../assets/test_myfeed/feedImg.png" class="themes__item__img">
        </div>
        <div class="themes__item">
          <div class="themes__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="themes__item__check">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img src="../../assets/test_myfeed/feedImg.png" class="themes__item__img">
        </div>
        <div class="themes__item">
          <div class="themes__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="themes__item__check">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img src="../../assets/test_myfeed/feedImg.png" class="themes__item__img">
        </div>
        <div class="themes__item">
          <div class="themes__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="themes__item__check">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img src="../../assets/test_myfeed/feedImg.png" class="themes__item__img">
        </div>
      </div>
    </div>

    <div class="feeds">
      <div class="feeds__title">전시할 피드 ({{choosed__feeds.length}})</div>
      <div class="feeds__list">
        <div class="feeds__item" v-for="(feed,idx) in feeds" :key="idx">
          <div class="feeds__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="feeds__item__check">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img src="../../assets/test_myfeed/feedImg.png" class="feeds__item__img">
        </div>
      </div>
    </div>
    <div class="footer">
      <!-- 저장버튼 누르면 모달뜨고 선택한 테마와 이미지 보여줌 글고 등록할거냐안할거냐 물어봄 -->
      <button class="save__button">
        저장
      </button>
    </div>
    
  </div>
</template>

<script>
export default {
  data() {
    return {
      //내 피드들 다 저장할거
      feeds: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,],
      //선택한 피드들 번호만 보냄
      choosed__feeds: [],
    }
  },
  created() {
    // 내 피드들을 다 가져온다.
    // 그다음에 게시할 피드 선택 란에 쭈욱 게시한다.
    // 테마는 여기에 저장한다. 한 5개하자.
  }
}
</script>

<style scoped>
@import "../../components/css/style.css";

.header {
  background-color: white;
  z-index:9999;
  position:fixed;
  top:0px;
  width:100%;
  text-align:center;
  font-size:20px;
  padding-top: 30px;
  padding-bottom: 30px;
  font-weight:700;
}

.goBack__button {
  position:absolute;
  left:15px;
}
.themes {
  /* z-index:9999; */
  background-color:white;
  margin-top: 90px;
  
  
}
.themes,
.feeds,
.save__button {
  padding-left:20px;
  padding-right:20px;
}
.themes,
.feeds{
  height:30%;
  
}
.feeds__title {
  /* z-index:9999; */
  background-color: white;
  padding-bottom:5px;
  margin-bottom:5px;
}
.themes__title,
.feeds__title {
  font-size: 16px;
  border-bottom:1px solid;
  padding-top:5px;
  margin-bottom:10px;
  /* margin-bottom:10px; */
}
.themes__title {
  color: #999999;
}
.feeds__title {
  color: #A593DF;
}
.themes__list,
.feeds__list {
  display:flex;
  flex-wrap:wrap;
}
.themes__item,
.feeds__item {
  width:100px;
  height:70px;
  position:relative;
  margin:8px;
}
.themes__item__checkbox,
.feeds__item__checkbox {
  position:absolute;
  right: 5px;
  top:5px;
  width:20px;
  height:20px;
  border-radius:100%;
  background-color:white;
  text-align:center;
}
.themes__item__img,
.feeds__item__img {
  width:100%;
  height:100%;
}
.footer {
  position:fixed;
  z-index:999;
  bottom:10px;
  text-align:center;
  width:100%;
  
}
.save__button {
  border-radius:20px;
  color:white;
  background: linear-gradient(270deg,#A593DF,#9279E9,#7D5BF4,#6D44FD);
  font-size: 18px;
  width:120px;
  height:40px;
  align-self:center;
}

</style>