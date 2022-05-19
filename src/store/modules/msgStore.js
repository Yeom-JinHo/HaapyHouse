import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const msgStore={
  namespaced:true,
  state: {
    successMsg: {
      visible: false,
      content: "",
    },
    infoMsg: {
      visible: false,
      content: "",
    },
    warningMsg: {
      visible: false,
      content: "",
    },
    dangerMsg: {
      visible: false,
      content: "",
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
      return state.successMsg.content;
    },
    getInfoMsg(state) {
      return state.infoMsg.content;
    },
    getWarningMsg(state) {
      return state.warningMsg.content;
    },
    getDangerMsg(state) {
      return state.dangerMsg.content;
    },
  },
  mutations: {
    SET_SUCCESS_MSG(state, { visible, msg }) {
      Vue.set(state.successMsg, "visible", visible);
      Vue.set(state.successMsg, "content", msg || "");
    },
    SET_INFO_MSG(state, { visible, msg }) {
      Vue.set(state.infoMsg, "visible", visible);
      Vue.set(state.infoMsg, "content", msg || "");
    },
    SET_WARNING_MSG(state, { visible, msg }) {
      Vue.set(state.warningMsg, "visible", visible);
      Vue.set(state.warningMsg, "content", msg || "");
    },
    SET_DANGER_MSG(state, { visible, msg }) {
      Vue.set(state.dangerMsg, "visible", visible);
      Vue.set(state.dangerMsg, "content", msg || "");
    },
    CLEAR_ALL_MSG(state) {
      Vue.set(state.successMsg, "visible", false);
      Vue.set(state.successMsg, "content", "");
      Vue.set(state.infoMsg, "visible", false);
      Vue.set(state.infoMsg, "content", "");
      Vue.set(state.warningMsg, "visible", false);
      Vue.set(state.warningMsg, "content", "");
      Vue.set(state.dangerMsg, "visible", false);
      Vue.set(state.dangerMsg, "content", "");
    },
  },
  actions: {},
  modules: {},
  plugins: [],
};

export default msgStore;
