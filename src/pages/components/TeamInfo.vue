<template>
  <div class="section section-team text-center">
    <div class="container">
      <h2 class="title">싸피 광주 4반 개발자!</h2>
      <div class="team">
        <div class="row">
          <div
            class="col-md-4"
            v-for="(developer, index) in developers"
            :key="index"
          >
            <div class="team-player">
              <img
                :src="developer.src"
                :alt="developer.name"
                class="rounded-circle img-fluid img-raised"
              />
              <h4 class="title">{{ developer.name }}</h4>
              <p class="category text-primary">{{ developer.position }}</p>
              <p class="description">{{ developer.desc }}</p>
              <a href="#pablo" class="btn btn-primary btn-icon btn-round"
                ><i class="fab fa-github"></i
              ></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      developers: [
        {
          name: "염진호",
          position: "프론트엔드 개발자",
          desc: "짱짱맨",
          src: "img/default-avatar.png",
        },
        {
          name: "이민재",
          position: "백엔드 개발자",
          desc: "짱짱맨",
          src: "img/default-avatar.png",
        },
        {
          name: "홍준표",
          position: "백엔드 개발자",
          desc: "짱짱맨",
          src: "img/default-avatar.png",
        },
      ],
    };
  },
};
</script>

<style></style>
