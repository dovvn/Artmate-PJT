<template>
  <div id="SearchList" class="search_list">
    <nav class="search_nav">
      <div @click="onBack" class="search_back_btn">
        <font-awesome-icon icon="chevron-left"/>
      </div>
      <form @submit="onSubmit" class="search_list_form">
        <input
          v-model="keyword"
          @focus="onFocus"
          @input="onInput"
          @blur="onBlur"
          type="text"
          class="search_list_input"
          placeholder="검색어를 입력해주세요."
        >
        <button class="search_list_btn">
          <font-awesome-icon icon="search"/>
        </button>
      </form>
    </nav>
    <div class="search_list_full_bar"></div>
    <ul
      v-if="isKeyUp && keyword.length && autocomplete.length" 
      class="keyword_list"
    >
      <li
        class="keyword_item"
        v-for="(item,idx) in autocomplete"
        :key="idx"
        @click="onClickAuto"
        :data-keyword="item"
      >
        <font-awesome-icon :data-keyword="item" class="auto_search_icon" size='sm' icon="search"/>
        <span :data-keyword="item">{{item}}</span> 
      </li>
    </ul>
    <section class="search_user_list" v-if="isSubmit">
      <div class="search_user_list_tlt">
        유저 검색 결과
        <span class="search_user_list_txt">  '{{keyword}}' {{users.length}}건</span>
      </div>
      <ul class="search_users">
        <li
          @click="onClickUser"
          v-for="(user,idx) in users"
          :key="idx"
          :data-idx=idx
        >
          <div :data-idx=idx class="search_user">
            <img :data-idx=idx :src="user.userImg" alt="" class="search_user_img">
            <p :data-idx=idx class="search_user_name">{{user.userName}}</p>
          </div>
        </li>
      </ul>
    </section>
    <section class="search_exhibition_list" v-if="isSubmit">
      <div class="search_exhibition_list_tlt">
        전시회 검색 결과
        <span class="search_exhibition_list_txt">  '{{keyword}}' {{exhibitions.length}}건</span>
      </div>
      <ul class="search_exhibitions">
        <li
          v-for="(exhibition, idx) in exhibitions"
          :key="idx"
        >
          <div class="search_exhibition">
            <img :src="exhibition.poster" alt="">
            <div class="search_exhibition_info">
              <p class="search_exhibition_tlt">{{exhibition.title}}</p>
              <p class="search_exhibition_place">{{exhibition.place}}</p>
              <p class="search_exhibition_expiration">{{exhibition.expiration}}</p>
            </div>
          </div>
        </li>
      </ul>
    </section>
  </div>
</template>

<script>
import {searchList, searchKeyword} from '@/api/search.js'
import * as Hangul from 'hangul-js';
export default {
  name:"SearchList",
  data(){
    return{
      isSubmit:false,
      latestList:[],
      keyword:"",
      isKeyUp:false,
      // autocomplete:[],
      keywordList:[],
      users:[],
      exhibitions:[
        // {
        //   title:"서울시 한국 근현대 인물화",
        //   place:"국립현대미술관",
        //   expiration:"2021.04.01 ~ 04.23",
        //   poster:require("../../assets/taste_1.jpg")
        // },
        // {
        //   title:"인물화 나려전시회",
        //   place:"대전시립미술관",
        //   expiration:"2021.05.01 ~ 05.17",
        //   poster:require("../../assets/taste_2.jpg")
        // },
        // {
        //   title:"인물화 나려전시회",
        //   place:"대전시립미술관",
        //   expiration:"2021.05.01 ~ 05.17",
        //   poster:require("../../assets/taste_2.jpg")
        // },
        // {
        //   title:"인물화 나려전시회",
        //   place:"대전시립미술관",
        //   expiration:"2021.05.01 ~ 05.17",
        //   poster:require("../../assets/taste_2.jpg")
        // },
      ]
    }
  },
  created(){
    for(let i=0; i<localStorage.length; i++){
      const key=localStorage.key(i);
      if(key!==null && key!=='access-token' && key!=='loglevel:webpack-dev-server')
        this.latestList.push(key);
    }    
    searchList(
      (res)=>{
        for(let i=0; i<res.data.length; i++){
          this.keywordList.push({name:res.data[i]});
        }
        this.keywordList.forEach(function (item) {
            var dis = Hangul.disassemble(item.name, true);
            var cho = dis.reduce(function (prev, elem) {
                elem = elem[0] ? elem[0] : elem;
                return prev + elem;
            }, "");
            item.diassembled = cho;
        });
      },
      (err)=>{
        console.error(err);
      }
    )
  },
  watch:{
    // keyword: function(){
    //   if(this.keyword.length===0) this.isKeyUp=false;
    //   else this.isKeyUp=true;
    // }
  },
  computed:{
    autocomplete(){
      const search = this.keyword;
      const search1 = Hangul.disassemble(search).join("");
      let arr=[];
      this.keywordList
      .filter(function (item) {
          return item.name.includes(search) || item.diassembled.includes(search1);
      })
      .forEach(function (item) {
        arr.push(item.name);
      });
      return arr;
    }
  },
  mounted(){
    document.querySelector('.search_list_input').focus();
  },
  methods:{
    onClickAuto(e){
      this.keyword=e.target.dataset.keyword;
      searchKeyword(e.target.dataset.keyword,
        (res)=>{
          this.users=res.data.userResult;
          // this.exhibitions=res.data.exhibitResult;
        },
        (err)=>{
          console.error(err);
        }
      );
      this.isKeyUp=false;
      this.isSubmit=true;
    },
    onInput(e){
      this.keyword=e.target.value;
    },
    onBack(){
      history.back();
    },
    onBlur(e){
      console.log(e);
    },
    onFocus(){
      this.isKeyUp=true;
      this.isSubmit=false;
    },
    onSubmit(e){
      e.preventDefault();
      const key=String(Date.now());
      if(localStorage.length<=12){
        localStorage.setItem(key,this.keyword);
        this.latestList.unshift(key);
      }
      else{
        let sortedList=this.latestList;
        sortedList.sort((a,b) => b*1-a*1);
        const delKey=sortedList[sortedList.length-1];
        localStorage.removeItem(delKey);
        const idx=this.latestList.indexOf(delKey);
        this.latestList.splice(idx,1,key);
        localStorage.setItem(key,this.keyword);
      }
      searchKeyword(this.keyword,
        (res)=>{
          this.users=res.data.userResult;
          // this.exhibitions=res.data.exhibitResult;
        },
        (err)=>{
          console.error(err);
        }
      );
      this.isSubmit=true;
      this.isKeyUp=false;
      document.querySelector('.search_list_input').blur();
    },
    onClickUser(e){
      console.log(e);
    }
  }
}
</script>

<style scoped src="../css/search_list.module.css">
</style>