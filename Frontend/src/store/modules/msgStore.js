import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const msgStore = {
  namespaced: true,
  state: {
    successMsg: {
      visible: false,
      msg: "",
    },
    infoMsg: {
      visible: false,
      msg: "",
    },
    warningMsg: {
      visible: false,
      msg: "",
    },
    dangerMsg: {
      visible: false,
      msg: "",
    },
  },
  getters: {
    getSuccessVisible(state) {
      return state.successMsg.visible;
    },
    getInfoVisible(state) {
      return state.infoMsg.visible;
    },
    getWarningVisible(state) {
      return state.warningMsg.visible;
    },
    getDangerVisible(state) {
      return state.dangerMsg.visible;
    },

    getSuccessMsg(state) {
      return state.successMsg.msg;
    },
    getInfoMsg(state) {
      return state.infoMsg.msg;
    },
    getWarningMsg(state) {
      return state.warningMsg.msg;
    },
    getDangerMsg(state) {
      return state.dangerMsg.msg;
    },
  },
  mutations: {
    SET_SUCCESS_MSG(state, { visible, msg }) {
      this.commit("msgStore/CLEAR_ALL_MSG");
      Vue.set(state.successMsg, "visible", visible);
      Vue.set(state.successMsg, "msg", msg || "");
    },
    SET_INFO_MSG(state, { visible, msg }) {
      this.commit("msgStore/CLEAR_ALL_MSG");
      Vue.set(state.infoMsg, "visible", visible);
      Vue.set(state.infoMsg, "msg", msg || "");
    },
    SET_WARNING_MSG(state, { visible, msg }) {
      this.commit("msgStore/CLEAR_ALL_MSG");
      Vue.set(state.warningMsg, "visible", visible);
      Vue.set(state.warningMsg, "msg", msg || "");
    },
    SET_DANGER_MSG(state, { visible, msg }) {
      this.commit("msgStore/CLEAR_ALL_MSG");
      Vue.set(state.dangerMsg, "visible", visible);
      Vue.set(state.dangerMsg, "msg", msg || "");
    },
    CLEAR_ALL_MSG(state) {
      Vue.set(state.successMsg, "visible", false);
      Vue.set(state.successMsg, "msg", "");
      Vue.set(state.infoMsg, "visible", false);
      Vue.set(state.infoMsg, "msg", "");
      Vue.set(state.warningMsg, "visible", false);
      Vue.set(state.warningMsg, "msg", "");
      Vue.set(state.dangerMsg, "visible", false);
      Vue.set(state.dangerMsg, "msg", "");
    },
  },
  actions: {},
  modules: {},
  plugins: [],
};

export default msgStore;
