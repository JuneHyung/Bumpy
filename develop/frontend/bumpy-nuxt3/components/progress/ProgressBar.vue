<template>
  <div class="progress-bar">
    <progress :value="value" :max="max" :class="{'high-level': value>70, 'middle-level': value<=70 && value>50, 'low-level': value<=50}"></progress>
  </div>
</template>
<script setup lang="ts">
interface Props {
  value: number,
  max: number
}
const props = defineProps<Props>()
</script>