<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div class="content">
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
                />
              </div>
              <div class="input-group input-password">
                <input
                  type="password"
                  class="form-control"
                  placeholder="비밀번호를 입력해주세요.."
                  v-model="password"
                />
              </div>
            </div>
            <template slot="raw-content">
              <div class="card-footer text-center">
                <button
                  @click="login"
                  class="btn btn-primary btn-round btn-lg btn-block"
                >
                  <i
                    v-if="loginWaiting"
                    class="now-ui-icons loader_refresh"
                  ></i>
                  <div v-else>로그인</div>
                </button>
              </div>
              <div class="pull-center">
                <h6>
                  <router-link to="/join" class="link footer-link"
                    >회원가입</router-link
                  >
                </h6>
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
export default {
  name: "login-page",
  bodyClass: "login-page",
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
  },
  data() {
    return {
      id: "",
      password: "",
      loginWaiting: false,
    };
  },
  methods: {
    login() {
      if (!this.loginWaiting) {
        this.loginWaiting = true;
        console.log("id : ", this.id, " password", this.password);
      } else {
        console.log("로그인 진행중입니담.. 잠시만요");
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
</style>
