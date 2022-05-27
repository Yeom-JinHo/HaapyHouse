<template>
  <tbody>
    <tr
      @click="moveDetail(board)"
      v-for="(board, index) in boards"
      :key="board.no"
    >
      <td>{{ startInd + index + 1 }}</td>
      <td>{{ board.title }}</td>
      <td>{{ board.nickName }}</td>
      <td>{{ board.regDate }}</td>
    </tr>
  </tbody>
</template>

<script>
export default {
  props: ["boards", "startInd"],
  methods: {
    moveDetail: function (board) {
      // console.log(board);
      this.$router.push("/board/detail/" + board.no);
    },
  },
};
</script>

<style scoped>
tr:hover {
  /* background-color: #f96332; */
  background-color: #f7a084;
}
</style>
