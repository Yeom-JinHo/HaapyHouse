<template>
  <div class="new-comment">
    <div class="new-comment__userid">{{ userInfo.nickName }}</div>
    <div class="input-container">
      <input class="new-comment__input" type="text" v-model="comment" />
      <div
        type="button"
        id="new-comment--regist"
        class="btn btn-simple btn-primary"
        @click="registComment"
      >
        등록
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
import commentApi from "@/apis/commentApi.js";
export default {
  data() {
    return {
      comment: "",
    };
  },
  props: ["commentdepth", "commentgroup", "noticeNo"],
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapMutations("commentStore", ["SET_COMMENT_LIST"]),
    async registComment() {
      let newComment = {
        nickName: this.userInfo.nickName,
        userId: this.userInfo.userId,
        comment: this.comment,
        notice_no: this.noticeNo,
        commentDepth: this.commentdepth,
        commentGroup: this.commentgroup,
        socialtype: this.userInfo.socialType,
      };
      console.log("COMMENT_REIGST", this.commentdepth, newComment);
      const res = await commentApi.post("", newComment);
      this.SET_COMMENT_LIST(res.data);
      this.comment = "";
    },
  },
};
</script>

<style lang="scss">
.new-comment {
  &__userid {
    font-size: 1.5em;
  }
  &__input {
    width: 100%;
    height: 3rem;
    font-size: 1.5rem;
    margin-right: 15px;
  }
  .input-container {
    display: flex;
    align-items: center;
  }
}
#new-comment--regist {
  width: 5vw;
  float: right;
}
#new-comment--regist:hover {
  background-color: #f96332;
  color: white;
}
</style>
