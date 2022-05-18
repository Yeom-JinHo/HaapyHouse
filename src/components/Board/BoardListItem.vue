<template>
  <tbody>
    <tr v-for="board in boards" :key="board.no">
      <td>{{ board.no }}</td>
      <td>{{ board.title }}</td>
      <td>{{ board.writer }}</td>
      <td>{{ board.regDate }}</td>
    </tr>
  </tbody>
</template>

<script>
export default {
  props: ["boards"],
  methods: {
    test: function () {
      alert(this.boards);
    },
  },
};
</script>

<style></style>
