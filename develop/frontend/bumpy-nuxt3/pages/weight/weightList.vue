<template>
  <main class="content-layout weight-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title">{{ weightStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag">Edit</button>
      </div>
      <ActivityList type="square" listType="weight" :list="weightList"></ActivityList>
    </div>
    <div class="content-wrap-box bp-mt-xl">
      <Calendar @focusDate="getFocusDate"></Calendar>
    </div>
  </main>
</template>
<script setup>
import Calendar from '/components/calendar/Calendar.vue';

import ActivityList from '~~/components/list/ActivityList.vue';
import { useRouter } from 'vue-router';
import { readWeightList } from '~/api/weight/weight'
import { useCommonStore } from '~~/store/common';
import { setErrorMessage } from '~~/api/alert/message';
import { readWeightCalendarList } from '~/api/weight/weight';
import { useWeightStore } from '~~/store/weight';

const commonStore = useCommonStore();
const weightStore = useWeightStore();
const router = useRouter();
const editFlag = computed(()=>commonStore.getToday===weightStore.getFocusDate)
const weightList = ref([])

definePageMeta({
  layout: 'main-layout',
});

const moveEdit = () => {
  router.push({ path: 'weightEdit' });
};


// Calendar 클릭시 focusdate변경
const getFocusDate = (v) => {
  weightStore.setFocusDate(v);
  getWeightList();
}

// weightList 조회
const getWeightList = async() => {
  weightList.value = [
    {
      seq: 1,
      name:'벤치프레스01',
      weightStart: 10,
      weightEnd: 20,
      repsStart: 10,
      repsEnd: 20,
      pollWeight: 10,
      setReps: 20,
      measure: 'kg',
      memo: 'memo test01',
    },
    {
      seq: 2,
      name:'벤치프레스02',
      weightStart: 10,
      weightEnd: 20,
      repsStart: 10,
      repsEnd: 20,
      pollWeight: 10,
      setReps: 20,
      measure: 'kg',
      memo: 'memo test02',
    }
  ]
  // try {
  //   const { data, error } = await readWeightList({ stdDate: weightStore.getFocusDate });
  //   if(error.value !== null){
  //   }else if(data.value!==null){
  //     weightList.value = data
  //   }
  // } catch (e) {
  //   console.error(e)
  // }
}

// calendarList 조회
const getCalendarList = async () =>{
  weightStore.setCalendarlist([
  { title: '운동 01', date: '2023-06-05' },
  { title: '운동 02', date: '2023-06-05' },
  { title: '운동 03', date: '2023-06-05' }
  ])
  // try{
  //   const {data, error} = await readWeightCalendarList();
  //   if(error.value !== null){

  //   }else if(data.value!==null){
  //     weightStore.setCalendarlist(data);
  //     // weightList.value = data
  //   }
  // }catch(e){
  //   setErrorMessage(e)
  // }
}

onMounted(()=>{
  const today = commonStore.getToday;
  weightStore.setFocusDate(today);
  // weightStore.resetSelectItem();
  console.log(weightStore.getSelectItem)
  getWeightList();
  getCalendarList();
})
</script>
