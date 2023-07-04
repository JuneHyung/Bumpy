<template>
  <Apexchart type="line" height="100%" :options="options" :series="props.series"></Apexchart>
</template>
<script setup lang="ts">
import { ref, onUpdated } from 'vue';

import Apexchart from 'vue3-apexcharts';

const props = defineProps({
  series: Array,
  xAxis: Array,
})

const options = ref({
  chart: {
    type: 'line',
    zoom: {
      enabled: false,
    },
    foreColor: '#FFFFFF',
  },
  // dataLabels: {
  //   enabled: true,
  // },
  // tooltip:{
  //   shared: false,
  // },
  stroke: {
    curve: 'straight',
  },
  labels: props.xAxis,
  xaxis: {
    categories: props.xAxis,
  },
});
// const series = ref([
//   {
//     name: 'Score',
//     data: [400, 430, 448, 470, 540, 580, 690, 1100, 1200, 1380],
//   },
//   {
//     name: 'Score2',
//     data: [400, 480, 548, 270, 240, 380, 650, 1000, 1120, 1380],
//   },
// ]);

</script>
<style>
</style>