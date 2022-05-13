<template>
  <div>
    <div class="page-header clear-filter" filter-color="orange">
      <parallax
        class="page-header-image"
        style="background-image: url('img/korea.jpg')"
      >
      </parallax>
      <div class="container">
        <div class="content-center brand" id="home-info">
          <img class="n-logo" src="img/ssafy.png" alt="" />
          <h1 class="h1">HAPPY HOUSE.</h1>
          <h2>최강 광주 4반 최종 관통 프로젝트</h2>
          <h2>우리도 집을 살 수 있을까요?</h2>
        </div>
        <h6 class="category category-absolute">
          Designed by
          <a href="http://invisionapp.com/" target="_blank">
            <img src="img/invision-white-slim.png" class="invision-logo" /> </a
          >. Coded by
          <a href="https://www.creative-tim.com" target="_blank">
            <img
              src="img/creative-tim-white-slim2.png"
              class="creative-tim-logo"
            /> </a
          >.
        </h6>
      </div>
    </div>
    <carousel-section></carousel-section>
    <team-info />
  </div>
</template>
<script>
import { Parallax } from "@/components";
import CarouselSection from "./components/CarouselSection";
import TeamInfo from "./components/TeamInfo";
export default {
  name: "index",
  bodyClass: "index-page",
  components: {
    Parallax,
    CarouselSection,
    TeamInfo,
  },
};
</script>
<style></style>
