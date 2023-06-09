<template>
  <main class="content-layout inbody-list-wrap-box">
    <h1 class="content-title">About My Body</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title">{{ inbodyStore.focusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag">Edit</button>
      </div>
      <div class="inbody-info-wrap-box">
        <ActivityList type="square" listType="inbody" :list="inbodyList"></ActivityList>
        <div class="chart-box">
          <p>chart</p>
        </div>
      </div>
    </div>
    <div class="content-wrap-box bp-mt-xl">
      <Calendar @focusDate="getFocusDate"></Calendar>
    </div>
  </main>
</template>
<script setup>
import ActivityList from '~~/components/list/ActivityList.vue';
import { useRouter } from 'vue-router';
import { useInbodyStore } from '~~/store/inbody';
import { useCommonStore } from '~~/store/common';

const commonStore = useCommonStore();
const inbodyStore = useInbodyStore();

const router = useRouter();
const editFlag = computed(()=>commonStore.getToday===inbodyStore.getFocusDate);
const inbodyList = ref([]);


definePageMeta({
  layout: 'main-layout',
});

const moveEdit = ()=>{
  router.push({path: 'inbodyEdit'})
}

// Calendar 클릭시 focusdate변경
const getFocusDate = (v) => {
  inbodyStore.setFocusDate(v);
  getInbodyList();
}

// inbody List 조회
const getInbodyList = async() => {
  inbodyList.value = [
    {
      seq: 1,
      height: 170,
      weight: 70,
      age: 27,
      muscle: 10,
      fat: 20,
      score: 10,
      bmi: 20,
      fatPercent: 20,
    },
  ]
  // try {
  //   const { data, error } = await readInbodyList({ stdDate: inbodyStore.getFocusDate });
  //   if(error.value !== null){
  //   }else if(data.value!==null){
  //     inbodyList.value = data
  //   }
  // } catch (e) {
  //   console.error(e)
  // }
}

// calendarList 조회
const getCalendarList = async () =>{
  inbodyStore.setCalendarlist([
  { title: '운동 01', date: '2023-06-05' },
  { title: '운동 02', date: '2023-06-05' },
  { title: '운동 03', date: '2023-06-05' }
  ])
  // try{
  //   const {data, error} = await readInobdyCalendarList();
  //   if(error.value !== null){

  //   }else if(data.value!==null){
  //     inbodyStore.setCalendarlist(data);
  //     // inbodyList.value = data
  //   }
  // }catch(e){
  //   setErrorMessage(e)
  // }
}

onMounted(()=>{
  const today= commonStore.getToday;
  inbodyStore.setFocusDate(today);
  getInbodyList();
  getCalendarList();
})
</script>
<style scoped lang="scss">
</style>