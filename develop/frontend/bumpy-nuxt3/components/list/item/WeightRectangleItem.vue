<template>
  <li class="rectangle-item bp-pa-sm bp-ma-sm">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md">
      <p>
        <span>{{ info.weightStart }}{{ info.measure }}</span> ~ <span>{{ info.weightEnd }}{{ info.measure }}</span> / <span>{{ info.pollWeight }}kg</span>
      </p>
      <p>
        <span>{{ info.repsStart }}reps</span> ~ <span>{{ info.repsEnd }}reps</span> / <span>{{ info.setReps }}set</span>
      </p>
    </div>
  </li>
</template>
<script setup lang="ts">
import { Weight } from '~~/types/weight';

interface Props {
  info: Weight;
}
const props = defineProps<Props>();
</script>
<style lang="scss" scoped></style>
