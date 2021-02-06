import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home/Home.vue'
import About from '@/views/About/About.vue'
import Alarm from '@/views/Alarm/Alarm.vue'
import Error from '@/views/Error/Error.vue'
import Feed from '@/views/Feed/Feed.vue'
import Search from '@/views/Search/Search.vue'
import Join from '@/views/User/Join.vue'
import Login from '@/views/User/Login.vue'
import FindPw from '@/views/User/FindPw.vue'
import MyFeed from '@/views/MyFeed/MyFeed.vue'
import MyPage from "@/views/MyPage/MyPage.vue"
import UserFeed from '@/views/UserFeed/UserFeed.vue'
import Around from '@/views/Around/Around.vue'
import Exhibition from '@/views/Exhibition/Exhibit.vue'
import ScrapBook from '@/views/ScrapBook/Scrapbook.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/join',
    name: 'Join',
    component: Join,
    children:[
      {
        path: "",
        name: "JoinUserInfo",
        component: () => import("@/components/User/JoinUserInfo.vue")
      },
      {
        path: "favorite",
        name: "JoinUserFavorite",
        component: () => import("@/components/User/JoinUserFavorite.vue"),
        props: true
      },
      {
        path: "success",
        name: "JoinSuccess",
        component: () => import("@/components/User/JoinSuccess.vue")
      }
    ]
  },
  {
    path:"/find",
    name: "FindPw",
    component: FindPw,
    children:[
      {
        path: "",
        name: "FindPwUserInfo",
        component: ()=> import("@/components/User/FindPwUserInfo.vue")
      },
      {
        path: "success",
        name: "FindPwSuccess",
        component: ()=> import("@/components/User/FindPwSuccess.vue")
      },
    ]
  },
  {
    path: "/",
    name: "Login",
    component: Login
  },
  {
    path: "/mypage",
    name: "MyPage",
    component: MyPage,
    children:[
      {
        path: "", 
        name: "MyPageModify",
        component: () => import("@/components/MyPage/MyPageModify.vue")
      },
      {
        path: "myfavorite",
        name: "MyFavoriteModify",
        component: () => import("@/components/MyPage/FavoriteModify.vue")
      },
    ],
  },
  {
    path: "/myfeed",
    name: "MyFeed",
    component: MyFeed,
    children:[
      {
        path:"add",
        name:"MyFeedAdd",
        component: ()=> import("@/components/MyFeed/MyFeedAdd.vue")
      },
      {
        path:"modify",
        name:"MyFeedModify",
        component: ()=> import("@/components/MyFeed/MyFeedModify.vue")
      },
      {
        path:"detail/:feedno",
        name:"MyFeedView",
        component: ()=> import("@/components/MyFeed/MyFeedDetail.vue")
      },
      {
        path:"",
        name:"MyFeedList",
        component: ()=> import("@/components/MyFeed/MyFeedList.vue")
      },
      {
        path:"follow",
        name:"MyFollow",
        component: ()=> import("@/components/MyFeed/MyPageFollower_ing_List.vue")
      },
    ]
  },
  {
    path: "/feed",
    name: "Feed",
    component: Feed,
    children:[
      {
        path:"",
        name:"FeedList",
        component: ()=> import("@/components/Feed/FeedList.vue")
      },
      {
        path:"bookmark",
        name:"BookmarkList",
        component: ()=> import("@/components/Feed/BookmarkList.vue")
      },
      {
        path:"detail/:feedId",
        name:"FeedDetail",
        component: ()=> import("@/components/Feed/FeedDetail.vue")
      },
    ],
    redirect: () => {
      return "/feed";
    }
  },
  {
    path:"/alarm",
    name: "Alarm",
    component: Alarm,
    children:[
      {
        path:"",
        name: "AlarmAll",
        component: () => import("@/components/Alarm/Alarm.vue")
      }
    ]
  },
  {
    path: "/search",
    name: "Search",
    component: Search,
    children:[
      {
        path:"",
        name: "SearchMain",
        component: ()=> import("@/components/Search/SearchMain.vue")
      },
    ],
  },
  {
    path: "/error",
    name: "Error",
    component: Error
  },
  {
    path: "/userfeed",
    name: "UserFeed",
    component: UserFeed,
    children: [
      {
        path:"list/:userId",
        name:"UserFeedList",
        component: ()=> import("@/components/UserFeed/UserFeedList.vue")
      },
      {
        path:"detail/:feedno",
        name:"UserFeedDetail",
        component: ()=> import("@/components/UserFeed/UserFeedDetail.vue")
      },
    ],
  },
  {
    path: "/around",
    name: "Around",
    component: Around
  },
  {
    path: "/exhibit",
    name: "Exhibition",
    component: Exhibition,
    children: [
      {
        path:"list",
        name:"ExhibitionList",
        component: ()=> import("@/components/Exhibition/ExhibitList.vue")
      },
      {
        path:"detail/:exno",
        name:"ExhibitionDetail",
        component: ()=> import("@/components/Exhibition/ExhibitDetail.vue")
      },
    ],
  },
  {
    path: "/scrap",
    name: "ScrapBook",
    component: ScrapBook
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


export default router
