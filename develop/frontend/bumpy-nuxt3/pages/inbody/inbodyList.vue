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

definePageMeta({
  layout: 'main-layout',
});

const commonStore = useCommonStore();
const inbodyStore = useInbodyStore();

onMounted(async ()=>{
  const today =commonStore.getToday();
  await inbodyStore.setFocusDate(today);
  await inbodyStore.resetSelectItem();
  await inbodyStore.getCalendarListByStdDate(today);
  await inbodyStore.getActivityListByStdDate(today);
})

</script>
<style scoped lang="scss"></style>
