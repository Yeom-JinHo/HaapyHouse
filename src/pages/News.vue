<template>
  <div>
    <div
      class="page-header clear-filter"
      id="custom-header"
      filter-color="orange"
    >
      <parallax
        class="page-header-image"
        style="background-image: url('img/korea.jpg')"
      >
      </parallax>
      <div class="container">
        <h1 class="title">뉴스</h1>
      </div>
    </div>
    <div class="section" id="custom-section">
      <div class="container">
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">
              아파트 관련 뉴스를 여기서 만나보세요!
            </h4>
          </div>
          <div class="col-md-10 ml-auto mr-auto">
            <div class="row collections">
              <div
                data-aos="fade-up"
                data-aos-duration="1000"
                id="news"
                class="col-md-6"
                v-for="(item, ind) in news"
                :key="ind"
                @click="link(item)"
              >
                <img :src="item.thumb" ref="test" class="img-raised" />
                <a :href="item.link" ref="link">{{ item.title }}</a>
                <p>{{ item.newsDate }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import boardApi from "@/apis/boardApi.js";
export default {
  name: "news",
  data() {
    return {
      news: [],
    };
  },
  async created() {
    const res = await boardApi.get("news");
    this.news = res.data.sort(() => Math.random() - Math.random());
  },
  methods: {
    link(news) {
      document.location.href = news.link;
    },
  },
};
</script>

<style lang="scss" scoped>
#news {
  img {
    width: 100%;
    height: 200px;
  }
  a {
    font-size: 2em;
  }
}
#news:hover {
  transform: scale(1.1);
  transition: ease-in-out;
  cursor: pointer;
}
</style>
