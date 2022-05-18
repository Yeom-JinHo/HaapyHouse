<template>
  <div class="comment-item">
    <div class="comment-item__header">
      <div class="header__left">
        <h3 class="header__writer">{{ comment.writer }}</h3>
        <h5>{{ getDate }}</h5>
      </div>
      <div class="header__right">
        <button id="btn--modify" class="btn btn-simple">수정</button>
        <button id="btn--delete" class="btn btn-simple">삭제</button>
      </div>
    </div>
    <div class="comment-item__main">
      <img src="img/comment.svg" alt="" />
      <h3>{{ comment.description }}</h3>
    </div>
  </div>
</template>

<script>
export default {
  props: ["comment"],
  computed: {
    getDate() {
      let date = this.comment.regDate;
      let year = date.slice(0, 4) + "년 ";
      let month = date.slice(5, 7) + "월 ";
      let day = date.slice(8, 10) + "일 ";
      let hour = date.slice(11, 13) + "시 ";
      let min = date.slice(14, 16) + "분 ";
      let sec = date.slice(17, 19) + "초 ";
      return year + month + day + hour + min + sec;
    },
  },
};
</script>

<style lang="scss" scoped>
.comment-item {
  margin-bottom: 3vh;
  padding: 5px;
  border-bottom: 1px solid gainsboro;
}

.comment-item__header {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.header__writer {
  margin: 0;
}

.comment-item__main {
  display: flex;
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
</style>
