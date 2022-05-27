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
              <th
                :class="{ sorted: sortedType == 'aptName' }"
                @click="sort('aptName')"
              >
                아파트이름
                <img
                  :class="{ descSort: !descSort }"
                  src="/img/up.svg"
                  alt=""
                />
                <img
                  :class="{ descSort: descSort }"
                  src="/img/down.svg"
                  alt=""
                />
              </th>
              <th class="text-center">주소</th>
              <th
                :class="{ sorted: sortedType == 'buildYear' }"
                @click="sort('buildYear')"
              >
                건축연도
                <img
                  :class="{ descSort: !descSort }"
                  src="/img/up.svg"
                  alt=""
                />
                <img
                  :class="{ descSort: descSort }"
                  src="/img/down.svg"
                  alt=""
                />
              </th>
              <th
                :class="{ sorted: sortedType == 'recentPrice' }"
                @click="sort('recentPrice')"
              >
                최근거래금액
                <img
                  :class="{ descSort: !descSort }"
                  src="/img/up.svg"
                  alt=""
                />
                <img
                  :class="{ descSort: descSort }"
                  src="/img/down.svg"
                  alt=""
                />
              </th>
            </tr>
          </thead>
          <!-- <tbody id="searchResult"></tbody> -->
          <tbody>
            <tr
              @click="moveDetail(apt.aptCode)"
              v-for="(apt, ind) in searchAptList"
              :key="ind"
            >
              <td>{{ ind + 1 }}</td>
              <td>{{ apt.aptName }}</td>
              <td class="text-center">
                {{ apt.sidoName }} {{ apt.gugunName }} {{ apt.dongName }}
                {{ apt.jibun }}
              </td>
              <td>{{ apt.buildYear }}</td>
              <td>{{ getWon(apt.recentPrice) }}</td>
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
      sortedType: "aptName",
      descSort: true,
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
    ...mapMutations("msgStore", ["SET_INFO_MSG", "SET_SUCCESS_MSG"]),
    async setGugun() {
      const res = await aptApi.get("/gugun?sido=" + this.sidoCode);
      this.gugunArr = res.data;
      this.gugunData = this.gugunCode || null;
      this.dongData = this.dongCode || null;
    },
    async setDong() {
      const res = await aptApi.get("/dong?gugun=" + this.gugunCode);
      console.log(res.data);
      const dongs = res.data.filter((apt) => apt.dongName != null);
      this.dongArr = dongs;
      this.dongData = this.dongCode || null;
    },
    async searchApt() {
      const res = await aptApi.get("/apt?dong=" + this.dongCode);
      const apts = res.data.map((apt) => {
        return {
          ...apt,
          recentPrice: +apt.recentPrice.split(",").join(""),
        };
      });
      this.searchAptList = apts;
      if (res.data.length == 0) {
        this.SET_INFO_MSG({ visible: true, msg: "검색 결과가 없습니다." });
      } else {
        this.SET_SUCCESS_MSG({
          visible: true,
          msg: `${res.data.length}건의 검색 결과가 있습니다.`,
        });
      }
    },
    moveDetail(code) {
      this.$router.push("/apt/detail/" + code);
    },
    sort(type) {
      if (type == this.sortedType) {
        this.descSort = !this.descSort;
      } else {
        this.descSort = true;
      }
      this.sortedType = type;
      console.log(type, this.sortedType, this.descSort);
      let sortOption = (a, b) => {
        let result = a[type] > b[type] ? 1 : -1;
        if (this.descSort) return result * -1;
        return result;
      };
      this.searchAptList = this.searchAptList.sort(sortOption);
      this.SET_SUCCESS_MSG({
        visible: true,
        msg: `${type}을 ${
          !this.descSort ? "오름차순" : "내림차순"
        }으로 정렬되었습니다.`,
      });
    },
    getWon(price) {
      if (price >= 10000) {
        let uk = Math.floor(price / 10000);
        let chun = price % 10000;
        if (chun == 0) return uk + "억";

        return uk + "억" + chun + "만원";
      } else {
        return price + "만원";
      }
    },
  },
};
</script>

<style lang="scss">
th {
  img {
    display: none;
  }
}
.sorted {
  text-decoration: underline;
  font-weight: 700;
  * {
    display: none;
  }
  .descSort {
    display: inline;
    width: 1em;
    height: 1em;
  }
}
</style>
