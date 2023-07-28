<template>
  <main class="content-layout inbody-list-wrap-box">
    <h1 class="content-title">About My Body</h1>
    <div class="inbody-list-box content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title">{{ inbodyStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag" class="short-filled-button edit-button">Edit</button>
      </div>
      <div class="inbody-info-box">
        <ActivityList type="square" listType="inbody" :list="inbodyList"></ActivityList>
      </div>
    </div>
    <div class="content-wrap-box bp-mt-xl inbody-calendar">
      <Calendar @focusDate="getFocusDate" type="inbody"></Calendar>
    </div>
  </main>
</template>
<script setup lang="ts">
import ActivityList from "~~/components/list/ActivityList.vue";
import { useRouter } from "vue-router";
import { useInbodyStore } from "~~/store/inbody";
import { useCommonStore } from "~~/store/common";
import { readInbodyActivityList, readInbodyCalendarList } from "~~/api/inbody/inbody";
import { InbodyList } from "~~/types/inbody";
import { setErrorMessage } from "~~/api/alert/message";

const commonStore = useCommonStore();
const inbodyStore = useInbodyStore();
const router = useRouter();
const editFlag = computed(() => commonStore.getToday === inbodyStore.getFocusDate);
const inbodyList: Ref<InbodyList> = ref([]);

definePageMeta({
  layout: "main-layout",
});

const moveEdit = () => {
  router.push({ path: "inbodyEdit" });
};

// inbody List 조회
const getInbodyList = async () => {
  try {
    const { data, error } = await readInbodyActivityList({ stdDate: inbodyStore.getFocusDate });
    if (error.value !== null) {
      setErrorMessage(error.value);
    } else if (data.value !== null) {
      inbodyList.value = data.value.data;
    }
  } catch (e) {
    setErrorMessage(e);
  }
};
// calendarList 조회
const getCalendarList = async () => {
  try {
    const { data, error } = await readInbodyCalendarList({ stdDate: inbodyStore.getFocusDate });
    if (error.value !== null) {
      setErrorMessage(error.value);
    } else if (data.value !== null) {
      inbodyStore.setCalendarlist(data.value.data);
    }
  } catch (e) {
    setErrorMessage(e);
  }
};

// Calendar 클릭시 focusdate변경
const getFocusDate = (v: string) => {
  inbodyStore.setFocusDate(v);
  getInbodyList();
};

onMounted(async () => {
  const today = commonStore.getToday;
  await inbodyStore.setFocusDate(today);
  await getCalendarList();
  await getInbodyList();
});
</script>
<style scoped lang="scss"></style>
