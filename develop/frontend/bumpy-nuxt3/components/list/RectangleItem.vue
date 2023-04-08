<template>
  <li class="rectangle-item bp-pa-sm bp-my-sm">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md">
      <p><span>{{ info.startWeight }}kg</span> ~ <span>{{info.endWeight}}kg</span> / <span>{{info.barWeight}}kg</span></p>
      <p><span>{{ info.startWeight }}reps</span> ~ <span>{{info.endWeight}}reps</span> / <span>{{info.barWeight}}set</span></p>
    </div>
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