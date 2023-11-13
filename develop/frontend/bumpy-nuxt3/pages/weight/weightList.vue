<template>
  <main class="content-layout list-page-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <ActivitySection info="weight" />
    <CalendarSection info="weight" />
  </main>
</template>
<script setup lang="ts">
import dayjs from 'dayjs';
import ActivitySection from '~~/components/section/ActivitySection.vue';
import CalendarSection from '~~/components/section/CalendarSection.vue';
import { useWeightStore } from '~~/store/weight';

definePageMeta({
  layout: 'main-layout',
  middleware: 'custom-router-guard'
});

const weightStore = useWeightStore();

onBeforeMount(async()=>{
  const today = dayjs().format('YYYY-MM-DD');
  await weightStore.setFocusDate(today);
  await weightStore.resetSelectItem();
  await weightStore.getCalendarListByStdDate(today);
  await weightStore.getActivityListByStdDate(today);
})
</script>
