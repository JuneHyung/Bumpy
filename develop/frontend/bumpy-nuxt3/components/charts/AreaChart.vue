<template>
  <Apexchart type="area" height="100%" :key="props.series" :options="options" :series="props.series"></Apexchart>
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
  // plotOptions: {
  //   bar: {
  //     borderRadius: 4,
  //     borderRadiusApplication: 'around',
  //   },
  // },
  labels: props.xAxis,
  // xaxis: {
  //   categories: props.xAxis,
  // },
  // xaxis: {
  //   categories: props.xAxis,
  // },
  // yaxis: {
  //   opposite: true,
  // },
  // legend: {
  //   horizontalAlign: 'right',
  // },
});
// const series = ref([
//   {
//     name: 'Score',
//     data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380],
//   },
// ]);
</script>
