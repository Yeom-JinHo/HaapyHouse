<template>
  <div class="container">
    <div class="ranking">
      <div class="ranking-title">
        순위표
        <!-- <button @click="test">순위 업데이트</button> -->
      </div>
      <div
        class="first"
        data-aos="fade-up"
        @click="
          moveDetail(rankingList.length > 0 ? rankingList[0].aptCode : '')
        "
      >
        <div class="tri">
          <div class="triangle"></div>
          <div class="tri-cnt">1</div>
        </div>
        <div class="title">
          <span class="aptName">{{
            rankingList.length > 0
              ? rankingList[0].aptName
              : "아직 순위권 데이터가 없어요!"
          }}</span>
          <i
            v-if="
              rankingList.length > 0 ? rankingList[0].rankChange > 0 : false
            "
            class="now-ui-icons arrows-1_minimal-up"
          ></i>
          <i
            v-else-if="
              rankingList.length > 0 ? rankingList[0].rankChange < 0 : false
            "
            class="now-ui-icons arrows-1_minimal-down"
          ></i>
          <i v-else class="now-ui-icons ui-1_simple-delete"></i>
          <span class="count">{{
            rankingList.length > 0 ? rankingList[0].rankChange : ""
          }}</span>
        </div>
      </div>

      <ul v-if="rankingList.length > 0">
        <li
          v-for="(item, index) in rankingList.slice(1)"
          :key="index"
          data-aos="fade-up"
          data-aos-easing="ease-out-back"
          data-aos-duration="1000"
          @click="moveDetail(item.aptCode)"
        >
          <!-- <div class="left"> -->
          <span class="number">{{ index + 2 }}</span>
          <span class="aptName">{{ item.aptName }}</span>
          <!-- </div> -->
          <!-- <div class="right"> -->
          <i
            v-if="item.rankChange > 0"
            class="now-ui-icons arrows-1_minimal-up"
          ></i>
          <i
            v-else-if="item.rankChange < 0"
            class="now-ui-icons arrows-1_minimal-down"
          ></i>
          <i v-else class="now-ui-icons ui-1_simple-delete"></i>
          <span class="count">{{ item.rankChange }}</span>
          <!-- </div> -->
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      rankingList: [],
    };
  },
  async created() {
    const res = await axios.get("http://localhost:9999/count/search");
    console.log("ranking", res);
    this.rankingList = res.data;
  },
  methods: {
    async test() {
      const res = await axios.get("http://localhost:9999/count/update");
      console.log("update", res);
      this.rankingList = res.data;
    },
    moveDetail(aptcode) {
      this.$router.push("/apt/detail/" + aptcode);
    },
  },
};
</script>

<style lang="scss" scoped>
.ranking {
  width: 80%;
  height: 50vh;
  margin: 10% 10% 0 10%;
  background-color: #2b2d36;
  font-size: 1.5rem;
  color: white;
}

.ranking-title {
  height: 4vh;
  display: flex;
  justify-content: center;
  font-size: 3vh;
}

.ranking .first {
  background-image: url("../../../public/img/dummyApt.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  height: 19vh;
  display: flex;
  flex-direction: column;
  justify-content: space-between;

  .title {
    display: flex;
    align-items: center;
  }
  .aptName {
    // flex-basis: 500px;
    flex-basis: 620px;
  }
  i {
    flex-basis: 110px;
  }
}

.ranking i {
  margin-right: 15px;
}

.ranking .arrows-1_minimal-up {
  color: red;
}

.ranking .arrows-1_minimal-down {
  color: blue;
}

.ranking .ui-1_simple-delete {
  color: greenyellow;
}

.first .title {
  padding: 0;
  padding-left: 1.5vw;
  height: 3.5rem;
  font-size: 3rem;
  background: rgba(0, 0, 0, 0.4);
}

.ranking ul {
  padding: 0;
}

.ranking li {
  height: 3vh;
  list-style: none;
  display: flex;
  justify-content: center;
  border-bottom: 1px solid rgba(200, 200, 200, 0.2);
}
.ranking li:hover {
  background-color: royalblue;
}
.ranking li .number {
  flex-basis: 4vw;
  display: flex;
  justify-content: center;
  align-items: center;
}

.ranking li .aptName {
  flex: 10;
  display: flex;
  align-items: center;
}

.ranking li .now-ui-icons {
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.ranking li .count {
  width: 10%;
  flex-grow: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}
.ranking .tri .tri-cnt {
  position: absolute;
  padding-left: 1.5vh;
  font-size: 2.5vh;
}
.ranking .tri .triangle {
  position: absolute;
  border-top: 3vh solid red;
  border-left: 3vh solid red;
  border-right: 3vh solid transparent;
  border-bottom: 3vh solid transparent;
}
</style>
