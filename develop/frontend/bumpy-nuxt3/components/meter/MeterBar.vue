<template>
  <div class="meter-bar">
    <meter :value="value" :max="max" :min="min" :low="low" :high="high" :optimum="optimum"></meter>
  </div>
</template>
<script setup lang="ts">
import { MeterList } from '~~/types/inbody';
const props = defineProps<MeterList>();
</script>
<style lang="scss"></style>
