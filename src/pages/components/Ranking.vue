<template>
  <div class="container">
    <div class="ranking">
      <div class="ranking-title">순위표</div>
      <div class="first" data-aos="fade-up">
        <div class="tri">
          <div class="triangle"></div>
          <div class="tri-cnt">1</div>
        </div>
        <div class="title">{{ dummy[0].title }}</div>
      </div>
      <ul>
        <li
          v-for="(item, index) in dummy.slice(1)"
          :key="index"
          data-aos="fade-up"
          data-aos-easing="ease-out-back"
        >
          <!-- <div class="left"> -->
          <span class="number">{{ index + 2 }}</span>
          <span class="aptName">{{ item.title }}</span>
          <!-- </div> -->
          <!-- <div class="right"> -->
          <i v-if="item.count > 0" class="now-ui-icons arrows-1_minimal-up"></i>
          <i
            v-else-if="item.count < 0"
            class="now-ui-icons arrows-1_minimal-down"
          ></i>
          <i v-else class="now-ui-icons ui-1_simple-delete"></i>
          <span class="count">{{ item.count }}</span>
          <!-- </div> -->
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dummy: [
        { title: "한빛아파트", count: -1 },
        { title: "**아파트", count: 1 },
        { title: "!!아파트", count: 0 },
        { title: "()아파트", count: 3 },
        { title: "{}아파트", count: 3 },
        { title: "**아파트", count: 1 },
        { title: "!!아파트", count: 0 },
        { title: "()아파트", count: 3 },
        { title: "{}아파트", count: 3 },
        { title: "**아파트", count: 1 },
      ],
    };
  },
};
</script>

<style scoped>
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
  height: 3vh;
  font-size: 2vh;
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
  flex-grow: 4;
  display: flex;
  justify-content: center;
  align-items: center;
}

.ranking li .aptName {
  flex-grow: 32;
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
