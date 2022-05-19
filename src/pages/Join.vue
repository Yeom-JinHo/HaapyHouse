<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="join" title="회원가입" plain>
            <img class="n-logo" slot="image" src="img/ssafy.png" alt="" />
            <div class="form-group no-border input-lg input-group">
              <div class="input-group">
                <input
                  class="form-control"
                  placeholder="아이디를 입력해주세요.."
                  v-model="id"
                />
              </div>
              <div class="input-group">
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
                <button class="btn btn-primary btn-round btn-lg btn-block">
                  회원가입
                </button>
              </div>
              <div class="pull-center">
                <h6>
                  <router-link to="/login" class="link footer-link"
                    >로그인</router-link
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
  name: "join-page",
  bodyClass: "join-page",
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
  },
  data() {
    return {
      id: "",
      password: "",
    };
  },
};
</script>
<style></style>
