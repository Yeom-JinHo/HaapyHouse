<template>
  <navbar
    position="fixed"
    type="primary"
    :transparent="transparent"
    :color-on-scroll="colorOnScroll"
    menu-classes="ml-auto"
  >
    <template>
      <router-link v-popover:popover1 class="navbar-brand" to="/">
        SSAFY 7기 광주 4반
      </router-link>
      <el-popover
        ref="popover1"
        popper-class="popover"
        placement="bottom"
        width="100"
        trigger="hover"
      >
        <div class="popover-body">다올쌤 최고!!</div>
      </el-popover>
    </template>
    <template slot="navbar-menu">
      <li class="nav-item">
        <router-link class="nav-link" to="/apt">
          <i class="now-ui-icons location_pin"></i>
          <p class="inline">아파트 살펴보기</p>
        </router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/news">
          <i class="now-ui-icons text_caps-small"></i>
          <p class="inline">뉴스</p>
        </router-link>
      </li>
      <li class="nav-item">
        <router-link class="nav-link" to="/board">
          <i class="now-ui-icons text_caps-small"></i>
          <p class="inline">게시판</p>
        </router-link>
      </li>

      <li class="nav-item" v-if="!isLogin">
        <router-link class="nav-link" to="/login">
          <i class="now-ui-icons users_circle-08"></i>
          <p>로그인</p>
        </router-link>
      </li>

      <li class="nav-item" v-if="!isLogin">
        <router-link class="nav-link" to="/join">
          <i class="now-ui-icons objects_spaceship"></i>
          <p>회원가입</p>
        </router-link>
      </li>

      <drop-down
        tag="li"
        title="내정보"
        icon="now-ui-icons users_single-02"
        class="nav-item"
        v-if="isLogin"
      >
        <button @click="logout" class="dropdown-item">로그아웃</button>
        <nav-link to="/profile">
          <i class="now-ui-icons users_single-02"></i>
          프로필
        </nav-link>
      </drop-down>
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from "@/components";
import { Popover } from "element-ui";
import { mapMutations, mapState } from "vuex";

export default {
  name: "main-navbar",
  props: {
    transparent: Boolean,
    colorOnScroll: Number,
  },
  components: {
    DropDown,
    Navbar,
    NavLink,
    [Popover.name]: Popover,
  },
  methods: {
    ...mapMutations("userStore", ["SET_IS_LOGIN", "SET_USER_INFO"]),
    ...mapMutations("msgStore", ["SET_SUCCESS_MSG"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push("/");
      this.SET_SUCCESS_MSG({ visible: true, msg: "로그아웃 되었습니다!" });
    },
  },
  computed: {
    ...mapState("userStore", ["isLogin"]),
  },
};
</script>

<style scoped>
nav {
  font-size: 1.5em;
}
</style>
