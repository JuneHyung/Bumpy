<template>
  <div class="content-layout main-page">
    <h1 class="content-title">Introduce</h1>
    <div class="content-wrap-box">
      <p>Welcome {{ userStore.getUserName() }}</p>
      <p>운동하다 죽기 딱 좋은 날씨에요!</p>
    </div>
    <div class="activity-meal-wrap-box">
      <div class="content-wrap-box activity-wrap-box">
        <div class="activity-title-box">
          <h3 class="activity-title">Your activity</h3>
          <p class="activity-date">{{ commonStore.getToday() }}</p>
        </div>
        <div class="calendar">
          <GrassCalendar />
        </div>
      </div>
      <div class="content-wrap-box meal-wrap-box">
        <div class="meal-title-box">
          <h3 class="meal-title">Today Meal</h3>
        </div>
        <ActivityList type="square" listType="meal" :list="mealStore.getTodayMealList()" />
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Activity</h3>
          <p class="activity-date">{{weightStore.getMainWeightDate()}}</p>
        </div>

        <ActivityList type="rectangle" listType="weight" :list="weightStore.getLastWeightList()" />
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">My Best</p>
            <p class="chart-info-item-value">{{weightStore.getMainWeightInfo().myBest}} kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Month Average</p>
            <p class="chart-info-item-value">{{weightStore.getMainWeightInfo().monthAverage}} kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">{{weightStore.getMainWeightInfo().reps}} reps / {{weightStore.getMainWeightInfo().sets}} sets</p>
          </li>
        </ul>
        <template v-if="weightStore.getMainWeightChartInfo().xAxis.length!==0">
          <LineChart class="last-activity-chart" :xAxis="weightStore.getMainWeightChartInfo().xAxis" :series="weightStore.getMainWeightChartInfo().series" />
        </template>
        <template v-else>
          <NoData />
        </template>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Aerobic</h3>
          <p class="activity-date">{{aerobicStore.getMainAerobicDate()}}</p>
        </div>
        <ActivityList type="rectangle" listType="aerobic" :list="aerobicStore.getLastAerobicList()" />
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">Incline Average</p>
            <p class="chart-info-item-value">{{ aerobicStore.getMainAerobicInfo().averageIncline }}</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Speed Average</p>
            <p class="chart-info-item-value">{{ aerobicStore.getMainAerobicInfo().averageSpeed }}</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">{{aerobicStore.getMainAerobicInfo().bestKcal}} kcal | {{aerobicStore.getMainAerobicInfo().bestTime}} m</p>
          </li>
        </ul>
        <template v-if="aerobicStore.getMainAerobicChartInfo().xAxis.length!==0">
          <AreaChart class="last-activity-cahrt" :xAxis="aerobicStore.getMainAerobicChartInfo().xAxis" :series="aerobicStore.getMainAerobicChartInfo().series" />
        </template>
        <template v-else>
          <NoData />
        </template>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import ActivityList from '~~/components/list/ActivityList.vue';
import GrassCalendar from '~~/components/calendar/GrassCalendar.vue';

import AreaChart from '~~/components/charts/AreaChart.vue'; // 컴포넌트 선언파일 추가 필요
import LineChart from '~~/components/charts/LineChart.vue';
import NoData from '~~/components/common/NoData.vue';
import {useCommonStore} from '~/store/common'

import { useMealStore } from '~~/store/meal';
import { useUserStore } from '~~/store/user';
import { useWeightStore } from '~~/store/weight';
import { useAerobicStore } from '~~/store/aerobic';
const commonStore = useCommonStore();
const userStore = useUserStore();
const mealStore = useMealStore();
const weightStore = useWeightStore();
const aerobicStore = useAerobicStore();

definePageMeta({
  layout: 'main-layout',
  middleware: 'custom-router-guard'
});

onMounted(async ()=>{
  mealStore.setFocusDate(commonStore.getToday());
  await mealStore.getTodayMealInfo();
  await weightStore.getLastWeightActivityInfo();
  await aerobicStore.getLastAerobicActivityInfo();
  if(weightStore.getLastWeightList().length!==0) await weightStore.getWeightChartInfo(weightStore.getLastWeightList()[0].name as string);
  if(aerobicStore.getLastAerobicList().length!==0) await aerobicStore.getAerobicChartInfo(aerobicStore.getLastAerobicList()[0].name as string);
})
</script>
