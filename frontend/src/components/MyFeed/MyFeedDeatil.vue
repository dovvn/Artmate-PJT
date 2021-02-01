<template>
  <div class="feed">
    <div class="writer__info">
      <div class="writer__info__left">
        <img class="writer__info__img" src="../../assets/test_myfeed/profileImg.png" alt="">
      </div>
      <div class="writer__info__right">
        <div class="writer__info__name"> <b>유진이</b> </div>
        <div class="writer__info__date">1일전</div>
      </div>
    </div>

    <div class="feed__info">
      <div class="feed__info__left">
        <b>
          <font-awesome-icon icon="map-marker-alt"/>
          <span class="feed__info__text"> 대전시립미술관</span>
        </b>
      </div>     
      <div class="feed__info__right">
        <b>
          <font-awesome-icon :icon="['fab', 'envira']" />
          <span class="feed__info__text"> 간직해온 마음들</span>
        </b>
      </div>
    </div>
    
    <img class="feed__img" src="../../assets/test_myfeed/feedImg.png" alt="">
    <div class="feed__interactions">
      <div class="feed__interactions__left">
        <font-awesome-icon icon="heart"/>
        <span><b> 유진이</b>님 외 <b>145명</b>이 좋아합니다</span>
      </div>
      <div class="feed__interactions__right">
        <font-awesome-icon class="feed__interactions__icons" icon="bookmark"/>
        <font-awesome-icon class="feed__interactions__icons" icon="share-alt" />
      </div>
    </div>
    
    <div class="feed__content">
      <b>유진이</b> 팀아이텔 작가님의 전시🤝 
      <br>작가님의 그림은 단순해보이지만 레이어와 
      치밀함 등으로 구성되어진 것 같았고, 극도의 정밀함을 볼 수 있었다.
      <br>미니멀한 느낌이 강했던 무료 전시 👨‍🦲
    </div>
    <div class="feed__memo">
      <span class="feed__memo__cnt">댓글 3개</span>
      <div class="feed__memo__list">
        <span class="feed__memo__items"><b>정다운</b> 저도 꼭 가보고 싶네요~~👈</span>
        <span class="feed__memo__items"><b>정혜지</b> 전시회 사진 느낌 있어요👍</span>
      </div>
      
      <div class="feed__memo__write">
        <input placeholder="  댓글을 달아보세요." class="feed__memo__write__input" type="text">
        <button class="feed__memo__write__button">
          <font-awesome-icon icon="paper-plane"/>
        </button>
      </div>
    </div>
    
    <div class="feed__btns">
      <b>
        <span>수정 </span>
        <span>삭제</span>
      </b>
    </div>
  </div>
</template>

<script>
export default {
  name: "FeedDetail",
  data() {
    return {
      feed: {
        feedno: 0,
        content: "팀아이텔 작가님의 전시🤝",
        userid: "유진이",
        img: "",
        regtime: "",
      },
      memos: []
    };
  },
  computed: {
    message: function() {
      return this.article.content.split("\n");
    }
  },
}
</script>

<style>
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
    font-weight:500;
  }
  .feed {
    width: 380px;
    height: 100%;
    box-sizing: border-box;
  }

  .writer__info {
    margin-top:15pt;
    display:flex;
  }

  .writer__info__img {
    width:41pt;
    height:41pt;
    border-radius:100%;
    margin-left:9pt;
  }

  .writer__info__name {
    font-size:12pt;
    margin-bottom:1pt;
  }

  .writer__info__date {
    font-size:9pt;
    color: #999999;
  }

  .writer__info__right {
    text-align:left;
    margin-left:5pt;
    display: flex;
    flex-direction:column;
    justify-content:center;
  }

  .feed__info {
    display:flex;
    font-size:9pt;
    justify-content: space-between;
    margin:9pt 7pt 9pt 7pt;
  }

  .feed__info__left {
    color:#999999;
  }

  .feed__info__right {
    color: #A593DF;
  }

  .feed__img {
    width:100%;
    height:70%;
  }
  
  .feed__interactions,
  .feed__content,
  .feed__memo,
  .feed__btns {
    margin-left:16pt;
    margin-right:16pt;
  }

  .feed__interactions {
    font-size:11pt;
    margin-top:5pt;
    display:flex;
    justify-content: space-between;
    align-items: center;
  }

  .feed__interactions__right {
    display:flex;
    /* font-size:15pt; */
    
  }
  
  .feed__interactions__icons {
    font-size:12pt;
    margin:3pt;
  }

  .feed__content {
    text-align:left;
    font-size:11pt;
    margin-top:8pt;
    line-height:1.3em;
  }

  .feed__memo {
    text-align:left;
    margin-top:10pt;
    font-size:10pt;
  }

  .feed__memo__cnt {
    margin-bottom:5pt;
    color:#A0A0A0;
  }

  .feed__memo__list {
    display:flex;
    flex-direction: column;
    align-items: flex-start;
    margin-top:2pt;
  }

  .feed__memo__items {
    margin-top:1pt;
  }

  .feed__memo__write {
    color: #999999;
    margin-top:15pt;
    display:flex;
  }

  .feed__memo__write__input {
    background-color: #F5F5F5;
    border-radius: 20px;
    flex-basis:90%;
    height:22pt;
  }

  .feed__memo__write__button {
    font-size:13pt;
    margin-left:10pt;
  }

  .feed__btns {
    color: #D43E3E;
    font-size:10pt;
    text-align: right;
    margin-top: 7pt;
  }

</style>