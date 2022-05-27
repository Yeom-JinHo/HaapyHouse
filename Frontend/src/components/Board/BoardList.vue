<template>
  <div class="container">
    <div class="list-header">
      <div class="search-bar">
        <select v-model="searchType" name="search-type" id="search-type">
          <option :value="null">검색옵션</option>
          <option value="nickName">작성자</option>
          <option value="title">제목</option>
        </select>
        <input
          v-model="searchVal"
          class="search-input"
          type="text"
          placeholder="검색어를 입력하세요.."
        />
        <button @click="searchByType" id="btn--search" class="btn btn-simple">
          검색
        </button>
      </div>
      <button @click="moveRegist" class="btn btn-simple" id="btn--regist">
        글쓰기
      </button>
    </div>

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
import { mapMutations, mapState } from "vuex";
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
      searchType: null,
      searchVal: "",
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("msgStore", ["successMsg"]),
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
  created() {
    this.search();
  },
  methods: {
    ...mapMutations("msgStore", [
      "SET_WARNING_MSG",
      "SET_INFO_MSG",
      "SET_SUCCESS_MSG",
      "SET_DANGER_MSG",
    ]),
    async search() {
      let params = "";
      if (this.searchType != null) {
        params = this.searchType == "nickName" ? "/?nickName=" : "/?title=";
        params += this.searchVal;
        this.SET_SUCCESS_MSG({ visible: false });
      }
      console.log(params);
      try {
        const res = await boardApi.get(params);
        // console.log(res);
        if (res.status == 200) {
          this.boards = res.data.reverse();
          !this.successMsg.visible &&
            this.SET_SUCCESS_MSG({
              visible: true,
              msg: `검색결과가 ${this.boards.length}건 있어요!`,
            });
          console.log(this.boards);
        } else if (res.status == 204) {
          this.boards = [];
          this.SET_INFO_MSG({
            visible: true,
            msg: "검색결과가 존재하지 않습니다.",
          });
        } else {
          this.SET_DANGER_MSG({
            visible: true,
            msg: "글을 조회하는데 실패하였습니다.",
          });
        }
      } catch (e) {}
    },
    searchByType() {
      if (!this.searchType) {
        this.SET_WARNING_MSG({ visible: true, msg: "검색타입을 선택해주세요" });
      } else if (!this.searchVal) {
        this.SET_WARNING_MSG({ visible: true, msg: "검색어를 입력해주세요" });
      } else {
        this.search();
      }
    },
    moveRegist() {
      if (!this.userInfo) {
        this.SET_WARNING_MSG({
          visible: true,
          msg: "로그인을 먼저 진행해주세요",
        });
      } else {
        this.$router.push("board/regist");
      }
    },
  },
};
</script>

<style lang="scss" scoped>
#btn--search,
#btn--regist {
  height: 2rem;
  font-size: 1.25rem;
  display: flex;
  align-items: center;
  color: #f96332;
  border: 1px solid #f96332;
  margin-left: 10px;
}

#btn--search:hover,
#btn--regist:hover {
  color: white;
  background-color: #f96332;
}

.list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
  a {
    margin: 0;
  }
  .search-bar {
    display: flex;
    align-items: center;
    .search-input,
    #search-type {
      height: 2rem;
      font-size: 1.25rem;
      margin-left: 5px;
    }
  }
}
.pagination {
  justify-content: center;
}
// .regist-btn {
//   font-size: 1.5rem;
//   margin-right: 1vw;
//   margin-bottom: 1vh;
// }
</style>
