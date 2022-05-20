<template>
  <div>
    <h5 id="or-bar">또는</h5>
    <div class="social">
      <button class="btn" id="kakao-btn">
        <img src="img/kakao.svg" class="social-icon kakao" />카카오로 시작하기
      </button>
      <button class="btn" id="naver-btn">
        <img src="img/naver.svg" class="social-icon naver" />네이버로 시작하기</button
      ><button class="btn" id="google-btn">
        <img src="img/google.svg" class="social-icon google" />구글로 시작하기
      </button>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style lang="scss">
.social {
  .btn {
    width: 100%;
    font-weight: bold;
  }
  #kakao-btn {
    background-color: #f9e000;
    color: black;
  }
  #naver-btn {
    background-color: #04cf5c;
  }
  #google-btn {
    background-color: white;
    color: black;
  }
}
</style>
