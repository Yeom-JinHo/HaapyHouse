<template>
  <div class="container">
    <form @submit="onSubmit" @reset="moveBoardList">
      <div>
        <label for="userid"
          >작성자
          <input
            id="userid"
            ref="userid"
            v-model="board.userid"
            type="text"
            placeholder="작성자 입력..."
          />
        </label>
      </div>

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
          id="content"
          ref="content"
          v-model="board.content"
          rows="10"
          max-rows="15"
          style="resize: none"
        ></textarea>
      </div>

      <button type="submit" variant="primary" v-if="type == 'regist'">
        글작성
      </button>
      <button type="submit" variant="primary" v-else>글수정</button>
      <button type="reset">글목록</button>
    </form>
  </div>
</template>

<script>
import boardApi from "@/apis/boardApi.js";
import { mapMutations } from "vuex";

export default {
  name: "BoardInputForm",
  data() {
    return {
      board: {
        userid: "",
        title: "",
        content: "",
      },
      isUserid: false,
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    //데이터 받아오기
    if (this.type === "modify") {
    }
  },
  methods: {
    ...mapMutations(["SET_WARNING_MSG", "SET_SUCCESS_MSG", "SET_DANGER_MSG"]),
    moveBoardList() {
      this.$router.push("/board");
    },
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.board.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.board.title &&
        ((msg = "제목 입력해주세요"),
        (err = false), //
        this.$refs.title.focus());
      err &&
        !this.board.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());
      err &&
        !this.board.content.length < 50 &&
        ((msg = "내용을 50자 이하로 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

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
        writer: this.board.userid,
        description: this.board.content,
      };
      const res = await boardApi.post("", newBoard);
      if (res.status == 200) {
        this.SET_SUCCESS_MSG({
          visible: true,
          msg: "글 등록에 성공하였습니다!!",
        });
        this.$router.push("/board");
      } else {
        this.SET_DANGER_MSG({
          visible: true,
          msg: "글 등록에 실패하였습니다. 다시 시도해주세요!",
        });
      }
    },
    modifyboard() {},
  },
};
</script>

<style></style>
