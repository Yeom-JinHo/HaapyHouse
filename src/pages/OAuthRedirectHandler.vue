<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <div slot="header" class="logo-container">카카오 로그인중!</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { mapActions } from "vuex";
import userApi from "@/apis/userApi.js";
export default {
  methods: {
    ...mapActions("userStore", ["login", "join"]),
    async getAccessToken() {
      const authCode = this.$route.query.code;
      const requestURL = `https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=${process.env.VUE_APP_KAKAO_LOGIN_API_KEY}&redirect_uri=http://localhost:8080/oauth&code=${authCode}`;
      const config = {
        headers: {
          "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
        },
      };
      const res = await axios.post(requestURL, config);
      console.log("GET_OAUTH_CODE", res);
      return res.data.access_token;
    },
    async getKakaoInfo(ACCESS_TOKEN) {
      window.Kakao.Auth.setAccessToken(ACCESS_TOKEN);

      const res = await window.Kakao.API.request({
        url: "/v2/user/me",
      });
      console.log("GET_KAKAO_INFO", res);

      return res;
    },
  },
  async created() {
    const ACCESS_TOKEN = await this.getAccessToken();
    const kakaoUserInfo = await this.getKakaoInfo(ACCESS_TOKEN);
    console.log(kakaoUserInfo);
    const res = await userApi.get(
      "/idCheck?userId=" + kakaoUserInfo.id + "&socialType=kakao"
    );
    // 가입 안된거
    if (res.status == 200) {
    } else {
      // 가입된거임
    }
    console.log(res);
  },
};
</script>
<style scoped>
@keyframes spin {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
.loader_refresh {
  animation: spin 2s linear infinite;
}
</style>
