<template>
  <div class="Follower_ing_List">
    <header class="tab">
      <div class="tab__left choosed" @click="showFollower">
        <div class="tab__title">
          팔로워
        </div>
        <div class="tab__line"></div>
      </div>
      <div class="tab__right" @click="showFollowing">
        <div class="tab__title">
          팔로잉
        </div>
        <div class="tab__line"></div>
      </div>
    </header>
    <div class="person__container">
      <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">박싸피</div>
      </div>
      <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">이철수</div>
      </div>
      <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
      <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
      <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
      <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
      <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
       <div class="person">
        <img class="person__img" src="../../assets/test_myfeed/feedImg.png" alt="">
        <div class="person__name">정마리</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  //팔로워, 팔로잉을 created에서 한번에 받아온 후 data에 넣는다.
  data() {
    return {
      choosed: 'Follower',
      followers: [],
      folloings: [],
    }
  },
  created() {
    //애초에 첨들올때 뭐눌럿는지를 정보로 받아서
    // 보여주는애를 정해준다.
  },
  methods: {
    // 데이터 처리는 받아오면 합시다~
    // 마이페이지도 마이페이지 완성되면 ㄱㄱ
    showFollower() {
      const tab__left = document.querySelector(".tab__left");
      const tab__right = document.querySelector(".tab__right");
      if(tab__left.classList.contains("choosed")) {
        return;
      } else {
        tab__left.classList.add("choosed");
        tab__right.classList.remove("choosed");
      }
      // console.log(tab__right,tab__left);
    },
    showFollowing() {
      const tab__left = document.querySelector(".tab__left");
      const tab__right = document.querySelector(".tab__right");
      if(tab__right.classList.contains("choosed")) {
        return;
      } else {
        tab__right.classList.add("choosed");
        tab__left.classList.remove("choosed");
      }
    }
  }
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
.Follower_ing_List {
  margin:10px 10px 0px 10px;
}
.tab {
  width: 100%;
  display:flex;
}
.tab__left,
.tab__right {
  widtH: 50%;
  color: #CCCCCC;
  margin-bottom:20px;
}
.tab__title {
  margin-bottom:10px;
  font-size:18px;
  text-align:center;
}
.tab__line {
  width: 100%;
  height:3px;
  background-color: #CCCCCC;
}
.person__container {
  display:flex;
  flex-wrap:wrap;
}
.person {
  width: 33%;
  height:33%;
  text-align:center;
}
.person__img {
  width:80px;
  height:80px;
  margin-bottom:8px;
  border-radius:100%;
}
.person__name {
  font-weight:900;
  font-size:16px;
  margin-bottom:8px;
  text-align:center
}
.choosed {
  color: #A593DF;
}
.choosed > .tab__line {
  background-color:#A593DF;
  
}
</style>