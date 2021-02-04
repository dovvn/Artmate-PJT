<template>
  <div class="FindPwUserInfo">
    <div class="wrap">
      <div @click="onBack" class="back_btn">
        <font-awesome-icon icon="chevron-left"/>
      </div>
      <div class="findpw_title">
        <span>비밀번호 찾기</span>
      </div>
      <form class="findpw_form">
        <label class="userid_label" for="userid_pw">
          가입하신 아이디(이메일)를 입력해주세요
        </label>
        <input 
          id="userid_pw"
          v-model="email"
          type="text"
          class="userid_input_pw"
          placeholder="아이디를 입력해주세요."
          >
        <!-- <font-awesome-icon class="login-form-input_id_error_icon" icon="times-circle"/> -->
        <div class="checkUser" v-if="!isPossible">
          <p class="impossible">회원 정보를 찾을 수 없습니다.</p> 
          <router-link :to='{name:"JoinUserInfo"}'>회원가입하기</router-link>
        </div>
        <button @click="onReset" :class="classObject">
          비밀번호 재설정
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import {findPw} from '@/api/user.js';
export default {
  name: "FindPw",
  data: () => {
    return {
      email: "",
      isPossible: true,
      component: this,
    }
  },
  created(){
    this.component = this;
  },
  watch: {
    email: function() {
      this.isPossible=true;
    }
  },
  computed: {
    classObject: function(){
      if(this.email.length > 0 )
        return "purple_btn findpw_form_btn active"
      else
        return "purple_btn findpw_form_btn inactive"
    }
  },
  methods: {
    onBack(){
      history.back();
    },
    onReset(e){
      e.preventDefault();
      findPw(
        this.email,
        (res)=>{
          if(res.data===true){
            alert('임시 비밀번호를 발송하였습니다. \n 다시 로그인 해주세요.')
            this.$router.push({
              name:"FindPwSuccess",
              params:{
                userId : this.email,
              }  
            });
          }
          else{
            this.isPossible=false;
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

<style scoped src="../../components/css/User/findpw.css">
</style>