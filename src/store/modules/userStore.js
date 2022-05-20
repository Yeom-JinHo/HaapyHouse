import jwt_decode from "jwt-decode";
import userApi from "../../apis/userApi";

const userStore = {
  namespaced: true,
  state: {
    isLogin: true,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async login({ commit }, user) {
      console.log("userSotre LOGIN", user);
      console.log(JSON.stringify(user));
      const res = await userApi.post("/login", user);
      console.log(res);
      if (res.data.message === "success") {
        let token = res.data["access-token"];
        commit("SET_IS_LOGIN", true);
        commit("SET_IS_LOGIN_ERROR", false);
        sessionStorage.setItem("access-token", token);
      } else {
        commit("SET_IS_LOGIN", false);
        commit("SET_IS_LOGIN_ERROR", true);
      }
    },
    async getUserInfo({ commit }) {
      const token = sessionStorage.getItem("access-token");
      const decode_token = jwt_decode(token);
      console.log(decode_token);
      let config = {
        headers: {
          "access-token": token,
        },
      };
      const res = await userApi.get(`/userinfo/${decode_token.userid}`, config);
      console.log(res);
      if (res.data.message === "success") {
        commit("SET_USER_INFO", res.data.userInfo);
      } else {
        console.log("유저 정보 없음!!");
      }
    },
    async join({ dispatch }, user) {
      console.log("userStore JOIN", user);
      const res = await userApi.post("insert", user);
      console.log("join", res);

      dispatch("login", {
        userId: user.userId,
        userPwd: user.userPwd,
        socialType: user.socialType,
      });
    },
  },
};

export default userStore;
