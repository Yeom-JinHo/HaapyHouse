import Vue from "vue";
import Router from "vue-router";
import Home from "./pages/Home.vue";
import Tmp from "./pages/Tmp.vue";
import Login from "./pages/Login.vue";
import Profile from "./pages/Profile.vue";
import Apt from "./pages/Apt.vue";
import Join from "./pages/Join.vue";
import Board from "./pages/Board.vue";
import MainNavbar from "./layout/MainNavbar.vue";
import MainFooter from "./layout/MainFooter.vue";
import BoardList from "./components/Board/BoardList.vue";
import BoardRegist from "./components/Board/BoardRegist.vue";
import BoardDetail from "./components/Board/BoardDetail.vue";
import BoardModify from "./components/Board/BoardModify.vue";
import AptSearch from "./components/Apt/AptSearch.vue";
import AptDetail from "./components/Apt/AptDetail.vue";
import OauthRedirectHandler from "./pages/OAuthRedirectHandler.vue";
import News from "./pages/News.vue";

import store from "@/store/index.js";

Vue.use(Router);

export default new Router({
  linkExactActiveClass: "active",
  mode: "history",
  routes: [
    {
      path: "/",
      name: "home",
      components: { default: Home, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/tmp",
      name: "tmp",
      components: { default: Tmp, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/login",
      name: "login",
      components: { default: Login, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/profile",
      name: "profile",
      components: { default: Profile, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      beforeEnter: function (to, from, next) {
        if (store.state.userStore.isLogin) {
          next();
        } else {
          next("/");
          store.state.msgStore.dangerMsg = {
            visible: true,
            msg: "로그인을 해주세요",
          };
        }
      },
    },
    {
      path: "/apt",
      components: { default: Apt, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      children: [
        { path: "", component: AptSearch },
        { path: "detail/:aptCode", component: AptDetail },
      ],
    },
    {
      path: "/join",
      name: "join",
      components: { default: Join, header: MainNavbar },
      props: {
        header: { colorOnScroll: 400 },
      },
    },
    {
      path: "/board",
      components: { default: Board, header: MainNavbar, footer: MainFooter },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
      children: [
        {
          path: "",
          component: BoardList,
        },
        {
          path: "regist",
          component: BoardRegist,
          beforeEnter: function (to, from, next) {
            if (store.state.userStore.isLogin) {
              next();
            } else {
              next("/");
              store.state.msgStore.dangerMsg = {
                visible: true,
                msg: "로그인을 해주세요",
              };
            }
          },
        },
        {
          path: "modify/:no",
          component: BoardModify,
        },
        {
          path: "detail/:no",
          component: BoardDetail,
        },
      ],
    },
    {
      path: "/oauth/:socialType",
      components: {
        default: OauthRedirectHandler,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
    {
      path: "/news",
      components: {
        default: News,
        header: MainNavbar,
        footer: MainFooter,
      },
      props: {
        header: { colorOnScroll: 400 },
        footer: { backgroundColor: "black" },
      },
    },
  ],
  scrollBehavior: (to) => {
    if (to.hash) {
      return { selector: to.hash };
    } else {
      return { x: 0, y: 0 };
    }
  },
});
