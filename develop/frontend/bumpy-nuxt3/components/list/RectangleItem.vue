<template>
  <li class="rectangle-item bp-pa-sm bp-my-sm">
    <p>{{ info.name }}</p>
    <p>{{ info.startWeight }}</p>
    <p>{{ info.endWeight }}</p>
    <p>{{ info.barWeight }}</p>
    <p>{{ info.startReps }}</p>
    <p>{{ info.endReps }}</p>
    <p>{{ info.setReps }}</p>
  </li>
</template>
<script setup lang="ts">
interface ActivityList{
  name: string,
  startWeight: number,
  endWeight: number,
  barWeight: number,
  startReps: number,
  endReps: number,
  setReps: number,
  memo?: string,
}
interface Props {
  info: ActivityList
}
const props = defineProps<Props>()
</script>
<style lang="scss" scoped>

</style>