<template>
  <ul class="food-item-list">
    <template v-for="(item, idx) in list" :key="idx">
      <li class="food-item">
        <input v-model="item.value" class="food-item-input"/>
        <CustomIcon iconName="mdiClose" @click="removeItem(idx)"></CustomIcon>
      </li>
    </template>
    <li class="food-item plus-item" v-if="props.list.length<10" @click="plusItem">
      <CustomIcon iconName="mdiPlus"></CustomIcon>
    </li>
  </ul>
</template>
<script setup>
import CustomIcon from '../icon/CustomIcon.vue';


const props = defineProps({
  list: Array,
});
const emits = defineEmits(['plus', 'remove'])

const plusItem = () =>{
  emits('plus');
}

const removeItem = (idx) =>{
  emits('remove', idx);
}
</script>
<style lagn="scss" scoped>

</style>