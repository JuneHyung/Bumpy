<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About My Routine</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ weightStore.getSelectItem.name }}</h2>
      <div class="weight-info-box">
        <div class="photo-list-wrap">
          <p>image</p>
        </div>
        <div class="info-list-out-wrap">
          <div class="info-list-wrap">
            <p class="bp-mr-sm">
              <span>시작 무게 : {{ weightStore.getSelectItem.weightStart }} kg</span>
              <span>시작 횟수 : {{ weightStore.getSelectItem.repsStart }} reps</span>
              <span>봉 무게 : {{ weightStore.getSelectItem.pollWeight }} kg</span>
            </p>
            <p class="bp-mr-sm">
              <span>종료 무게 : {{ weightStore.getSelectItem.weightEnd }} kg</span>
              <span>종료 횟수 : {{ weightStore.getSelectItem.repsEnd }} reps</span>
              <span>세트 횟수 : {{ weightStore.getSelectItem.setReps }} reps</span>
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
      <div class="weightDetail-button-wrap">
        <button class="short-ghost-button" @click="moveWeightList">취소</button>
        <button class="short-filled-button bp-mx-sm" v-if="weightStore.getIsToday" @click="removeWeightItem">삭제</button>
        <button class="short-filled-button" v-if="weightStore.getIsToday" @click="moveModifyItem">수정</button>
      </div>
    </div>
  </main>
</template>
<script setup>
import { setErrorMessage, setMessage } from "~~/api/alert/message";
import { deleteWeightItem } from "~~/api/weight/weight";
import { useWeightStore } from "~~/store/weight";
const router = useRouter();
const weightStore = useWeightStore();
const removeWeightItem = async () => {
  try {
    const params = {
      stdDate: weightStore.focusDate,
      seq: weightStore.getSelectItem.seq,
    };
    const { data, error } = await deleteWeightItem(params);
    if (error.value !== null) {
      await setErrorMessage(error.value.messaage);
    } else if (data.value !== null) {
      const message = data.value.message;
      await setMessage(message);
      await moveWeightList();
    }
  } catch (e) {
    setErrorMessage(e);
  }
};
const moveWeightList = async () => {
  await router.push({ path: "weightList" });
};
const moveModifyItem = () => {
  router.push({ path: "weightEdit" });
};

definePageMeta({
  layout: "main-layout",
});
</script>
