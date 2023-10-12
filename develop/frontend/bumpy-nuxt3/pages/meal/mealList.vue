<template>
  <main class="content-layout list-page-wrap-box">
    <h1 class="content-title">Your Meal List</h1>
    <ActivitySection info="meal" />
    <CalendarSection info="meal" />
  </main>
</template>
<script setup lang="ts">
import ActivitySection from '~~/components/section/ActivitySection.vue';
import CalendarSection from '~~/components/section/CalendarSection.vue';
import { useCommonStore } from '~~/store/common';
import { useMealStore } from '~~/store/meal';

definePageMeta({
  layout: 'main-layout',
  middleware: 'custom-router-guard'
});

const commonStore = useCommonStore();
const mealStore = useMealStore();

onMounted(async ()=>{
  const today =commonStore.getToday();
  await mealStore.setFocusDate(today);
  await mealStore.resetSelectItem();
  await mealStore.getCalendarListByStdDate(today);
  await mealStore.getActivityListByStdDate(today);
})

</script>