<template>
  <ul class="load-list-wrap-box">
    <template v-for="(alpha, idx) in charList" :key="idx">
      <li class="load-list-category-box">
        <p class="load-list-category">{{ alpha }}</p>
        <ul class="load-item-list">
          <template v-for="(item, idx) in list.filter((el) => el.dtlCd.includes(alpha))" :key="idx">
            <li class="ellipsis-1" style="width: 50px">{{ item.dtlNm }}</li>
          </template>
        </ul>
      </li>
    </template>
  </ul>
</template>
<script setup>
const charList = ['가', '나', '다', '라', '마', '바', '사', '아', '자', '차', '카', '타', '파', '하'];
const list = [
  { dtlCd: '가01', dtlNm: '가01' },
  { dtlCd: '나01', dtlNm: '나01' },
  { dtlCd: '나02', dtlNm: '나02' },
  { dtlCd: '다01', dtlNm: '다01' },
  { dtlCd: '다02', dtlNm: '다02' },
  { dtlCd: '다03', dtlNm: '다03' },
  { dtlCd: '라01', dtlNm: '라01123131231' },
  { dtlCd: '라02', dtlNm: '라02' },
  { dtlCd: '마01', dtlNm: '마01' },
  { dtlCd: '바01', dtlNm: '바01' },
  { dtlCd: '사01', dtlNm: '사01' },
  { dtlCd: '아01', dtlNm: '아01' },
  { dtlCd: '자01', dtlNm: '자01' },
  { dtlCd: '차01', dtlNm: '차01' },
  { dtlCd: '카01', dtlNm: '카01' },
  { dtlCd: '타01', dtlNm: '타01' },
  { dtlCd: '파01', dtlNm: '파01' },
  { dtlCd: '히01', dtlNm: '히01' },
];
</script>
<style scoped lang="scss">
.load-list-wrap-box {
  display: flex;
  .load-list-category-box {
    display: flex;
    flex-direction: column;
    flex: 1;
    border: 1px solid black;
    box-sizing: border-box;
    padding: 4px;
    .load-list-category {
      border-bottom: 1px solid black;
    }
  }
  .load-item-list {
    li {
      width: 100%;
      overflow: hidden;
      cursor: pointer;
    }
  }
}
</style>
