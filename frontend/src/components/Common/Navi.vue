<template>
  <nav class="navi">
    <div class="navi_left">
      <!-- <font-awesome-icon @click="onClickBack" :class="isBack" icon="chevron-left"/> -->
      <!-- <font-awesome-icon @click="onClickMenu" class="navi_item" icon="bars"/> -->
      <div ref="sideNav" class="bm-menu">
            <!-- 누르면 나올 리스트  -->
            <nav class="bm-item-list">
              <div class="r_list">
                <p class="r_keyword">영화</p>
                <p class="r_keyword">현대예술</p>
                <p class="r_keyword">유화</p>
              </div>
              <div class="proContent">
                <p class="nickname">{{userInfo.userName}}</p>
                <p id="uid">{{userInfo.userId}}</p>
              </div>
              <div class="userPicture">
                <img class="proimg" v-if="imageUrl==''||imageUrl==null" src="../../assets/person.jpg"/>
                <img class="proimg" v-else :src="imageUrl"/>
                
                <p id="ptxx" @click="mypage"> <font-awesome-icon id="modifyicon" :icon="['fas', 'edit']" size="sm"/>내 정보 수정</p> 
              </div>
                  <ul id="mlist">
                  <!-- <li><a class="ml" id="Home"  href="/home">Home</a></li> -->
                  <li><router-link class="ml" id="Home" to="/home">HOME</router-link></li>
                  <li><router-link class="ml" id="about"  to="/about">ABOUT</router-link></li>
                  <li><router-link class="ml" id="exhibition"  to="#">EXHIBITION</router-link></li>
                  <li><router-link class="ml" id="search" to="/search">SEARCH</router-link></li>
                  <li><router-link class="ml" id="around"  to="#">AROUND</router-link></li>
                  <li><router-link class="ml" id="feed"  to="/feed">NEWSFEED</router-link></li>
                  <li><router-link class="ml" id="scrapbook"  to="#">SCRAPBOOK</router-link></li>
                  <li><a class="ml" id="logout" @click="logout">LOGOUT</a></li>
                </ul>
            </nav>
            <!-- X 버튼 부분 -->
            <span class="bm-cross-button cross-style" @click="closeMenu" :class="{ hidden: !crossIcon }">
                <span v-for="(x, index) in 2" :key="x" class="bm-cross" :style="{ position: 'absolute', width: '3px', height: '14px',transform: index === 1 ? 'rotate(45deg)' : 'rotate(-45deg)'}">
                </span>
            </span>
        </div>
        <!-- 아이콘 누르면 반응하는 부분 -->
        <div ref="bmBurgerButton" class="bm-burger-button" @click="openMenu" :class="{ hidden: !burgerIcon }">
            <span class="bm-burger-bars line-style" :style="{top:20 * (index * 2) + '%'}" v-for="(x, index) in 3" :key="index"></span>
        </div>
    </div>
    <div class="navi_right">
      <font-awesome-icon @click="onClickSearch" class="navi_item" size='sm' icon="search"/>
      <font-awesome-icon @click="onClickAlarm" class="navi_item" size='sm' icon="bell"/>
      <!-- <img @click="onClickProfile" src="../../assets/profile_ex.jpg" alt="" class="profile navi_item"> -->
      <img class="profile navi_item" @click="onClickProfile" v-if="imageUrl==null||imageUrl==''" src="../../assets/person.jpg"/>
      <img class="profile navi_item" @click="onClickProfile" v-else :src="imageUrl"/>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
        return {
          userInfo:{
            userImg: "",
            userName: "",
            userId:"",
            // myTag:[]
          },
          imageUrl:"",
          isSideBarOpen: false
        };
      },
      
      props: {
        isOpen: {
          type: Boolean,
          required: false
        },
        right: {
          type: Boolean,
          required: false
        },
        width: {
          type: [String],
          required: false,
          default: '300'
        },
        disableEsc: {
          type: Boolean,
          required: false
        },
        noOverlay: {
          type: Boolean,
          required: false
        },
        onStateChange: {
          type: Function,
          required: false
        },
        burgerIcon: {
          type: Boolean,
          required: false,
          default: true
        },
        crossIcon: {
          type: Boolean,
          required: false,
          default: true
        },
        disableOutsideClick: {
          type: Boolean,
          required: false,
          default: false
        },
        closeOnNavigation: {
          type: Boolean,
          required: false,
          default: false
        }
      },

  methods:{
    mypage(){
      this.$router.push('/mypage');
    },
    onClickBack(){
      console.log(window.history);
      console.log();
      history.back();
    },
    // onClickMenu(){
    //   // 슬라이드 열리기
    // },
    onClickAlarm(){
      this.$router.push('/alarm');
    },
    onClickSearch(){
      this.$router.push('/search');
    },
    onClickProfile(){
      this.$router.push('/myfeed');
    },
    logout(){
      // 로그아웃 구현 !!! 🎈 
    },
    openMenu() {
          this.$emit('openMenu');
          this.isSideBarOpen = true;

          if (!this.noOverlay) {
            document.body.classList.add('bm-overlay');
          }
          if (this.right) {
            this.$refs.sideNav.style.left = 'auto';
            this.$refs.sideNav.style.right = '0px';
          }
          this.$nextTick(function() {
            this.$refs.sideNav.style.width = this.width
              ? this.width + 'px'
              : '300px';
          });
        },

        closeMenu() {
          this.$emit('closeMenu');
          this.isSideBarOpen = false;
          document.body.classList.remove('bm-overlay');
          this.$refs.sideNav.style.width = '0px';
        },

        closeMenuOnEsc(e) {
          e = e || window.event;
          if (e.key === 'Escape' || e.keyCode === 27) {
            this.closeMenu();
          }
        },
        documentClick(e) {
          let element = this.$refs.bmBurgerButton;
          let target = null;
          if (e && e.target) {
            target = e.target;
          }

          if (
            element &&
            element !== target &&
            !element.contains(target) &&
            !this.hasClass(target,'bm-menu') &&
            this.isSideBarOpen &&
            !this.disableOutsideClick
          ) {
            this.closeMenu();
          } else if (
            element &&
            this.hasClass(target,'bm-menu') &&
            this.isSideBarOpen &&
            this.closeOnNavigation
          ) {
            this.closeMenu();
          }
        },
        hasClass(element, className) {
          do {
            if (element.classList && element.classList.contains(className)) {
              return true;
            }
            element = element.parentNode;
          } while (element);
          return false;
        },
  },
  
  mounted() {
        if (!this.disableEsc) {
          document.addEventListener('keyup', this.closeMenuOnEsc);
        }
      },
      created: function() {
        document.addEventListener('click', this.documentClick);
        this.userInfo =  this.$store.getters.getUser;
        this.imageUrl = this.userInfo.userImg;
      },
      destroyed: function() {
        document.removeEventListener('keyup', this.closeMenuOnEsc);
        document.removeEventListener('click', this.documentClick);
      },
      watch: {
        isOpen: {
          deep: true,
          immediate: true,
          handler(newValue, oldValue) {
            this.$nextTick(() => {
              if (!oldValue && newValue) {
                this.openMenu();
              }
              if (oldValue && !newValue) {
                this.closeMenu();
              }
            });
          }
        },
        right: {
          deep: true,
          immediate: true,
          handler(oldValue, newValue) {
            if (oldValue) {
              this.$nextTick(() => {
                this.$refs.bmBurgerButton.style.left = 'auto';
                this.$refs.bmBurgerButton.style.right = '36px';
                this.$refs.sideNav.style.left = 'auto';
                this.$refs.sideNav.style.right = '0px';
                document.querySelector('.bm-burger-button').style.left = 'auto';
                document.querySelector('.bm-burger-button').style.right = '36px';
                document.querySelector('.bm-menu').style.left = 'auto';
                document.querySelector('.bm-menu').style.right = '0px';
                document.querySelector('.cross-style').style.right='250px';
              });
            }
            if (newValue) {
              if (
                this.$refs.bmBurgerButton.hasAttribute('style')
              ) {
                this.$refs.bmBurgerButton.removeAttribute('style');
                this.$refs.sideNav.style.right = 'auto';
                document
                  .querySelector('.bm-burger-button')
                  .removeAttribute('style');
                document.getElementById('sideNav').style.right = 'auto';
                document.querySelector('.cross-style').style.right='0px';
              }
            }
          }
        }
      }
    
}
</script>

