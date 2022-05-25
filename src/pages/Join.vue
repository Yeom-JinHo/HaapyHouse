<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div class="content" id="custom-content">
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
                  ref="id"
                />
              </div>
              <button
                class="btn btn-primary btn-round btn-lg btn-block"
                @click="chkID"
                ref="chkID"
              >
                중복확인
              </button>
              <div class="input-group">
                <input
                  type="password"
                  class="form-control"
                  placeholder="비밀번호를 입력해주세요.."
                  v-model="password"
                  ref="password"
                />
              </div>
              <div class="input-group">
                <input
                  type="password"
                  class="form-control"
                  placeholder="비밀번호를 한번 더 입력해주세요.."
                  v-model="chkPassword"
                  ref="chkPassword"
                />
              </div>
              <div class="input-group">
                <input
                  type="text"
                  class="form-control"
                  placeholder="닉네임을 입력해주세요.."
                  v-model="nickname"
                  ref="nickname"
                />
              </div>
            </div>
            <template slot="raw-content">
              <div class="card-footer text-center">
                <button
                  @click="joinUser"
                  class="btn btn-primary btn-round btn-lg btn-block"
                >
                  회원가입
                </button>
              </div>
              <!-- <social /> -->
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
import userApi from "@/apis/userApi.js";
import Social from "../components/User/Social.vue";
import { mapMutations, mapActions, mapState } from "vuex";
export default {
  name: "join-page",
  bodyClass: "join-page",
  components: {
    Card,
    MainFooter,
    [Button.name]: Button,
    // Social,
  },
  data() {
    return {
      id: "",
      password: "",
      chkPassword: "",
      nickname: "",
      validId: "",
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin"]),
  },
  methods: {
    ...mapMutations("msgStore", [
      "SET_WARNING_MSG",
      "SET_SUCCESS_MSG",
      "SET_DANGER_MSG",
    ]),
    ...mapActions("userStore", ["join", "getUserInfo"]),
    async chkID() {
      if (!this.id) {
        this.$refs.id.focus();
        this.SET_WARNING_MSG({ visible: true, msg: "아이디를 입력해주세요" });
        return;
      }
      try {
        this.validId = "";
        const res = await userApi.get(
          "/idCheck?userId=" + this.id + "&socialType=general"
        );
        console.log(res);
        if (res.status == 200) {
          this.SET_SUCCESS_MSG({
            visible: true,
            msg: "사용 가능한 아이디입니다.",
          });
          this.validId = this.id;
        }
      } catch (e) {
        console.log(e);
        this.SET_WARNING_MSG({
          visible: true,
          msg: "이미 사용중인 아이디입니다.",
        });
      }
    },
    async joinUser() {
      let err = true;
      let msg = "";
      const passwordReg = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
      !this.id &&
        ((msg = "아이디를 입력해주세요"), //
        (err = false),
        this.$refs.id.focus());
      err &&
        this.validId != this.id &&
        ((msg = "아이디 중복확인을 해주세요"),
        (err = false),
        this.$refs.chkID.focus());
      err &&
        !this.password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false), //
        this.$refs.password.focus());
      err &&
        !this.password &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false), //
        this.$refs.password.focus());
      err &&
        !passwordReg.test(this.password) &&
        ((msg = "비밀번호를 8자이상 숫자 대소문자를 포함해주세요"),
        (err = false), //
        this.$refs.password.focus());
      err &&
        this.password != this.chkPassword &&
        ((msg = "비밀번호 두개가 일치하지 않아요."),
        (err = false), //
        this.$refs.chkPassword.focus());
      if (!err) {
        this.SET_WARNING_MSG({ visible: true, msg });
      } else {
        await this.join({
          userId: this.id,
          userPwd: this.password,
          nickName: this.nickname,
          socialType: "general",
        });
        console.log("join 후 isLogin 상태", this.isLogin);
        if (this.isLogin) {
          await this.getUserInfo("general");
          this.$router.push("/");
          this.SET_SUCCESS_MSG({
            visible: true,
            msg: "회원가입에 성공하였습니다.",
          });
        } else {
          this.SET_DANGER_MSG({
            visible: true,
            msg: "회원가입에 실패하였습니다.",
          });
        }
      }
      console.log("JOINUSE END__");
    },
  },
};
</script>
<style lang="scss">
#or-bar {
  &::before,
  &::after {
    content: "";
    border-top: 1px solid white;
    width: 40%;
    display: inline-block;
    margin: 4px;
  }
}

#custom-content {
  margin-top: 8%;
}
</style>
