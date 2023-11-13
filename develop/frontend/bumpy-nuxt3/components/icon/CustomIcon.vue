<template>
  <svg width="24" height="24" viewBox="0 0 24 24">
    <path :d="mdiIcons[props.iconName]" />
  </svg>
</template>
<script setup>
import * as mdiIcons from "@mdi/js";
const props = defineProps({
  iconName: String,
})
</script>
<style scoped lang="scss">

svg path{
  fill: #fff;
  transition:all 0.2s;
  &:hover{
    fill: #fb8c00;
    cursor:pointer;
  }
}
</style>