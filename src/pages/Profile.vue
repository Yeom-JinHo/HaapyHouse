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
        <div class="card col-sm-12 mt-1">
          <div class="card-body">
            <table class="table mt-2">
              <colgroup>
                <col width="100" />
                <col width="150" />
                <col width="*" />
                <col width="120" />
                <col width="120" />
              </colgroup>
              <thead>
                <tr>
                  <th>번호</th>
                  <th>아파트이름</th>
                  <th
                    :class="{ sorted: sortedType == 'dealDate' }"
                    @click="sort('dealDate')"
                  >
                    거래일자
                    <img
                      :class="{ descSort: !descSort }"
                      src="/img/up.svg"
                      alt=""
                    />
                    <img
                      :class="{ descSort: descSort }"
                      src="/img/down.svg"
                      alt=""
                    />
                  </th>
                  <th
                    :class="{ sorted: sortedType == 'dealAmout' }"
                    @click="sort('dealAmout')"
                  >
                    거래금액
                    <img
                      :class="{ descSort: !descSort }"
                      src="/img/up.svg"
                      alt=""
                    />
                    <img
                      :class="{ descSort: descSort }"
                      src="/img/down.svg"
                      alt=""
                    />
                  </th>
                  <th
                    :class="{ sorted: sortedType == 'area' }"
                    @click="sort('area')"
                  >
                    면적
                    <img
                      :class="{ descSort: !descSort }"
                      src="/img/up.svg"
                      alt=""
                    />
                    <img
                      :class="{ descSort: descSort }"
                      src="/img/down.svg"
                      alt=""
                    />
                  </th>
                  <th
                    :class="{ sorted: sortedType == 'floor' }"
                    @click="sort('floor')"
                  >
                    층수
                    <img
                      :class="{ descSort: !descSort }"
                      src="/img/up.svg"
                      alt=""
                    />
                    <img
                      :class="{ descSort: descSort }"
                      src="/img/down.svg"
                      alt=""
                    />
                  </th>
                </tr>
              </thead>
              <!-- <tbody id="searchResult"></tbody> -->
              <tbody>
                <tr v-for="(apt, ind) in aptDealList" :key="ind">
                  <td>{{ ind + 1 }}</td>
                  <td>{{ apt.aptName }}</td>
                  <td>
                    {{ apt.dealYear }}년 {{ apt.dealMonth }}월
                    {{ apt.dealDay }}일
                  </td>
                  <td>{{ getWon(apt.dealAmout) }}</td>
                  <td>{{ apt.area }}</td>
                  <td>{{ apt.floor }}층</td>
                </tr>
              </tbody>
            </table>
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
</style>
