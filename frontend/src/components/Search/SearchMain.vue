<template>
  <div id="SearchMain" class="search_main">
    <nav class="search_nav">
      <div @click="onBack" class="search_back_btn">
        <font-awesome-icon icon="chevron-left"/>
      </div>
      <div class="search_nav_name">
        <span>검색</span>
      </div>
    </nav>
    <div class="search_header">무엇을 찾으시나요?</div>
    <form @submit="onSubmit" class="search_input_box">
      <input
        maxlength="15"
        v-model="keyword"
        @input="onInput"
        type="text"
        class="search_input"
        placeholder="검색어를 입력해주세요"
      >
      <button class="search_btn">
        <font-awesome-icon icon="search"/>
      </button>
    </form>
    <div class="search_full_bar"></div>
    <ul
      v-if="isShowAuto" 
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
        <span class="auto_search_text" :data-keyword="item">{{item}}</span> 
      </li>
    </ul>
    <section class="search_latest" v-if="!isShowAuto && !isSubmit">
      <div class="search_latest_tlt">
        <span>최근 검색어</span>
      </div>
      <ul class="search_latest_list">
        <li
          class="search_latest_item"
          v-for="(item,idx) in keywordLatest"
          :key="idx"
        >
        <button @click="onClickLatest">{{item.value}}</button>&nbsp;
        <button :data-key="item.key" @click="onDeleteItem">X</button>
        </li>
      </ul>
    </section>
    <section class="search_popularity" v-if="!isShowAuto && !isSubmit">
      <div class="search_popularity_tlt">
        <span>인기 검색어</span>
      </div>
      <ul class="search_popularity_list">
        <li
          class="search_popularity_item"
          v-for="(item,idx) in popularities"
          :key="idx"
        >
          <div v-if="idx<=2" class="search_popularity_rank purple">{{idx+1}}</div>
          <div v-else class="search_popularity_rank">{{idx+1}}</div>
          <div @click="onClickPopularity" class="search_popularity_keyword">{{item}}</div>
        </li>
      </ul>
    </section>
    <section class="search_user_list" v-if="!isShowAuto && isSubmit">
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
    <section class="search_exhibition_list" v-if="!isShowAuto && isSubmit">
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
    <footer>
      <img 
        class="search_owl_img"
        src="../../assets/searchPicture.png"
        alt=""
        v-if="!isShowAuto && !isSubmit"
      >
    </footer>
  </div>
</template>

<script>
import { searchPopularity, searchList, searchKeyword} from '@/api/search.js';
import * as Hangul from 'hangul-js';
export default {
  name: "SearchMain",
  data: () => {
    return {
      isShowAuto:false,
      isSubmit:false,
      keyword:"",
      latestList:[],
      popularities:[],
      keywordList:[],
      users:[],
      exhibitions:[],
    }
  },
  mounted(){
    document.addEventListener('click',this.handleAuto);
  },
  destroyed(){
    document.removeEventListener('click',this.handleAuto);
  },
  created(){
    for(let i=0; i<localStorage.length; i++){
      const key=localStorage.key(i);
      if(key!==null && key!=='access-token' && key!=='loglevel:webpack-dev-server')
        this.latestList.push(key);
    }
    searchPopularity(
      (res)=>{
        this.popularities=res.data;
      },
      (err)=>{
        console.error(err);
      }
    )
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
  computed:{
    keywordLatest(){
      let sortedList = this.latestList.slice(0,);
      sortedList.sort((a,b) => b*1-a*1)
      for(let i=0; i<sortedList.length; i++){
        sortedList[i]={key:sortedList[i],value:localStorage.getItem(sortedList[i])};
      }
      return sortedList;
    },
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
      return arr.slice(0,10);
    }
  },
  methods:{
    handleAuto(e){
      const x=['keyword_list','keyword_item','auto_search_icon','auto_search_text']
      if(!x.includes(e.target.className)){
        this.isShowAuto=false;
      }
    },
    onBack(){
      history.back();
    },
    onDeleteItem(e){
      const key=e.target.dataset.key;
      localStorage.removeItem(key);
      const idx=this.latestList.indexOf(key);
      this.latestList.splice(idx,1);
    },
    search(){
      const key=String(Date.now());
      if(localStorage.length<=9){
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
      document.querySelector('.search_input').blur();
    },
    onClickAuto(e){
      this.keyword=e.target.dataset.keyword;
      this.search();
      this.isShowAuto=false;
    },
    onInput(e){
      this.keyword=e.target.value;
      this.isSubmit=false;
      if(this.keyword.length===0) this.isShowAuto=false;
      else this.isShowAuto=true;
    },
    onSubmit(e){
      e.preventDefault();
      this.search();
    },
    onClickUser(e){
      const user=this.users[e.target.dataset.idx];
      const userId=user.userId;
      this.$router.replace({
        name: "UserFeedList",
        params: {userId: userId}
      });
    },
    onClickLatest(e){
      this.keyword=e.target.innerText;
      this.search();
    },
    onClickPopularity(e){
      this.keyword=e.target.innerText;
      this.search();
    },
  }
}
</script>

<style scoped src="../../components/css/search.module.css">
</style>