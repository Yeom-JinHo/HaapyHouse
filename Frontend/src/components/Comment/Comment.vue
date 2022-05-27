<template>
  <div class="comment-compo">
    <h2>댓글</h2>
    <comment-item
      v-for="(com, index) in commentList"
      :key="index"
      :comment="com"
    >
    </comment-item>

    <!-- 0뎁스 댓글 게시글에 바로 댓글! -->
    <comment-input
      :noticeNo="noticeNo"
      :commentdepth="0"
      :commentgroup="0"
    ></comment-input>
  </div>
</template>

<script>
import CommentItem from "./CommentItem.vue";
import CommentInput from "./CommentInput.vue";
import commentApi from "@/apis/commentApi.js";

import { mapMutations, mapState } from "vuex";
export default {
  props: ["noticeNo"],
  components: { CommentItem, CommentInput },
  data() {
    return {};
  },
  computed: {
    ...mapState("commentStore", ["commentList"]),
  },
  methods: {
    ...mapMutations("commentStore", ["SET_COMMENT_LIST"]),
  },
  async created() {
    try {
      const res = await commentApi.get(`${this.$route.params.no}`);
      console.log("GET_COMMENT:", res);
      if (res.status == 200) {
        this.SET_COMMENT_LIST([...res.data]);
      }
    } catch (e) {
      console.log(e);
    }
  },
};
</script>

<style lang="scss" scoped>
.comment-compo {
  padding: 1vw 1vh;
}
</style>
