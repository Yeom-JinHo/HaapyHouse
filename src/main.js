/*!

 =========================================================
 * Vue Now UI Kit - v1.1.0
 =========================================================

 * Product Page: https://www.creative-tim.com/product/now-ui-kit
 * Copyright 2019 Creative Tim (http://www.creative-tim.com)

 * Designed by www.invisionapp.com Coded by www.creative-tim.com

 =========================================================

 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

 */
import Vue from "vue";
import App from "./App.vue";
// You can change this import to `import router from './starterRouter'` to quickly start development from a blank layout.
import router from "./router";
import NowUiKit from "./plugins/now-ui-kit";
import VueTyperPlugin from "vue-typer";
import store from "./store";

Vue.config.productionTip = false;

Vue.use(NowUiKit);
Vue.use(VueTyperPlugin);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");

router.beforeEach(function (to, from, next) {
  store.commit("SET_SUCCESS_MSG", { visible: false });
  store.commit("SET_WARNING_MSG", { visible: false });
  store.commit("SET_INFO_MSG", { visible: false });
  store.commit("SET_DANGER_MSG", { visible: false });
  next();
});
