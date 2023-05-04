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
        <ActivityList type="square" :list="testList"></ActivityList>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Activity</h3>
          <p class="activity-date">2023.02.07</p>
        </div>
        <ActivityList type="rectangle" :list="testList"></ActivityList>
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">My Best</p>
            <p class="chart-info-item-value">40kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Month Average</p>
            <p class="chart-info-item-value">26.6kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">12reps / 5sets</p>
          </li>
        </ul>
        <LineChart class="last-activity-cahrt"></LineChart>
      </div>
    </div>

    <div class="content-wrap-box last-activity-wrap-box">
      <ul class="last-activity-list">
        <div class="activity-title-box">
          <h3 class="activity-title">Last Aerobic</h3>
          <p class="activity-date">2023.02.07</p>
        </div>
        <ActivityList type="rectangle" :list="testList"></ActivityList>
      </ul>
      <div class="last-activity-chart-box">
        <ul class="last-activity-chart-info">
          <li class="chart-info-item">
            <p class="chart-info-item-title">My Best</p>
            <p class="chart-info-item-value">40kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Month Average</p>
            <p class="chart-info-item-value">26.6kg</p>
          </li>
          <li class="chart-info-item">
            <p class="chart-info-item-title">Reps Average</p>
            <p class="chart-info-item-value">12reps / 5sets</p>
          </li>
        </ul>
        <AreaChart class="last-activity-cahrt"></AreaChart>
      </div>
    </div>
  </div>
</template>
<script setup>
import ActivityList from '~/components/list/ActivityList.vue';
import GrassCalendar from '~/components/calendar/GrassCalendar.vue';

import AreaChart from '~/components/charts/AreaChart';
import LineChart from '~/components/charts/LineChart';
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
]
const testList = [
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모' },
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
];
</script>
<style lang="scss">

</style>
