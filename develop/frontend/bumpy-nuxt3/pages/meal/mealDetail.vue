<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About Today's Meal</h1>
    <div class="content-wrap-box" v-if="mealStore.getSelectItem().name !=null">
      <h2 class="content-title">{{ mealStore.getSelectItem().name }}</h2>
      <div class="meal-info-box">
        <div class="photo-list-wrap">
          <ImageList :list="imageList()"></ImageList>
        </div>
        <div class="info-list-wrap">
          <div class="info-item">
            <template v-for="(info, idx) in infoList" :key="idx">
              <p class="bp-mr-sm">
                <template v-for="(item, iIdx) in info" :key="item.key">
                  <span>{{ item.label }} : {{ mealStore.getSelectItem()[item.key as keyof Meal]?.toString() }} {{ item.unit }}</span>
                </template>
              </p>
            </template>
          </div>
          <div class="info-memo-box">
            <textarea disabled class="memo-box" :rows="10" :value="mealStore.getSelectItem().memo"></textarea>
          </div>
        </div>
      </div>
      <div class="youtube-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="youtube-wrap">
          <YoutubeList :list="mealStore.getSelectYoutubeList()"/>
        </div>
      </div>
      <div class="detail-button-wrap">
        <button class="short-ghost-button bp-mr-sm" @click="moveMealList">취소</button>
        <button class="short-ghost-button bp-mr-sm" v-if="mealStore.getIsToday()" @click="removeMealItem">삭제</button>
        <button class="short-filled-button" v-if="mealStore.getIsToday()" @click="moveModifyItem">수정</button>
      </div>
    </div>
    <NoData v-else></NoData>
  </main>
</template>
<script setup lang="ts">
import NoData from "~~/components/common/NoData.vue";
import YoutubeList from "~~/components/common/YoutubeList.vue";
import ImageList from "~~/components/list/ImageList.vue";
import { useMealStore } from "~~/store/meal";
import { Meal } from "~~/types/meal";

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
    { key: 'food', label: 'Food', }
  ],
];

const imageList = () => {
  const list = mealStore.getSelectItem().picture as ImageData[];
  return list.map(el=>el.data);
}

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

const getVideoList = async () =>{
  await mealStore.getYoutubeList()
}

onMounted(async ()=>{
  if(mealStore.getSelectItem().name!==undefined){
    await getVideoList();
  }
})
</script>
