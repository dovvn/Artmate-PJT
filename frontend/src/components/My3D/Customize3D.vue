<template>
  <div class="customize">
    <div class="header">
      <button class="goBack__button" @click="goBack">
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </button>
      <div class="title">전시회 커스터마이징</div>
    </div>
    

    <div class="themes">
      <div class="themes__title">전시회 테마</div>
      <div class="themes__list">
        <!-- v-for로 테마들 이미지 띄움, 피드들이랑 똑같이 -->
        <div class="themes__item" v-for="(theme,idx) in themes" :key="idx" @click="chooseTheme(theme,idx)">
          <div class="themes__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="themes__item__check" v-if="choosed__themes[idx]">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img :src="themes_img[idx]" class="themes__item__img">
          
        </div>
        
      </div>
    </div>

    <div class="feeds">
      <div class="feeds__title">전시할 피드 ({{choosed__feeds.length}})</div>
      <div class="feeds__list">
        <div class="feeds__item" v-for="(feed,idx) in feeds" :key="idx" @click="choose(feed,idx)">
          <div class="feeds__item__checkbox">
            <!-- v-if로 check되어 있을때만 보이게 -->
            <div class="feeds__item__check" v-if="choosed__feeds[idx]">
              <font-awesome-icon icon="check" class="check__icon"/>
            </div>
          </div>
          <img :src="feed.feedImg" class="feeds__item__img">
        </div>
      </div>
    </div>
    <div class="footer">
      <!-- 저장버튼 누르면 모달뜨고 선택한 테마와 이미지 보여줌 글고 등록할거냐안할거냐 물어봄 -->
      <button class="save__button" @click="save">
        저장
      </button>
    </div>
    <b-modal id="pos-check-modal" modal-class="pos-check-modal" hide-header hide-footer centered size="sm">
      <div class="pos-check-modal-body">
        <div class="pos-check-title">
          저장하시겠습니까?
        </div>
        <button class="pos-check-yes-button" @click="goMyfeed">예</button>
        <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal')">아니오</button>
      </div>
    </b-modal>
  </div>
</template>

<script>
import http from "@/util/http-common";
import {mapState} from "vuex";

export default {
  data() {
    return {
      //내 피드들 다 저장할거
      themes:[0,1],
      themes_img:[require('../../assets/0_theme.jpg'),require('../../assets/1_theme.jpg')],
      feeds: [],
      //선택한 피드들 번호만 보냄
      choosed__feeds: [],
      choosed__themes: [false,false],
    }
  },
  created() {
    // 내 피드들을 다 가져온다.
    // 그다음에 게시할 피드 선택 란에 쭈욱 게시한다.
    // console.log(this.user.userId);
    http
		.get(`/api/feed/list/${this.user.userId}`)
		.then((response) => {
			this.feeds = response.data;
      for(let i = 0; i < response.data.length; i++) {
        this.choosed__feeds.push(false);
      }
      
		})
    .catch((error) => {
      console.error(error);
    })
    .then((response) => {
      http
      .get(`/api/feed/exhibit/${this.user.userId}`)
      .then((response) => {
        for(let i = 0; i < response.data.length; i++) {
          for(let j = 0; j < this.feeds.length; j++) {
            if(this.feeds[j].id == response.data[i].id) {
              this.$set(this.choosed__feeds,j,true);
              break;
            }
          }
        }
      })
      .catch((error) => {
        console.error(error);
      })
    })
    
     //피드 테마받아서 해당 피드 테마번호 true로
    http
    .get(`/api/feed/theme/${this.user.userId}`)
    .then((response) => {
      const nowTheme = response.data;
      this.choosed__themes[nowTheme] = true; 
      // console.log(this.choosed__themes);
    })
    .catch((error) => {
      console.error(error);
    })
    
    // 테마는 여기에 저장한다. 한 5개하자.
  },
  computed: {
		...mapState(["user"])
	},
  methods: {
    chooseTheme(theme,idx) {
      if(!this.choosed__themes[idx]) {
        
        http
        .put(`api/feed/theme/${this.user.userId}/${theme}`)
        .then(()=>{
          //전체 다 false로 만들고
          const len = this.choosed__themes.length;
          for(let i = 0; i < len; i++) {
            this.$set(this.choosed__themes,i,false);
          }
          this.$set(this.choosed__themes,idx,true);
          
          // console.log(response);
        })
        .catch((error) => {
          console.error(error);
        })
        
      }
    },
    goMyfeed() {
      this.$bvModal.hide('pos-check-modal')
      this.$router.push({
					name: "UserFeedList",
          params: {
            userId: this.user.userId,
          },
				})
    },
    save() {
      this.$bvModal.show('pos-check-modal')
    },
    goBack() {
      this.$router.push({
					name: "UserFeedList",
          params: {
            userId: this.user.userId,
          },
				})
    },
    choose(feed,idx) {
      if(this.choosed__feeds[idx]) {
        http
        .delete(`/api/feed/exhibit/${this.user.userId}/${feed.id}`)
        .then(() => {
          // console.log(response);
        })
        .catch((error) => {
          console.error(error);
        })
      } else {
        http
        .put(`/api/feed/exhibit/${this.user.userId}/${feed.id}`)
        .then(() => {
          // console.log(response);
        })
        .catch((error) => {
          console.error(error);
        })
      }
      // this.choosed__feeds[idx] = !this.choosed__feeds[idx];
      if(this.choosed__feeds[idx]) {
        this.$set(this.choosed__feeds,idx,false);
        // console.log(this.choosed__feeds);
      } else {
        this.$set(this.choosed__feeds,idx,true);
      }
      
    }
  },
}
</script>

<style scoped>
@import "../../components/css/style.css";

.header {
  /* background-color: white; */
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
  width:90px;
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
img {
  object-fit: cover;
}

/* 모달 */
.pos-check-yes-button {
  color:white;
  /* background-color:#CB3E47; */ /* 삭제, 탈되 확인 색 */
  background-color:#9279e9;  /* 수정, 등록 확인 색 */
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

</style>