<template>
  <main class="content-layout list-page-wrap-box">
    <h1 class="content-title">Your Meal List</h1>
    <ActivitySection info="meal" />
    <CalendarSection info="meal" />
  </main>
</template>
<script setup lang="ts">
import dayjs from 'dayjs';
import ActivitySection from '~~/components/section/ActivitySection.vue';
import CalendarSection from '~~/components/section/CalendarSection.vue';
import { useMealStore } from '~~/store/meal';

definePageMeta({
  layout: 'main-layout',
  middleware: 'custom-router-guard'
});

const mealStore = useMealStore();

onBeforeMount(async ()=>{
  const today =dayjs().format('YYYY-MM-DD');
  await mealStore.setFocusDate(today);
  await mealStore.resetSelectItem();
  await mealStore.getCalendarListByStdDate(today);
  await mealStore.getActivityListByStdDate(today);
})

</script>