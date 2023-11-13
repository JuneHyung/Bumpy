<template>
  <Apexchart type="radialBar" height="90%" :options="chartOptions" :series="[calculated]" />
</template>
<script setup lang="ts">
import Apexchart from 'vue3-apexcharts';
interface Props {
  data: String,
}
const props = defineProps<Props>()
const calculated = computed(()=>{
  const result = (Math.floor(Number(props.data) / 10))
  return isNaN(result) ? 0 : result;
})
const chartOptions =  {
  chart: {
    type: 'radialBar',
    offsetY: -10,
  },
  plotOptions: {
    radialBar: {
      startAngle: -135,
      endAngle: 135,
      dataLabels: {
        name: { // Label Name
          fontSize: '16px',
          color: '#fb8c00',
          offsetY: 120
        },
        value: {
          offsetY: 76,
          fontSize: '22px',
          color: '#e65100',
          formatter: function (val: number) {
            return val*10 + "kcal";
          }
        },
        background: 'red',
      },
    }
  },
  fill: {
    type: 'gradient',
    gradient: {
      shade: 'dark',
      shadeIntensity: 0.15,
      gradientToColors: ['#e65100'],
      opacityFrom: 1,
      opacityTo: 1,
      stops: [0, 100]
    },
    background: '#7E36AF'
  },
  stroke: {
    dashArray: 4,
  },
  colors: ['#fb8c00'],
  labels: ['Burn Kcal'],
}
</script>
