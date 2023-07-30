<template>
  <div class="content-wrap-box bp-mt-xl weight-calendar">
    <Calendar @focusDate="getFocusDate" type="weight"></Calendar>
  </div>
</template>
<script setup lang="ts">
import { useWeightStore } from "~~/store/weight";
import { useAerobicStore } from "~~/store/aerobic";
import { useMealStore } from "~~/store/meal";
import { useInbodyStore } from "~~/store/inbody";
import { useCommonStore } from "~~/store/common";
interface Props {
  info: string;
}
const props = defineProps<Props>();
const commonStore = useCommonStore();
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
  }
};

const store = switchStore();

const getFocusDate = async (v: string) => {
  await store.setFocusDate(v);
  await store.getActivityListByStdDate(v);
};
</script>
