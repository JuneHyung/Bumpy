<template>
  <main class="content-layout weight-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">2022-02-01</h3>
        <button @click="moveEdit">Edit</button>
      </div>
      <SquareList type="square" :list="testList"></SquareList>
    </div>
    <div class="content-wrap-box bp-mt-xl">
      <p>calendar ing</p>
    </div>
  </main>
</template>
<script setup>
import SquareList from '~~/components/list/SquareList.vue';
import { useRouter } from 'vue-router';
const router = useRouter();
definePageMeta({
  layout: 'main-layout',
});
const moveDetail = ()=>{
  router.push({path: 'weightDetail'})
}
const moveEdit = ()=>{
  router.push({path: 'weightEdit'})
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
.weight-list-wrap-box{
  .title-wrap-box{
    display: flex;
    justify-content: space-between;
  }
}
</style>