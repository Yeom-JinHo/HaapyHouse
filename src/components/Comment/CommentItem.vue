<template>
  <div class="comment-flex">
    <img
      id="replyArrow"
      v-if="comment.commentDepth != 0"
      src="/img/replyArrow.svg"
    />
    <div class="comment-container">
      <div class="comment-item">
        <div class="comment-item__header">
          <div class="header__left">
            <img id="profile" src="/img/korea.jpg" lt="" />
            <h3 class="header__writer">{{ comment.nickName }}</h3>
            <h5>{{ getDate }}</h5>
          </div>
          <div
            class="header__right"
            v-if="
              userInfo &&
              userInfo.userId == comment.userId &&
              userInfo.socialType == comment.socialtype
            "
          >
            <button id="btn--modify" class="btn btn-simple">수정</button>
            <button id="btn--delete" class="btn btn-simple">삭제</button>
          </div>
        </div>
        <div class="comment-item__main">
          <h3>{{ comment.comment }}</h3>
        </div>
        <button
          v-if="userInfo"
          @click="toggleReply"
          id="btn--reply"
          class="btn btn-simple"
        >
          <img id="reply" src="/img/comment.svg" alt="" />대댓글달기
        </button>
        <comment-input
          :noticeNo="comment.notice_no"
          :commentdepth="comment.commentDepth + 1"
          :commentgroup="comment.comment_no"
          v-if="replyShow"
        ></comment-input>
      </div>

      <div class="children" v-if="comment.children">
        <comment-item
          v-for="(com, index) in comment.children"
          :key="index"
          :comment="com"
        >
        </comment-item>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import CommentInput from "./CommentInput.vue";
import CommentItem from "./CommentItem.vue";
export default {
  name: "commentItem",
  data() {
    return {
      replyShow: false,
    };
  },
  components: { CommentInput, CommentItem },
  props: ["comment"],
  computed: {
    ...mapState("userStore", ["userInfo"]),
    getDate() {
      let date = this.comment.comment_time;
      let year = date.slice(0, 4) + "년 ";
      let month = date.slice(5, 7) + "월 ";
      let day = date.slice(8, 10) + "일 ";
      let hour = date.slice(11, 13) + "시 ";
      let min = date.slice(14, 16) + "분 ";
      let sec = date.slice(17, 19) + "초 ";
      return year + month + day + hour + min + sec;
    },
  },
  methods: {
    toggleReply() {
      this.replyShow = !this.replyShow;
    },
  },
};
</script>

<style lang="scss" scoped>
.comment-flex {
  display: flex;
  .comment-container {
    width: 100%;

    .comment-item {
      // margin-bottom: 3vh;
      width: 100%;
      border-bottom: 1px solid #716b60;
      padding: 5px;

      &__header {
        display: flex;
        align-items: center;
        justify-content: space-between;
      }
    }
  }
}

.header {
  &__left {
    display: flex;
    align-items: center;
    h5 {
      margin: 0;
    }
  }

  &__writer {
    margin: 0;
    margin-right: 10px;
  }

  &__profile {
    height: 100%;
  }
}

.children {
  margin-left: 20px;
}
#profile {
  width: 3em;
  height: 3em;
  border-radius: 50%;
  margin-right: 5px;
}
#replyArrow {
  margin-top: 10px;
  width: 2em;
  height: 2em;
}
#reply {
  width: 1em;
  height: 1em;
}
.comment-item__main {
  display: flex;
  margin-top: 15px;
  padding: 3px;
  align-items: center;
  h3 {
    margin: 0;
  }
  img {
    width: 1.5rem;
    height: 1.5rem;
    margin-right: 10px;
  }
}
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

#btn--reply {
  border: 0;
  img {
    margin-right: 5px;
  }
}

#btn--reply:hover {
  color: black;
}
</style>
