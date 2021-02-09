<template>
  <div>
    <div class="exhibit_main">
      <div class="exhibit_main_header">
      <font-awesome-icon @click="onClickDown" class="exhibit_main_down_btn" icon="chevron-down" size="2x"/>
        <div class="exhibit_main_box">
          <img src="../../assets/exhibitions/exhibit_main1.jpg" alt="" class="exhibit_main_poster">
          <div class="exhibit_side_box">
            <span class="exhibit_side_tlt">Artmate</span>&nbsp;
            <span class="exhibit_side_year">2021</span>
          </div>
        </div>
        <div class="exhibit_bottom_tlt">Exhibit</div>
        <div class="exhibit_bottom_text">
          <div>The art exhibitions for you must see.</div>
          <div>Here are our exhibitions highlights</div>
          <div>for 2021.</div>
        </div>
      </div>
      <div class="exhibit_footer_box">
        <img src="../../assets/exhibitions/exhibit_main2.jpg" alt="" class="exhibit_footer_poster">
      </div>
    </div>


    <div class="exhibit_list">
      <Navi class="main_nav" />
      <div class="exhibit_nav">
        <button @click="onClickAll" id="all" class="exhibit_nav_item">All</button>
        <button @click="onClickBest" id="best" class="exhibit_nav_item">Best</button>
        <button @click="onClickOnline" id="online" class="exhibit_nav_item">Online</button>
        <button @click="onClickArea" id="area" class="exhibit_nav_item">Area</button>
        
      </div>
      <div class="exhibit_items">
        <div class="exhibit_item" v-for="(item,idx) in ex_list" :key="idx">
          <div class="exhibit_duration">{{item.duration}}</div>
          <img :src="item.poster" alt="" class="exhibit_poster">
          <div class="exhibit_box">
            <div class="exhibit_tlt">
              <font-awesome-icon class="exhibit_tlt_icon" icon="leaf"/>
              {{item.title}}
            </div>
            <div class="exhibit_scrap">
              <div class="exhibit_scrap_cnt">{{item.scrapCnt}}</div>
              <button class="exhibit_scrap_btn">
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navi from '@/components/Common/Navi.vue';
export default {
  name: "ExhibitList",
  data(){
    return{
      ex_list:[
        {
          title:"외곽의 지층들",
          poster:require("../../assets/main/slide1_1.jpg"),
          duration:"2021.01.05 ~ 2021.02.14",
          scrapCnt: 823,
        },
        {
          title:"외곽의 지층들",
          poster:require("../../assets/main/slide1_1.jpg"),
          duration:"2021.01.05 ~ 2021.02.14",
          scrapCnt: 823,
        },
        {
          title:"외곽의 지층들",
          poster:require("../../assets/main/slide1_1.jpg"),
          duration:"2021.01.05 ~ 2021.02.14",
          scrapCnt: 823,
        },
      ]
    }
  },
  components:{
    Navi,
  },
  mounted(){
    // document.addEventListener('scroll',this.handleScroll);
    document.querySelector('#all').classList.add('active');
  },
  destroyed(){
    // document.removeEventListener('scroll',this.handleScroll);
  },
  methods:{
    onClickAll(){

    },
    onClickBest(){

    },
    onClickOnline(){

    },
    onClickArea(){

    },
    onClickDown(){
      const list = document.querySelector('.exhibit_list');
      list.scrollIntoView({behavior:'smooth'});
    },
    handleScroll(){
      const main = document.querySelector('.exhibit_main');
      const mainRect = main.getBoundingClientRect();
      const list = document.querySelector('.exhibit_list');
      main.style.opacity = (mainRect.height-window.scrollY) / mainRect.height;
      list.style.opacity = window.scrollY / mainRect.height;
    }
  },
}
</script>

<style scoped src="../css/ExhibitList/exhibit_list.module.css">
</style>