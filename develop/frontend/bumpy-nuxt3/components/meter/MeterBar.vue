<template>
  <div class="meter-bar">
    <meter :value="props.info.value" :max="props.info.max" :min="props.info.min" :low="props.info.low" :high="props.info.high" :optimum="props.info.optimum" />
  </div>
</template>
<script setup lang="ts">
import { MeterItem } from '~~/types/inbody';
interface Props {
  info: MeterItem,
}
const props = defineProps<Props>();
</script>
