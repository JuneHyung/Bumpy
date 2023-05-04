<template>
  <li class="square-item bp-pa-sm bp-ma-sm">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p><span>{{ info.startWeight }}kg</span> ~ <span>{{info.endWeight}}kg</span> / <span>{{info.barWeight}}kg</span></p>
      <p><span>{{ info.startWeight }}reps</span> ~ <span>{{info.endWeight}}reps</span> / <span>{{info.barWeight}}set</span></p>
    </div>
    <p class="ellipsis-3 item-memo">{{info.memo}}</p>
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