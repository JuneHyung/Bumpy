<template>
  <div class="meter-bar">
    <meter :value="value" :max="max" :min="min" :low="low" :high="high" :optimum="optimum"></meter>
  </div>
</template>
<script setup lang="ts">
interface Props {
  value: number;
  max?: number;
  min?: number;
  low?: number;
  high?: number;
  optimum?: number;
}
const props = defineProps<Props>();
</script>
<style lang="scss"></style>
