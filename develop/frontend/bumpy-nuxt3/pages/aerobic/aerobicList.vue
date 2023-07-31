<template>
  <main class="content-layout list-page-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <ActivitySection info="aerobic" />
    <CalendarSection info="aerobic" />
  </main>
</template>
<script setup lang="ts">
import ActivitySection from '~~/components/section/ActivitySection.vue';
import CalendarSection from '~~/components/section/CalendarSection.vue';
import { useAerobicStore } from '~~/store/aerobic';
import { useCommonStore } from '~~/store/common';
const commonStore = useCommonStore();
const aerobicStore = useAerobicStore();

definePageMeta({
  layout: 'main-layout',
});

onMounted(async ()=>{
  const today =commonStore.getToday;
  await aerobicStore.setFocusDate(today);
  await aerobicStore.getCalendarListByStdDate(today);
  await aerobicStore.getActivityListByStdDate(today);
})

</script>
<style scoped lang="scss">

</style>