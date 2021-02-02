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
    <div class="person__container" v-if='choosed=="Follower"' >
      <div class="person" v-for="(person,idx) in followers" :key="idx" @click="seeFeedList(person)">
        <img class="person__img" :src="person.userImg" alt="">
        <div class="person__name">{{person.userName}}</div>
      </div>
    </div>
    <div class="person__container" v-if='choosed=="Following"' >
      <div class="person" v-for="(person,idx) in followings" :key="idx" @click="seeFeedList(person)">
        <img class="person__img" :src="person.userImg" alt="">
        <div class="person__name">{{person.userName}}</div>
      </div>
    </div>
  </div>
</template>

<script>
import {listFollower, listFollowing} from '@/api/follow.js';
import {mapState} from "vuex";

export default {
  //팔로워, 팔로잉을 created에서 한번에 받아온 후 data에 넣는다.
  props: ["clicked", "userId"],
  data() {
    return {
      choosed: 'Follower',
      followers: [],
      followings: [],
    }
  },
  created() {
    //애초에 첨들올때 뭐눌럿는지를 정보로 받아서
    // 보여주는애를 정해준다.
    // console.log(this.props.clicked);
    // console.log(this.props);
    console.log(this.userId);
    listFollower(this.userId,(response) => {
      this.followers = response.data;
    }, (error) => {
      console.error(error);
    });
    listFollowing(this.userId,(response) => {
      this.followings = response.data;
    }, (error) => {
      console.error(error);
    });
    
  },
  mounted() {
    
    this.choosed = this.clicked;
    // console.log(this.choosed);
    if(this.choosed == "Follower") {
      this.showFollower();
      // console.log('왜안햐냐고')
    } else {
      this.showFollowing();
    }
  },
  computed: {
    ...mapState(["user"])
  },
  methods: {
    seeFeedList(person) {
    //  this.$router.go(this.$router.currentRoute);
      console.log(person.userId);
      this.$emit('seeFeedList',person.userId)
    },
    showFollower() {
      this.choosed = "Follower";
      const tab__left = document.querySelector(".tab__left");
      const tab__right = document.querySelector(".tab__right");
      if(tab__left.classList.contains("choosed")) {
        return;
      } else {
        tab__left.classList.add("choosed");
        tab__right.classList.remove("choosed");
      }
      // console.log(tab__right,tab__left);
      // console.log(this.clicked);
    },
    showFollowing() {
      this.choosed = "Following";
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