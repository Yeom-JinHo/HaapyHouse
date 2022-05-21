<template>
  <div>
    <div
      class="page-header clear-filter"
      id="custom-header"
      filter-color="orange"
    >
      <parallax
        class="page-header-image"
        style="background-image: url('img/bg5.jpg')"
      >
      </parallax>
      <div class="container" v-if="userInfo">
        <div class="content" id="profile-set">
          <div></div>

          <img
            v-if="!isModify"
            @click="modifyData"
            id="modifyBtn"
            src="img/modifyBtn.svg"
            alt="회원정보수정"
          />

          <img
            v-else
            @click="modifyData"
            id="modifyBtn"
            src="img/modifyBtn2.svg"
            alt="회원정보수정"
          />
        </div>

        <div class="photo-container">
          <img src="img/ryan.jpg" alt="" />
        </div>
        <div class="container" v-if="!isModify">
          <h3 class="title">
            {{ userInfo.userName }}
          </h3>
          <p class="category">{{ userInfo.joinDate }}</p>
          <div class="content">
            <div class="social-description">
              <h2>26</h2>
              <p>Stars</p>
            </div>
            <div class="social-description">
              <h2>26</h2>
              <p>Comments</p>
            </div>
            <div class="social-description">
              <h2>48</h2>
              <p>Bookmarks</p>
            </div>
          </div>
        </div>
        <div class="modify-form content" v-else>
          <label for="">아이디</label>
          <input type="text" v-model="m_userId" disabled />
          <label for="">비밀번호"</label>
          <input type="text" v-model="m_pwd" ref="pwd" />
          <label for="">비밀번호 확인</label>
          <input type="text" v-model="m_chkPwd" ref="chkPwd" />
          <label for="">닉네임</label>
          <input type="text" v-model="m_nickName" ref="nickName" />
          <button @click="modifyUser" class="btn btn-simple" id="btn--modify">
            회원정보 수정
          </button>
          <button @click="modifyData" class="btn btn-simple" id="btn--list">
            취소
          </button>
        </div>
      </div>
    </div>
    <div class="section" id="custom-section">
      <div class="container">
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">된다면 여기에 관심 아파트 리스트</h4>
          </div>
          <div class="col-md-10 ml-auto mr-auto">
            <div class="row collections">
              <div class="col-md-6">
                <img src="img/bg6.jpg" ref="test" class="img-raised" />
                <img src="img/bg11.jpg" alt="" class="img-raised" />
              </div>
              <div class="col-md-6">
                <img src="img/bg7.jpg" alt="" class="img-raised" />
                <img src="img/bg8.jpg" alt="" class="img-raised" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapMutations, mapState } from "vuex";
import userApi from "@/apis/userApi.js";

export default {
  name: "profile",
  bodyClass: "profile-page",
  data() {
    return {
      isModify: false,
      m_userId: "",
      m_pwd: "",
      m_chkPwd: "",
      m_nickName: "",
    };
  },
  components: {},
  created() {},
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    ...mapActions("userStore", ["getUserInfo"]),
    ...mapMutations("msgStore", [
      "SET_WARNING_MSG",
      "SET_SUCCESS_MSG",
      "SET_DANGER_MSG",
    ]),
    modifyData() {
      this.initSetData();
      this.isModify = !this.isModify;
    },
    initSetData() {
      console.log("SET", this.userInfo);
      this.m_userId = this.userInfo.userId;
      // this.m_pwd = this.userInfo.userPwd;
      // this.m_chkPwd=this.userInfo.userPwd;
      this.m_nickName = this.userInfo.userName;
    },
    async modifyUser() {
      let err = true;
      let msg = "";
      this.m_pwd != this.m_chkPwd &&
        ((msg = "비밀번호가 서로 달라요! 다시 입력해주세요"),
        (err = false),
        this.$refs.chkPwd.focus());
      err &&
        !this.m_nickName &&
        ((msg = "닉네임을 입력해주세요"),
        (err = false), //
        this.$refs.nickName.focus());
      if (!err) {
        this.SET_WARNING_MSG({ visible: true, msg });
      } else {
        const newUser = {
          ...this.userInfo,
          userId: this.m_userId,
          userPwd: this.m_pwd,
          userName: this.m_nickName,
        };
        console.log(newUser);
        const res = await userApi.put(
          "/update/" + this.userInfo.userId,
          newUser
        );
        console.log(res);
        if (res.status == 200) {
          this.SET_SUCCESS_MSG({
            visible: true,
            msg: "유저 정보수정에 성공하였습니다.",
          });
          await this.getUserInfo(this.userInfo.socialType);
          this.isModify = !this.isModify;
        } else {
          this.SET_DANGER_MSG({
            visible: true,
            msg: "유저정보 수정에 실패하였습니다.",
          });
        }
      }
    },
  },
};
</script>
<style lang="scss">
.modify-form {
  display: flex;
  flex-direction: column;
  text-align: start;
  label {
    font-size: 1.5rem;
  }
}

#profile-set {
  display: flex;
  justify-content: space-between;
}

#modifyBtn {
  width: 1.5rem;
}

#custom-header {
  min-height: 10vh;
}

#custom-section {
  min-height: 77vh;
}

// .btn {
// background-color: white;
// }

#btn--modify,
#btn--regist {
  color: #18ce0f;
  border: 1px solid #18ce0f;
}

#btn--modify:hover,
#btn--regist:hover {
  color: white;
  background-color: #18ce0f;
}

#btn--list {
  color: blue;
  border: 1px solid blue;
}

#btn--list:hover {
  color: white;
  background-color: blue;
}
</style>
