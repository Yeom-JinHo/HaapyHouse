<template>
  <div class="map_wrap">
    <div id="map" style="width: 100%; height: 500px"></div>
    <!-- <ul id="category">
      <li id="BK9" data-order="0">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li id="CS2" data-order="5">
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul> -->
  </div>
</template>

<script>
export default {
  props: ["aptList"],
  data() {
    return {
      markers: [],
      placeMarkers: [],
      infowindow: null,
      curCategory: "",
      placeOverlay: "",
      ps: "",
      map: "",
    };
  },
  watch: {
    aptList: function () {
      console.log(this.aptList);
      this.displayMarker(this.aptList);
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_MAP_API_KEY}&libraries=services`;
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.5125, 127.102778),
        level: 5,
      };
      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      this.ps = new kakao.maps.services.Places(map);

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);

      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });

      const contentNode = document.createElement("div");
      contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      this.addEventHandle(
        contentNode,
        "mousedown",
        kakao.maps.event.preventMap
      );
      this.addEventHandle(
        contentNode,
        "touchstart",
        kakao.maps.event.preventMap
      );

      this.placeOverlay.setContent(contentNode);

      this.addCategoryClickEvent();
    },
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    addCategoryClickEvent() {
      let category = document.getElementById("category"),
        children = category.children;

      for (let i = 0; i < children.length; i++) {
        children[i].onclick = this.onClickCategory;
      }
    },
    onClickCategory(e) {
      // let id = this.id,
      // className = this.className;
      let id = e.currentTarget.getAttribute("id");
      let className = e.currentTarget.getAttribute("class");

      console.log("onClickCategory", id, className);

      this.placeOverlay.setMap(null);

      if (className === "on") {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removePlaceMarker();
      } else {
        this.currCategory = id;
        this.changeCategoryClass(e.currentTarget);
        this.searchPlaces();
      }
    },
    changeCategoryClass(el) {
      let category = document.getElementById("category"),
        children = category.children,
        i;
      console.log("changeCategoryClass", el);
      for (i = 0; i < children.length; i++) {
        // children[i].setAttribute("class", "");
        children[i].className = "";
        // children[i].setAttribute("class", "");
      }

      if (el) {
        el.className = "on";
      }
    },
    displayPlaces(places) {
      // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
      // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
      let order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");

      console.log("displayPlace Order : ", order, places);

      for (let i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        let marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order
        );
        // // 마커와 검색결과 항목을 클릭 했을 때
        // // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
        // (function (marker, place) {
        //   kakao.maps.event.addListener(marker, "click", function () {
        //     displayPlaceInfo(place);
        //   });
        // })(marker, places[i]);
      }
    },
    addMarker(position, order) {
      console.log("addMarker", position, order);
      let imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png"; // 마커 이미지 url, 스프라이트 이미지를 씁니다
      let imageSize = new kakao.maps.Size(27, 28); // 마커 이미지의 크기
      let imgOptions = {
        spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(46, order * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      };

      let markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imgOptions
      );

      let placeMarker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });
      placeMarker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.placeMarkers.push(placeMarker); // 배열에 생성된 마커를 추가합니다
      return placeMarker;
    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      // 커스텀 오버레이를 숨깁니다
      this.placeOverlay.setMap(null);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removePlaceMarker();

      // this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
      //   useMapBounds: true,
      // });
      this.ps.categorySearch(this.currCategory, this.placesSearchCB);
    },
    placesSearchCB(data, status, pagination) {
      console.log("palceSearchCB", data);
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
        this.displayPlaces(data);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
      } else if (status === kakao.maps.services.Status.ERROR) {
        // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
      }
    },
    removePlaceMarker() {
      console.log("RemovePlace");
      for (let i = 0; i < this.placeMarkers.length; i++) {
        this.placeMarkers[i].setMap(null);
      }
      this.placeMarkers = [];
    },

    /// 화면 표시
    displayMarker(markerPositions) {
      console.log("DISPLAY MARKER", markerPositions);
      markerPositions = markerPositions.map((item) => [item.lat, item.lng]);
      // 마커 초기화
      this.removeAllMarker();
      if (!window.kakao) return;
      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      console.log("DisPlay MArker", positions);
      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      let iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
    removeAllMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
    },
  },
};
</script>

<style>
/* .map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
} */
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 50px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}
#category li .category_bg {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png)
    no-repeat;
}
#category li .bank {
  background-position: -10px 0;
}
#category li .mart {
  background-position: -10px -36px;
}
#category li .pharmacy {
  background-position: -10px -72px;
}
#category li .oil {
  background-position: -10px -108px;
}
#category li .cafe {
  background-position: -10px -144px;
}
#category li .store {
  background-position: -10px -180px;
}
#category li.on .category_bg {
  background-position-x: -46px;
}
.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png)
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
