<template>
  <div v-if="board.no != ''" class="container detail">
    <h1 class="detail__title">
      {{ board.title }}
    </h1>
    <div class="detail__header">
      <div class="header__left">
        <h5 class="detail__writer">작성자 : {{ board.nickName }}</h5>
        <h5 class="detail__regDate">작성일자 : {{ getDate }}</h5>
      </div>
      <div
        class="header__right"
        v-if="
          userInfo &&
          board.userId == userInfo.userId &&
          board.socialType == userInfo.socialType
        "
      >
        <button @click="moveModifiy" id="btn--modify" class="btn btn-simple">
          수정
        </button>
        <button @click="deleteBoard" id="btn--delete" class="btn btn-simple">
          삭제
        </button>
      </div>
    </div>

    <div class="main-content">
      <h2>
        {{ board.description }}
      </h2>
    </div>
    <comment :noticeNo="board.no"></comment>
  </div>
</template>

<script>
import boardApi from "@/apis/boardApi.js";
import Comment from "@/components/Comment/Comment.vue";
import { mapMutations, mapState } from "vuex";

export default {
  components: {
    Comment,
  },
  data() {
    return {
      board: {
        no: "",
        title: "",
        userId: "",
        nickName: "",
        socialType: "",
        description: "",
        regDate: "",
      },
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    getDate() {
      let date = this.board.regDate;
      let year = date.slice(0, 4) + "년 ";
      let month = date.slice(5, 7) + "월 ";
      let day = date.slice(8, 10) + "일 ";
      let hour = date.slice(11, 13) + "시 ";
      let min = date.slice(14, 16) + "분 ";
      let sec = date.slice(17, 19) + "초 ";
      return year + month + day + hour + min + sec;
    },
  },
  async created() {
    try {
      const res = await boardApi.get(`?no=${this.$route.params.no}`);
      console.log("GET_DETAIL", res.data);
      if (res.status == 200) {
        this.board = { ...res.data[0] };
        console.log(this.board);
      } else if (res.status == 204) {
        this.$router.push("/board");
        this.SET_DANGER_MSG({
          visible: true,
          msg: "해당 글이 존재하지 않습니다.",
        });
      } else {
        this.$router.push("/board");
        this.SET_DANGER_MSG({
          visible: true,
          msg: "해당 글이 조회하는데 실패하였습니다.",
        });
      }
    } catch (e) {
      console.log(e);
    }
  },
  methods: {
    ...mapMutations("msgStore", ["SET_SUCCESS_MSG", "SET_DANGER_MSG"]),
    async deleteBoard() {
      try {
        const res = await boardApi.delete("/?no=" + this.board.no);
        console.log(res);
        if (res.status == 200) {
          this.$router.push("/board");
          this.SET_SUCCESS_MSG({
            visible: true,
            msg: "글을 정상적으로 삭제하였습니다.",
          });
        } else {
          this.SET_DANGER_MSG({
            visible: true,
            msg: "글을 삭제하는데 실패하였습니다.",
          });
        }
      } catch (e) {
        console.log(e);
      }
    },
    moveModifiy() {
      this.$router.push("/board/modify/" + this.board.no);
    },
  },
  watch: {},
};
</script>

<style lang="scss" scoped>
#btn--modify {
  color: blue;
  border: 1px solid blue;
}

#btn--modify:hover {
  color: white;
  background-color: blue;
}

#btn--delete {
  color: red;
  border: 1px solid red;
  margin-left: 10px;
}

#btn--delete:hover {
  color: white;
  background-color: red;
}

.detail__title {
  height: 6vh;
  display: flex;
  justify-content: center;
}

.detail__writer,
.detail__regDate {
  height: 2.6vh;
}

.detail__header {
  display: flex;
  justify-content: space-between;
}

.main-content {
  padding: 1vw 1vh;
  min-height: 30vh;
  border-top: 1px solid gainsboro;
  border-bottom: 1px solid gainsboro;
}

.nickName,
.regDate {
  display: flex;
  justify-content: end;
}
</style>
