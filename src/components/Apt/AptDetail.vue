<template>
  <div class="container">
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
              </th>
              <th
                :class="{ sorted: sortedType == 'dealAmount' }"
                @click="sort('dealAmount')"
              >
                거래금액
              </th>
              <th
                :class="{ sorted: sortedType == 'area' }"
                @click="sort('area')"
              >
                면적
              </th>
              <th
                :class="{ sorted: sortedType == 'floor' }"
                @click="sort('floor')"
              >
                층수
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
              <td>{{ apt.dealAmount }}</td>
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
import { mapMutations } from "vuex";

export default {
  data() {
    return {
      aptDealList: [],
      sortedType: "dealDate",
      descSort: true,
    };
  },
  async created() {
    const res = await aptApi.get(
      "/apt/deal/?aptCode=" + this.$route.params.aptCode
    );
    console.log(res.data);
    this.aptDealList = res.data;
  },
  methods: {
    ...mapMutations("msgStore", [
      "SET_INFO_MSG",
      "SET_SUCCESS_MSG",
      "CLEAR_ALL_MSG",
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
      this.CLEAR_ALL_MSG();
      this.SET_SUCCESS_MSG({
        visible: true,
        msg: `${type}을 ${
          !this.descSort ? "오름차순" : "내림차순"
        }으로 정렬되었습니다.`,
      });
    },
  },
};
</script>

<style>
.sorted {
  color: red;
}
</style>
