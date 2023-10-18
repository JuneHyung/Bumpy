<template>
  <li class="rectangle-item bp-pa-sm bp-ma-sm" @click="getSelectWeightChartInfo(props.info.name as string)">
    <p>{{ props.info.name }}</p>
    <div class="bp-mt-md">
      <p>
        <span>{{ props.info.weightStart }}{{ info.measure==='1' ? 'kg' : 'lb' }}</span> ~ <span>{{ props.info.weightEnd }}{{ props.info.measure==='1' ? 'kg' : 'lb' }}</span> / <span>{{ props.info.pollWeight }}kg</span>
      </p>
      <p>
        <span>{{ props.info.repsStart }}reps</span> ~ <span>{{ props.info.repsEnd }}reps</span> / <span>{{ props.info.setReps }}set</span>
      </p>
    </div>
  </li>
</template>
<script setup lang="ts">
import { useWeightStore } from '~~/store/weight';
import { Weight } from '~~/types/weight';

interface Props {
  info: Weight;
}
const props = defineProps<Props>();
const weightStore = useWeightStore();
const getSelectWeightChartInfo = async (name: string) =>{
  await weightStore.getWeightChartInfo(name)
} 
</script>
<style lang="scss" scoped></style>
