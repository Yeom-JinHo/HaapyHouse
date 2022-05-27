<template>
  <div>
    <div class="page-header clear-filter" filter-color="orange">
      <parallax
        class="page-header-image"
        style="background-image: url('/img/boardHeader.jpg')"
      >
      </parallax>
      <div class="container">
        <h1 class="title">QnA 게시판</h1>
      </div>
    </div>
    <div class="custom-section section">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {
  name: "board",
  bodyClass: "board-page",
  components: {},
};
</script>
<style>
.custom-section {
  min-height: 70vh;
}
.custom-section .container {
  /* height: 100%; */
  min-height: 70vh;
}

.custom-section .container .table-container {
  /* height: 90%; */
  /* min-height: 63vh; */
}

.custom-section .container .table {
  /* height: 100%; */
  min-height: 63vh;
}
</style>
