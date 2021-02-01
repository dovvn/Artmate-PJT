<template>
  <div class="findSuccess">
    <!-- <img src="../../assets/join_background.jpg" alt="" class="bg"> -->
    <div class="wrap">
      <header class="success_header">
        <span>비밀번호 찾기</span>
      </header>
      <div class="success_text">
        <p>{{this.$route.params.userId}} 으로</p>
        <p>임시 비밀번호가 발송되었습니다.</p>
      </div>
      <div class="success_btn_group">
        <button @click="onClickMail" class="success_mail_btn white_btn">메일함 이동</button>
        <button @click="onClickLogin" class="success_login_btn purple_btn">
          <router-link :to="{name:'Login'}">로그인</router-link>  
        </button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name:"JoinSuccess",
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

<style scoped>
@import '../../components/css/style.css';
@import '../../components/css/User/join_success.css';
</style>