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
    <form class="search_input_box">
      <input
        type="text"
        class="search_input"
        placeholder="검색어를 입력해주세요"
        @click="onClickSearch"
      >
      <button class="search_btn">
        <font-awesome-icon icon="search"/>
      </button>
    </form>
    <div class="full_bar"></div>
    <section class="search_latest">
      <div class="search_latest_tlt">
        <span>최근 검색어</span>
        <!-- <button class="search_latest_del_btn">검색기록 지우기</button> -->
      </div>
      <ul class="search_latest_list">
        <li
          class="search_latest_item"
          v-for="(item,idx) in keywordLatest"
          :key="idx"
        >
        {{item.value}} <button :data-key="item.key" @click="onDeleteItem">x</button>
        </li>
      </ul>
    </section>
    <section class="search_popularity">
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
          <div class="search_popularity_keyword">{{item}}</div>
        </li>
      </ul>
    </section>
  </div>
</template>

<script>
import { searchPopularity, searchList} from '@/api/search.js';
export default {
  name: "SearchMain",
  data: () => {
    return {
      latestList:[],
      popularities:[],
    }
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
        this.keywordList=res.data;
        // console.log(this.keywordList);
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
  },
  methods:{
    onBack(){
      history.back();
    },
    onDeleteItem(e){
      const key=e.target.dataset.key;
      localStorage.removeItem(key);
      const idx=this.latestList.indexOf(key);
      this.latestList.splice(idx,1);
    },
    onClickSearch(){
      this.$router.push({name:"SearchList"});
    }
  }
}
</script>

<style scoped>
  @import '../../components/css/search.module.css';
</style>