<template>
  <Apexchart type="line" height="90%" :options="options" :series="props.series"></Apexchart>
</template>
<script setup lang="ts">
import { ref } from "vue";

import Apexchart from "vue3-apexcharts";

const props = defineProps({
  series: Array,
  xAxis: Array,
});

const options = ref({
  chart: {
    type: "line",
    zoom: {
      enabled: false,
    },
    foreColor: "#FFFFFF",
  },
  stroke: {
    curve: "straight",
  },
  labels: props.xAxis,
  xaxis: {
    categories: props.xAxis,
  },
});
</script>
