<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About Your Inbody</h1>
    <div class="content-wrap-box" v-if="inbodyStore.getSelectItem().bmi!=null">
      <h2 class="content-title">{{ inbodyStore.getFocusDate() }} Inbody</h2>
      <div class="inbody-info-box">
        <div class="chart-wrap">
          <ImageList :list="imageList()"></ImageList>
        </div>
        <div class="info-list-wrap">
          <div class="info-item">
            <template v-for="(info, idx) in infoList" :key="idx">
              <p class="bp-mr-sm">
                <template v-for="(item, iIdx) in info" :key="iIdx">
                  <span>{{ item.label }} : {{ inbodyStore.getSelectItem()[item.key as keyof Inbody] }} {{ item.unit }}</span>
                </template>
              </p>
            </template>
          </div>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <p>Youtube or chart</p>
        </div>
      </div>
      <div class="inbodyDetail-button-wrap">
        <button class="short-ghost-button" @click="moveInbodyList">취소</button>
        <button class="short-filled-button bp-mx-sm" v-if="inbodyStore.getIsToday()" @click="inbodyStore.removeInbodyItem()">삭제</button>
        <button class="short-filled-button" v-if="inbodyStore.getIsToday()" @click="moveModifyItem">수정</button>
      </div>
    </div>
    <NoData v-else></NoData>
  </main>
</template>
<script setup lang="ts">
import { useInbodyStore } from "~~/store/inbody";
import ImageList from "~~/components/list/ImageList.vue";
import { Inbody } from "~~/types/inbody";
import NoData from "~~/components/common/NoData.vue";

definePageMeta({
  layout: "main-layout",
});
const router = useRouter();
const inbodyStore = useInbodyStore();

const infoList = [
  [
    { key: "stdDate", label: "검사 날짜", unit: "" },
    { key: "height", label: "키", unit: "cm" },
    { key: "weight", label: "체중", unit: "kg" },
    { key: "muscle", label: "골격근량", unit: "kg" },
    { key: "fat", label: "체지방량", unit: "kg" },
    { key: "score", label: "인바디 점수", unit: "점" },
    { key: "bmi", label: "BMI", unit: "kg/m2" },
    { key: "fatRate", label: "체지방률", unit: "%" },
  ],
];

const imageList = () => {
  const list = inbodyStore.getSelectItem().picture as ImageData[];
  return list.map((el) => el.data);
};
const moveInbodyList = async () => {
  await router.push({ path: "inbodyList" });
};

const moveModifyItem = async () => {
  await router.push({ path: "inbodyEdit" });
};

onMounted(()=>{
  console.log(inbodyStore.getSelectItem())
})
</script>
