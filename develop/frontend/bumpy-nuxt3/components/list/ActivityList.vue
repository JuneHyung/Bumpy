<template>
  <ul :class="{ 'square-list-wrap-box': props.type === 'square', 'rectangle-list-wrap-box': props.type === 'rectangle' }">
    <template v-for="(item, idx) in props.list" :key="item.seq" v-if="list!==undefined && list.length>0">
      <ListItem :info="item" />
    </template>
    <template v-else>
      <NoData />
    </template>
  </ul>
</template>
<script setup lang="ts">
import { defineAsyncComponent } from 'vue';
import { AerobicList } from '~~/types/aerobic';
import { InbodyList } from '~~/types/inbody';
import { MealList } from '~~/types/meal';
import { WeightList } from '~~/types/weight';
import NoData from '~~/components/common/NoData.vue';
interface Props {
  type: 'square' | 'rectangle';
  listType: 'weight' | 'aerobic' | 'meal' | 'inbody';
  list: WeightList | AerobicList | MealList | InbodyList;
}
const props = defineProps<Props>();

const switchComponent = () => {
  switch (props.listType) {
    case 'weight':
      if (props.type === 'square') return defineAsyncComponent(() => import('~~/components/list/item/WeightSquareItem.vue'));
      else return defineAsyncComponent(() => import('~~/components/list/item/WeightRectangleItem.vue'));
    case 'aerobic':
      if (props.type === 'square') return defineAsyncComponent(() => import('~~/components/list/item/AerobicSquareItem.vue'));
      else return defineAsyncComponent(() => import('~~/components/list/item/AerobicRectangleItem.vue'));
    case 'meal':
      if (props.type === 'square') return defineAsyncComponent(() => import('~~/components/list/item/MealSquareItem.vue'));
      else return defineAsyncComponent(() => import('~~/components/list/item/MealRectangleItem.vue'));
    case 'inbody':
      if (props.type === 'square') return defineAsyncComponent(() => import('~~/components/list/item/InbodySquareItem.vue'));
      else return defineAsyncComponent(() => import('~~/components/list/item/InbodyRectangleItem.vue'));
    default:
      return defineAsyncComponent(() => import('~~/components/common/NoData.vue'))
  }
};
const ListItem = switchComponent();
</script>
