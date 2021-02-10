<template>
  <div class="around">
    <div class="around_nav">
      <div @click="onBack" class="around_back_btn">
        <font-awesome-icon icon="chevron-left"/>
      </div>
      <div class="around_nav_tlt">
        <span>AROUND</span>
      </div>
      <div></div>
    </div>

    <div class="around_header">
      <div class="around_header_tlt">
        <span>현재 우리 지역의 전시회</span>
      </div>
      <div class="around_header_place">
        <span><font-awesome-icon :icon="['far', 'flag']"/>대전광역시</span>
      </div>
    </div>

    <div id="map" class="around_map"></div>
    
    <div class="around_list">
      <div class="around_list_nav">
        <div></div>
        <div></div>
        <div v-if="isToggled" class="around_list_nav_tlt">전시회 목록</div>
        <button v-if="!isToggled" @click="onToggle" class="around_list_toggle_btn">목록보기</button>
        <button v-else @click="onToggle" class="around_list_toggle_btn">숨기기</button>
      </div>
      <ul class="around_list_items">
        <li
          style="list-style:none;"
          v-for="(item,idx) in aroundEx"
          :key="idx"
        >
          <div class="around_list_item_box">
            <img :src="item.img" alt="" class="around_list_item_img">
            <div class="around_list_item_info">
              <p class="around_list_item_tlt">
                <font-awesome-icon class="feed_message_icon" icon="leaf"/>{{item.title}}
              </p>
              <p class="around_list_item_place">
                <font-awesome-icon class="feed_message_icon" icon="map-marker-alt"/>
                {{item.place}}
              </p>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
// import AroundList from '@/components/Around/AroundList.vue'
export default {

  name: "Around",
  data(){
    return{
      isToggled:false,
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
        {
          img:require('../../assets/main/slide2_3.png'),
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
        {
          img:require('../../assets/main/slide2_3.png'),
          title: "간직해온 마음들",
          place: "대전시립미술관",
          duration: "21.01.13 ~ 21.02.28"
        },
      ]
    }
  },
  computed:{
    aroundEx(){
      if(!this.isToggled){
        return [this.aroundList[0]];
      }
      else{
        return this.aroundList;
      }
    }
  },
  mounted(){
    // if (window.kakao && window.kakao.maps) {
    //   this.initMap();
    // } else {
    //   const script = document.createElement('script');
      
    //   script.onload = () => kakao.maps.load(this.initMap);
    //   script.src ='http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0c6121d667946c4583e303b2760cca80&libraries=services';
    //   document.head.appendChild(script);
    // }
    this.initMap();
    document.addEventListener('click',this.handleToggle);
  },
  destroyed(){
    document.addEventListener('click',this.handleToggle);
  },
  methods:{
    handleToggle(e){
      // console.log(typeof e.target.classList['around_list']);
      if(this.isToggled)
        if(typeof e.target.className == 'object' || 
        typeof e.target.className == 'string' && 
        !e.target.className.includes('around_list'))
          this.onToggle();
    },
    onBack(){
      history.back();
    },
    onToggle(){
      let list = document.querySelector('.around_list');
      let nav = document.querySelector('.around_nav');
      let header = document.querySelector('.around_header');
      let map = document.querySelector('.around_map');
      if (!this.isToggled){
        this.isToggled=true;
        list.classList.add('active');
        nav.classList.add('inactive');
        header.classList.add('inactive');
        map.classList.add('inactive');
      }
      else{
        this.isToggled=false;
        list.classList.remove('active');
        nav.classList.remove('inactive');
        header.classList.remove('inactive');
        map.classList.remove('inactive');
      }
      
    },
    initMap() {
      var mapContainer = document.getElementById('map'), // 지도를 표시할 div
          mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
          };

      var map = new kakao.maps.Map(mapContainer, mapOption);
      // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder();

      // 주소로 좌표를 검색합니다
      geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

          // 정상적으로 검색이 완료됐으면 
          if (status === kakao.maps.services.Status.OK) {

              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                  map: map,
                  position: coords
              });

              // 인포윈도우로 장소에 대한 설명을 표시합니다
              var infowindow = new kakao.maps.InfoWindow({
                  content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
              });
              infowindow.open(map, marker);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
          } 
      });
      // var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667);
      // var marker = new kakao.maps.Marker({
      //     position: markerPosition
      // });
      // marker.setMap(map);
    },
  },
};
</script>

<style scoped>
  @import '../../components/css/style.css';
  @import '../../components/css/Around/around.module.css';
</style>