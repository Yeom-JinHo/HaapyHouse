<template>
  <div class="section" id="custom-section">
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
            <tbody>
              <tr
                @click="moveDetail(apt.aptCode)"
                v-for="(apt, ind) in likeAptList"
                :key="ind"
              >
                <td>{{ ind + 1 }}</td>
                <td>{{ apt.aptName }}</td>
                <td>
                  {{ apt.dealYear }}년 {{ apt.dealMonth }}월 {{ apt.dealDay }}일
                </td>
                <td>{{ getWon(apt.recentPrice) }}</td>
                <td>{{ apt.area }}</td>
                <td>{{ apt.floor }}층</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import profileApi from "@/apis/profileApi.js";
export default {
  name: "LikeApt",
  data() {
    return {
      sortedType: "aptName",
      descSort: true,
    };
  },
  props: ["likeAptList"],
  methods: {
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
    moveDetail(aptcode) {
      this.$router.push("/apt/detail/" + aptcode);
    },
  },
};
</script>

<style></style>
