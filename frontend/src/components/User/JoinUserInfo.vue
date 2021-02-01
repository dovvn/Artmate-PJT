<template>
  <div id="JoinUserInfo">
    <!-- <div class="bg">
      <img src="../../assets/join_background.jpg" alt="">
    </div> -->
    <div class="wrap">
      <div @click="onBack" class="back_btn">
        <font-awesome-icon icon="chevron-left"/>
      </div>
      <div class="join_text">
        <span>회원가입</span>
      </div>
      <form @submit="onSubmit" class="join_form">
        <label class="username_label" for="username">닉네임</label>
        <div class="username_input_box">
          <input 
            id="username"
            v-model="username"
            class="username_input" 
            type="text" 
            placeholder="닉네임을 입력해주세요."
          >
          <button @click="onCheckName" class="same_check" type="button">중복체크</button>
        </div>
        <div class="full_bar"></div>
        <br>
        <div class="userid_input_box">
          <label class="userid_label" for="userid">아이디(이메일)</label>
          <input 
            id="userid"
            v-model="email"
            type="text"
            class="userid_input"
            placeholder="아이디를 입력해주세요."
          >
          <div :class="isEmail">
            <font-awesome-icon v-if="error.email" icon="times"/>
            <font-awesome-icon v-else icon="check"/>
          </div>
        </div>
        <div class="full_bar"></div>

        <p :class="isEmailMessage">{{error.email}}</p> 
        <br>
        <div class="userpw_input_box">
        <label class="userpw_label" for="userpw">비밀번호</label><br>
          <input 
            id="userpw"
            v-model="password"
            type="password"
            class="userpw_input" 
            placeholder="비밀번호(8자이상)를 입력해주세요." 
            >
          <div :class="isPassword">
            <font-awesome-icon v-if="error.password" icon="times"/>
            <font-awesome-icon v-else icon="check"/>
          </div>
        </div>
        <div class="full_bar"></div>
        <p :class="isPasswordMessage">{{error.password}}</p>
        <br>
        <div class="userpw_check_input_box">
          <label class="userpw_check_label" for="userpw_check">비밀번호 확인</label>
          <input 
            id="userpw_check"
            v-model="passwordCheck"
            type="password"
            class="userpw_check_input" 
            placeholder="비밀번호를 다시 입력해주세요." 
          >
          <div :class="isPasswordCheck">
            <font-awesome-icon v-if="error.passwordCheck" icon="times"/>
            <font-awesome-icon v-else icon="check"/>
          </div>
        </div>
        <div class="full_bar"></div>
        <!-- <font-awesome-icon class="login-form-input_pw_error_icon" icon="times-circle"/> -->
        <p :class="isPasswordCheckMessage">{{error.passwordCheck}}</p>
        <button :class="classObject">
          입력 완료
        </button>
        <!-- <button type="button" @click="onDelete">탈퇴</button> -->
      </form>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import {checkName} from '@/api/user.js';
// import axios from 'axios';
export default {
  name: "JoinUserInfo",
  data: () => {
    return {
      username: "",
      email: "",
      password: "",
      passwordCheck: "",
      passwordSchema: new PV(),
      error:{
        username: '닉네임을 입력해주세요.',
        email: '이메일을 입력해주세요.',
        password: '비밀번호를 입력해주세요.',
        passwordCheck: '비밀번호를 한 번 더 입력해주세요.',
      },
      isChecked:false,
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
    username: function() {
      this.isChecked=false;
      this.error.username='닉네임 중복여부를 확인해주세요.';
    },
    password: function() {
      this.checkForm();
    },
    passwordCheck: function() {
      this.checkForm();
    },
    email: function() {
      this.checkForm();
    }
  },
  computed: {
    classObject: function(){
      if(this.email.length>0 && this.password.length>0 && this.passwordCheck.length>0 && this.error.password===false && this.error.email===false && this.error.passwordCheck===false)
        return "purple_btn join_form_btn active"
      return "purple_btn join_form_btn inactive"
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
    isPasswordCheck: function(){
      if(this.passwordCheck==='') return "hide";
      if(this.error.passwordCheck===false)
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
    isPasswordCheckMessage: function(){
      if(this.passwordCheck==='' || (this.passwordCheck.length>0 && this.error.passwordCheck===false))
        return "hide";
      return "error";
    },
  },
  methods: {
    // onDelete(){
    //   axios({
    //     method: 'delete',
    //     url: 'http://localhost:7777/api/user/jihyeong16@naver.com',
    //     // data: 'jihyeong16@naver.com'
    //   })
    //   .then((res)=>{
    //     console.log(res.data);
    //   })
    //   .catch((err)=>{
    //     console.error(err.data);
    //   })
    // },
    checkForm(){
      if(this.email.length >= 0 && !EmailValidator.validate(this.email))
        this.error.email ="이메일 형식이 아닙니다";
      else this.error.email=false;

      if(this.password.length >= 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문, 숫자 포함 8 자리 이상이어야 합니다";
      else this.error.password = false;

      if(this.passwordCheck.length >= 0 && !this.passwordSchema.validate(this.passwordCheck))
        this.error.passwordCheck = "영문, 숫자 포함 8 자리 이상이어야 합니다";
      else if(this.passwordCheck.length >= 0 && this.password!==this.passwordCheck)
        this.error.passwordCheck = "비밀번호가 일치하지 않습니다."
      else this.error.passwordCheck = false;
    },
    onCheckName(){
      checkName(
        this.username,
        (res) => {
          if(res.data===true){
            alert('사용가능한 닉네임입니다.');
            this.error.username=false;
            this.isChecked=true;
          }
          else {
            alert('닉네임이 중복됩니다. \n다른 닉네임을 사용해주세요.');
            this.error.username = '닉네임이 중복됩니다. \n다른 닉네임을 사용해주세요.';
          }
        },
        (err) => {
          console.error(err);
        }
      )
    },
    onBack(){
      history.back();
    },
    onSubmit(event){
      event.preventDefault();
      if(this.error.username){
          alert(this.error.username);
        // else alert('닉네임 중복여부를 확인해주세요.');
      }
      else if(this.error.email){
        alert(this.error.email);
      }
      else if(this.error.password){
        alert(this.error.password);
      }
      else if(this.error.passwordCheck){
        alert(this.error.passwordCheck);
      }
      else{
        const user={
          userName: this.username,
          userId: this.email,
          userPw: this.password,
        }
        this.$router.push({
          name:"JoinUserFavorite",
          params:{
            ...user,
          }
        })
      }
    }
  }
}
</script>

<style scoped>
@import '../../components/css/style.css';
@import '../../components/css/User/join.css';
</style>