<template>
  <div class="page-header clear-filter" filter-color="orange">
    <div
      class="page-header-image"
      style="background-image: url('img/login.jpg')"
    ></div>
    <div id="index_section" class="container">
      <div class="card col-sm-12 mt-1">
        <div class="card-body">
          <div class="form-group form-inline justify-content-center">
            <label class="mr-2" for="sido">시도 : </label>
            <select class="form-control" id="sido">
              <option value="0">선택</option>
            </select>
            <label class="mr-2 ml-3" for="gugun">구군 : </label>
            <select class="form-control" id="gugun">
              <option value="0">선택</option>
            </select>
            <label class="mr-2 ml-3" for="dong">읍면동 : </label>
            <select class="form-control" id="dong">
              <option value="0">선택</option>
            </select>
            <!-- <button type="button" id="aptSearchBtn">검색</button> -->
          </div>
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
                <th class="text-center">주소</th>
                <th>건축연도</th>
                <th>최근거래금액</th>
              </tr>
            </thead>
            <tbody id="searchResult"></tbody>
          </table>
          <div id="map" style="width: 100%; height: 500px"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script></script>

<style></style>
