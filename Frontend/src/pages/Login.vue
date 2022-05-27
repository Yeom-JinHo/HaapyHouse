<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div class="content" id="custom-content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" title="로그인" plain>
            <img class="n-logo" slot="image" src="img/ssafy.png" alt="" />
            <div class="form-group no-border input-lg input-group">
              <div class="input-group">
                <input
                  class="form-control"
                  placeholder="아이디를 입력해주세요.."
                  v-model="id"
                  ref="id"
                />
              </div>
              <div class="input-group input-password">
                <input
                  type="password"
                  class="form-control"
                  placeholder="비밀번호를 입력해주세요.."
                  v-model="password"
                  ref="password"
                />
              </div>
            </div>
            <template slot="raw-content">
              <div class="card-footer text-center">
                <button
                  @click="loginUser"
                  class="btn btn-primary btn-round btn-lg btn-block"
                >
                  <div>로그인</div>
                </button>
              </div>
              <social />
              <div class="pull-center">
                <router-link to="/join" class="link footer-link"
                  >회원가입</router-link
                >
              </div>
            </template>
          </card>
        </div>
      </div>
    </div>
    <main-footer></main-footer>
  </div>
</template>
<script>
import { Card, Button } from "@/components";
import MainFooter from "@/layout/MainFooter";
import { mapActions, mapMutations, mapState } from "vuex";
import Social from "../components/User/Social.vue";
export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    Card,
    MainFooter,
    Social,
    [Button.name]: Button,
  },
  data() {
    return {
      id: "",
      password: "",
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin"]),
  },
  methods: {
    ...mapMutations("msgStore", [
      "SET_INFO_MSG",
      "SET_WARNING_MSG",
      "SET_DANGER_MSG",
      "SET_SUCCESS_MSG",
    ]),
    ...mapActions("userStore", ["login", "getUserInfo"]),
    async loginUser() {
      let err = true;
      let msg = "";
      !this.id &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false), //
        this.$refs.password.focus());
      if (!err) {
        this.SET_WARNING_MSG({ visible: true, msg });
      } else {
        //여기서 로그인 처리
        try {
          await this.login({
            userId: this.id,
            userPwd: this.password,
            socialType: "general",
          });
          if (this.isLogin) {
            this.$router.push("/");
            this.SET_SUCCESS_MSG({
              visible: true,
              msg: "로그인에 성공하였습니다.",
            });
          } else {
            this.SET_DANGER_MSG({
              visible: true,
              msg: "아이디 혹은 비밀번호가 일치하지않습니다.",
            });
          }
        } catch (e) {
          console.log(e);
          this.SET_DANGER_MSG({
            visible: true,
            msg: "로그인 처리중 오류 발생!",
          });
        }
      }
    },
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

#custom-content {
  margin-top: 8%;
}
</style>
