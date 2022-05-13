<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div class="content">
      <div class="container">
        <div class="col-md-5 ml-auto mr-auto">
          <card type="login" plain>
            <img class="n-logo" src="img/ssafy.png" alt="" />
            <div class="form-group no-border input-lg input-group">
              <div class="input-group">
                <div class="input-group-prepend">
                  <i class="now-ui-icons users_circle-08 input-group-text"></i>
                </div>
                <input class="form-control" />
                <div class="input-group-append">
                  <i class="now-ui-icons users_circle-08 input-group-text"></i>
                </div>
              </div>
            </div>

            <template slot="raw-content">
              <div class="card-footer text-center">
                <button class="btn btn-primary btn-round btn-lg btn-block">
                  Get Started
                </button>
              </div>
              <div class="pull-center">
                <h6>
                  <router-link to="/regist" class="link footer-link"
                    >Create Account</router-link
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
};
</script>
<style></style>
