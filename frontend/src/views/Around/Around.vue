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
        <span><font-awesome-icon :icon="['far', 'flag']"/> {{currentPlace}}</span>
      </div>
    </div>

    <div id="map" class="around_map"></div>
    
    <div class="around_list">
      <div class="around_list_nav">
        <div></div>
        <div></div>
        <div class="around_list_nav_tlt">{{selectedLocation}}</div>
        <button v-if="!isToggled" @click="onToggle" class="around_list_toggle_btn">목록보기</button>
        <button v-else @click="onToggle" class="around_list_toggle_btn">숨기기</button>
      </div>
      <ul class="around_list_items">
        <li
          style="list-style:none;"
          v-for="(item,idx) in aroundEx"
          :key="idx"
        >
          <div @click="onClickEx" :data-id="item.id" class="around_list_item_box">
            <img :src="item.exImg" :data-id="item.id" alt="" class="around_list_item_img">
            <div class="around_list_item_info" :data-id="item.id" >
              <p class="around_list_item_tlt" :data-id="item.id" >
                <font-awesome-icon class="feed_message_icon" icon="leaf" :data-id="item.id" />{{item.name}}
              </p>
              <p class="around_list_item_place" :data-id="item.id" >
                <font-awesome-icon class="feed_message_icon" icon="map-marker-alt" :data-id="item.id" />
                {{item.location}}
              </p>
            </div>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import {getListForMap} from '@/api/exhibit.js'
export default {

  name: "Around",
  data(){
    return{
      currentPlace:"",
      isToggled:false,
      idx:0,
      selectedLocation:"",
      aroundList:[]
    }
  },
  computed:{
    aroundEx(){
      if(!this.isToggled){
        if(this.selectedLocation==="") return [];
        // console.log(this.selectedLocation);
        let tmp=[];
        let a = this.aroundList.filter(item=>item.location===this.selectedLocation);
        tmp.push(a[0]);
        return tmp;
      }
      else{
        return this.aroundList.filter(item=>item.location===this.selectedLocation);
      }
    }
  },
  created(){
    getListForMap(
      (res)=>{
        this.aroundList=res.data;
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
              center: new kakao.maps.LatLng(37.49887, 127.026581), // 지도의 중심좌표
              level: 3, // 지도의 확대 레벨
            };

        var map = new kakao.maps.Map(mapContainer, mapOption);
        var geocoder = new kakao.maps.services.Geocoder();
        // 주소로 좌표를 검색합니다
        let ps = new kakao.maps.services.Places();
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
        let obj= new Object();
        for(let i=0; i<this.aroundList.length; i++){
          if(obj[this.aroundList[i].location]===undefined){
            obj[this.aroundList[i].location]=[this.aroundList[i]]
          }
          else{
            obj[this.aroundList[i].location].push(this.aroundList);
          }
        }
        let flag=false;
        for(let key in obj){
          ps.keywordSearch(key,(data)=>{
            for(let arr of data){
              if(arr.category_group_name==="문화시설"){
                var coords = new kakao.maps.LatLng(Number(arr.y), Number(arr.x));
                var imageSize = new kakao.maps.Size(24, 35);
                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
                var marker = new kakao.maps.Marker({
                    map: map, // 마커를 표시할 지도
                    position: new kakao.maps.LatLng(Number(arr.y), Number(arr.x)), // 마커를 표시할 위치
                    title : key, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image : markerImage // 마커 이미지 
                });
                var infowindow = new kakao.maps.InfoWindow({
                    content: key, // 인포윈도우에 표시할 내용
                });
                (function(marker, infowindow) {
                  kakao.maps.event.addListener(marker, 'mouseover', function() {
                    infowindow.open(map, marker);
                  });
                  kakao.maps.event.addListener(marker, 'mouseout', function() {
                    infowindow.close();
                  });
                  // kakao.maps.event.addListener(marker, 'click', function() {
                  //   this.selectedLocation=marker.Fb;
                  //   console.log(this.selectedLocation,marker.Fb);
                  // });
                })(marker, infowindow);
                if(!flag){
                  this.selectedLocation=key;
                  map.setCenter(coords);
                  flag=true;
                }
                break;
              }
            }
          })
        }
      },
      (err)=>{
        console.error(err);
      }
    )
  },
  mounted(){
    this.initMap();
    document.addEventListener('click',this.handleToggle);
  },
  destroyed(){
    document.addEventListener('click',this.handleToggle);
  },
  methods:{
    handleToggle(e){
      // console.log(e);
      if(this.isToggled){
        if(typeof e.target.className == 'object' || 
        typeof e.target.className == 'string' && 
        !e.target.className.includes('around_list'))
          this.onToggle();
      }
      else{
        if(e.target.tagName==="IMG" && e.target.src==="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"){
          this.selectedLocation=e.target.title;
          // console.log(this.selectedLocation);
        }
      }
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
      let place = "";
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
          var lat = position.coords.latitude, // 위도
              lon = position.coords.longitude; // 경도
          var geocoder = new kakao.maps.services.Geocoder();
          
          geocoder.coord2Address(lon,lat, (res)=>{
            place=res[0].address.region_1depth_name + ' ' + res[0].address.region_2depth_name;
          });
        });
        setTimeout(()=>{
          this.setCurrentPlace(place);
          // console.log(this.currentPlace);
        },500);
      }
      else{
        this.setCurrentPlace("서울")
      }

      // var markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667);
      // var marker = new kakao.maps.Marker({
      //     position: markerPosition
      // });
      // marker.setMap(map);
    },
    setCurrentPlace(place){
      this.currentPlace=place;
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
  },
};
</script>

<style scoped src="../../components/css/Around/around.module.css">
</style>