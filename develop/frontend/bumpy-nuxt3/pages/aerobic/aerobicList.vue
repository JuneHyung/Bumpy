<template>
  <main class="content-layout list-page-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <ActivitySection info="aerobic" />
    <CalendarSection info="aerobic" />
  </main>
</template>
<script setup lang="ts">
import dayjs from 'dayjs';
import ActivitySection from '~~/components/section/ActivitySection.vue';
import CalendarSection from '~~/components/section/CalendarSection.vue';
import { useAerobicStore } from '~~/store/aerobic';

definePageMeta({
  layout: 'main-layout',
  middleware: 'custom-router-guard'
});

const aerobicStore = useAerobicStore();

onMounted(async ()=>{
  const today =dayjs().format('YYYY-MM-DD');
  await aerobicStore.setFocusDate(today);
  await aerobicStore.getCalendarListByStdDate(today);
  await aerobicStore.getActivityListByStdDate(today);
})

</script>