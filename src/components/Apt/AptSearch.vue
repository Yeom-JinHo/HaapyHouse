<template>
  <div class="container">
    <div class="card col-sm-12 mt-1">
      <div class="card-body">
        <div class="form-group form-inline justify-content-center">
          <label class="mr-2" for="sido">시도 : </label>
          <select
            @change="setGugun"
            class="form-control"
            id="sido"
            v-model="sidoData"
          >
            <option :value="null">시 도</option>
            <option
              v-for="(sido, index) in sidoArr"
              :key="index"
              :value="sido.sidoCode"
            >
              {{ sido.sidoName }}
            </option>
          </select>

          <label class="mr-2 ml-3" for="gugun">구군 : </label>
          <select
            @change="setDong"
            class="form-control"
            id="gugun"
            v-model="gugunData"
          >
            <option :value="null">구 군</option>
            <option
              v-for="(gugun, index) in gugunArr"
              :key="index"
              :value="gugun.gugunCode"
            >
              {{ gugun.gugunName }}
            </option>
          </select>

          <label class="mr-2 ml-3" for="dong">읍면동 : </label>
          <select
            @change="searchApt"
            class="form-control"
            id="dong"
            v-model="dongData"
          >
            <option :value="null">읍 면 동</option>
            <option
              v-for="(dong, index) in dongArr"
              :key="index"
              :value="dong.dongCode"
            >
              {{ dong.dongName }}
            </option>
          </select>
          <button @click="test">테스트</button>
          <button @click="test2">테스트2</button>
        </div>
        <kakao-map :aptList="searchAptList" />
        <table class="table mt-2">
          <colgroup>
            <col width="100" />
            <col width="150" />
            <col width="*" />
            <col width="120" />
            <col width="120" />
          </colgroup>
          <thead>
            <tr>
              <th>번호</th>
              <th>아파트이름</th>
              <th class="text-center">주소</th>
              <th>건축연도</th>
              <th>최근거래금액</th>
            </tr>
          </thead>
          <!-- <tbody id="searchResult"></tbody> -->
          <tbody>
            <tr v-for="(apt, ind) in searchAptList" :key="ind">
              <td>{{ ind + 1 }}</td>
              <td>{{ apt.aptName }}</td>
              <td class="text-center">
                {{ apt.sidoName }} {{ apt.gugunName }} {{ apt.dongName }}
                {{ apt.jibun }}
              </td>
              <td>{{ apt.buildYear }}</td>
              <td>{{ apt.recentPrice }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import KakaoMap from "./KakaoMap.vue";
import aptApi from "@/apis/aptApi.js";
import { mapMutations, mapState } from "vuex";
export default {
  components: { KakaoMap },
  data() {
    return {
      sidoArr: null,
      gugunArr: null,
      dongArr: null,
      searchAptList: [],
    };
  },
  computed: {
    ...mapState("aptStore", ["sidoCode", "gugunCode", "dongCode"]),
    sidoData: {
      get() {
        return this.sidoCode;
      },
      set(code) {
        this.SET_SIDO_CODE(code);
      },
    },
    gugunData: {
      get() {
        return this.gugunCode;
      },
      set(code) {
        this.SET_GUGUN_CODE(code);
      },
    },
    dongData: {
      get() {
        return this.dongCode;
      },
      set(code) {
        this.SET_DONG_CODE(code);
      },
    },
  },
  async created() {
    this.SET_INFO_MSG({
      visible: true,
      msg: "아직 서비스가 서울만 지원하고 있어요!",
    });
    const res = await aptApi.get("/sido");
    this.sidoArr = res.data;
    if (!!this.sidoCode) {
      this.setGugun();
    }
    if (!!this.gugunCode) {
      this.setDong();
    }
    if (!!this.dongCode) {
      this.searchApt();
    }
  },
  methods: {
    ...mapMutations("aptStore", [
      "SET_SIDO_CODE",
      "SET_GUGUN_CODE",
      "SET_DONG_CODE",
    ]),
    ...mapMutations("msgStore", ["SET_INFO_MSG"]),
    async test() {
      const res = await aptApi.get("/apt?dong=" + 1171010200);
      console.log("SEARCH TEST", res.data);
      const newData = res.data.reduce(function (acc, current) {
        if (
          acc.findIndex(({ aptName }) => aptName === current.aptName) === -1
        ) {
          acc.push(current);
        }
        return acc;
      }, []);
      console.log("NEW", newData);
      // this.searchAptList = res.data;
      this.searchAptList = newData;
    },
    async test2() {
      const res = await aptApi.get("/apt/deal?aptCode=" + "5398");
      console.log("SEARCH Deal TEST", res.data);
      // this.searchAptList = res.data;
      this.searchAptList = newData;
    },
    async setGugun() {
      const res = await aptApi.get("/gugun?sido=" + this.sidoCode);
      this.gugunArr = res.data;
    },
    async setDong() {
      const res = await aptApi.get("/dong?gugun=" + this.gugunCode);
      this.dongArr = res.data;
    },
    async searchApt() {
      const res = await aptApi.get("/apt?dong=" + this.dongCode);
      this.searchAptList = res.data;
    },
  },
};
</script>

<style></style>
