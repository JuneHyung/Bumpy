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
const commonStore = useCommonStore();
const mealStore = useMealStore();

definePageMeta({
  layout: 'main-layout',
});

onMounted(async ()=>{
  const today =commonStore.getToday();
  await mealStore.setFocusDate(today);
  await mealStore.getCalendarListByStdDate(today);
  await mealStore.getActivityListByStdDate(today);
})

</script>
<style scoped lang="scss">

</style>