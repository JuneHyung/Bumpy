<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About My Routine</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ info.name }}</h2>
      <div class="weight-info-box">
        <div class="photo-list-wrap">
          <p>image</p>
        </div>
        <div class="info-list-wrap">
          <p class="bp-mr-sm">
            <span>시작 무게 : {{ info.startWeight }} kg</span>
            <span>시작 횟수 : {{ info.startReps }} reps</span>
          </p>
          <p class="bp-mr-sm">
            <span>종료 무게 : {{ info.endWeight }} kg</span>
            <span>종료 횟수 : {{ info.endReps }} reps</span>
          </p>
          <p class="bp-mr-sm">
            <span>봉 무게 : {{ info.barWeight }} kg</span>
            <span>세트 횟수 : {{ info.setReps }} reps</span>
          </p>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <p>Youtube or chart</p>
        </div>
      </div>
      <div class="weightDetail-button-wrap">
        <button class="short-ghost-button bp-mr-sm">취소</button>
        <button class="short-filled-button">수정</button>
      </div>
    </div>
  </main>
</template>
<script setup>
definePageMeta({
  layout: 'main-layout',
});
const info = {
  name: '벤치 프레스',
  startWeight: 0,
  endWeight: 20,
  barWeight: 20,
  startReps: 12,
  endReps: 8,
  setReps: 5,
  memo: 'asdfdasfasfas',
};
</script>
