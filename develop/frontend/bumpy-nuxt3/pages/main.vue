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
          <GrassCalendar></GrassCalendar>
        </div>
      </div>
      <div class="content-wrap-box meal-wrap-box">
        <div class="meal-title-box">
          <h3 class="meal-title">Today Meal</h3>
        </div>
        <ActivityList type="square" listType="meal" :list="mealStore.getTodayMealList()"></ActivityList>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Activity</h3>
          <p class="activity-date">{{weightStore.getMainWeightDate()}}</p>
        </div>

        <ActivityList type="rectangle" listType="weight" :list="weightStore.getLastWeightList()"></ActivityList>
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
        <LineChart class="last-activity-chart" :xAxis="weightStore.getMainWeightChartInfo().xAxis" :series="weightStore.getMainWeightChartInfo().series"></LineChart>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Aerobic</h3>
          <p class="activity-date">{{aerobicStore.getMainAerobicDate()}}</p>
        </div>
        <ActivityList type="rectangle" listType="aerobic" :list="aerobicStore.getLastAerobicList()"></ActivityList>
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
        <AreaChart class="last-activity-cahrt" :xAxis="aerobicStore.getMainAerobicChartInfo().xAxis" :series="aerobicStore.getMainAerobicChartInfo().series"></AreaChart>
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
});

onMounted(async ()=>{
  commonStore.setToday();
  mealStore.setFocusDate(commonStore.getToday());
  await mealStore.getTodayMealInfo();
  await weightStore.getLastWeightActivityInfo();
  await aerobicStore.getLastAerobicActivityInfo();
  await weightStore.getWeightChartInfo();
  await aerobicStore.getAerobicChartInfo();
})
</script>
<style lang="scss"></style>
