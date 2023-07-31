<template>
  <div class="content-wrap-box bp-mt-xl activity-calendar">
    <Calendar @focusDate="getFocusDate" :type="props.info" v-if="store.getCalendarList().length!==0"></Calendar>
  </div>
</template>
<script setup lang="ts">
import { useWeightStore } from "~~/store/weight";
import { useAerobicStore } from "~~/store/aerobic";
import { useMealStore } from "~~/store/meal";
import { useInbodyStore } from "~~/store/inbody";

interface Props {
  info: 'weight' | 'aerobic' |'meal' |'inbody';
}
const props = defineProps<Props>();

const switchStore = () => {
  switch (props.info) {
    case "weight":
      return useWeightStore();
    case "aerobic":
      return useAerobicStore();
    case "meal":
      return useMealStore();
    case "inbody":
      return useInbodyStore();
    default:
      return useWeightStore();
  }
};

const store = switchStore();

const getFocusDate = async (v: string) => {
  await store.setFocusDate(v);
  await store.getActivityListByStdDate(v);
};
</script>
