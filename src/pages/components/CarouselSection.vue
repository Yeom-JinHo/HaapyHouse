<template>
  <div class="section" id="carousel">
    <div class="container">
      <div class="title">
        <h3>SSAFY 캠퍼스 근처 아파트 보기</h3>
        <h5>
          캠퍼스 이미지를 클릭하면 캠퍼스 근처 아파트 거래 현황조회가
          가능합니다.
        </h5>
      </div>
      <div class="row justify-content-center">
        <div class="col-8">
          <el-carousel height="500px">
            <el-carousel-item v-for="(cam, index) in campus" :key="index">
              <img
                class="d-block"
                @click="moveSearch"
                :src="cam.src"
                :alt="cam.alt"
              />
              <div class="carousel-caption d-none d-md-block">
                <h5>{{ cam.title }}</h5>
              </div>
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { Carousel, CarouselItem } from "element-ui";

export default {
  components: {
    [Carousel.name]: Carousel,
    [CarouselItem.name]: CarouselItem,
  },
  data() {
    return {
      campus: [
        {
          title: "서울캠퍼스 (멀티 스퀘어)",
          alt: "서울캠퍼스",
          src: "img/seoul.png",
        },
        {
          title: "광주캠퍼스 (삼성전자 광주사업장)",
          alt: "광주캠퍼스",
          src: "img/gwangju.png",
        },
        {
          title: "대전캠퍼스 (삼성화재 유성연수원)",
          alt: "대전캠퍼스",
          src: "img/daejeon.png",
        },
        {
          title: "구미 캠퍼스 (삼성전자 구미사업장)",
          alt: "구미 캠퍼스",
          src: "img/gumi.png",
        },
        {
          title: "부울경 캠퍼스 (삼성전기 부산사업장)",
          alt: "부울경 캠퍼스",
          src: "img/buulgyeong.png",
        },
      ],
    };
  },
  methods: {
    moveSearch() {
      // console.log("123");
    },
  },
};
</script>
<style scoped>
.d-block {
  width: 100%;
  height: 100%;
}
</style>
