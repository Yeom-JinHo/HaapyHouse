<template>
  <div class="container">
    <form class="input-form" @submit="onSubmit" @reset="moveBoardList">
      <div>
        <div class="nickName">작성자 : {{ getNickName }}</div>
      </div>
      <hr />
      <div>
        <label for="title">
          제목
          <input
            id="title"
            ref="title"
            v-model="board.title"
            type="text"
            placeholder="제목 입력..."
          />
        </label>
      </div>

      <div>
        <textarea
          id="description"
          ref="description"
          v-model="board.description"
          rows="10"
          max-rows="15"
          style="resize: none"
        ></textarea>
      </div>

      <button
        id="btn--regist"
        class="btn btn-simple"
        type="submit"
        variant="primary"
        v-if="type == 'regist'"
      >
        글작성
      </button>
      <button
        id="btn--modify"
        class="btn btn-simple"
        type="submit"
        variant="primary"
        v-else
      >
        글수정
      </button>
      <button id="btn--list" class="btn btn-simple" type="reset">글목록</button>
    </form>
  </div>
</template>

<script>
import boardApi from "@/apis/boardApi.js";
import { mapMutations, mapState } from "vuex";

export default {
  name: "BoardInputForm",
  data() {
    return {
      board: {
        title: "",
        description: "",
        no: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  async created() {
    //데이터 받아오기
    if (this.type === "modify") {
      try {
        const res = await boardApi.get("/?no=" + this.$route.params.no);
        if (res.status == 200) {
          this.board = { ...res.data[0] };
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
      } catch (e) {}
    }
  },
  methods: {
    ...mapMutations("msgStore", [
      "SET_WARNING_MSG",
      "SET_SUCCESS_MSG",
      "SET_DANGER_MSG",
    ]),
    moveBoardList() {
      this.$router.push("/board");
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.board.title &&
        ((msg = "제목 입력해주세요"),
        (err = false), //
        this.$refs.title.focus());
      err &&
        !this.board.description &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.description.focus());
      err &&
        !this.board.description.length > 50 &&
        ((msg = "내용을 50자 이하로 입력해주세요"),
        (err = false),
        this.$refs.description.focus());

      if (!err) {
        this.SET_WARNING_MSG({ visible: true, msg });
      } else {
        this.SET_WARNING_MSG({ visible: false, msg: "" });
        this.type === "regist" ? this.registBoard() : this.modifyBoard();
      }
    },
    async registBoard() {
      const newBoard = {
        title: this.board.title,
        nickName: this.getNickName,
        socialType: this.userInfo.socialType,
        userId: this.userInfo.userId,
        description: this.board.description,
      };
      const res = await boardApi.post("", newBoard);
      if (res.status == 200) {
        this.$router.push("/board");
        this.SET_SUCCESS_MSG({
          visible: true,
          msg: "글 등록에 성공하였습니다!!",
        });
      } else {
        this.SET_DANGER_MSG({
          visible: true,
          msg: "글 등록에 실패하였습니다. 다시 시도해주세요!",
        });
      }
    },
    async modifyBoard() {
      try {
        const res = await boardApi.put("/", {
          title: this.board.title,
          description: this.board.description,
          no: this.board.no,
        });
        this.$router.push("/board");
        if (res.status == 200) {
          this.SET_SUCCESS_MSG({
            visible: true,
            msg: "글 수정에 성공하였습니다.",
          });
        } else {
          this.SET_DANGER_MSG({
            visible: true,
            msg: "해당 글을 수정하는데 실패하였습니다.",
          });
        }
      } catch {}
    },
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    getNickName() {
      return this.userInfo.nickName;
    },
  },
};
</script>

<style lang="scss" scoped>
.input-form {
  padding: 0 10%;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;

  * {
    width: 100%;
    font-size: 2rem;
  }
  label {
  }
}

#btn--modify,
#btn--regist {
  color: #18ce0f;
  border: 1px solid #18ce0f;
}

#btn--modify:hover,
#btn--regist:hover {
  color: white;
  background-color: #18ce0f;
}

#btn--list {
  color: blue;
  border: 1px solid blue;
}

#btn--list:hover {
  color: white;
  background-color: blue;
}
</style>
