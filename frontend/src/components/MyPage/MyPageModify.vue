<template>
  <div id="mypage">
    <div id="back" @click="back()">
      <font-awesome-icon :icon="['fas', 'chevron-left']" size="2x" />
    </div>
    <div id="mpg">
      <div id="content">
        <div class="up">
          <div class="p">
          <p id="txt">프로필을 입력하세요</p>
        </div>
        <div class="ppp">
          <div id="userPicture">
            <input type="file" id="file" ref="file" accept=".jpg, .png, .jpeg" hidden v-on:change="handleFileUpload()" @change="onChangeImages"/>
            <img id="upimg" v-if="imageUrl==''||imageUrl==null" src="../../assets/person.jpg"/>
            <img id="upimg" v-else :src="imageUrl"/>
          </div>
          <div class="ch">
            <p class="ptx" @click="onClickImageUpload()">프로필 사진 변경</p> 
            <p class="ptx" style="color:red" @click="delPicture()">[삭제]</p>
          </div>
        </div>
      
        <div class="fom">
          <form class="change-form">
          <label class="label" for="userName">닉네임</label><br />
          <input
            id="userName"
            name="userName"
            placeholder="닉네임을 입력하세요."
            v-model="userInfo.userName"
            type="text"
          />
          <b-button id="check" :variant="this.idCheck ? 'outline-success' : 'outline-secondary'" @click="idCheckFunc" >중복체크</b-button>
           <br />

          <label class="label" for="password">비밀번호</label><br />
          <input
            id="password"
            name="password"
            v-model="password"
            placeholder="변경할 비밀번호를 입력하세요."
            type="password"
          />
           <p class="error" v-if="error.password">{{error.password}}</p>
          <br />
          <label class="label" for="feedName">피드명</label><br />
          <input
            id="feedName"
            name="feedName"
            v-model="userInfo.feedName"
            placeholder="피드명을 입력하세요.(10자 이내)"
            type="text"
          /><br />
          <label class="label" for="introduction ">소개말</label><br />
          <input
            id="introduction"
            name="introduction"
            v-model="userInfo.introduction"
            placeholder="소개말을 입력하세요.(30자이내)"
            type="text"
          />
        </form>
        <div id="favorite"> <!-- myTag  선택한 취향 보여주기 갱신~🎈 myTag -->
          <p class="tag1" v-for="(tag,idx) in userInfo.myTag" :key="idx">{{tag}}</p>
        </div>
        </div>
        </div>
        
        <!-- 수정완료 후 메인 -->
        <div id="btn">
            <button id="changeFinish" @click="$bvModal.show('pos-check-modal2')">완료</button>
            <b-modal id="pos-check-modal2" modal-class="pos-check-modal2" hide-header hide-footer centered size="sm" >
              <div class="pos-check-modal-body">
                <div class="pos-check-title">
                  수정을 완료하시겠습니까?
                </div>
                <button class="pos-check-yess-button" @click="update()">예</button>
                <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal2')">아니오</button>
              </div>
            </b-modal>
        </div>

        <div id="next">
          <font-awesome-icon
            :icon="['fas', 'chevron-circle-right']"
            size="3x" :style="{ color: '#7d5bf4' }"
            @click="favChangeGO()"
          />
        </div>
      </div>
      
    </div>
    <!-- 유저 데이터 삭제 후 로그인 페이지 -->
      <div class="example">
        <p id="out" @click="$bvModal.show('pos-check-modal')">탈퇴하기</p>
        <b-modal id="pos-check-modal" modal-class="pos-check-modal" hide-header hide-footer centered size="sm" >
          <div class="pos-check-modal-body">
            <div class="pos-check-title">
              탈퇴하시겠습니까?
            </div>
            <button class="pos-check-yes-button" @click="out()">예</button>
            <button class="pos-check-no-button" @click="$bvModal.hide('pos-check-modal')">아니오</button>
          </div>
        </b-modal>

        
      </div>
  </div>
</template>

<script scope>
 import http from "@/util/http-common";