<style lang="scss">
@import '../css/style.css';
@import '../css/navi.css';
   html {
      height: 100%;
    }
    .bm-burger-button {
      position: absolute;
      width: 20px;
      height: 17px;
      left: 36px;
      top: 36px;
      cursor: pointer;
    }
    .bm-burger-button.hidden {
      display: none;
    }
    .bm-burger-bars {
      background-color: #373a47;
    }
    .line-style {
      position: absolute;
      height: 20%;
      left: 0;
      right: 0;
    }
    .cross-style {
      position: absolute;
      top: 12px;
      right: 2px;
      margin-right: 10px;
      cursor: pointer;
    }
    .bm-cross {
      margin-top: 20px;
      background: #bdc3c7;
    }
    .bm-cross-button {
      height: 24x;
      width: 24px;
    }
    .bm-cross-button.hidden {
      display: none;
    }
    .bm-menu {
      height: 100%; /* 100% Full-height */
      width: 0; /* 0 width - change this with JavaScript */
      position: fixed; /* Stay in place */
      z-index: 1000; /* Stay on top */
      top: 0;
      left: 0;
      background-image : url(../../assets/menu_img.png); 
      background-repeat: no-repeat;
      background-position: fixed;
      background-size: cover;
      overflow-x: hidden; /* Disable horizontal scroll */
      padding-top: 45px; /* Place content 60px from the top */
      transition: 0.5s; /*0.5 second transition effect to slide in the sidenav*/
      -ms-overflow-style: none; /* IE and Edge */
      scrollbar-width: none; /* Firefox */
    }
    .bm-menu::-webkit-scrollbar {
        display: none; /* Chrome, Safari, Opera*/
    }
    .bm-item-list {
      color: white;
      margin-left: 10%;
      font-size: 20px;
    }
    .bm-item-list > * {
      /* display: flex; */
      text-decoration: none;
      /* padding: 0.7em; */
    }
    .bm-item-list > * > span {
      margin-left: 10px;
      font-weight: 700;
      color: white;
    }
    #mlist{
      // margin-top: 80px;
      list-style:none;
      float: right;
      text-align: right;
      padding-right: 30px;
    }
    #mlist>li{
        padding-bottom: 15px;
        font-size: 21px;
    }
    #mlist>li>a{
      color: white;
      text-decoration: none;
    }
     #mlist>li>a:visited { text-decoration: none;}
     #mlist>li>a:active {text-decoration: none;}
     #mlist>li>a:hover {text-decoration: none; font-weight: bold; font-size: 25px; color: #EEEEEE;}

    .userPicture{
       width: 130px;
       display: flex;
       flex-direction: column;
       justify-items: center;
    }
    .proimg{
      display: inline-block;
      width: 130px;
      height: 130px;
      border-radius: 50%;
    }
    #ptxx{
      text-align: center;
      margin: 0 auto;     
      display: inline-block;
      color: #CCCCCC;
      padding: 3px;
      font-size: 14px;
    }
    .proContent{
      width: 160px;
       display: flex;
       flex-direction: column;
       justify-items: center;
       float: right;
       margin-right: 10px;
    }
    .proContent>p{
      margin: 0;
    }
    .nickname{
      display: inline-block;
      word-break:break-all;
      font-weight: bold;
      font-size: 20px;
    }
    #uid{
      display: inline-block;
      word-break:break-all;
      font-size: 16px;
    }
    .r_list{
      margin-bottom: 35px;
    }
    .r_keyword{
      display: inline-block;
      margin-right: 10px;
      border: 1px solid white;
      border-radius: 30%;
      font-size: 14px;
      padding: 5px;
    }
    #modifyicon{
      margin-right: 5px;
    }
</style>