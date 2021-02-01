<template>
  <div class="MemoList">
    <div class="fixed-top">
      <div class="title">댓글</div>
      <div class="feed__info">
        <img class="feed__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="feed__content">
          <b>유진이</b> 팀아이텔 작가님의 전시🤝 
          <br>작가님의 그림은 단순해보이지만 레이어와 
          치밀함 등으로 구성되어진 것 같았고, 극도의 정밀함을 볼 수 있었다.
          <br>미니멀한 느낌이 강했던 무료 전시 👨‍🦲
        </div>
      </div>
      <div class="feed__date">2주 전</div>
      <div class="line"></div>
    </div>
   
    <div class="memos">
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정다운</div>
        <div class="memo__content">저도 꼭 가보고 싶네요~~~~~<br>~~~~~~~~~~<br>~~~</div>
        <div class="memo__buttons">
          <button class="memo__modify">수정</button>
          <button class="memo__delete">X</button>
        </div>
        
      </div>
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정혜지</div>
        <div class="memo__content">전시회 사진 느낌있어요</div>
      </div>
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정혜지</div>
        <div class="memo__content">위치가 궁금해요!</div>
      </div>
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정혜지</div>
        <div class="memo__content">위치가 궁금해요!</div>
      </div>
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정혜지</div>
        <div class="memo__content">위치가 궁금해요!</div>
      </div>
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정혜지</div>
        <div class="memo__content">위치가 궁금해요!</div>
      </div>
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정혜지</div>
        <div class="memo__content">위치가 궁금해요!</div>
      </div>
      <div class="memo">
        <img class="memo__writer__img" src="../../../assets/test_myfeed/profileImg.png" alt="">
        <div class="memo__writer__name">정혜지</div>
        <div class="memo__content">위치가 궁금해요!</div>
      </div>
    </div>
    
    <!-- 댓글 쓰기 폼 -->
    <div class="fixed-bottom">
      <div class="line"></div>
      <div>댓글을 달아보세요.💅🏻</div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
@font-face {
  font-family: 'NanumBarunGothic';
  font-style: normal;
  font-weight: 400;
  src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot');
  src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWeb.ttf') format('truetype');
}

@font-face {
  font-family: 'NanumBarunGothic';
  font-style: normal;
  font-weight: 700;
  src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot');
  src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebBold.ttf') format('truetype')
}

@font-face {
  font-family: 'NanumBarunGothic';
  font-style: normal;
  font-weight: 300;
  src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot');
  src: url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.eot?#iefix') format('embedded-opentype'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.woff') format('woff'), url('//cdn.jsdelivr.net/font-nanumlight/1.0/NanumBarunGothicWebLight.ttf') format('truetype');
}

* {
    font-family: 'NanumBarunGothic', sans-serif;
}
* {
  box-sizing: border-box;
}

/* 댓글 쓰기폼, 피드정보, 백 버튼 , 타이틀은 fixed or sticky로 고정하자 */
.fixed-top {
  background-color:white;
  position:sticky;
  top:0;
  width:100%;
}

.fixed-bottom {
  background-color:white;
  position:fixed;
  bottom:0;
  width:100%;
}

.title {
  font-size:20px;
  font-weight:700;
  padding-top:50px;
}

.feed__info,
.feed__date,
.memos {
  margin-left:20px;
  margin-right:20px;
}

.feed__info {
  display:flex;
}

.feed__writer__img {
  flex-basis:10%;
  height:33px;
  width:33px;
}

.feed__content {
  flex-basis:90%;
  text-align: left;
  font-size:13px;
  margin-top:8px;
  margin-left:8px;
}

.feed__date {
  text-align: right;
  color:#A0A0A0;
  font-size:13px;
  margin-top:7px;
}

.line {
  width:100%;
  height:1px;
  background-color: #CCCCCC;
  margin-top:8px;
}

.memos {
  margin-bottom:25px;
  margin-top:20px;
}

.memo {
  display:flex;
  align-items: center;
  font-size:13px;
}

.memo__writer__img {
  width:33px;
  height:33px;
}

.memo__writer__name {
  font-weight:800;
  margin-left:15px;
  /* height:100%; */
}
/* 댓글 몇자이상이면 더보기 버튼눌러서 볼 수있게함 */
.memo__content {
  margin-left:3px;
  /* height:100%; */
  text-align:left;
}

.memo__buttons {
  position: absolute;
  right: 20px;
}

.memo__modify {
  color:#997DF4;
}

.memo__delete {
  margin-left:5px;
  font-size:12px;
}

</style>