import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const aptStore = {
  namespaced: true,
  state: {
    sidoCode: "",
    gugunCode: "",
    dongCode: "",
  },
  getters: {},
  mutations: {
    SET_SIDO_CODE(state, code) {
      state.sidoCode = code;
    },
    SET_GUGUN_CODE(state, code) {
      state.gugunCode = code;
    },
    SET_DONG_CODE(state, code) {
      state.dongCode = code;
    },
  },
  actions: {},
  modules: {},
  plugins: [],
};

export default aptStore;
