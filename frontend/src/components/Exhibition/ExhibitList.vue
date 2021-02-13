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
        <div @click="onClickEx" class="exhibit_item" v-for="(item,idx) in filteredList" :key="idx" :data-id="item.id">
          <div class="exhibit_duration" :data-id="item.id">{{item.startDate}} ~ {{item.endDate}}</div>
          <img :src="item.exImg" alt="" class="exhibit_poster" :data-id="item.id">
          <div class="exhibit_box" :data-id="item.id">
            <div class="exhibit_tlt" :data-id="item.id">
              <font-awesome-icon class="exhibit_tlt_icon" icon="leaf" :data-id="item.id"/>
              {{item.name}}
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
import {getExhibitList} from '@/api/exhibit.js';
import Navi from '@/components/Common/Navi.vue';
export default {
  name: "ExhibitList",
  data(){
    return{
      currentPlace:"서울",
      target:"all",
      around_list:[],
      ex_list:[]
    }
  },
  components:{
    Navi,
  },
  created(){
    const user =  this.$store.getters.getUser;
    getExhibitList(
      user.userId,
      (res)=>{
        this.ex_list=res.data;
        this.initMap();
      },
      (err)=>{
        console.error(err);
      }
    )
  },
  computed:{
    filteredList(){
      if(this.target==="all")  return this.ex_list.slice(0,10);
      else if(this.target==="best"){
        let tmp = this.ex_list.slice(0,);
        tmp.sort((a,b) => b.scrapCnt-a.scrapCnt);
        return tmp.slice(0,10);
      }
      else if(this.target==="online"){
        return this.ex_list.slice(0,10);
      }
      else{
        return this.around_list;
      }
    },
  },
  mounted(){
    // document.addEventListener('scroll',this.handleScroll);
    document.querySelector('#all').classList.add('active');
  },
  destroyed(){
    // document.removeEventListener('scroll',this.handleScroll);
  },
  methods:{
    initMap(){
      // if (navigator.geolocation) {
      //   navigator.geolocation.getCurrentPosition(function(position) {
      //     var lat = position.coords.latitude, // 위도
      //         lon = position.coords.longitude; // 경도
      //     var geocoder = new kakao.maps.services.Geocoder();
      //     geocoder.coord2Address(lon,lat, (res)=>{
      //       // console.log(res[0].address.region_1depth_name);
      //       // this.currentPlace="서울";
      //       this.currentPlace=res[0].address.region_1depth_name;
      //     });
      //   });
      // }
      // else{
      //   this.currentPlace="서울";
      // }
      let ps = new kakao.maps.services.Places();
      // console.log(this.ex_list);
      for(let i=0; i<this.ex_list.length; i++){
        ps.keywordSearch(this.ex_list[i].location,(data)=>{
          for(let j=0; j<data.length; j++){
            // console.log(this.ex_list[i]);
            if(data[j].address_name.includes(this.currentPlace) && data[j].category_group_name==="문화시설"){
              this.around_list.push(this.ex_list[i]);
            }
          }
        })
      }
      // console.log(this.around_list);
    },
    onClickAll(){
      document.querySelector(`#${this.target}`).classList.remove('active');
      this.target="all";
      document.querySelector(`#${this.target}`).classList.add('active');
    },
    onClickBest(){
      document.querySelector(`#${this.target}`).classList.remove('active');
      this.target="best";
      document.querySelector(`#${this.target}`).classList.add('active');
    },
    onClickOnline(){
      document.querySelector(`#${this.target}`).classList.remove('active');
      this.target="online";
      document.querySelector(`#${this.target}`).classList.add('active');
    },
    onClickArea(){
      document.querySelector(`#${this.target}`).classList.remove('active');
      this.target="area";
      document.querySelector(`#${this.target}`).classList.add('active');
    },
    onClickDown(){
      const list = document.querySelector('.exhibit_list');
      list.scrollIntoView({behavior:'smooth'});
    },
    onClickEx(e){
      // console.log(e.target.dataset.id);
      this.$router.replace({
        name:"ExhibitionDetail",
        params:{
          id:e.target.dataset.id,
        }
      })
    },
    // handleScroll(){
    //   const main = document.querySelector('.exhibit_main');
    //   const mainRect = main.getBoundingClientRect();
    //   const list = document.querySelector('.exhibit_list');
    //   main.style.opacity = (mainRect.height-window.scrollY) / mainRect.height;
    //   list.style.opacity = window.scrollY / mainRect.height;
    // }
  },
}
</script>

<style scoped src="../css/ExhibitList/exhibit_list.module.css">
</style>