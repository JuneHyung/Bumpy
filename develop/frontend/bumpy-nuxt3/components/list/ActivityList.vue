<template>
  <ul :class="{ 'square-list-wrap-box': type === 'square', 'rectangle-list-wrap-box': type === 'rectangle' }">
    <template v-for="(item, idx) in list" :key="idx">
      <ListItem :info="item"></ListItem>
    </template>
  </ul>
</template>
<script setup lang="ts">
import { ref, defineAsyncComponent } from 'vue';
import { AerobicList } from '~~/types/aerobic';
import { MealList } from '~~/types/meal';
import { WeightList } from '~~/types/weight';

interface Props {
  type: 'square' | 'rectangle';
  listType: 'weight' | 'aerobic' | 'meal';
  list: WeightList | AerobicList | MealList;
}
const props = defineProps<Props>();

const switchComponent = () => {
  switch (props.listType) {
    case 'weight':
      if (props.type === 'square') return defineAsyncComponent(() => import('components/list/item/WeightSquareItem.vue'));
      else return defineAsyncComponent(() => import('components/list/item/WeightRectangleItem.vue'));
    case 'aerobic':
      if (props.type === 'square') return defineAsyncComponent(() => import('components/list/item/AerobicSquareItem.vue'));
      else return defineAsyncComponent(() => import('components/list/item/AerobicRectangleItem.vue'));
    case 'meal':
      if (props.type === 'square') return defineAsyncComponent(() => import('components/list/item/MealSquareItem.vue'));
      else return defineAsyncComponent(() => import('components/list/item/MealRectangleItem.vue'));
  }
};
const ListItem = switchComponent();

// const ListItem = defineAsyncComponent(()=> import('./RectangleItem.vue'))
</script>
<style scoped lang="scss"></style>
