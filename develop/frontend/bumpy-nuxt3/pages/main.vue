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
          <GrassCalendar></GrassCalendar>
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
            <p class="chart-info-item-value">{{weightInfo.reps}} reps / {{weightInfo.sets}} sets</p>
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
<script setup lang="ts">
import ActivityList from '~~/components/list/ActivityList.vue';
import GrassCalendar from '~~/components/calendar/GrassCalendar.vue';

import AreaChart from '~~/components/charts/AreaChart.vue'; // 컴포넌트 선언파일 추가 필요
import LineChart from '~~/components/charts/LineChart.vue';
import {useCommonStore} from '~/store/common'
import { setErrorMessage } from '~~/api/alert/message';
import { getAerobicActivityForMain, getAerobicChartInfoForMain, getMealInfoForMain, getWeightActivityForMain, getWeightChartInfoForMain } from '~~/api/main';
import {MainAerobicInfo, MainWeightChartInfo, MainAerobicChartData, MainAerobicChartInfo, MainWeightChartData} from '~~/types/main';
import { MealList } from '~~/types/meal';
import { WeightList } from '~~/types/weight';
import { AerobicList } from '~~/types/aerobic';
import { useMealStore } from '~~/store/meal';
const commonStore = useCommonStore();
const mealStore = useMealStore();
definePageMeta({
  layout: 'main-layout',
});

const todayMealList: Ref<MealList> = ref([]);
const lastWeightList: Ref<WeightList> = ref([]);
const lastAerobicList: Ref<AerobicList> = ref([]);
const weightStdDate: Ref<string> = ref('')
const aerobicStdDate = ref('')

const weightInfo: Ref<MainWeightChartInfo> = ref({
  myBest:'',
  reps:'',
  sets:'',
  monthAverage:'',
})
const weightChartInfo: Ref<MainWeightChartData> = ref({
  series: [],
  xAxis: [],
})
const aerobicInfo: Ref<MainAerobicChartInfo> = ref({
  bestKcal:'',
  bestTime:'',
  averageIncline:'',
  averageSpeed:'',
})
const aerobicChartInfo: Ref<MainAerobicChartData> = ref({
  series: [],
  xAxis: [],
})

const getTodayMealInfo = async () => {
try{
    const {data, error} = await getMealInfoForMain();
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      
      const list = data.value.data;
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
      weightStdDate.value = list[0].stdDate as string;
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
      lastAerobicList.value = list as AerobicList
      aerobicStdDate.value = list[0].stdDate as string;
    }
  }catch(e){
    setErrorMessage(e);
  }
}

const getWeightChartInfo = async () =>{
try{
    const {data, error} = await getWeightChartInfoForMain({name: lastWeightList.value[0].name as string});
    // const {data, error} = await getWeightChartInfoForMain({name: 'TEST-4'});
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const result = data.value.data
      const infoKeys = Object.keys(weightInfo.value)
      const chartKeys = Object.keys(weightChartInfo.value)
      for(const key of infoKeys) weightInfo.value[key] = result[key];
      
      weightChartInfo.value.xAxis = result.xAxis;
      weightChartInfo.value.series = result.series;
    }
  }catch(e){
    setErrorMessage(e);
  }
}
const getAerobicChartInfo = async () =>{
try{
    const {data, error} = await getAerobicChartInfoForMain({name:lastAerobicList.value[0].name as string});
    // const {data, error} = await getAerobicChartInfoForMain({name:'TEST-2'});
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const result = data.value.data
      const infoKeys = Object.keys(aerobicInfo.value)
      for(const key of infoKeys) aerobicInfo.value[key] = result[key];
      aerobicChartInfo.value.xAxis = result.xAxis;
      aerobicChartInfo.value.series = result.series;
    }
  }catch(e){
    setErrorMessage(e);
  }  
}


onMounted(async ()=>{
  commonStore.setToday();
  mealStore.setFocusDate(commonStore.getToday);
  await getTodayMealInfo()
  await getLastAerobicActivityInfo();
  await getLastWeightActivityInfo();
  await getWeightChartInfo();
  await getAerobicChartInfo();
})
</script>
<style lang="scss"></style>
