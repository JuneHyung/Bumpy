<template>
  <div class="content-layout main-page">
    <h1 class="content-title">Introduce</h1>
    <div class="content-wrap-box">
      <p>Welcome 준형갓!</p>
      <p>운동하다 죽기 딱 좋은 날씨에요!</p>
    </div>
    <div class="activity-meal-wrap-box">
      <div class="content-wrap-box activity-wrap-box">
        <div class="activity-title-box">
          <h3 class="activity-title">Your activity</h3>
          <p class="activity-date">2023.02.07</p>
        </div>
        <div class="calendar">
          <GrassCalendar :activeList="activeList"></GrassCalendar>
        </div>
      </div>
      <div class="content-wrap-box meal-wrap-box">
        <div class="meal-title-box">
          <h3 class="meal-title">Today Meal</h3>
        </div>
        <ActivityList type="square" listType="meal" :list="todayMealList"></ActivityList>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Activity</h3>
          <p class="activity-date">{{weightStdDate}}</p>
        </div>

        <ActivityList type="rectangle" listType="weight" :list="lastWeightList"></ActivityList>
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">My Best</p>
            <p class="chart-info-item-value">{{weightInfo.myBest}} kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Month Average</p>
            <p class="chart-info-item-value">{{weightInfo.monthAverage}} kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">{{weightInfo.reos}} reps / {{weightInfo.sets}} sets</p>
          </li>
        </ul>
        <LineChart class="last-activity-chart" :xAxis="weightChartInfo.xAxis" :series="weightChartInfo.series"></LineChart>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Aerobic</h3>
          <p class="activity-date">{{aerobicStdDate}}</p>
        </div>
        <ActivityList type="rectangle" listType="aerobic" :list="lastAerobicList"></ActivityList>
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">Incline Average</p>
            <p class="chart-info-item-value">{{ aerobicInfo.averageIncline }}</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Speed Average</p>
            <p class="chart-info-item-value">{{ aerobicInfo.averageSpeed }}</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">{{aerobicInfo.bestKcal}} kcal | {{aerobicInfo.bestTime}} m</p>
          </li>
        </ul>
        <AreaChart class="last-activity-cahrt" :xAxis="aerobicChartInfo.xAxis" :series="aerobicChartInfo.series"></AreaChart>
      </div>
    </div>
  </div>
</template>
<script setup>
import ActivityList from '~/components/list/ActivityList.vue';
import GrassCalendar from '~/components/calendar/GrassCalendar.vue';

import AreaChart from '~/components/charts/AreaChart';
import LineChart from '~/components/charts/LineChart';
import {useCommonStore} from '~/store/common'
import { setErrorMessage } from '~~/api/alert/message';
import { getAerobicActivityForMain, getAerobicChartInfoForMain, getMealInfoForMain, getWeightActivityForMain, getWeightChartInfoForMain } from '~~/api/main';
const commonStore = useCommonStore();
definePageMeta({
  layout: 'main-layout',
});
const activeList = [
  '2023-04-01',
  '2023-04-05',
  '2023-04-12',
  '2023-04-18',
  '2023-04-25',
  '2023-05-01',
  '2023-05-03',
  '2023-05-05',
  '2023-05-12',
  '2023-05-15',
  '2023-05-27',
  '2023-05-28',
  '2023-05-29',
  '2023-05-30',
  '2023-05-13',
  '2023-05-09',
  '2023-05-18',
  '2023-06-01',
  '2023-06-03',
  '2023-06-12',
  '2023-06-17',
  '2023-06-25',
  '2023-06-26',
  '2023-06-27',
  '2023-06-28',
  '2023-07-01',
  '2023-07-11',
  '2023-07-21',
  '2023-07-31',
  '2023-08-31',
];
const testList = [
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', weightStart: 10, weightEnd: 30, pollWeight: 20, repsStart: 12, repsEnd: 8, setReps: 5, memo: '메모메모' },
];

const todayMealList = ref([]);
const lastWeightList = ref([]);
const lastAerobicList = ref([]);
const weightStdDate = ref('')
const aerobicStdDate = ref('')
const weightInfo = ref({
  myBest: 0,
  reps: 0,
  sets: 0,
  monthAverage: 0,
})
const weightChartInfo = ref({
  series: [],
  xAxis: [],
})
const aerobicInfo = ref({
  bestKcal: 0,
  bestTime: 0,
  averageIncline: 0,
  averageSpeed: 0,
})
const aerobicChartInfo = ref({
  series: [],
  xAxis: [],
})

const getTodayMealInfo = async () => {
try{
    const {data, error} = await getMealInfoForMain();
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const list = data.value.data
      todayMealList.value = list;
    }
  }catch(e){
    setErrorMessage(e);
  }
}
const getLastWeightActivityInfo = async () =>{
try{
    const {data, error} = await getWeightActivityForMain();
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const list = data.value.data
      lastWeightList.value = list;
      weightStdDate.value = list[0].stdDate;
    }
  }catch(e){
    setErrorMessage(e);
  }
}
const getLastAerobicActivityInfo = async () =>{
try{
    const {data, error} = await getAerobicActivityForMain();
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const list = data.value.data
      lastAerobicList.value = list
      aerobicStdDate.value = list[0].stdDate;
    }
  }catch(e){
    setErrorMessage(e);
  }
}
const getWeightChartInfo = async () =>{
try{
    // const {data, error} = await getWeightChartInfoForMain({name: lastWeightList.value[0].name});
    const {data, error} = await getWeightChartInfoForMain({name: '벤치프레스22'});
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const result = data.value.data
      const infoKeys = Object.keys(weightInfo.value)
      const chartKeys = Object.keys(weightChartInfo.value)
      for(const key of infoKeys) weightInfo.value[key] = result[key];
      // for(const key of chartKeys) weightChartInfo.value[key] = result[key];
      // console.log(result)
      weightChartInfo.value.xAxis = result.xAxis;
      const series = result.series.map(el=>{
        const key = Object.keys(el)[0];
        const value = el[key].slice();
        const result = {
          name: key,
          data: value,
        }
        return result;
      })
      weightChartInfo.value.series = series;
      // console.log(weightChartInfo.value)
    }
  }catch(e){
    setErrorMessage(e);
  }
}
const getAerobicChartInfo = async () =>{
try{
    const {data, error} = await getAerobicChartInfoForMain({name:lastAerobicList.value[0].name});
    // const {data, error} = await getAerobicChartInfoForMain({name:'TEST-2'});
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const result = data.value.data
      const infoKeys = Object.keys(aerobicInfo.value)
      // console.log(result)
      const chartKeys = Object.keys(aerobicChartInfo.value)
      for(const key of infoKeys) aerobicInfo.value[key] = result[key];
      // for(const key of chartKeys) aerobicChartInfo.value[key] = result[key];
      // // console.log(result)
      aerobicChartInfo.value.xAxis = result.xAxis;
      const series = result.series.map(el=>{
        const key = Object.keys(el)[0];
        const value = el[key].slice();
        const result = {
          name: key,
          data: value,
        }
        return result;
      })
      aerobicChartInfo.value.series = series;
      // console.log(aerobicChartInfo.value)

    }
  }catch(e){
    setErrorMessage(e);
  }  
}


onMounted(async ()=>{
  commonStore.setToday();
  await getTodayMealInfo()
  await getLastAerobicActivityInfo();
  await getLastWeightActivityInfo();
  await getWeightChartInfo();
  await getAerobicChartInfo();
})
</script>
<style lang="scss"></style>
