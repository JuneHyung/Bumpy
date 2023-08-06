<template>
  <main class="content-layout list-page-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <ActivitySection info="weight" />
    <CalendarSection info="weight" />
  </main>
</template>
<script setup lang="ts">
import ActivitySection from '~~/components/section/ActivitySection.vue';
import CalendarSection from '~~/components/section/CalendarSection.vue';
import { useCommonStore } from '~~/store/common';
import { useWeightStore } from '~~/store/weight';

const commonStore = useCommonStore();
const weightStore = useWeightStore();

definePageMeta({
  layout: 'main-layout',
});

onMounted(async()=>{
  const today = commonStore.getToday();
  weightStore.setFocusDate(today);
  weightStore.resetSelectItem();
  weightStore.getCalendarListByStdDate(today);
  weightStore.getActivityListByStdDate(today);
})
</script>
