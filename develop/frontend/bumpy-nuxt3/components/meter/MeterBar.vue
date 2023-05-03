<template>
  <div class="meter-bar">
    <meter :value="info.value" :max="info.max" :min="info.min" :low="info.low" :high="info.high" :optimum="info.optimum"></meter>
  </div>
</template>
<script setup lang="ts">
import { MeterItem } from '~~/types/inbody';
interface Props {
  info: MeterItem,
}
const props = defineProps<Props>();
</script>
<style lang="scss"></style>
