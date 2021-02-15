<template>
  <div class="exdiv_detail">
      <div>
        <Navi class="exDetial__navi"/>
        <button class="goBack__button" @click="goBack(id)">
        <font-awesome-icon :icon="['fas', 'chevron-left']" />
      </button>
      </div>
      <div class="detail">
          <!-- 전시회 포스터  -->
          <div class="exPoster">
              <img class="poster_img" :src="exhibit.exImg" alt="" >
          </div>
          <div class="bar"></div>
          <!-- 전시회내용 -->
          <div class="exContent">
              <div class="exName">
                <font-awesome-icon :icon="['fab', 'envira']" class="ex__icon" style="color:#A593DF"/>
                <span class="ex__name"> {{exhibit.name}}</span>
                <span class="scrapCnt"> 
                    <font-awesome-icon v-if="exhibit.scrapmark == 0" @click="addScrap(exhibit.scrapmark,exhibit.id)" :icon="['far', 'star']" style="color:white"/> 
                    <font-awesome-icon v-if="exhibit.scrapmark == 1" @click="addScrap(exhibit.scrapmark,exhibit.id)"  :icon="['fas', 'star']" style="color:white"/>
                    {{exhibit.scrapCnt}}</span>
              </div>
              <div class="tag">
                  <span class="tags" v-for="tag in exhibit.tagList" :key="tag" >
                {{tag}}
              </span>
              </div>
              
              <div class="exInfo">
                  <div class="ex__date" v-if="exhibit.vrLink == null">
                    <div class="day"> 날짜 : </div>
                    <div class="days"> {{$moment(exhibit.startDate).format('YYYY-MM-DD')}} ~ {{$moment(exhibit.endDate).format('YYYY-MM-DD')}} </div>
                  </div>
                  <div class="ex__area" v-if="exhibit.vrLink == null">
                    <div class="area"> 장소 : </div>
                    <div class="areas"> {{exhibit.location}}</div>
                  </div>
                  <div class="ex__artist">
                    <div class="artist"> 작가 : </div>
                    <div class="artists"  v-if="exhibit.artist != null && !showArtist" v-html="exhibit.artist"> </div>
                    <div class="artistTogle " id="info-box1" ref="infoBox1" v-if="exhibit.artist != null && showArtist" v-html="exhibit.artist"> </div>
                    <div class="artists" v-if="exhibit.artist == null"> 작가없음 </div>
                    <div class="btns" v-if="exhibit.artist != null && exhibit.artist.length > 78"> <!--버튼 보일 때 조건-->
                        <b-button class="more" pill variant="outline-secondary" v-if="!showArtist" @click="toggleArtistShow">더보기▼</b-button>
                        <b-button class="mores" pill variant="outline-secondary" v-if="showArtist" @click="toggleArtistShow">닫기 X</b-button>
                    </div>
                  </div>
                  <div class="ex__text">
                    <div class="text"> 소개 : </div>
                    <div class="texts"  v-if="exhibit.description != null && !showDes" v-html="exhibit.description"> </div>
                    <div class="textTogle" id="info-box2" ref="infoBox2" v-if="exhibit.description != null && showDes" v-html="exhibit.description" ></div>
                    <div class="texts" v-if="exhibit.description == null"> 소개없음 </div>
                    <div class="btns" v-if="exhibit.description != null && exhibit.description.length > 61 " >
                        <b-button class="more" pill variant="outline-secondary" v-if="exhibit.description != null && !showDes" @click="toggleDesShow">더보기▼</b-button>
                        <b-button class="mores" pill variant="outline-secondary" v-if="exhibit.description != null && showDes" @click="toggleDesShow">닫기 X</b-button>
                    </div>
                  </div>
              </div>
          </div>
          <!-- 새탭에서 열기방법 -->
            <b-button class="vr" variant="outline-light" v-if="exhibit.vrLink != null"><a class="vrgo" :href="exhibit.vrLink" target="_blank" >VR 보러가기</a></b-button>

        <!-- 모달로 보이기 -->
        <!-- <div>
        <b-button v-b-modal.modal-1 class="vr" variant="outline-light" v-if="exhibit.vrLink != null">VR 보러가기</b-button>

        <b-modal id="modal-1" title="온라인 전시회">
            <div class="modal-body">
            <iframe
              :src="exhibit.vrLink"
              name="myIframe"
              class="w-100 h-100"
            ></iframe>
          </div>
        </b-modal>
        </div> -->



          <div class="bar"></div>
          <!-- 함께 즐겨요 -->
          <div class="exReview">
            <div class="exFeeds">
                <font-awesome-icon :icon="['fas', 'globe-americas']" class="earth__icon" style="color:#5F9EA0"/>
                <span class="ex__name"> 함께 즐겨요 </span>
                <div class="mention" v-if="exhibit.feedCnt != 0">
                    <span class="feeds">{{exhibit.feedCnt}}</span><span class="feeds">명의 회원님이 </span>
                <span class="feeds">"{{exhibit.name}}"</span><span class="feeds"> 을 먼저 다녀가셨어요 😃</span>
            </div>
            <div class="mention" v-if="exhibit.feedCnt == 0">
                    <span class="feeds">아직 다녀가신 회원님이 없습니다😥</span>
            </div>
                <vueper-slides
                    class="no-shadow img"
                    :visible-slides="3"
                    slide-multiple
                    :gap="3"
                    :dragging-distance="200"
                    :breakpoints="{ 800: { visibleSlides: 3, slideMultiple: 2 } }"
                    disableArrowsOnEdges=true 
                    :bullets = "false" >
                     <vueper-slide
                        class="feed_img"
                        v-for="f in feed" :key="f.id"
                        :image="f.feedImg"
                        @click.native ="goUserFeedDetail(f.id)">
                    </vueper-slide>
                </vueper-slides>
                
              </div>
          </div>
          <div class="bar"></div>
          <!-- 오시는 길 -->
          <div class="exRode">
            <font-awesome-icon icon="map-marker-alt" class="location__icon" style="color:gray"/>
            <span class="ex__name"> 오시는 길 </span>
            <div class="rode">
                 <div id="map" style="width:270px;height:220px;"></div>
            </div>
           
          </div>
      </div>
  </div>
