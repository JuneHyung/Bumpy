<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About My Routine</h1>
    <div class="content-wrap-box" v-if="weightStore.getSelectItem().name !=null">
      <h2 class="content-title">{{ weightStore.getSelectItem().name }}</h2>
      <div class="weight-info-box">
        <div class="photo-list-wrap">
          <ImageList :list="imageList()"></ImageList>
        </div>
        <div class="info-list-out-wrap">
          <div class="info-list-wrap">
            <p class="bp-mr-sm">
              <span>시작 무게 : {{ weightStore.getSelectItem().weightStart }} {{ weightStore.getSelectItem().measure==='1' ? 'kg' : 'lb' }}</span>
              <span>시작 횟수 : {{ weightStore.getSelectItem().repsStart }} reps</span>
              <span>봉 무게 : {{ weightStore.getSelectItem().pollWeight }} kg</span>
            </p>
            <p class="bp-mr-sm">
              <span>종료 무게 : {{ weightStore.getSelectItem().weightEnd }} {{ weightStore.getSelectItem().measure==='1' ? 'kg' : 'lb' }}</span>
              <span>종료 횟수 : {{ weightStore.getSelectItem().repsEnd }} reps</span>
              <span>세트 횟수 : {{ weightStore.getSelectItem().setReps }} reps</span>
            </p>
          </div>
          <div class="info-memo-box">
            <textarea disabled class="memo-box" :rows="10" :value="weightStore.getSelectItem().memo"></textarea>
          </div>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <YoutubeList :list="weightStore.getSelectYoutubeList()"></YoutubeList>
        </div>
      </div>
      <div class="weightDetail-button-wrap">
        <button class="short-ghost-button" @click="moveWeightList">취소</button>
        <button class="short-filled-button bp-mx-sm" v-if="weightStore.getIsToday()" @click="removeWeightItem">삭제</button>
        <button class="short-filled-button" v-if="weightStore.getIsToday()" @click="moveModifyItem">수정</button>
      </div>
    </div>
    <NoData v-else></NoData>
  </main>
</template>
<script setup lang="ts">
import { useWeightStore } from "~~/store/weight";
import ImageList from "~~/components/list/ImageList.vue";
import NoData from "~~/components/common/NoData.vue";
import YoutubeList from "~~/components/common/YoutubeList.vue";

definePageMeta({
  layout: "main-layout",
});

const router = useRouter();
const weightStore = useWeightStore();

const imageList = () => {
  const list = weightStore.getSelectItem().picture as ImageData[];
  return list.map(el=>el.data);
}

const moveWeightList = async () => {
  await router.push({ name: "weight-weightList" });
};

const moveModifyItem = () => {
  router.push({ name: "weight-weightEdit" });
};

const removeWeightItem = async () => {
  await weightStore.removeWeightItem();
  await moveWeightList();
};

const getVideoList = async () =>{
  const keyword = weightStore.getSelectItem().name as string
  await weightStore.getYoutubeList(keyword)
}

onMounted(async ()=>{
  if(weightStore.getSelectItem().name!==undefined){
    await getVideoList();
  }
})
</script>
