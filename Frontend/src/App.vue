<template>
  <div id="app">
    <router-view name="header" />
    <div class="wrapper">
      <router-view />
    </div>
    <router-view name="footer" />
    <alert type="success" dismissible :visible="getSuccessVisible">
      <div class="alert-icon">
        <i class="now-ui-icons ui-2_like"></i>
      </div>
      <strong>성공!</strong> {{ getSuccessMsg }}
    </alert>
    <alert type="info" dismissible :visible="getInfoVisible">
      <div class="alert-icon">
        <i class="now-ui-icons travel_info"></i>
      </div>
      <strong>정보</strong> {{ getInfoMsg }}
    </alert>
    <alert type="warning" dismissible :visible="getWarningVisible">
      <div class="alert-icon">
        <i class="now-ui-icons ui-1_bell-53"></i>
      </div>
      <strong>주의</strong> {{ getWarningMsg }}
    </alert>

    <alert type="danger" dismissible :visible="getDangerVisible">
      <div class="alert-icon">
        <i class="now-ui-icons objects_support-17"></i>
      </div>
      <strong>실패</strong> {{ getDangerMsg }}
    </alert>
  </div>
</template>
<script>
import { Alert } from "@/components";
import { mapGetters } from "vuex";
export default {
  components: {
    Alert,
  },
  computed: {
    ...mapGetters("msgStore",[
      "getSuccessMsg",
      "getInfoMsg",
      "getWarningMsg",
      "getDangerMsg",
      "getSuccessVisible",
      "getInfoVisible",
      "getWarningVisible",
      "getDangerVisible",
    ]),
  },
};
</script>
