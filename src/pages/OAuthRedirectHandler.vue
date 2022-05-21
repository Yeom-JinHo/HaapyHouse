<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <div slot="header" class="logo-container">
            {{ this.$route.params.socialType }} 로그인중!
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
import { mapActions, mapMutations } from "vuex";
import userApi from "@/apis/userApi.js";
export default {
  methods: {
    ...mapActions("userStore", ["socialLogin"]),
    ...mapMutations("msgStore", ["SET_SUCCESS_MSG"]),
    moveHome(type) {
      let msg = "";
      if (type == "join") {
        msg = "회원가입에 성공하였습니다.";
      } else if (type == "login") {
        msg = "로그인에 성공하였습니다.";
      }
      this.$router.push("/");
      this.SET_SUCCESS_MSG({ visible: true, msg });
    },
    async kakao() {
      const ACCESS_TOKEN = await this.getKakaoAccessToken();
      const kakaoUserInfo = await this.getKakaoInfo(ACCESS_TOKEN);
      console.log(kakaoUserInfo);
      const result = await this.socialLogin({
        userId: kakaoUserInfo.id,
        nickName: kakaoUserInfo.kakao_account.profile.nickname,
        profileImg: kakaoUserInfo.kakao_account.profile.profile_image_url,
        socialType: "kakao",
      });

      this.moveHome(result);
    },
    async getKakaoAccessToken() {
      const authCode = this.$route.query.code;
      const requestURL = `https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=${process.env.VUE_APP_KAKAO_LOGIN_API_KEY}&redirect_uri=http://localhost:8080/oauth/kakao&code=${authCode}`;
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
    async naver() {
      const ACCESS_TOKEN = await this.getNaverAccessToken();
      const NaverUserInfo = await this.getNaverInfo(ACCESS_TOKEN);
      console.log(NaverUserInfo);
      const result = await this.socialLogin({
        userId: NaverUserInfo.id,
        nickName: NaverUserInfo.nickname,
        profileImg: NaverUserInfo.profile_image,
        socialType: "naver",
      });
      this.moveHome(result);
    },
    async getNaverAccessToken() {
      const authCode = this.$route.query.code;
      const requestURL = `/oauth2.0/token?grant_type=authorization_code&client_id=${process.env.VUE_APP_NAVER_API_KEY}&client_secret=${process.env.VUE_APP_NAVER_API_SECRET_KEY}&code=${authCode}&state=1234`;
      const config = {
        headers: {
          "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
        },
      };
      const res = await axios.post(requestURL, config);
      console.log("GET_NAVER_ACCE", res);
      return res.data.access_token;
    },
    async getNaverInfo(ACCESS_TOKEN) {
      console.log("NAVER_INFO", ACCESS_TOKEN);
      const config = {
        headers: {
          Authorization: `Bearer ${ACCESS_TOKEN}`,
        },
      };
      const res = await axios.get("/v1/nid/me", config);

      console.log("GET_Naver_INFO", res);

      return res.data.response;
    },
  },
  async mounted() {
    const socialType = this.$route.params.socialType;
    console.log(socialType);
    if (socialType == "kakao") {
      this.kakao();
    } else if (socialType == "naver") {
      this.naver();
    }
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
