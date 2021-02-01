<template>
  <div id="login">
    <div class="wrap">
      <div class="logo">
        <span class="logo_text"><span class="logo_text_point">a r t</span> m a t e</span>
      </div>
      <form @submit="onSubmit" class="login-form">
        <div class="userid_input_box">
          <label class="user-id_label" for="user-id">아이디(이메일)</label><br>
          <input 
            id="user-id"
            v-model="email"
            type="text"
            class="login-form-input_id"
            placeholder="아이디를 입력하세요."
          >
          <div :class="isEmail">
            <font-awesome-icon v-if="error.email" icon="times"/>
            <font-awesome-icon v-else icon="check"/>
          </div>
        </div>
        <div class="full_bar"></div>
        <p :class="isEmailMessage">{{error.email}}</p>

        <div class="userpw_input_box">
          <label class="user-pw_label" for="user-pw">비밀번호</label><br>
          <input 
            id="user-pw"
            v-model="password"
            type="password"
            class="login-form-input_password" 
            placeholder="비밀번호를 입력하세요." 
          >
          <div :class="isPassword">
            <font-awesome-icon v-if="error.password" icon="times"/>
            <font-awesome-icon v-else icon="check"/>
          </div>
        </div>
        <div class="full_bar"></div>
        <p :class="isPasswordMessage">{{error.password}}</p>
        <button :class="classObject">로그인</button>
      </form>
      <div class="horizontal">
        <div class="bar"></div>
        <div>또는</div>
        <div class="bar"></div>
      </div>

      <div class="sns-login">
        <Google class="icon"/>
        <Kakao class="icon"/>
      </div>
      <div class="router">
        <router-link :to="{name:'JoinUserInfo'}">회원가입</router-link> |
        <router-link :to="{name:'FindPwUserInfo'}">비밀번호 찾기</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import Google from '@/components/User/snsLogin/Google.vue'
import Kakao from '@/components/User/snsLogin/Kakao.vue'
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import {login} from '@/api/user.js'
// window.addEventListener('resize',(e)=>{
//   console.log(e);
//   console.log(e.innerWidth, e.innerHeight);
// })
export default {
  name: "login",
  components:{
    Google, Kakao,
  },
  data: () => {
    return {
      vWidth: 0,
      vHeight: 0,
      email: "",
      password: "",
      passwordSchema: new PV(),
      error:{
        email: false,
        password: false,
      },
      component: this,
    }
  },
  created(){
    this.component = this;
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(20)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function() {
      this.checkForm();
    },
    email: function() {
      this.checkForm();
    }
  },
  computed: {
    classObject: function(){
      if(this.email.length>0 && this.password.length>0 && this.error.password===false && this.error.email===false)
        return "purple_btn login-form_btn active"
      return "purple_btn login-form_btn inactive"
    },
    isEmail: function(){
      if(this.email==='') return "hide";
      if(this.error.email===false)
        return "success input_icon";
      return "error input_icon";
    },
    isPassword: function(){
      if(this.password==='') return "hide";
      if(this.error.password===false)
        return "success input_icon";
      return "error input_icon";
    },
    isEmailMessage: function(){
      if(this.email==='' || (this.email.length>0 && this.error.email===false))
        return "hide";
      return "error";
    },
    isPasswordMessage: function(){
      if(this.password==='' || (this.password.length>0 && this.error.password===false))
        return "hide";
      return "error";
    },
  },
  methods: {
    checkForm(){
      if(this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email ="이메일 형식이 아닙니다";
      else this.error.email=false;
      if(this.password.length >= 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문, 숫자 포함 8 자리 이상이어야 합니다";
      else this.error.password = false;
    },
    onSubmit(event){
      event.preventDefault();
      localStorage.setItem("access-token", "");
      const user={
        userId: this.email,
        userPw: this.password
      };
      login(
        user,
        (res)=>{
          console.log(res.data.user);

          const token = res.data['auth-token'];
          if(token){
            localStorage.setItem("access-token", token);
            this.$store.commit("setUserInfo",res.data.user);
            this.$router.push("/home");
          }
          else{
            alert(res.data['message']);
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
@import '../../components/css/style.css';
@import '../../components/css/User/login.module.css';
</style>