import PV from "password-validator";
export default {
  name: "MyPageModify",
  data: () =>{
    return{
      userInfo:{
        userImg: "",
        userName: "",
        userPw:"",
        feedName:"",
        introduction:"",
      },
      nickname:"",
      imageUrl:"",
      idCheck: false,
      password:"",
      passwordSchema: new PV(),
      error:{
        password: false,
      },
      file:""
    }
  },
  created(){
    this.userInfo =  this.$store.getters.getUser;
    this.password = this.userInfo.userPw;
    this.imageUrl = this.userInfo.userImg;
    this.nickname = this.userInfo.userName;

    // console.log(this.userInfo);
    // console.log("img:"+this.imageUrl);
            console.log("nickname : "+this.nickname);
            console.log('----------------------------')
            console.log("userInfo : "+this.userInfo.userName);
            console.log('----------------------------')
            console.log("getters : "+this.$store.getters.getUser.userName);
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
    }  
  },
  methods: {
    idCheckFunc() {
      // console.log(this.userInfo.userName);
      http
        .get(`/api/user/checkname/${this.userInfo.userName}`)
        .then(({ data }) => {
          if (!data) { // db에 닉네임이 있으면 false
            alert('중복되는 닉네임 입니다. 다른 닉네임을 입력해주세요!');
            this.idCheck = false;
          } else if (data) { // 없으면 true
            alert('사용가능한 닉네임 입니다.');
            this.idCheck = true;
            console.log("nickname : "+this.nickname);
            console.log('----------------------------')
            console.log("userInfo : "+this.userInfo.userName);
            console.log('----------------------------')
            console.log("getters : "+this.$store.getters.getUser.userName);
          }
        })
        .catch((err) => {
          console.log(err);
        });
      
    },
    favChangeGO: function(){
      // 취향 수정하러 가기
      this.$router.push('/mypage/myfavorite');
    },
    out:function(){
      // 유저 데이터 삭제
      // console.log("아이디:"+this.userInfo.userId);
      http
        .delete(`/api/user/`+this.userInfo.userId)
        .then(({ data }) => {
          if (!data) {
            alert('탈퇴에 실패하였습니다.');
          } else if (data) {
            // 모달창 넣기 -> 한번더 확인 
            // alert('탈퇴되었습니다.');
            this.$router.push("/login")
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    onClickImageUpload() {
      this.$refs.file.click();
    },
    update:function(){
      this.userInfo.userPw = this.password;
      this.userInfo.userImg = this.imageUrl;

      const formData = new FormData();
      formData.append("file", this.file);
      formData.append("user", new Blob([JSON.stringify(this.userInfo)], { type: "application/json" }));
      
      // console.log("file : "+this.file);
      // console.log("userImg : "+this.userInfo.userImg);

      if(this.nickname != this.userInfo.userName){ // 닉 변경을 하려면 중복체크 하도록 
        if(!this.idCheck){
         alert("닉네임 중복확인 후 진행해주세요.")
        }else{
            http.
            put(`/api/user`, formData,{
                    headers: {
                        "Content-Type": `multipart/form-data`,
                    }
                  })
            .then(({data}) => {
                if(data != 'fail'){
                  // alert("수정이 완료되었습니다.");
                  // console.log("store: "+this.$store);
                  this.userInfo.userImg = data; // 리턴받은 url 넣기 
                  this.$store.commit('setUserInfo',this.userInfo);
                  this.$router.push("/home")
                }else{
                  alert("회원정보 수정에 실패하였습니다.");
                }
              }).catch((err) => {
                console.log(err);
              });
        }
      }else{ // 닉 변경안하고 싶으면 그냥 통과
        http.
          put(`/api/user`, formData,{
                headers: {
                    "Content-Type": `multipart/form-data`,
                }
              })
          .then(({data}) => {
            if(data != 'fail'){
              // alert("수정이 완료되었습니다.");
              // console.log("store: "+this.$store);
              this.userInfo.userImg = data;
              this.$store.commit('setUserInfo',this.userInfo);
              this.$router.push("/home")
            }else{
              alert("회원정보 수정에 실패하였습니다!!!.");
            }
          }).catch((err) => {
            console.log(err);
          });
      }
      
    },
    handleFileUpload(){
        this.file = this.$refs.file.files[0];
      },
       onChangeImages(e) {
            console.log(e.target.files)
            const file = e.target.files[0];
            this.imageUrl = URL.createObjectURL(file);
        },
    back:function(){ // 수정 폼 뒤로가기는 메인
      this.$router.push('/home');
    },
    checkForm(){
      if(this.password.length >= 0 && !this.passwordSchema.validate(this.password))
        this.error.password = "영문, 숫자 포함 8 자리 이상이어야 합니다";
      else this.error.password = false;
    },
    delPicture(){
      http
        .put(`/api/user/`+this.userInfo.userId)
        .then(({ data }) => {
          if (!data) {
            alert('프사삭제 실패하였습니다.');
          } else if (data) {
            // alert('프사삭제 성공');
            this.userInfo.userImg = ""; 
            this.imageUrl = ""; //미리보기 삭제 
            this.$store.commit('setUserInfo',this.userInfo);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    }
  },
};
</script>

<style scoped>
@import "../../components/css/style.css";
/* ---------------- 모달 css ---------------- */
.pos-check-yes-button {
  color:white;
  background-color:#CB3E47; 
  border-radius:10px;
  font-size:14px;
  width:100px;
  height:30px;
}
.pos-check-yess-button {
  color:white;
  background-color:#9279e9; 
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
::v-deep .pos-check-modal2 > .modal-dialog >.modal-content{
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
/* ------------------------------ 좁아질 때 반응형 ------------------------------ */
@media screen and (max-width: 1024px) {
    #mypage {
    width: 380px;
    height: 100%;
    text-align: center;
    margin: 0 auto;
  }
  #back {
    margin-top: 20px;
    padding-left: 25px;
    width: 30px;
    float: left;
    display: inline-block;
  }
  #mpg {
    margin-top: 65px;
    display: inline-block;
    margin-bottom: 10px;
  }
  #txt {
    clear: both;
    float: left;
    font-size: 28px;
    font-weight: bold;
  }
  #content {
    margin-top: 20px;
    width: 300px;
    text-align: center;
    margin: 0 auto;
  }
  #userPicture {
    clear: both;
    display: block;
    padding-bottom: 14px;
    text-align: center;
    margin: 0 auto;
    width: 93px;
    height: 93px;
    border-radius: 50%;
    /* border: 3px solid blueviolet; */
  }
  .label{
    margin-top: 10px;
  }
  #upimg{
    width: 93px;
    height: 93px;
    border-radius: 50%;
    object-fit: cover;
  }
  .ptx {
    color: var(--color-medium-purple);
    font-size: 11px;
    margin-top: 10px;
    text-align: center;
    display: inline-block;
    margin-left: 10px;
  }
  .change-form {
    margin-top: 5px;
    text-align: left;
  }
  #userName {
    outline: none;
    border-bottom: 2px solid;
    width: 70%;
    margin-bottom: 5px;
    /* font-size: var(--font-txt-md); */
  }
  #feedName,
  #introduction,
  #password  {
    outline: none;
    border-bottom: 2px solid;
    width: 100%;
    margin-bottom: 5px;
    /* font-size: var(--font-txt-md); */
  }
  #check {
    float: right;
    margin-left: 7px;
    margin-right: 4px;
    padding: 6px;
    font-size: 14px;
    border-radius: 5px;
  }
  #favorite {
    height: 45px;
  }
  #favorite>p{
    font-size: var(--font-tlt-2xs);
    margin-top: 10px;
    display: inline-block;
    border: 1px solid gray;
    padding: 4px;
    margin-left: 5px;
    border-radius: 10px;
    float: left;
  }
  #favorite>.tag1{
    color: var(--color-purple);
  }
  #changeFinish {
    color: white;
    background-image: var(--background-image-purple-gd);
    border-radius: 20px;
    font-size: var(--font-tlt-sm);
    width: 160px;
    height: 39px;
  }
  #btn{
    margin-left: 60px;
    float: left;
    width: 160px;
    height: 39px;
    padding-top: 10px;
    
  }
  #next {
    float: right;
  }
  #out{
    color: red;
    font-size: 12px;
    text-align: right;
    margin-right: 41px;
    padding-bottom: 50px;
  }
  .error{
    font-size: 12px;
    color: var(--color-red);
    text-align: right;
  }
}
/* ------------------------------ 넓어질 때 반응형 ------------------------------ */
@media screen and (min-width: 1024px) {
  #mypage {
  width: 760px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
  }
  #back {
    margin-top: 20px;
    padding-left: 25px;
    width: 30px;
    float: left;
    display: inline-block;
  }
  #mpg {
    margin-top: 65px;
    display: inline-block;
    margin-bottom: 20px;
  }
  .p{
    display: block;
  }
  #txt {
    clear: both;
    text-align: left;
    font-size: 30px;
    font-weight: bold;
    margin-bottom: 30px;
  }
  #content {
    margin-top: 20px;
    width: 700px;
    text-align: center;
    margin: 0 auto;
  }
  .ppp{
    padding-left: 20px;
    padding-right: 30px;
    height: 100%;
    display: inline-block;
    float: left;
  }
  #userPicture {
    clear: both;
    display: inline-block;
    padding-bottom: 14px;
    display: flex;
       flex-direction: column;
       justify-items: center;
    /* text-align: center; */
    /* margin: 0 auto; */
    width: 130px;
    height: 130px;
    border-radius: 50%;
    /* border: 3px solid blueviolet; */
  }
  .fom{
    float: right;
  }
  .label{
    margin-top: 10px;
  }
  #upimg{
    width: 130px;
    height: 130px;
    border-radius: 50%;
    object-fit: cover;
  }
  .ptx {
    color: var(--color-medium-purple);
    font-size: 11px;
    margin-top: 10px;
    text-align: center;
    display: inline-block;
    margin-left: 10px;
    flex-direction: column;
    justify-items: center;
  }
  .ch{
    display: inline-block;
    display: flex;
    justify-items: center;
  }
  .change-form {
    margin-top: 5px;
    text-align: left;
    width: 500px;
  }
  #userName {
    outline: none;
    border-bottom: 2px solid;
    width: 80%;
    margin-bottom: 5px;
    /* font-size: var(--font-txt-md); */
  }
  #feedName,
  #introduction,
  #password  {
    outline: none;
    border-bottom: 2px solid;
    width: 100%;
    margin-bottom: 5px;
    /* font-size: var(--font-txt-md); */
  }
  #check {
    float: right;
    margin-left: 7px;
    margin-right: 4px;
    padding: 6px;
    font-size: 14px;
    border-radius: 5px;
  }
  #favorite {
    height: 45px;
  }
  #favorite>p{
    font-size: var(--font-tlt-2xs);
    margin-top: 10px;
    display: inline-block;
    border: 1px solid gray;
    padding: 4px;
    margin-left: 5px;
    border-radius: 10px;
    float: left;
  }
  #favorite>.tag1{
    color: var(--color-purple);
  }
  #changeFinish {
    color: white;
    background-image: var(--background-image-purple-gd);
    border-radius: 20px;
    font-size: var(--font-tlt-sm);
    width: 200px;
    height: 39px;
    margin-left: 280px;
  }
  #btn{
    margin-left: 60px;
    float: left;
    width: 160px;
    height: 39px;
    padding-top: 10px;
    
  }
  #next {
    float: right;
  }
  #out{
    color: red;
    font-size: 12px;
    text-align: right;
    margin-right: 41px;
    padding-bottom: 50px;
  }
  .error{
    font-size: 12px;
    color: var(--color-red);
    text-align: right;
  }
}
</style>
