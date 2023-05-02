<template>
  <main class="content-layout inbody-list-wrap-box">
    <h1 class="content-title">About My Body</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">2022-02-01</h3>
        <button @click="moveEdit">Edit</button>
      </div>
      <div class="inbody-info-wrap-box">
        <ActivityList type="square" :list="testList"></ActivityList>
        <div class="chart-box">
          <p>chart</p>
        </div>
      </div>
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
  router.push({path: 'inbodyDetail'})
}
const moveEdit = ()=>{
  router.push({path: 'inbodyEdit'})
}
const testList = [
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
];

</script>
<style scoped lang="scss">
.inbody-list-wrap-box{
  .title-wrap-box{
    display: flex;
    justify-content: space-between;
  }
  .inbody-info-wrap-box{
    display:flex;
    // flex-direction: row;
    margin-top:20px;
    justify-content: center;
    align-items: center;
    .chart-box{
      // width: calc(100% - 200px);
      width: 100%;
      height:200px;
      border:1px solid black;
      display:flex;
      justify-content: center;
      align-items: center;
    }
  }
}
</style>