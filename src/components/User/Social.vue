<template>
  <div>
    <h5 id="or-bar">또는</h5>
    <div class="social">
      <button @click="startKakao" class="btn" id="kakao-btn">
        <img src="img/kakao.svg" class="social-icon kakao" />
        <span>카카오 계정으로 로그인</span>
      </button>
      <button @click="startNaver" class="btn" id="naver-btn">
        <img src="img/naver.svg" class="social-icon naver" />
        <span>네이버 계정으로 로그인</span>
      </button>
      <div
        id="g_id_onload"
        data-client_id="500435208160-mah8jeah7glj4i1h7i1edf5fip99o5e7.apps.googleusercontent.com"
        data-callback="startGoogle"
      ></div>
      <div class="btn" id="google-container">
        <div
          class="g_id_signin"
          data-type="standard"
          data-width="300"
          data-logo_alignment="center"
        ></div>
      </div>
    </div>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import { mapActions, mapMutations } from "vuex";
export default {
  created() {
    window.startGoogle = this.startGoogle;
    this.loadScript();
  },
  methods: {
    ...mapActions("userStore", ["socialLogin"]),
    ...mapMutations("msgStore", ["SET_SUCCESS_MSG"]),
    async loadScript() {
      let scriptEl = document.createElement("script");
      scriptEl.setAttribute("src", "https://accounts.google.com/gsi/client");
      document.getElementsByTagName("head")[0].appendChild(scriptEl);
    },
    startKakao() {
      console.log(process.env.VUE_APP_KAKAO_LOGIN_API_KEY);
      const REST_API_KEY = process.env.VUE_APP_KAKAO_LOGIN_API_KEY;
      const REDIRECT_URI = `http://localhost:8080/oauth/kakao`;
      const requestURL = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
      window.location.href = requestURL;
    },
    startNaver() {
      console.log(process.env.VUE_APP_NAVER_API_KEY);
      const API_KEY = process.env.VUE_APP_NAVER_API_KEY;
      const REDIRECT_URI = `http://localhost:8080/oauth/naver`;
      const requestURL = `https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=${API_KEY}&redirect_uri=${REDIRECT_URI}&state=1234`;
      window.location.href = requestURL;
    },
    async startGoogle(googleUser) {
      console.log(jwt_decode(googleUser.credential));
      const googleUserInfo = jwt_decode(googleUser.credential);
      const type = await this.socialLogin({
        userId: googleUserInfo.email,
        nickName: googleUserInfo.name,
        profileImg: googleUserInfo.picture,
        socialType: "google",
      });
      let msg;
      if (type == "join") {
        msg = "회원가입에 성공하였습니다.";
      } else if (type == "login") {
        msg = "로그인에 성공하였습니다.";
      }
      this.$router.push("/");
      this.SET_SUCCESS_MSG({ visible: true, msg });
    },
  },
};
</script>

<style lang="scss">
.social {
  @mixin customBtn {
    width: 300px;
    font-family: "Google Sans", arial, sans-serif;
    font-size: 1em;
    font-weight: 600;
    img {
      margin-right: 10px;
    }
  }
  #kakao-btn {
    @include customBtn();
    background-color: #f9e000;
    color: black;
  }
  #naver-btn {
    @include customBtn();

    background-color: #04cf5c;
  }
  #google-container {
    display: flex;
    justify-content: center;
    align-items: center;

    padding: 0;

    background-color: rgba($color: #000000, $alpha: 0);
  }
  .g_id_signin {
    margin: 0;
  }
}
</style>
