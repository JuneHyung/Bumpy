<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About My Routine</h1>
    <div class="content-wrap-box" v-if="aerobicStore.getSelectItem().name !=null">
      <h2 class="content-title">{{ aerobicStore.getSelectItem().name }}</h2>
      <div class="aerobic-info-box">
        <div class="kcal-chart-wrap">
          <RadialBarChart :data="aerobicStore.getSelectItem().kcal"></RadialBarChart>
        </div>
        <div class="info-list-out-wrap">
          <div class="info-list-wrap">
            <p class="bp-mr-sm">
              <span>시작 Incline : {{ aerobicStore.getSelectItem().inclineStart }} </span>
              <span>시작 Speed : {{ aerobicStore.getSelectItem().speedStart }} </span>
              <span>시간 : {{ aerobicStore.getSelectItem().time }} m</span>
            </p>
            <p class="bp-mr-sm">
              <span>종료 Incline : {{ aerobicStore.getSelectItem().inclineEnd }} </span>
              <span>종료 Speed : {{ aerobicStore.getSelectItem().speedEnd }} </span>
              <span>Kcal : {{ aerobicStore.getSelectItem().kcal }}</span>
            </p>
          </div>
          <div class="info-memo-box">
            <textarea disabled class="memo-box" :rows="10" :value="aerobicStore.getSelectItem().memo"></textarea>
          </div>
        </div>
      </div>
      <div class="youtube-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="youtube-wrap">
          <YoutubeList :list="aerobicStore.getSelectYoutubeList()"></YoutubeList>
        </div>
      </div>
      <div class="detail-button-wrap">
        <button class="short-ghost-button" @click="moveAerobicList">취소</button>
        <button class="short-filled-button bp-mx-sm" v-if="aerobicStore.getIsToday()" @click="removeAerobicItem">삭제</button>
        <button class="short-filled-button" v-if="aerobicStore.getIsToday()" @click="moveModifyItem">수정</button>
      </div>
    </div>
    <NoData v-else></NoData>
  </main>
</template>
<script setup lang="ts">
import RadialBarChart from "~~/components/charts/RadialBarChart.vue";
import NoData from "~~/components/common/NoData.vue";
import YoutubeList from "~~/components/common/YoutubeList.vue";
import { useAerobicStore } from "~~/store/aerobic";
definePageMeta({
  layout: "main-layout",
  middleware: 'custom-router-guard'
});

const router = useRouter();
const aerobicStore = useAerobicStore();

const moveAerobicList = async () => {
  await router.push({ name: "aerobic-aerobicList" });
};

const moveModifyItem = async () => {
  await router.push({ name: "aerobic-aerobicEdit" });
};

const removeAerobicItem = async () => {
  await aerobicStore.removeAerobicItem();
  await moveAerobicList();
}
const getVideoList = async () =>{
  const keyword = aerobicStore.getSelectItem().name as string
  await aerobicStore.getYoutubeList(keyword)
}

onMounted(async ()=>{
  if(aerobicStore.getSelectItem().name!==undefined){
    await getVideoList();
  }
})
</script>
