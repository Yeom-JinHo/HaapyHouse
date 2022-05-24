<template>
  <div>
    <div
      class="page-header clear-filter"
      id="custom-header"
      filter-color="orange"
    >
      <parallax
        class="page-header-image"
        style="background-image: url('img/korea.jpg')"
      >
      </parallax>
      <div class="container">
        <h1 class="title">뉴스</h1>
      </div>
    </div>
    <div class="section" id="custom-section">
      <div class="container">
        <div class="row">
          <div class="col-md-6 ml-auto mr-auto">
            <h4 class="title text-center">
              아파트 관련 뉴스를 여기서 만나보세요!
            </h4>
          </div>
          <div class="col-md-10 ml-auto mr-auto">
            <div class="row collections">
              <div
                data-aos="fade-up"
                data-aos-duration="1000"
                class="col-md-6 news"
                v-for="(item, ind) in news"
                :key="ind"
              >
                <div>
                  <img src="img/bg6.jpg" ref="test" class="img-raised" />
                  <a :href="item.링크">{{ item.기사제목 }}</a>
                  <p>{{ item.기사일자 }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "news",
  data() {
    return {
      news: {
        1: {
          기사제목: "올해 1분기, 서울 주택거래 10채 중 3채 외지인이 사들여",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/437348/",
          기사일자: "2022.05.18 10:21",
        },
        2: {
          기사제목: "주춤했던 1억 이하 경기도 아파트 거래량 다시 증가세",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/438875/",
          기사일자: "2022.05.18 14:28",
        },
        3: {
          기사제목: "새롭게 탈바꿈 될 수변 공간 주변서 신규 공급 잇따라",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/438099/",
          기사일자: "2022.05.18 12:02",
        },
        4: {
          기사제목:
            "미국도 집 모자라서 난리다. 금리 인상 상관없이 집값은 오른다",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/436900/",
          기사일자: "2022.05.18 09:04",
        },
        5: {
          기사제목:
            "매경부동산사업단, 임대인을 위한 상가임대차법 해설 특강 개최",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/436831/",
          기사일자: "2022.05.18 08:46",
        },
        6: {
          기사제목:
            "거래 '상위 10곳' 보니…올 최고가 아파트 키워드는 한남동·한강뷰",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/435688/",
          기사일자: "2022.05.17 17:19",
        },
        7: {
          기사제목:
            "'3채 이상' vs '2채 이상' 다주택자 기준 놓고 일반인 생각 팽팽",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/435066/",
          기사일자: "2022.05.17 15:34",
        },
        8: {
          기사제목: '"서울 강남이 아니었어"…올해 최고가 85억 찍은 곳은',
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/434300/",
          기사일자: "2022.05.17 13:35",
        },
        9: {
          기사제목: "집값 1~2년 만에 40% 폭락 한다면 매수 절호의 찬스",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/432456/",
          기사일자: "2022.05.16 21:49",
        },
        10: {
          기사제목: "서울 매매·전세가 다시 '꿈틀'…부동의 강남·서초 꺾은 용산",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/431794/",
          기사일자: "2022.05.16 16:23",
        },
        11: {
          기사제목: "낮아진 공공기관 종사자 연령…주변 오피스텔 관심",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/431459/",
          기사일자: "2022.05.16 15:27",
        },
        12: {
          기사제목:
            '"강남 용산 등 상급지 빼고 외곽 하급지 정리"…양극화 원인된 양도세 중과 한시배제',
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/431188/",
          기사일자: "2022.05.16 14:51",
        },
        13: {
          기사제목:
            "[매경부동산사업단 칼럼] 건설도급계약서가 세법에서 왜 중요한가?",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/430678/",
          기사일자: "2022.05.16 13:01",
        },
        14: {
          기사제목: "갱신권 안쓴 월·전세 재계약 시 10% 넘게 뛰어",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/429636/",
          기사일자: "2022.05.16 10:31",
        },
        15: {
          기사제목: '"2억 내렸어요"…양도세 중과 유예에 급매물 는다',
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/427736/",
          기사일자: "2022.05.15 15:05",
        },
        16: {
          기사제목: "'5채 중 1채는 서울 사람이 원정 투자'…어디?",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/427665/",
          기사일자: "2022.05.15 14:51",
        },
        17: {
          기사제목: "건축주가 시공사와 체결하는 도급계약서가 왜 중요한가?",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/425825/",
          기사일자: "2022.05.14 09:01",
        },
        18: {
          기사제목: "전남 일대 중대형 아파트매매가격지수 가장 높아",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/424426/",
          기사일자: "2022.05.13 15:52",
        },
        19: {
          기사제목:
            "올해 지방광역시 대단지 공급 3곳 불과…건자재값 상승에 공급 가뭄 심화",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/423427/",
          기사일자: "2022.05.13 12:10",
        },
        20: {
          기사제목:
            "서울 거주자 아파트 매입 지역 어딘가 보니…강원 충남 충북 집중",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/423078/",
          기사일자: "2022.05.13 11:06",
        },
        21: {
          기사제목: "급등한 아파트 전셋값에…공공전세 빌라 경쟁 후끈",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/421265/",
          기사일자: "2022.05.12 17:28",
        },
        22: {
          기사제목: "'국민주택형' 전용 84㎡ 1순위 청약자 비율 전년 比 9.1%↑",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/421128/",
          기사일자: "2022.05.12 17:04",
        },
        23: {
          기사제목: "블루코브자산운용, 옛 한국전력기술 용인사옥 부지 본격 개발",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/420458/",
          기사일자: "2022.05.12 15:24",
        },
        24: {
          기사제목: "'수도권 아파트값' 한 주 만에 다시 하락 전환",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/420202/",
          기사일자: "2022.05.12 14:44",
        },
        25: {
          기사제목: "광역시 뺀 기타 지방 아파트값도 평당 1000만원 넘었다",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/419307/",
          기사일자: "2022.05.12 11:14",
        },
        26: {
          기사제목: "지방 아파트값 평당 1000만원 돌파…지난 1년간 28% 올라",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/419104/",
          기사일자: "2022.05.12 10:53",
        },
        27: {
          기사제목:
            "윤 대통령 당선 후 노원구에 무슨 일이?…'이 아파트' 눈에 띄네",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/417821/",
          기사일자: "2022.05.11 21:08",
        },
        28: {
          기사제목: "매경부동산사업단 5/14일 토요 정기특강 실시",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/415462/",
          기사일자: "2022.05.11 11:30",
        },
        29: {
          기사제목: "고가 아파트값, 저가 10배…5년새 집값 2배로 벌어져",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/413249/",
          기사일자: "2022.05.10 17:17",
        },
        30: {
          기사제목: "서울 중산층, 월급 19년 모아야 중간값 주택 산다",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/412069/",
          기사일자: "2022.05.10 14:25",
        },
        31: {
          기사제목: "현재 전셋값이면 5년 전 아파트 사고도 남았다",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/407250/",
          기사일자: "2022.05.09 11:29",
        },
        32: {
          기사제목: "서울서 새로 전셋집 구하려면 갱신보다 1.5억 더 필요",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/406747/",
          기사일자: "2022.05.09 10:04",
        },
        33: {
          기사제목: '"규제완화 신호에도…집값 당분간 하락" [매부리TV]',
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/405703/",
          기사일자: "2022.05.08 17:02",
        },
        34: {
          기사제목: '"이참에 양도세 덜 내고 팔까"...수도권 매물 다시 늘었다',
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/405702/",
          기사일자: "2022.05.08 17:02",
        },
        35: {
          기사제목: "임대료 내면 남는 게 없다는 사장님들의 말 엄살 아니었다",
          링크: "https://www.mk.co.kr/news/realestate/view/2022/05/403422/",
          기사일자: "2022.05.06 22:03",
        },
      },
    };
  },
};
</script>

<style lang="scss" scoped>
.news {
  a {
    font-size: 2em;
  }
}
.news:hover {
  transform: scale(1.1);
  transition: ease-in-out;
}
</style>
