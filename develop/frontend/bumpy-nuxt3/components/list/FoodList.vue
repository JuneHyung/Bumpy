<template>
  <ul class="food-item-list">
    <template v-for="(item, idx) in props.list" :key="idx">
      <li class="food-item">
        <input v-model="item.value" class="food-item-input" maxlength="20"/>
        <CustomIcon iconName="mdiClose" @click="removeItem(idx)"></CustomIcon>
      </li>
    </template>
    <li class="food-item plus-item" v-if="props.list!==undefined && props.list.length<10" @click="plusItem">
      <CustomIcon iconName="mdiPlus"></CustomIcon>
    </li>
  </ul>
</template>
<script setup lang="ts">
import CustomIcon from '../icon/CustomIcon.vue';
import {FoodListType} from '~~/types/meal';
interface Props {
  list: FoodListType
}
const props = defineProps<Props>();
const emits = defineEmits(['plus', 'remove'])

const plusItem = () =>{
  emits('plus');
}

const removeItem = (idx: number) =>{
  emits('remove', idx);
}
</script>