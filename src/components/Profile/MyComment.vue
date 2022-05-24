<template>
  <div class="section" id="custom-section">
    <div class="container">
      <div class="card col-sm-12 mt-1">
        <div class="card-body">
          <table class="table mt-2">
            <colgroup>
              <col width="150" />
              <col width="*" />
              <col width="150" />
            </colgroup>
            <thead>
              <tr>
                <th>댓글 번호</th>
                <th>댓글 내용</th>
                <th>작성 일자</th>
              </tr>
            </thead>
            <tbody>
              <tr
                @click="moveComment(comment.notice_no)"
                v-for="(comment, ind) in myComments"
                :key="ind"
              >
                <td>{{ comment.comment_no }}</td>
                <td>{{ comment.comment }}</td>
                <td>{{ comment.comment_time }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ["myComments"],
  methods: {
    moveComment(no) {
      this.$router.push("/board/detail/" + no);
    },
  },
};
</script>

<style></style>
