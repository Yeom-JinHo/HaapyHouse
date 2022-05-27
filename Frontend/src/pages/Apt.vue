<template>
  <div>
    <div
      class="page-header clear-filter"
      filter-color="orange"
      id="custom-header"
    >
      <parallax
        class="page-header-image"
        style="background-image: url('img/boardHeader.jpg')"
      >
      </parallax>
      <div class="container">
        <h1 class="title">아파트 살펴보기</h1>
      </div>
    </div>
    <div id="custom-section">
      <div class="section">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss" scoped>
.wrapper {
  background-image: url("../../public/img/dummyApt.jpg");
  background-repeat: no-repeat;
}
#custom-header {
  min-height: 10vh;
}

#custom-section {
  min-height: 77vh;
}
</style>
