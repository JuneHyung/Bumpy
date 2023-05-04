<template>
  <main class="content-layout meal-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">2022-02-01</h3>
        <button @click="moveEdit">Edit</button>
      </div>
      <ActivityList type="square" :list="testList"></ActivityList>
    </div>
    <div class="content-wrap-box bp-mt-xl">
      <p>calendar ing</p>
    </div>
  </main>
</template>
<script setup>
import ActivityList from '~~/components/list/ActivityList.vue';
import { useRouter } from 'vue-router';
const router = useRouter();
definePageMeta({
  layout: 'main-layout',
});
const moveDetail = ()=>{
  router.push({path: 'mealDetail'})
}
const moveEdit = ()=>{
  router.push({path: 'mealEdit'})
}
const testList = [
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모' },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
];

</script>
<style scoped lang="scss">
.meal-list-wrap-box{
  .title-wrap-box{
    display: flex;
    justify-content: space-between;
  }
}
</style>