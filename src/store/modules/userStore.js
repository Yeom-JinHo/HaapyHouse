import jwt_decode from "jwt-decode";
import userApi from "../../apis/userApi";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
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
      state.userInfo = userInfo;
    },
  },
  actions: {
    async socialLogin({ dispatch }, user) {
      try {
        const res = await userApi.get(
          "/idCheck?userId=" + user.userId + "&socialType=" + user.socialType
        );
        await dispatch("join", user);
        return "join";
      } catch (e) {
        await dispatch("login", user);
        return "login";
      }
    },
    async login({ commit, dispatch }, user) {
      const res = await userApi.post(
        "/login",
        user, //
        { withCredentials: true }
      );
      if (res.data.message === "success") {
        let token = res.data["access-token"];
        commit("SET_IS_LOGIN", true);
        commit("SET_IS_LOGIN_ERROR", false);
        sessionStorage.setItem("access-token", token);
        await dispatch("getUserInfo", user.socialType);
      } else {
        commit("SET_IS_LOGIN", false);
        commit("SET_IS_LOGIN_ERROR", true);
      }
    },
    async getUserInfo({ commit }, socialType) {
      const token = sessionStorage.getItem("access-token");
      const decode_token = jwt_decode(token);
      let config = {
        headers: {
          "access-token": token,
        },
      };
      const res = await userApi.get(
        `/userinfo/${decode_token.userid}?socialType=${socialType}`,
        config
      );
      if (res.data.message === "success") {
        commit("SET_USER_INFO", res.data.userInfo);
      } else {
      }
    },
    async join({ dispatch }, user) {
      const res = await userApi.post("insert", user);

      await dispatch("login", {
        userId: user.userId,
        userPwd: user.userPwd,
        socialType: user.socialType,
      });
    },
  },
};

export default userStore;
