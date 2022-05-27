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
          <!-- <img src="img/ryan.jpg" alt="" /> -->
          <img :src="userInfo.profileImg" alt="" />
        </div>
        <div class="container" v-if="!isModify">
          <div id="userinfo">
            <img
              v-if="userInfo.socialType == 'kakao'"
              class="social-icon kakao"
              src="img/kakao.svg"
              alt="kakao"
            />
            <img
              v-if="userInfo.socialType == 'naver'"
              class="social-icon naver"
              src="img/naver.svg"
              alt="naver"
            />
            <img
              v-if="userInfo.socialType == 'google'"
              class="social-icon google"
              src="img/google.svg"
              alt="google"
            />
            <h3 class="title">
              {{ userInfo.nickName }}
            </h3>
          </div>

          <p class="category">{{ userInfo.joinDate }}</p>
          <div class="content myAct">
            <div @click="changeView('Apt')" class="social-description">
              <h2 :class="{ isSelected: selected == 'Apt' }">
                {{ likeAptList.length }}
              </h2>
              <p :class="{ isSelected: selected == 'Apt' }">관심 매물</p>
            </div>
            <div @click="changeView('Notice')" class="social-description">
              <h2 :class="{ isSelected: selected == 'Notice' }">
                {{ myNotices.length }}
              </h2>
              <p :class="{ isSelected: selected == 'Notice' }">
                내가 작성한 글
              </p>
            </div>
            <div @click="changeView('Comment')" class="social-description">
              <h2 :class="{ isSelected: selected == 'Comment' }">
                {{ myComments.length }}
              </h2>
              <p :class="{ isSelected: selected == 'Comment' }">
                내가 작성한 댓글
              </p>
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
    <like-apt v-if="selected == 'Apt'" :likeAptList="likeAptList"></like-apt>
    <my-comment
      v-if="selected == 'Comment'"
      :myComments="myComments"
    ></my-comment>
    <my-notice v-if="selected == 'Notice'" :myNotices="myNotices"></my-notice>
  </div>
</template>
<script>
import { mapActions, mapMutations, mapState } from "vuex";
import LikeApt from "@/components/Profile/LikeApt.vue";
import MyComment from "@/components/Profile/MyComment.vue";
import MyNotice from "@/components/Profile/MyNotice.vue";
import userApi from "@/apis/userApi.js";
import profileApi from "@/apis/profileApi.js";

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
      selected: "Apt",
      likeAptList: [],
      myNotices: [],
      myComments: [],
    };
  },
  components: {
    LikeApt,
    MyComment,
    MyNotice,
  },
  async created() {
    this.getLikeApt();
    this.getMyNotice();
    this.getMyComment();
  },
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
    async getLikeApt() {
      const res = await profileApi.post("/apt", {
        userId: this.userInfo.userId,
        socialType: this.userInfo.socialType,
      });
      this.likeAptList = res.data;
    },
    async getMyNotice() {
      const res = await profileApi.post("/notice", {
        userId: this.userInfo.userId,
        socialType: this.userInfo.socialType,
      });
      this.myNotices = res.data;
    },
    async getMyComment() {
      const res = await profileApi.post("/comment", {
        userId: this.userInfo.userId,
        socialType: this.userInfo.socialType,
      });
      this.myComments = res.data;
    },
    modifyData() {
      this.initSetData();
      this.isModify = !this.isModify;
    },
    initSetData() {
      console.log("SET", this.userInfo);
      this.m_userId = this.userInfo.userId;
      // this.m_pwd = this.userInfo.userPwd;
      // this.m_chkPwd=this.userInfo.userPwd;
      this.m_nickName = this.userInfo.nickName;
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
          nickName: this.m_nickName,
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
    changeView(val) {
      this.selected = val;
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
#userinfo {
  display: flex;
  align-items: center;
  justify-content: center;
  .social-icon {
    padding: 3px;
    margin-top: 30px;
    margin-right: 10px;
  }
  .kakao {
    background-color: #f9e000;
  }
  .naver {
    background-color: #04cf5c;
  }
  .google {
    background-color: white;
  }
  .title {
    margin: 0;
  }
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

.myAct {
  color: gray;
}
.myAct:hover {
  cursor: pointer;
}

.isSelected {
  color: white;
  font-weight: 900;
}
</style>
