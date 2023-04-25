<template>
  <Apexchart type="area" :key="series" height="350" width="100%" :options="options" :series="series"></Apexchart>
</template>
<script setup lang="ts">
import { ref } from 'vue';

import Apexchart from 'vue3-apexcharts';
const options = ref({
  chart: {
    type: 'area',
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
  plotOptions: {
    bar: {
      borderRadius: 4,
      borderRadiusApplication: 'around',
    },
  },
  xaxis: {
    categories: ['South Korea', 'Canada', 'United Kingdom', 'Netherlands', 'Italy', 'France', 'Japan', 'United States', 'China', 'Germany'],
  },
  yaxis: {
    opposite: true,
  },
  legend: {
    horizontalAlign: 'right',
  },
});
const series = ref([
  {
    name: 'Score',
    data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380],
  },
]);
</script>
