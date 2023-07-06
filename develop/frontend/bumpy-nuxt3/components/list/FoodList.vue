<template>
  <ul class="food-item-list">
    <template v-for="(item, idx) in list" :key="idx">
      <li class="food-item">
        {{ item }}
        <button class="delete-button" type="button" @click="removeItem(idx)">
        </button>
      </li>
    </template>
    <li class="food-item" v-if="props.list.length<3">PLUS</li>
  </ul>
</template>
<script setup>
const props = defineProps({
  list: Array,
});
const emits = defineEmits(['remove'])
const removeItem = (idx) =>{
  emits('remove', idx);
}
</script>
<style lagn="scss" scoped>

</style>