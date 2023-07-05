<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About My Routine</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ infoName.value }}</h2>
      <div class="aerobic-info-box">
        <div class="aerobic-chart-wrap">
          <p>Chart</p>
        </div>
        <div class="info-list-out-wrap">
          <div class="info-list-wrap">
            <p class="bp-mr-sm">
              <span>시작 Incline : {{ aerobicStore.getSelectItem.inclineStart }} </span>
              <span>시작 Speed : {{ aerobicStore.getSelectItem.speedStart }} </span>
              <span>시간 : {{ aerobicStore.getSelectItem.time }} m</span>
            </p>
            <p class="bp-mr-sm">
              <span>종료 Incline : {{ aerobicStore.getSelectItem.inclineEnd }} </span>
              <span>종료 Speed : {{ aerobicStore.getSelectItem.speedEnd }} </span>
              <span>Kcal : {{ aerobicStore.getSelectItem.kcal }}</span>
            </p>
          </div>
          <div class="info-memo-box">
            <textarea style="width: 100%" :rows="10"></textarea>
          </div>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <p>Youtube or chart</p>
        </div>
      </div>
      <div class="aerobicDetail-button-wrap">
        <button class="short-ghost-button" @click="moveAerobicList">취소</button>
        <button class="short-filled-button bp-mx-sm" v-if="aerobicStore.getIsToday" @click="removeAerobicItem">삭제</button>
        <button class="short-filled-button" v-if="aerobicStore.getIsToday" @click="moveModifyItem">수정</button>
      </div>
    </div>
  </main>
</template>
<script setup>
import { deleteAerobicItem } from "~~/api/aerobic/aerobic";
import { setErrorMessage, setMessage } from "~~/api/alert/message";
import { useAerobicStore } from "~~/store/aerobic";

const router = useRouter();
const aerobicStore = useAerobicStore();

const removeAerobicItem = async () => {
  try {
    const body = {
      stdDate: aerobicStore.focusDate,
      seq: aerobicStore.getSelectItem.seq,
    };
    const { data, error } = await deleteAerobicItem(body);
    if (error.value !== null) {
      await setErrorMessage(error.value.messaage);
    } else if (data.value !== null) {
      await setMessage(data.value.message);
      await moveAerobicList();
    }
  } catch (e) {
    setErrorMessage(e);
  }
};
const moveAerobicList = async () => {
  await router.push({ path: "aerobicList" });
};

const moveModifyItem = async () => {
  await router.push({ path: "aerobicEdit" });
};
definePageMeta({
  layout: "main-layout",
});
const infoName = { key: "name", label: "", value: "Walking" };
const infoList = [
  [
    { key: "inclineStart", label: "시작 Incline", value: "0", unit: "" },
    { key: "speedStart", label: "시작 Speed", value: "12", unit: "" },
  ],
  [
    { key: "inclineEnd", label: "종료 Incline", value: "20", unit: "" },
    { key: "speedEnd", label: "종료 Speed", value: "8", unit: "" },
  ],
  [
    { key: "time", label: "시간", value: "63:28", unit: "" },
    { key: "kcal", label: "Kcal", value: "5", unit: "" },
  ],
];
const infoMemo = { key: "memo", label: "", value: "memomemomemeomeoemo" };
</script>
