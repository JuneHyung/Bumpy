<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p class="bp-my-md">
        <span>{{ info.seq }}번째 식사</span>
        <span>{{ info.time }} m</span>
      </p>
      <p class="bp-my-md">
        <span>{{ info.kcal }}kcal</span>
      </p>
      <p class="bp-my-md">
        <span> Water : {{ info.water }}L</span>
      </p>
    </div>
    <p class="ellipsis-3 item-memo">{{ info.memo }}</p>
  </li>
</template>
<script setup lang="ts">
import { useMealStore } from '~~/store/meal';
import { Meal } from '~~/types/meal';

interface Props {
  info: Meal;
}
const props = defineProps<Props>();
const router=  useRouter();
const mealStore = useMealStore();

const moveDetail = async () => {
  await mealStore.getSelectItemByStdDateSeq(mealStore.getFocusDate(), props.info.seq as number)
  await router.push({ path: 'mealDetail' });
};

</script>
<style lang="scss" scoped></style>
