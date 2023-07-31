<template>
  <main class="content-layout list-page-wrap-box">
    <h1 class="content-title">Your Inbody List</h1>
    <ActivitySection info="inbody" />
    <CalendarSection info="inbody" />
  </main>
</template>
<script setup lang="ts">
import ActivitySection from '~~/components/section/ActivitySection.vue';
import CalendarSection from '~~/components/section/CalendarSection.vue';
import { useCommonStore } from '~~/store/common';
import { useInbodyStore } from '~~/store/inbody';
const commonStore = useCommonStore();
const inbodyStore = useInbodyStore();

definePageMeta({
  layout: 'main-layout',
});

onMounted(async ()=>{
  const today =commonStore.getToday();
  await inbodyStore.setFocusDate(today);
  await inbodyStore.getCalendarListByStdDate(today);
  await inbodyStore.getActivityListByStdDate(today);
})

</script>
<style scoped lang="scss"></style>
