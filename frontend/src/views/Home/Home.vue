<template>
  <div class="home wrap2">
    <Navi id="navi"/>
    <div class="online_bg">
      <div class="home_logo">
        <img class="home_logo_img" src="../../assets/main/logo_img.png" alt="">
        <span class="home_logo_text">ARTMATE</span>
      </div>
      <!-- Online -->
      <div class="online_box">
        <div class="online_tlt">온라인 전시회</div>
        <carousel
          :items=1
          :autoplay="true"
          :autoplayTimeout=3000
          :autoplayHoverPause="true"
          :nav=false
          :loop=true
          :dots=false
          :margin=10
        >                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
          <div 
            class="online_exhibition"
            v-for="(item,idx) in onlineList"
            :key="idx"
          >
            <img 
              class="online_exhibition_poster"
              :src="item.img"
              alt=""
            >
            <div class="online_exhibition_info">
              <div>
                <p class="online_exhibition_tlt">{{item.title}}</p>
                <p class="online_exhibition_place">{{item.place}}</p>
              </div>
            </div>
          </div>
        </carousel>
        <div class="online_slide">
          

        </div>
      </div>
    </div>
    

    <!-- Recommend -->
    
    <div class="recommend_box">
      <div class="recommend_tlt">Recommend</div>
      <div class="recommend_keyword_list">
        <div class="recommend_keyword">영화</div>
        <div class="recommend_keyword">현대예술</div>
        <div class="recommend_keyword">유화</div>
      </div>
      <div class="test">
        <carousel-3d
          :autoplay=true
          :autoplayHoverPause=true
          :style="carouselStyle"
          :space="carouselSpace"
          :display="3"
          :perspective="0"
          :border="0"
          :width="carouselWidth"
          :height="carouselHeight"
          ref="mycarousel"
          @after-slide-change="onAfterSlideChange"
          @before-slide-change="onBeforeSlideChange"
        >
          <slide  class="slide" v-for="(item, i) in recommendList" :key="i" :index="i">
            <img class="recommend_exhibition_poster a" :src="item.img" alt="" >
            <div class="recommend_exhibition_info b">
              <p class="recommend_exhibition_tlt">{{item.title}}</p>
              <p class="recommend_exhibition_place">{{item.place}}</p>
              <p class="recommend_exhibition_duration">{{item.duration}}</p>
            </div>
          </slide>
        </carousel-3d>
      </div>
    </div>
    <!-- Feed -->
    <div class="feed_box">
      <div class="feed_header">
        <div class="feed_tlt">Popular Feed</div>
        <div class="feed_more_btn">
          <span>더보기 </span>
          <font-awesome-icon class="online_slide_right_btn" icon="chevron-right"/>
        </div>
      </div>
      <div class="feed_message">
        <span>인기있는 피드를 만나보세요 </span>
        <font-awesome-icon class="feed_message_icon" icon="camera"/>
      </div>
      <div class="feed_list">
        <img
          class="feed_item"
          v-for="(image,idx) in images"
          :key="idx"
          :src="image"
          alt=""
        >
      </div>
    </div>

    <!-- Around -->
    <div class="around_box">
      <div class="around_header">
        <div class="around_tlt">Around</div>
        <div class="around_more_btn">
          <span>더보기</span>
          <font-awesome-icon class="online_slide_right_btn" icon="chevron-right"/>
        </div>
      </div>
      <div class="feed_message">
        <span>덕명동 주변 리스트입니다 </span>
        <font-awesome-icon class="feed_message_icon" icon="pallet"/>
      </div>
      <div class="around_list">
        <div 
          class="around_exhibition"
          v-for="(item,idx) in aroundList"
          :key="idx"
        >
          <img :src="item.img" alt="" class="around_exhibition_img">
          <div class="around_exhibition_info">
            <p class="around_exhibition_tlt">
              <font-awesome-icon class="feed_message_icon" icon="leaf"/>{{item.title}}
            </p>
            <p class="around_exhibition_place">
              <font-awesome-icon class="feed_message_icon" icon="map-marker-alt"/>
              {{item.place}}
            </p>
            <p class="around_exhibition_duration">{{item.duration}}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Footer -->
    <footer class="footer_box">
      <div class="footer_tlt">Artmate</div>
      <div class="footer_section">
        <p>상호: 아트메이트 / 대표 : 유진이</p>
        <p>주소: 대전 유성구 동서대로 98-39</p>
      </div>
      <div class="footer_section" >
        <p id="last">Copyright@2021 아트메이트 All Rights Reserved</p>
      </div>
    </footer>
  </div>
