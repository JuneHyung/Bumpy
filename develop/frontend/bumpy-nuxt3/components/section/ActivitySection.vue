<template>
  <div class="activity-list-box content-wrap-box bp-my-lg">
    <div class="title-wrap-box">
      <h3 class="content-title">{{ store.getFocusDate() }}</h3>
      <button @click="moveEdit" v-if="editFlag" class="short-filled-button edit-button">추가</button>
    </div>
    <ActivityList type="square" :listType="props.info" :list="store.getActivityList()" />
  </div>
</template>
<script setup lang="ts">
import ActivityList from "~~/components/list/ActivityList.vue";
import { useWeightStore } from "~~/store/weight";
import { useAerobicStore } from "~~/store/aerobic";
import { useMealStore } from "~~/store/meal";
import { useInbodyStore } from "~~/store/inbody";
import { useCommonStore } from "~~/store/common";
interface Props {
  info: 'weight' | 'aerobic' |'meal' |'inbody';
}
const router = useRouter();
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
    default:
      return useWeightStore();
  }
};

const store = switchStore();
const editFlag = computed(() => commonStore.getToday() === store.getFocusDate());
const moveEdit = () => {
  store.resetSelectItem();

  switch (props.info) {
    case "weight":
      router.push({ name: "weight-weightEdit" });
      break;
    case "aerobic":
      router.push({ name: "aerobic-aerobicEdit" });
      break;
    case "meal":
      router.push({ name: "meal-mealEdit" });
      break;
    case "inbody":
      router.push({ name: "inbody-inbodyEdit" });
      break;
    default:
      break;
  }
};
</script>
