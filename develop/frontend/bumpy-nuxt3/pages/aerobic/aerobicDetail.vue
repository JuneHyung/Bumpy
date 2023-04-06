<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About My Routine</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ infoName.value }}</h2>
      <div class="aerobic-info-box">
        <div class="chart-wrap">
          <p>image</p>
        </div>
        <div class="info-list-wrap">
          <div class="info-item">
            <template v-for="(info, idx) in infoList" :key="idx">
              <p class="bp-mr-sm">
                <template v-for="(item, iIdx) in info" :key="iIdx">
                  <span>{{ item.label }} : {{ item.value }} {{ item.unit }}</span>
                </template>
              </p>
            </template>
          </div>
          <div class="info-memo-box">
            <textarea stype="width:100%;"></textarea>
          </div>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <p>Youtube or chart</p>
        </div>
      </div>
      <div class="aerobicDetail-button-wrap">
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
const infoName = { key: 'name', label: '', value: 'Walking' };
const infoList = [
  [
    { key: 'startIncline', label: '시작 Incline', value: '0', unit: '' },
    { key: 'startSpeed', label: '시작 Speed', value: '12', unit: '' },
  ],
  [
    { key: 'endIncline', label: '종료 Incline', value: '20', unit: '' },
    { key: 'endSpeed', label: '종료 Speed', value: '8', unit: '' },
  ],
  [
    { key: 'time', label: '시간', value: '63:28', unit: '' },
    { key: 'kcal', label: 'Kcal', value: '5', unit: '' },
  ],
];
const infoMemo = { key: 'memo', label: '', value: 'memomemomemeomeoemo' };
</script>
