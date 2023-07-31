<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p>
        <span>{{ info.weightStart }}{{ info.measure }}</span> ~ <span>{{ info.weightEnd }}{{ info.measure }}</span> / <span>{{ info.pollWeight }}kg</span>
      </p>
      <p>
        <span>{{ info.repsStart }}reps</span> ~ <span>{{ info.repsEnd }}reps</span> / <span>{{ info.setReps }}set</span>
      </p>
    </div>
    <p class="ellipsis-3 item-memo">{{ info.memo }}</p>
  </li>
</template>
<script setup lang="ts">
import { useWeightStore } from '~~/store/weight';
import { Weight } from '~~/types/weight';

interface Props {
  info: Weight;
}
const props = defineProps<Props>();
const router=  useRouter();
const weightStore = useWeightStore();

const moveDetail = async () => {
  await weightStore.getSelectItemByStdDateSeq(weightStore.getFocusDate(), props.info.seq as number)
  await router.push({ path: 'weightDetail' });
};
</script>
<style lang="scss" scoped></style>
