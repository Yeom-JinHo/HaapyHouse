# 🏚 HappyHouse

우리도 집을 살 수 있을까요 ??

<a href="https://docs.google.com/spreadsheets/d/1xI6x8tR1MtWfgvk3urXf1aQnyEKxnyC6h6kf_ihnXZs/edit#gid=0">백로그</a>

## 👨‍👦‍👦 팀소개

<div align="center">

|    염진호    |   이민재    |   홍준표    |
| :----------: | :---------: | :---------: |
| Web Frontend | Web Backend | Web Backend |

</div>

## 🛠 개발환경 및 라이브러리

<div align="center">
  <img src="https://img.shields.io/badge/node-14.19.2-339933?logo=node.js"> 
<img src="https://img.shields.io/badge/vue-2.6-4FC08D?logo=vue.js">
<img src="https://img.shields.io/badge/scss--CC6699?logo=sass"> 
<img src="https://img.shields.io/badge/mysql-8.0.28-4479A1?logo=mysql"> 
<img src="https://img.shields.io/badge/spring-2.6.7-6DB33F?logo=spring">

</div>

## 🔥 주요 기능

1. 지역별 주변 아파트 검색
2. 아파트 실거래과 현황 조회
3. 아파트 관련 뉴스 확인
4. 자유게시판, 무한 대댓글 가능
5. 소셜 로그인

## 🖥 화면 구성

### 홈화면

> 홈화면

#### 👍 최강없는 최강 팀

![스크린샷 2022-05-26 오후 3 21 51](https://user-images.githubusercontent.com/81306489/170429514-f72f6e9e-fdab-4251-a4ca-0f54b11123b2.png)

> 랭킹시스템

#### 👍 10분단위의 조회수 랭킹 시스템

![스크린샷 2022-05-26 오후 3 22 20](https://user-images.githubusercontent.com/81306489/170429728-3493c604-0287-4aa0-b428-36772655f983.png)

### 아파트화면

> 아파트조회

### 👍 시도 구군 동 단위의 아파트 검색

![스크린샷 2022-05-26 오후 3 22 26](https://user-images.githubusercontent.com/81306489/170429814-da9f6322-7144-4986-ae87-5859f34d7e16.png)

### 뉴스

#### 👍 아파트 관련 기사들

![스크린샷 2022-05-26 오후 3 22 33](https://user-images.githubusercontent.com/81306489/170429944-6daea609-5ba0-461b-a171-4f63fe4661fb.png)

### 게시판

#### 👍 게시글 CRUD, 무한대댓글

![스크린샷 2022-05-26 오후 3 22 44](https://user-images.githubusercontent.com/81306489/170430096-d1791592-1f9e-40c0-88de-61cdf1caeed6.png)

### 로그인

#### 👍 소셜로그인 ( 네이버, 카카오, 구글 )

![스크린샷 2022-05-26 오후 3 22 52](https://user-images.githubusercontent.com/81306489/170430111-218a8d6f-f7a2-4608-a270-056a16199eb1.png)

## 🥕 기술적인 고민들

<details>
<summary>🍪 클라이언트단에 JWT 보관장소</summary>

- JWT 사용하게 됨으로써 클라이언트단에 토큰을 저장할 필요가 있었는데, 저희가
  고려해본 저장소는 JS변수, 로컬스토리지, 세션 스토리지, 쿠키 입니다.

- 첫번째 JS변수는 재사용성이 떨어짐으로 제외하였고 로컬스토리지는 브라우저가 꺼져도 유지되므로 제외하였습니다.

- 최종적으로 세션스토리지, 쿠키가 남았는데 세션스토리지는 XSS에 취약하고, 쿠키는 CSRF에 취약하기에 무척 고민되었습니다. 결론적으로 말씀드리자면 완벽하게 대비하는것은 힘들겠지만, 두가지 공격을 대비하여 액세스 토큰 외로 리프레시 토큰을 발급받았습니다.

- CSRF 공격을 대비한 **세션스토리지에 액세스토큰**을, XSS공격을 대비한 **Httponly 쿠키에 리프레시 토큰**을 담았습니다.

</details>

<details>
<summary>🥇 랭킹 시스템 최적화</summary>

- aptCode를 외래키로, count,precount,preRank,rankChange로 테이블을 구성하였습니다. 초기에는 다 0 값으로 초기화됩니다. 이후에 조회수가 증가하면 precount와 count의 차이만큼 랭킹이 정렬되고 이전 랭킹과 비교하여 순위변동까지 보여줄 수 있는 로직으로 구성하였습니다.

```java
        @Override
	public void rankUpdate() throws Exception {
		List<CountDto> list = countMapper.preCountList();

		for (int i = 0; i < list.size(); i++) {
			// ( preCount를 count로 업데이트 )
			list.get(i).setPreCount(list.get(i).getCount());
			// 현재 index - preRank 를 rankChange에 업데이트
			list.get(i).setRankChange(-(i + 1 - list.get(i).getPreRank()));
			// preRank는 현재 index로 업데이트
			list.get(i).setPreRank(i + 1);
		}
		countMapper.rankUpdate(list);
	}
```

- 랭킹시스템 서비스단 로직입니다. 정렬 갱신 갱신으로 상당히 시간적으로 손해가 큰 로직인데 저희는 설계를 실시간성이 아니라 10분단위로 생각하였기에, 데이터가 4만건인 테이블에서 그렇게까지 시간소비가 안될 것이라고 예상하였습니다. 결과는 예상대로 맥os환경에서는 2분이나… 윈도우에서는 무려 7분이 걸렸습니다.

```java

	@Override
	public void visitApt(String aptCode) throws Exception {
		if(countMapper.checkApt(aptCode) == 1) {
			countMapper.visitApt(aptCode);
		}else {
			List<CountDto> list = countMapper.countList();

			Map<String, Object> map = new HashMap<>();
			map.put("size", list.size()+1);
			map.put("aptCode", aptCode);
			countMapper.visitInsertApt(map);
		}
	}
```

- aptCount를 아예 다비우고, 조회가 발생한 아파트만 DB에 밀어넣는 방식으로 문제를 해결하였습니다.

- 저희 설계 상으로는 실시간성이아니라 문제는 해결되었지만, 만약 모든 아파트가 조회가 일정하게 되고, 그 안에 트래픽이 몰린다면, 어떻게 로직을 구성하면 좋을지 고민중입니다.

</details>
