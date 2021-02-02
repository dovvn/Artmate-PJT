<template>
  <div class="fiddiv">
    <div id="back" @click="back()">
      <font-awesome-icon :icon="['fas', 'chevron-left']" size="2x" />
    </div>
 <!-- 처음 피드 메뉴바 또는 스크롤 맨위에 올리면 스르륵 변하기 -->
    <div id="feednav1" >
        <p id="txt">NEWSFEED</p>
        
        <div id="nav" class="nav">
            <router-link to="/feed" class="div2" id="follow">팔로우 피드 </router-link> | 
            <router-link to="/feed/bookmark" class="div2" id="mark">북마크 </router-link>
        </div>
    </div>

 <!-- 스크롤 내리면 변하는 메뉴바  -->
      <!-- txt2 글씨 보여주고 메뉴 변하기  -->
    <!-- <div id="feednav2">
        <div id="nav" class="nav">
            <router-link to="/feed" class="div22" id="follow">팔로우 피드 </router-link> | 
            <router-link to="/feed/bookmark" class="div22" id="mark">북마크 </router-link>
        </div>
    </div> -->

  </div>
</template>

<script>
export default {
  
  methods:{
    back:function(){ 
       this.$router.push('/home');
    }
  }
}
</script>

<style>
.fiddiv {
  width: 380px;
  height: 100%;
  text-align: center;
  margin: 0 auto;
}
#back {
  padding-top: 30px;
  padding-left: 25px;
  float: left;
  width: 30px;
  display: inline-block;
}
#feednav1{
    text-align: center;
    margin: 0 auto;
    height: 210px;
    margin-bottom: 17px;
}
#feednav1>#txt {
  clear: both;
  float: left;
  font-size: 28px;
  font-weight: bold;
  margin-left: 50px;
  padding-bottom: 30px;
}
.nav{
    padding-top: 20px;
    clear: both;
    float: left;
    margin-left: 50px;
    color: #CCCCCC;
}
#follow, #mark{
  text-decoration: none;
  color: #CCCCCC;
}

#feednav2{
    text-align: center;
    margin: 0 auto;
    height: 50px;
    margin-bottom: 17px;
}
#txt2 {
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  padding-top: 30px;
}

</style>