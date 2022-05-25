<template>
  <div class="container">
    <div class="detail-header">
      <div></div>
      <h1>{{ aptDealList.length > 0 ? aptDealList[0].aptName : "" }}</h1>
      <img
        v-if="isLike"
        @click="toggleLike()"
        src="/img/heart-fill.svg"
        alt=""
      />
      <img v-else @click="toggleLike()" src="/img/heart-empty.svg" alt="" />
    </div>
    <div class="card col-sm-12 mt-1">
      <div class="card-body">
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
              <th
                :class="{ sorted: sortedType == 'dealDate' }"
                @click="sort('dealDate')"
              >
                거래일자
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
                :class="{ sorted: sortedType == 'dealAmout' }"
                @click="sort('dealAmout')"
              >
                거래금액
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
                :class="{ sorted: sortedType == 'area' }"
                @click="sort('area')"
              >
                면적
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
                :class="{ sorted: sortedType == 'floor' }"
                @click="sort('floor')"
              >
                층수
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
            <tr v-for="(apt, ind) in aptDealList" :key="ind">
              <td>{{ ind + 1 }}</td>
              <td>{{ apt.aptName }}</td>
              <td>
                {{ apt.dealYear }}년 {{ apt.dealMonth }}월 {{ apt.dealDay }}일
              </td>
              <td>{{ getWon(apt.dealAmout) }}</td>
              <td>{{ apt.area }}</td>
              <td>{{ apt.floor }}층</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
import aptApi from "@/apis/aptApi.js";
import profileApi from "@/apis/profileApi.js";
import { mapMutations, mapState } from "vuex";

export default {
  data() {
    return {
      aptDealList: [],
      sortedType: "dealDate",
      descSort: true,
      isLike: false,
    };
  },
  async created() {
    const res = await aptApi.get(
      "/apt/deal/?aptCode=" + this.$route.params.aptCode
    );
    console.log(res.data);
    if (res.status == 200) {
      this.SET_SUCCESS_MSG({
        visible: true,
        msg: `${res.data.length}개의 거래 정보가 있습니다.`,
      });
      const apts = res.data.map((apt) => {
        return {
          ...apt,
          dealAmout: +apt.dealAmout.split(",").join(""),
        };
      });
      this.aptDealList = apts;
      if (this.userInfo) {
        await this.getIsLike();
      }
    }
    await aptApi.put(
      "http://localhost:9999/count/visit?aptCode=" + this.$route.params.aptCode
    );
  },
  methods: {
    ...mapMutations("msgStore", [
      "SET_WARNING_MSG",
      "SET_INFO_MSG",
      "SET_SUCCESS_MSG",
    ]),
    sort(type) {
      if (type == this.sortedType) {
        this.descSort = !this.descSort;
      } else {
        this.descSort = true;
      }
      this.sortedType = type;
      let sortOption = (a, b) => {
        let result = a[type] > b[type] ? 1 : -1;
        if (this.descSort) return result * -1;
        return result;
      };

      if (type == "dealDate") {
        sortOption = (a, b) => {
          let result =
            a.dealYear + a.dealMonth + a.dealDay >
            b.dealYear + b.dealMonth + b.dealDay
              ? 1
              : -1;
          if (this.descSort) return result * -1;
          return result;
        };
      }

      this.aptDealList = this.aptDealList.sort(sortOption);
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
    async toggleLike() {
      if (!this.userInfo) {
        this.SET_WARNING_MSG({
          visible: true,
          msg: "로그인을 먼저 진행해주세요",
        });
        return;
      }
      let res;
      if (!this.isLike) {
        res = await profileApi.post("/apt/regist", {
          userId: this.userInfo.userId,
          socialType: this.userInfo.socialType,
          aptCode: this.$route.params.aptCode,
        });
        if (res.status == 200) {
          this.SET_SUCCESS_MSG({
            visible: true,
            msg: "관심 매물 등록이 완료되었습니다.",
          });
        }
      } else {
        res = await profileApi.delete(
          `/apt/?userId=${this.userInfo.userId}&socialType=${this.userInfo.socialType}&aptCode=${this.$route.params.aptCode}`
        );
        if (res.status == 200) {
          this.SET_SUCCESS_MSG({
            visible: true,
            msg: "관심 매물 삭제가완료되었습니다.",
          });
        }
      }
      this.isLike = !this.isLike;
    },
    async getIsLike() {
      const likeRes = await profileApi.get(
        `/apt/check?aptCode=${this.$route.params.aptCode}&userId=${this.userInfo.userId}&socialType=${this.userInfo.socialType}`
      );
      this.isLike = likeRes.data;
    },
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
};
</script>

<style lang="scss">
.detail-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 50px;

  h1 {
    margin: 0;
  }
  img {
    width: 40px;
  }
}
</style>
