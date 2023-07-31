<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About Today's Meal</h1>
    <div class="content-wrap-box" v-if="mealStore.getSelectItem().name !=null">
      <h2 class="content-title">{{ mealStore.getSelectItem().name }}</h2>
      <div class="meal-info-box">
        <div class="chart-wrap">
          <ImageList :list="imageList()"></ImageList>
        </div>
        <div class="info-list-wrap">
          <div class="info-item">
            <template v-for="(info, idx) in infoList" :key="idx">
              <p class="bp-mr-sm">
                <template v-for="(item, iIdx) in info" :key="item.key">
                  <span>{{ item.label }} : {{ mealStore.getSelectItem()[item.key as keyof Meal] }} {{ item.unit }}</span>
                </template>
              </p>
            </template>
          </div>
          <div class="info-memo-box">
            <textarea disabled class="memo-box" :rows="10" :value="mealStore.getSelectItem().memo"></textarea>
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
        <button class="short-ghost-button bp-mr-sm" v-if="mealStore.getIsToday()" @click="moveMealList">취소</button>
        <button class="short-ghost-button bp-mr-sm" v-if="mealStore.getIsToday()" @click="removeMealItem">삭제</button>
        <button class="short-filled-button" @click="moveModifyItem">수정</button>
      </div>
    </div>
    <NoData v-else></NoData>
  </main>
</template>
<script setup lang="ts">
import NoData from "~~/components/common/NoData.vue";
import ImageList from "~~/components/list/ImageList.vue";
import { useMealStore } from "~~/store/meal";
import { Meal } from "~~/types/meal";
definePageMeta({
  layout: 'main-layout',
});
const router = useRouter();
const mealStore = useMealStore();
const imageList = () => {
  const list = mealStore.getSelectItem().picture as ImageData[];
  return list.map(el=>el.data);
}

const infoList = [
  [
    { key: 'kcal', label: 'Kcal', unit: 'kcal' },
    { key: 'time', label: 'Time', unit: '' },
    { key: 'water', label: 'Water', unit: 'L' },
  ],
];

const moveMealList = async ()=>{
  await router.push({name: 'meal-mealList'});
}

const moveModifyItem = ()=>{
  router.push({name: 'meal-mealEdit'})
}

const removeMealItem = async () =>{
  await mealStore.removeMealItem();
  await moveMealList();
}
</script>
