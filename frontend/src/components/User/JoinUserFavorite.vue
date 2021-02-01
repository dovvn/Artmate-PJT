<template>
  <div class="JoinUserFavorite">
    <div class="wrap">
      <header class="favorite_header">
        <div class="back_btn">
          <font-awesome-icon icon="chevron-left"/>
        </div>
        <div class="favorite_text">
          <div class="favorite_text_tlt">당신의 취향을 알려주세요 !</div>
          <div class="favorite_text_txt">최대 3가지를 선택해주세요.</div>
        </div>
      </header>
      <article class="favorite_list">
        <img id="1" @click="onClick" data-keyword="현대예술" class="favorite_item" src="../../assets/taste_1.jpg" alt="">
        <img id="2" @click="onClick" data-keyword="수묵화" class="favorite_item" src="../../assets/taste_2.jpg" alt="">
        <img id="3" @click="onClick" data-keyword="풍경화" class="favorite_item" src="../../assets/taste_3.jpg" alt="">
        <img id="4" @click="onClick" data-keyword="서양화" class="favorite_item" src="../../assets/taste_4.jpg" alt="">
        <img id="5" @click="onClick" data-keyword="조각" class="favorite_item" src="../../assets/taste_5.jpg" alt="">
        <img id="6" @click="onClick" data-keyword="디자인" class="favorite_item" src="../../assets/taste_6.jpg" alt="">
      </article>
      <button @click="onSignUp" :class="classObject">
        가입하기
      </button>
    </div>
    
  </div>
</template>

<script>
import {join} from '@/api/user.js'
export default {
  name: "JoinUserFavorite",
  data: () => {
    return {
      user:{
        userName: "",
        userId: "",
        userPw: "",
        myTag:[],
      },
      component: this,
    }
  },
  created(){
    // params 비어있으면 유저정보 입력페이지로 푸시
    console.log(this.$route.params);
    this.user.userName=this.$route.params.userName;
    this.user.userId=this.$route.params.userId;
    this.user.userPw=this.$route.params.userPw;
  },
  computed:{
    classObject: function(){
      if(this.user.myTag.length===0)
        return "purple_btn join_btn inactive"
      return "purple_btn join_btn active"
    }
  },
  methods:{
    onClick(e){
      if(!e.target.classList.contains('checked')){
        if(this.user.myTag.length>=3){
          alert('최대 3개까지 선택할 수 있습니다.')
          return;
        }
        e.target.classList.add('checked');
        this.user.myTag.push(e.target.dataset.keyword);
      }
      else{
        e.target.classList.remove('checked');
        const idx=this.user.myTag.indexOf(e.target.dataset.keyword);
        this.user.myTag.splice(idx,1);
      }
    },
    onSignUp(){
      if(this.user.myTag.length===0){
        alert('취향을 선택해주세요.');
        return;
      }
      join(
        this.user,
        (res)=>{
          console.log(res.data)
          if(res.data===true){
            alert("입력하신 이메일 주소로\n본인 인증 메일을 전송하였습니다.\n본인 인증 후 로그인 해주시기 바랍니다.");
            this.$router.push({
              name:"JoinSuccess",
              params:{
                ...this.user,
              }
            })
          }
          else{
            console.log(res.data)
          }
        },
        (err)=>{
          console.error(err);
        }
      )
    }
  }
} 
</script>

<style scoped>
  @import '../../components/css/User/joinFavorite.css';
  @import '../../components/css/common.css';
</style>