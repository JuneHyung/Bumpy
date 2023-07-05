<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About Today's Meal</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ infoName.value }}</h2>
      <div class="meal-info-box">
        <div class="chart-wrap">
          <ImageList :list="testImageList"></ImageList>
        </div>
        <div class="info-list-wrap">
          <div class="info-item">
            <template v-for="(info, idx) in infoList" :key="idx">
              <p class="bp-mr-sm">
                <template v-for="(item, iIdx) in info" :key="iIdx">
                  <span>{{ item.label }} : {{ item.value }} {{ item.unit }}</span>
                </template>
              </p>
            </template>
          </div>
          <div class="info-memo-box">
            <textarea></textarea>
          </div>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <p>Youtube or chart</p>
        </div>
      </div>
      <div class="mealDetail-button-wrap">
        <button class="short-ghost-button bp-mr-sm">취소</button>
        <button class="short-filled-button">수정</button>
      </div>
    </div>
  </main>
</template>
<script setup>
import ImageList from "~~/components/list/ImageList.vue";
definePageMeta({
  layout: 'main-layout',
});
const infoName = { key: 'name', label: '', value: '닭밥' };
const infoList = [
  [
    { key: 'kcal', label: 'Kcal', value: 235, unit: 'kcal' },
    { key: 'time', label: 'Time', value: '10 : 00', unit: '' },
    { key: 'water', label: 'Water', value: 3.2, unit: 'L' },
  ],
];
const infoMemo = { key: 'memo', label: '', value: 'memomemomemeomeoemo' };


const testImageList = [
  'http://localhost:3000/_nuxt/assets/images/p01.jpg',
  'http://localhost:3000/_nuxt/assets/images/p02.jpg',
  'http://localhost:3000/_nuxt/assets/images/p03.jpg',
  'http://localhost:3000/_nuxt/assets/images/p04.jpg',
  'http://localhost:3000/_nuxt/assets/images/p05.jpg',
]
</script>
