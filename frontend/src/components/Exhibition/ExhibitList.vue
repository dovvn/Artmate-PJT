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
          <div v-if="item.startDate && item.endDate" class="exhibit_duration" :data-id="item.id"> {{$moment(item.startDate).format('YYYY-MM-DD')}} ~ {{$moment(item.endDate).format('YYYY-MM-DD')}} </div>
          <img :src="item.exImg" alt="" class="exhibit_poster" :data-id="item.id">
          <div class="exhibit_box" :data-id="item.id">
            <div class="exhibit_tlt" :data-id="item.id">
              <font-awesome-icon class="exhibit_tlt_icon" :icon="['fab', 'envira']" :data-id="item.id"/>
              {{item.name}}
            </div>
            
            <div class="exhibit_scrap">
              <span>
                <font-awesome-icon v-if="item.scrapmark == 0" @click="addScrap(item.scrapmark,item.id)" :icon="['far', 'star']" style="color:white"/> 
                <font-awesome-icon v-if="item.scrapmark == 1" @click="addScrap(item.scrapmark,item.id)"  :icon="['fas', 'star']" style="color:white"/> 
                {{item.scrapCnt}}
              </span>
            </div>
            
          </div>
          
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import {getExhibitList,getOnlineExhibit} from '@/api/exhibit.js';
import Navi from '@/components/Common/Navi.vue';
export default {
  name: "ExhibitList",
  data(){
    return{
      locationInfo:{},
      target:"all",
      around_list:[],
      ex_list:[],
      online_list:[],
      userInfo:{},
    }
  },
  components:{
    Navi,
  },
  created(){
    const user =  this.$store.getters.getUser;
    this.userInfo=user;
    this.locationInfo = this.$store.getters.getCurrentLocation;
    getExhibitList(
      user.userId,
      (res)=>{
        this.ex_list=res.data;
        for(var i=0; i<this.ex_list.length; i++){
          console.log("사진주소 "+this.ex_list[i].exImg);
        }
        this.initMap();
      },
      (err)=>{
        console.error(err);
      }
    );
    getOnlineExhibit(
      this.userInfo.userId,
      (res)=>{
        this.online_list=res.data;
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
        return this.online_list.slice(0,10);
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
      let ps = new kakao.maps.services.Places();
      const tmp = this.locationInfo.location.split(" ");
      for(let i=0; i<this.ex_list.length; i++){
        if(this.ex_list[i].location===undefined || this.ex_list[i].location === "온라인") continue;
        ps.keywordSearch(this.ex_list[i].location,(data)=>{
          for(let j=0; j<data.length; j++){
            if(data[j].address_name.includes(tmp[0]) && data[j].category_group_name==="문화시설"){
              this.around_list.push(this.ex_list[i]);
              break;
            }
          }
        })
      }
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
      if(e.target.dataset.id){
        this.$router.replace({
          name:"ExhibitionDetail",
          params:{
            id:e.target.dataset.id,
          }
        })
      }
    },
    addScrap:function(scrap, exid){
      if(scrap == 0){ // 스크랩 안눌린 상태
        if(this.target==="online"){
          for(let i=0; i<this.online_list.length; i++){
            if(this.online_list[i].id===exid){
              this.online_list[i].scrapCnt++;
              this.online_list[i].scrapmark = 1;
              break;
            }
          }
        }
        else if(this.target==="area"){
          for(let i=0; i<this.around_list.length; i++){
            if(this.around_list[i].id===exid){
              this.around_list[i].scrapCnt++;
              this.around_list[i].scrapmark = 1;
              break;
            }
          }
        }
        else{
          for(let i=0; i<this.ex_list.length; i++){
            if(this.ex_list[i].id===exid){
              this.ex_list[i].scrapCnt++;
              this.ex_list[i].scrapmark = 1;
              break;
            }
          }
        }
        http
        .put(`api/scrapbook/${this.userInfo.userId}/${exid}`)
        .then((data) => {
            if (data) {
                // alert('스크랩');
            } else {
                alert('오류가 발생하였습니다.');
            }
        })
        .catch((err) => console.log(err));
      }else if(scrap == 1){ // 스크랩 눌린 상태 
        if(this.target==="online"){
          for(let i=0; i<this.online_list.length; i++){
            if(this.online_list[i].id===exid){
              this.online_list[i].scrapCnt--;
              this.online_list[i].scrapmark = 0;
              break;
            }
          }
        }
        else if(this.target==="area"){
          for(let i=0; i<this.around_list.length; i++){
            if(this.around_list[i].id===exid){
              this.around_list[i].scrapCnt--;
              this.around_list[i].scrapmark = 0;
              break;
            }
          }
        }
        else{
          for(let i=0; i<this.ex_list.length; i++){
            if(this.ex_list[i].id===exid){
              this.ex_list[i].scrapCnt--;
              this.ex_list[i].scrapmark = 0;
              break;
            }
          }
        }
        http
        .delete(`api/scrapbook/${this.userInfo.userId}/${exid}`)
        .then((data) => {
            if (data) {
                // alert('스크랩 취소..');
            } else {
                alert('오류가 발생하였습니다.');
            }
        })
        .catch((err) => console.log(err));
      }
    },
    // handleScroll(){
    //   const main = document.querySelector('.exhibit_main');
    //   const mainRect = main.getBoundingClientRect();
    //   const list = document.querySelector('.exhibit_list');
    //   const mainB = (mainRect.height-window.scrollY) / mainRect.height;
    //   const listB = window.scrollY / mainRect.height;
    //   main.style.filter = `brightness(${mainB})`;
    //   list.style.filter = `brightness(${listB})`;
    //   main.style.opacity = mainB;
    //   list.style.opacity = listB;
    // }
  },
}
</script>

<style scoped src="../css/ExhibitList/exhibit_list.module.css">
</style>