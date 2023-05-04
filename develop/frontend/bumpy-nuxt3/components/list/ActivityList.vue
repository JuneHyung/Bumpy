<template>
  <ul :class="{'square-list-wrap-box': type==='square', 'rectangle-list-wrap-box': type==='rectangle'}">
    <template v-for="(item, idx) in list" :key="idx">
      <ListItem :info="item"></ListItem>
    </template>
  </ul>
</template>
<script setup lang="ts">
import { ref, defineAsyncComponent } from 'vue';

interface ActivityList {
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
  type: 'square' | 'rectangle';
  list: ActivityList[]
}
const props = defineProps<Props>();

const ListItem = defineAsyncComponent(()=>{
  if(props.type==='square') return import('./SquareItem.vue')
  else return import ('./RectangleItem.vue')
})
// const ListItem = defineAsyncComponent(()=> import('./RectangleItem.vue'))


</script>
<style scoped lang="scss">

</style>