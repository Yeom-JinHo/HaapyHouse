<template>
  <div class="container">
    <router-link class="pull-right regist-btn" to="/board/regist"
      >글쓰기</router-link
    >
    <div class="table-container">
      <table class="table">
        <thead>
          <tr>
            <th>No</th>
            <th>title</th>
            <th>writer</th>
            <th>regTiem</th>
          </tr>
        </thead>
        <board-list-item :boards="sliceBoardList" :startInd="startInd" />
      </table>
      <pagination
        type="primary"
        :total="boards.length"
        v-model="nowPage"
      ></pagination>
    </div>
  </div>
</template>

<script>
import boardApi from "@/apis/boardApi.js";
import Pagination from "@/components/Pagination.vue";
import BoardListItem from "@/components/Board/BoardListItem.vue";
export default {
  components: {
    Pagination,
    BoardListItem,
  },
  data() {
    return {
      boards: [],
      nowPage: 1,
      defaultPerPage: 10,
    };
  },
  computed: {
    startPage: function () {
      return (this.nowPage - 1) * this.defaultPerPage;
    },
    sliceBoardList: function () {
      return this.boards.slice(this.startPage, this.startPage + 10);
    },
    startInd: function () {
      return (this.nowPage - 1) * this.defaultPerPage;
    },
  },
  async created() {
    this.showInfoMsg();
    try {
      const res = await boardApi.get();
      this.boards = res.data.reverse();
    } catch (e) {
      console.log(e);
    }
  },
  methods: {
    showInfoMsg() {
      if (this.boards.length == 0) {
        this.$store.commit("SET_INFO_MSG", {
          visible: true,
          msg: "글이 하나도 없어요!!",
        });
      } else {
        this.$store.commit("SET_INFO_MSG", {
          visible: false,
          msg: "",
        });
      }
    },
  },
  watch: {
    boards: function () {
      this.showInfoMsg();
    },
  },
};
</script>

<style scoped>
.pagination {
  justify-content: center;
}
.regist-btn {
  font-size: 1.5rem;
  margin-right: 1vw;
  margin-bottom: 1vh;
}
</style>
