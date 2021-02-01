<template>
  <div>
    <div id="back" @click="back()">
      <font-awesome-icon :icon="['fas', 'chevron-left']" size="2x" />
    </div>
<<<<<<< HEAD
      <h1>취향을 바꿀거예요~~ 🎈</h1> 
      <!-- 회원가입에서 JoinFavorite.vue 파일과 같은 형식 : 지형오빠가 만든 폼 그대로 이용 -->
=======
    <div id="mpg2">
      <div id="content2">
        <div class="favorite_text">
          <div class="favorite_text_tlt">원하는 취향으로 변경하세요. </div>
          <div class="favorite_text_txt">최대 3가지를 선택해주세요.</div>
        </div>
      <article class="favorite_list">
        <img id="1" @click="onClick" data-keyword="현대예술" class="favorite_item" src="../../assets/taste_1.jpg" alt="">
        <img id="2" @click="onClick" data-keyword="수묵화" class="favorite_item" src="../../assets/taste_2.jpg" alt="">
        <img id="3" @click="onClick" data-keyword="풍경화" class="favorite_item" src="../../assets/taste_3.jpg" alt="">
        <img id="4" @click="onClick" data-keyword="서양화" class="favorite_item" src="../../assets/taste_4.jpg" alt="">
        <img id="5" @click="onClick" data-keyword="조각" class="favorite_item" src="../../assets/taste_5.jpg" alt="">
        <img id="6" @click="onClick" data-keyword="디자인" class="favorite_item" src="../../assets/taste_6.jpg" alt="">
      </article>
      <div id="finish">
      <font-awesome-icon @click="changeFinish" :class="act" :icon="['fas', 'check-circle']" size="3x" />
    </div>
  

    </div>
    </div>
>>>>>>> develop
  </div>
</template>

<script>
export default {
   name: "FavoriteModify",
  data: () => {
    return {
      userInfo:{
        myTag:[],
      },
      component: this,
    }
  },
  created(){
    // myTag[] 정보 가져오기 -> 표시 🎈
    this.userInfo =  this.$store.getters.getUser;
    console.log(this.userInfo);  
    
  },
  computed:{
    act: function(){
      if(this.userInfo.myTag.length===0)
        return "act inactive"
      return "act active"
    }
  },
    methods:{
    back:function(){
      // 수정된 취향 정보 가지고 돌아가기
      this.$router.push('/mypage');
    },
    onClick(e){
      if(!e.target.classList.contains('checked')){
        if(this.userInfo.myTag.length>=3){
          alert('최대 3개까지 선택할 수 있습니다.')
          return;
        }
        e.target.classList.add('checked');
        this.userInfo.myTag.push(e.target.dataset.keyword);
      }
      else{
        e.target.classList.remove('checked');
        const idx=this.userInfo.myTag.indexOf(e.target.dataset.keyword);
        this.userInfo.myTag.splice(idx,1);
      }
    },
    changeFinish(){
      if(this.userInfo.myTag.length===0){
        alert('취향을 선택해주세요.');
        return;
      }else{
        // 선택한 태그로 데이터 바꾸고 🎈
        //this.$store.commit('setUserInfo',this.userInfo);
        //console.log("바꾼 취향 : "+this.userInfo.myTag[0]+", "+this.userInfo.myTag[1]+", "+this.userInfo.myTag[2]+" ");
        this.$router.push('/mypage'); // 다시 수정폼으로 
      }
    }
  }
}
</script>

<<<<<<< HEAD
<style>
#back {
  margin-top: 40px;
  padding-left: 25px;
  width: 30px;
}
=======
<style scoped>
  @import '../../components/css/User/joinFavorite.css';
  @import '../../components/css/common.css';
#back2 {
  margin-top: 30px;
  padding-left: 25px;
  width: 30px;
}
#mpg2 {
  display: inline-block;
  margin-bottom: 10px;
}
#content2 {
  margin-top: 10px;
  width: 300px;
}
.act{
  color: #7d5bf4;
}
#finish{
    margin: 1em;
    float: right;
}
>>>>>>> develop
</style>