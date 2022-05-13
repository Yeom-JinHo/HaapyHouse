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
        <router-link class="nav-link" to="/main">
          <i class="now-ui-icons location_pin"></i>
          <p>아파트 살펴보기</p>
        </router-link>
      </li>

      <li class="nav-item">
        <router-link class="nav-link" to="/login">
          <i class="now-ui-icons users_circle-08"></i>
          <p>로그인</p>
        </router-link>
      </li>

      <drop-down
        tag="li"
        title="유저정보"
        icon="now-ui-icons design_image"
        class="nav-item"
      >
        <nav-link to="/logout">
          <i class="now-ui-icons education_paper"></i> Landing
        </nav-link>
        <nav-link to="/profile">
          <i class="now-ui-icons users_single-02"></i> Profile
        </nav-link>
      </drop-down>
    </template>
  </navbar>
</template>

<script>
import { DropDown, Navbar, NavLink } from "@/components";
import { Popover } from "element-ui";
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
};
</script>

<style scoped></style>
