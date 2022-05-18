<template>
  <div class="comment">
    <h2>댓글</h2>
    <comment-item
      v-for="(com, index) in dummyCom"
      :key="index"
      :comment="com"
    ></comment-item>
    <div class="input-header__userid">짱짱맨</div>
    <input class="comment__input" type="text" />
    <div type="button" id="comment--regist" class="btn btn-simple btn-primary">
      등록
    </div>
  </div>
</template>

<script>
import CommentItem from "./CommentItem.vue";
export default {
  components: { CommentItem },
  data() {
    return {
      dummyCom: [
        {
          description:
            "개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ개웃기네 ㅋㅋ",
          writer: "짱짱맨",
          regDate: "2022-05-18T10:26:02",
        },
        {
          description: "개웃기네 ㅋㅋ",
          writer: "짱짱맨",
          regDate: "2022-05-18T10:26:02",
        },
        {
          description: "개웃기네 ㅋㅋ",
          writer: "짱짱맨",
          regDate: "2022-05-18T10:26:02",
        },
        {
          description: "개웃기네 ㅋㅋ",
          writer: "짱짱맨",
          regDate: "2022-05-18T10:26:02",
        },
      ],
    };
  },
};
</script>

<style lang="scss" scoped>
.comment {
  padding: 1vw 1vh;

  &__input {
    width: 100%;
    height: 3rem;
    font-size: 1.5rem;
  }
}
#comment--regist {
  width: 5vw;
  float: right;
}
#comment--regist:hover {
  background-color: #f96332;
  color: white;
}

.input-header__userid {
  font-size: 1.5rem;
}
</style>
