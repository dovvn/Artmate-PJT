<template>
  <div id="JoinSuccess" class="JoinSuccess">
    <!-- <img src="../../assets/join_background.jpg" alt="" class="bg"> -->
    <div class="wrap">
      <header class="success_header">
        <span>회원가입 완료</span>
      </header>
      <div class="success_text">
        <p>{{this.$route.params.userId}}으로</p>
        <p>본인 인증 메일이 전송되었습니다.</p>
        <p>본인 인증 후 로그인 해주시기 바랍니다.</p>
      </div>
      <div class="success_btn_group">
        <button @click="onClickMail" class="success_mail_btn white_btn">메일함 이동</button>
        <button @click="onClickLogin" class="success_login_btn purple_btn">
          로그인
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name:"JoinSuccess",
  data: ()=>{
    return{
      mail:'',
    }
  },
  created(){
    // params 데이터 없으면 유저정보 입력 페이지로 푸시
  },
  methods:{
    onClickMail(){
      const idx = this.$route.params.userId.indexOf('@');
      const url = this.$route.params.userId.slice(idx,);
      window.open(`https://www.${url}`);
    },
    onClickLogin(){
      this.$router.push({name:"Login"});
    }
  }
}
</script>

<style scoped src="../../components/css/User/join_success.css">
</style>