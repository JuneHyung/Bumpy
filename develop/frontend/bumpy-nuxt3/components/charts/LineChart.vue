<template>
  <Apexchart type="line" :key="series" height="350" width="100%" :options="options" :series="series"></Apexchart>
</template>
<script setup lang="ts">
import { ref } from 'vue';

import Apexchart from 'vue3-apexcharts';
const options = ref({
  chart: {
    type: 'line',
    zoom: {
      enabled: false,
    },
  },
  // dataLabels: {
  //   enabled: true,
  // },
  stroke: {
    curve: 'straight',
  },
  xaxis: {
    categories: ['South Korea', 'Canada', 'United Kingdom', 'Netherlands', 'Italy', 'France', 'Japan', 'United States', 'China', 'Germany'],
  },
});
const series = ref([
  {
    name: 'Score',
    data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380],
  },
  {
    name: 'Score2',
    data: [400, 480, 548, 270, 240, 380, 650, 1000, 1120, 1380],
  },
]);
</script>