</template>

<script>
import Navi from '@/components/Common/Navi.vue';
import http from "@/util/http-common";
import { VueperSlides, VueperSlide } from 'vueperslides'
import 'vueperslides/dist/vueperslides.css'
function handleNavi() {
  const navbar = document.querySelector('.exDetial__navi');
  const navbarHeight = navbar.getBoundingClientRect().height;
  if(window.scrollY > navbarHeight){
    navbar.style.background="#272626";
  }
  else{
    navbar.style.background="transparent";
  }
}
export default {
    name: "ExhibitDetail",
    components: {
        Navi,
        VueperSlides, VueperSlide
    },
    destroyed(){
        document.removeEventListener('scroll',handleNavi);
        this.observer.disconnect();
    },
    mounted(){
        document.addEventListener('scroll',handleNavi);
        const target=document.querySelector(".bm-menu");
        // console.log(target);
        this.observer = new MutationObserver((mutations)=>{
            mutations.forEach((mutation)=>{
                if(mutation.target.classList.contains('moveRight')){
                document.querySelector('.goBack__button').style.display = 'none';          
                } else {
                document.querySelector('.goBack__button').style.display = 'block';
                }

            })
        })
        const config = {attributes: true, childList: true, characterData: true};
        this.observer.observe(target,config);

        // if (window.kakao && window.kakao.maps) {
        //     this.initMap();
        // } else {
        //     const script = document.createElement('script');
        //     /* global kakao */
        //     script.onload = () => kakao.maps.load(this.initMap);
        //     script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8c64ae9266e5ca128223c03d5686eed0';
        //     document.head.appendChild(script);
        // }
    },
    data() {
        return {
            id: 0,
            exhibit:{
                artist:"",
                description:"",
                startDate:"",
                endDate:"",
                exImg:"",
                feedCnt:0,
                location:"",
                name:"",
                scrapCnt:0,
                scrapmark:0,
                tagList:[],
                vrLink:""
            },
            userInfo:{
                userId:"",
            },
            feed:[],
            showArtist: false,
            showDes: false,
            vr:false,
            slides: [
            ]
        };
    },
    created() {
        this.userInfo =  this.$store.getters.getUser;
        this.id = this.$route.params.id;
        console.log("처음! : "+this.userInfo, this.id);
        // 피드 가져오는 곳
        http
        .get(`api/exhibit/feed/${this.id}`) 
        .then(res => {
            this.feed = res.data;
            console.log("이미지: "+res.data.feedImg);
        })
        .catch(err => {
            console.error(err);
            console.log("에러!!!");
        });

        // 상세 데이터 가져오는 곳
        http
        .get(`api/exhibit/${this.userInfo.userId}/${this.id}`) //${this.id}
        .then(res => {
            console.log("데이터야 : "+res.data.name);
            this.exhibit = res.data;
            console.log("솔묭ㅋㅋ"+this.exhibit.vrLink);
        })
        .catch(err => {
            console.error(err);
        });

        
    },
    methods:{
        toggleArtistShow(){
            this.showArtist = !this.showArtist;
        },
        toggleDesShow(){
            this.showDes = !this.showDes;
        },
        addScrap:function(scrap, exid){
            this.exhibit.scrapmark = ! this.exhibit.scrapmark;
            if(scrap == 0){ // 스크랩 안눌린 상태 
                this.exhibit.scrapCnt ++;
                http
                .put(`api/scrapbook/${this.userInfo.userId}/${exid}`)
                .then((data) => {
                    console.log(data); 
                    if (data) {
                        // alert('스크랩');
                    } else {
                        alert('오류가 발생하였습니다.');
                    }
                })
                .catch((err) => console.log(err));
            }else if(scrap == 1){ // 스크랩 눌린 상태 
                this.exhibit.scrapCnt --;
                http
                .delete(`api/scrapbook/${this.userInfo.userId}/${exid}`)
                .then((data) => {
                    console.log(data); 
                    if (data) {
                        // alert('스크랩 취소..');
                    } else {
                        alert('오류가 발생하였습니다.');
                    }
                })
                    .catch((err) => console.log(err));
            }
        },
        goUserFeedDetail(feedno){ // 피드 게시물로 이동
            console.log(feedno);
            this.$router.replace({
                name: "UserFeedDetail",
                params: {feedno: feedno, status:"ExhibitionDetail"}
            });
        },
        goBack:function(){ // 들어온 테마 전시회 리스트로
            this.$router.push('/exhibit');
        },
        initMap() {
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
                };  

            // 지도를 생성합니다    
            var map = new kakao.maps.Map(mapContainer, mapOption); 

            // 주소-좌표 변환 객체를 생성합니다
            var geocoder = new kakao.maps.services.Geocoder();

            // 주소로 좌표를 검색합니다
            geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {

            // 정상적으로 검색이 완료됐으면 
            if (status ===    kakao.maps.services.Status.OK) {

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

        }
    }
}
</script>

<style scoped>
@import "../../components/css/style.css";
    .exDetial__navi{
        width: 380px;
    }
    .exdiv_detail{
        width: 380px;
        height: 100%;
        text-align: center;
        margin: 0 auto;
        padding-bottom: 40px;
        background-color: #313030;
    }
    .detail{
        padding-top: 40px;
        width: 340px;
        text-align: center;
        margin: 0 auto;
    }
    .exDetial__navi >>> .bm-burger-bars {
        background-color: white;
    }
    .exDetial__navi >>> .navi_item{
        color: white;
    }
    .exPoster{
        padding-top: 10px;
    }
    .poster_img{
        width: 280px;
        text-align: center;
        margin: 0 auto;
        margin-bottom: 25px;
    }
    .bar{
        padding-top: 10px;
        border-bottom: solid 0.1px #B9B9B9;
        clear: both;
    }
    .exContent, .exReview, .exRode{
        padding-top: 10px;
        text-align: left;
        font-size: 20px;
    }
    .exFeeds{
        padding-bottom: 30px;
    }
    .ex__name{
        font-size: 20px;
        color: white;
        display: inline-block;
        width: 260px;
        margin-left: 5px;
        vertical-align: top;
    }
    .exContent, .exReview, .exRode{
        width: 320px;
        padding-top: 20px;
        margin: 0 auto;
    }
    .ex__date, .ex__area, .ex__artist, .ex__text{
        height: auto;
        margin-bottom: 12px;
        
    }
    .day, .area, .artist, .text{
        color: #FFFFFF;
        font-size: 14px;
        float: left;
        padding-left: 10px;
        border-left: solid 3px #A593DF;
    }
    .days, .areas, .artists, .texts{
        padding-left: 10px;
        color: #FFFFFF;
        font-size: 14px;
        height: auto;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: normal; 
        line-height: 1.25; 
        text-align: left; 
        word-wrap: break-word; 
        display: -webkit-box; 
        -webkit-line-clamp: 3; 
        -webkit-box-orient: vertical;
    }
    .artistTogle, .textTogle{
        padding-left: 55px;
        color: #FFFFFF;
        font-size: 14px;
        height: auto;
        line-height: 1.25; 
    }
    .feeds{
        color: #FFFFFF;
        font-size: 12px;
    }
    .mention{

        margin-left: 10px;
        line-height: 1; 
    }
    .review__list{
        margin-top: 10px;
        line-height: 100px;
    }
    .img{
        padding-top: 20px;
        margin: 0 auto;
        width: 270px;
        height: 80px;
    }
    .vueperslides__arrow {
        color : #A593DF
    }
    .vueperslides__arrow svg {
        padding: 25px;
    }
    .tag{
        margin-bottom: 10px;
    }
    .tags{
        font-size: 12px;
        color: #B9B9B9;
        border:1px solid #B9B9B9;
        margin-right: 10px;
        border-radius: 15px;
        padding: 2px;
        padding-left: 4px;
        padding-right: 4px;
        text-align: center;
    }
    .feed_img{
        width: 80px;
        height: 80px;
        border-radius: 5px;
        vertical-align: middle;
        margin-right: 13px;
    }
    .rode{
        display: block;
        width: 270px;
        text-align: center;
        margin: 0 auto;
        padding-top: 10px;
    }
    .scrapCnt{
        float: right;
        font-size: 12px;
        color: white;
        margin-top: 5px;
    }
    .more{
        color: #A593DF;
        border: 1px solid #A593DF;
        font-size: 11px;
        line-height: 13px;
        margin-top: 7px;
        margin-bottom: 10px;
        margin-left: 130px;
    }
    .more:hover{
        background-color: #A593DF;
        color:whitesmoke;
    }
    .mores{
        background-color: #A593DF;
        color:whitesmoke;
        font-size: 11px;
        line-height: 13px;
        margin-top: 2px;
        margin-bottom: 10px;
    }
    .btns > .mores{
        margin-left: 126px;
    }
    .btns{
        height: auto;
        margin-left: 110px;
    }
    .swiper-container{
        width: 320px;
        height: 100px;
    }
    .goBack__button {
        font-size:22px;
        position:relative;
        z-index:3;
        top:24px;
        margin-left:10px;
        margin-right: 340px;
        color: #FFFFFF;
    }
    .vr{
        font-size: 12px;
        line-height: 14px;
    }
    .vrgo{
        color: #FFFFFF;
    }
    .vrgo:hover{
        color: black;
        text-decoration:none;
    }

/* ------------------------------ 커질때반응형 ------------------------------ */
@media screen and (min-width: 1024px) {
  .feedLine {
    width: 760px;
  }
  .exDetial__navi{
        width: 760px;
    }
    .exdiv_detail{
        width: 760px;
    }
    .detail{
        padding-top: 70px;
        width: 720px;
        text-align: center;
        margin: 0 auto;
    }
    .goBack__button{
        margin-right: 720px;
    }
    .poster_img{
        width: 400px;
    }
    .exContent, .exReview, .exRode{
        width: 700px;
    }
    .ex__name{
        width: 500px;
    }
    .rode{
        width: 600px;
    }
    .img{
        width: 680px;
        height: 200px;
    }
    .feed_img{
        width: 200px;
        height: 200px;
        border-radius: 5px;
        vertical-align: middle;
        margin-right: 13px;
    }
    .more{
        margin-left: 500px;
    }
    .btns > .mores{
        margin-left: 500px;
    }
    
}
</style>