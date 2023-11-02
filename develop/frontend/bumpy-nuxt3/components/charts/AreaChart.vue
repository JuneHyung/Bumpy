<template>
  <Apexchart type="area" height="90%" :key="props.series" :options="options" :series="props.series" />
</template>
<script setup lang="ts">
import { ref } from 'vue';
import Apexchart from 'vue3-apexcharts';

const props = defineProps({
  series: Array,
  xAxis: Array,
})

const options = ref({
  chart: {
    type: 'area',
    zoom: {
      enabled: false,
    },
    foreColor: '#FFFFFF',
  },
  dataLabels: {
    enabled: true,
  },
  stroke: {
    curve: 'straight',
  },
  labels: props.xAxis,
  xAxis:{
    categories: props.xAxis
  }
});
</script>
