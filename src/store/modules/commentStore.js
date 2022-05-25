import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const commentStore = {
  namespaced: true,
  state: {
    commentList: [],
  },
  getters: {
    getCommentList(state) {
      return state.commentList;
    },
  },
  mutations: {
    SET_COMMENT_LIST(state, newCommentList) {
      state.commentList = newCommentList;
    },
  },
  actions: {},
  modules: {},
  plugins: [],
};

export default commentStore;
