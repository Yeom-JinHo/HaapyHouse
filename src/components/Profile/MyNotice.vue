<template>
  <div class="section" id="custom-section">
    <div class="container">
      <div class="card col-sm-12 mt-1">
        <div class="card-body">
          <table class="table mt-2">
            <colgroup>
              <col width="100" />
              <col width="250" />
              <col width="*" />
              <col width="150" />
            </colgroup>
            <thead>
              <tr>
                <th>글 번호</th>
                <th>글 제목</th>
                <th>글 내용</th>
                <th>작성 일자</th>
              </tr>
            </thead>
            <tbody>
              <tr
                @click="moveNotice(notice.no)"
                v-for="(notice, ind) in myNotices"
                :key="ind"
              >
                <td>{{ notice.no }}</td>
                <td>{{ notice.title }}</td>
                <td>{{ notice.description.slice(0, 50) }}</td>
                <td>{{ notice.regDate }}</td>
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
  props: ["myNotices"],
  methods: {
    moveNotice(no) {
      this.$router.push("/board/detail/" + no);
    },
  },
};
</script>

<style></style>
