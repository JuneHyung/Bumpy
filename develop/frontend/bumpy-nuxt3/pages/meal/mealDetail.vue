<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About Today's Meal</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ mealStore.getSelectItem().name }}</h2>
      <div class="meal-info-box">
        <div class="chart-wrap">
          <ImageList :list="testImageList"></ImageList>
        </div>
        <div class="info-list-wrap">
          <div class="info-item">
            <template v-for="(info, idx) in infoList" :key="idx">
              <p class="bp-mr-sm">
                <template v-for="(item, iIdx) in info" :key="item.key">
                  <span>{{ item.label }} : {{ mealStore.getSelectItem()[item.key] }} {{ item.unit }}</span>
                </template>
              </p>
            </template>
          </div>
          <div class="info-memo-box">
            <textarea v-model="mealStore.getSelectItem().memo"></textarea>
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
        <button class="short-ghost-button bp-mr-sm" v-if="mealStore.getIsToday()" @click="moveWeightList">취소</button>
        <button class="short-ghost-button bp-mr-sm" v-if="mealStore.getIsToday()" @click="mealStore.removeMealItem()">삭제</button>
        <button class="short-filled-button" @click="moveModifyItem">수정</button>
      </div>
    </div>
  </main>
</template>
<script setup>
import ImageList from "~~/components/list/ImageList.vue";
import { useMealStore } from "~~/store/meal";
definePageMeta({
  layout: 'main-layout',
});
const router = useRouter();
const mealStore = useMealStore();
const infoList = [
  [
    { key: 'kcal', label: 'Kcal', unit: 'kcal' },
    { key: 'time', label: 'Time', unit: '' },
    { key: 'water', label: 'Water', unit: 'L' },
  ],
];
const testImageList = [
  'http://localhost:3000/_nuxt/assets/images/p01.jpg',
  'http://localhost:3000/_nuxt/assets/images/p02.jpg',
  'http://localhost:3000/_nuxt/assets/images/p03.jpg',
  'http://localhost:3000/_nuxt/assets/images/p04.jpg',
  'http://localhost:3000/_nuxt/assets/images/p05.jpg',
]

const moveWeightList = async ()=>{
  await router.push({path: 'mealList'});
}
const moveModifyItem = ()=>{
  router.push({path: 'mealEdit'})
}

</script>