</template>

<script defer>
// @ is an alias to /src
import Navi from '@/components/Common/Navi.vue';
import carousel from 'vue-owl-carousel';
import { Carousel3d, Slide } from 'vue-carousel-3d';
document.addEventListener('scroll',()=>{
  // console.log(e);
  const navbar = document.querySelector('#navi');
  const navbarHeight = navbar.getBoundingClientRect().height;
  // console.log(navbar)
  // console.log(navbar.style.background);
  if(window.scrollY > navbarHeight){
    navbar.style.background="white";
  }
  else{
    navbar.style.background="transparent";
  }
})
export default {
  name: 'Home',
  mounted(){
    if(window.innerWidth<=1024){
      this.carouselWidth=120;
      this.carouselHeight=270;
      this.carouselSpace=170;
      this.carouselStyle='margin: 20px 0 0 90px; overflow-x:hidden';
    }
    else{
      this.carouselWidth=200;
      this.carouselHeight=400;
      this.carouselSpace=320;
      this.carouselStyle='margin: 40px 0 0 200px; overflow-x:hidden';
    }
    window.addEventListener('resize',()=>{
      const carousel=document.querySelector('.carousel-3d-slider');
      console.log(carousel)
      if (window.innerWidth<=1024){
          this.carouselWidth=120;
          this.carouselHeight=270;
          this.carouselSpace=170;
          this.carouselStyle='margin: 20px 0 0 90px; overflow-x:hidden';
      }
      else{
          this.carouselWidth=200;
          this.carouselHeight=400;
          this.carouselSpace=320;
          this.carouselStyle='margin: 40px 0 0 200px; overflow-x:hidden';
      }
    });
    this.$refs.mycarousel.$children[0].$slots.default[0].elm.classList.remove('a');
    this.$refs.mycarousel.$children[0].$slots.default[1].elm.classList.remove('b');
  },
  data() {
    return{
      carouselWidth:0,
      carouselHeight:0,
      carouselSpace:0,
      carouselStyle:"",
      onlineList:[
        {
          img:require('../../assets/main/slide1_1.jpg'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide2_1.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide2_2.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
      ],
      recommendList:[
        {
          img:require('../../assets/main/slide2_2.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide1_1.jpg'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide2_2.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide2_3.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
      ],
      images:[
        require('../../assets/main/feed_1.png'),
        require('../../assets/main/feed_2.png'),
        require('../../assets/main/feed_3.png'),
        require('../../assets/main/feed_4.png'),
        require('../../assets/main/feed_5.png'),
        require('../../assets/main/feed_6.png'),
        require('../../assets/main/feed_7.png'),
        require('../../assets/main/feed_8.png'),
        require('../../assets/main/feed_9.png'),
      ],
      aroundList:[
        {
          img:require('../../assets/main/slide1_1.jpg'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide2_2.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide2_2.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
        {
          img:require('../../assets/main/slide2_3.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
      ]
    }
  },
  components: {
    carousel,
    Navi,
    Carousel3d,
    Slide
  },
  methods:{
    onAfterSlideChange(idx){
      const before = idx-1<0 ? 3 : idx-1;
      this.$refs.mycarousel.$children[before].$slots.default[0].elm.classList.add('a');
      this.$refs.mycarousel.$children[before].$slots.default[1].elm.classList.add('b');
      this.$refs.mycarousel.$children[idx].$slots.default[0].elm.classList.remove('a');
      this.$refs.mycarousel.$children[idx].$slots.default[1].elm.classList.remove('b');
    },
    onBeforeSlideChange(idx){
      const next = idx+1>=4 ? 0 : idx+1;
      this.$refs.mycarousel.$children[next].$slots.default[0].elm.classList.add('a');
      this.$refs.mycarousel.$children[next].$slots.default[1].elm.classList.add('b');
      this.$refs.mycarousel.$children[idx].$slots.default[0].elm.classList.remove('a');
      this.$refs.mycarousel.$children[idx].$slots.default[1].elm.classList.remove('b');
    }
  }
}
</script>
<style scoped>
  @import '../../components/css/Home/home.css';
  @import '../../components/css/style.css';
.a{
  /* font-size: 50px; */
  opacity: 0.5;
}
.b{
  display: none;
}
</style>