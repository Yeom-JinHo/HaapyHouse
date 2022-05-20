<template>
  <div>
    <h5 id="or-bar">또는</h5>
    <div class="social">
      <button @click="startKakao" class="btn" id="kakao-btn">
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
export default {
  methods: {
    startKakao() {
      console.log(process.env.VUE_APP_KAKAO_LOGIN_API_KEY);
      const REST_API_KEY = process.env.VUE_APP_KAKAO_LOGIN_API_KEY;
      const REDIRECT_URI = `http://localhost:8080/oauth`;
      const requestURL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
      window.location.href = requestURL;
    },
  },
};
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
