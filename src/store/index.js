import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

import msgStore from "@/store/modules/msgStore.js";
import aptStore from "@/store/modules/aptStore.js";
import userStore from "@/store/modules/userStore.js";

const store = new Vuex.Store({
  modules: {
    msgStore,
    aptStore,
    userStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

export default store;
