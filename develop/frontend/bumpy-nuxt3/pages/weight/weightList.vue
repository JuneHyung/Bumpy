<template>
  <main class="content-layout weight-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">{{ weightStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag">Edit</button>
      </div>
      <ActivityList type="square" listType="weight" :list="weightList" @click="moveDetail"></ActivityList>
    </div>
    <div class="content-wrap-box bp-mt-xl">
      <Calendar :list="calendarList" @focusDate="getFocusDate"></Calendar>
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
const calendarList = ref([
  { title: '운동 01', date: '2023-06-05' },
  { title: '운동 02', date: '2023-06-05' },
  { title: '운동 03', date: '2023-06-05' },
  { title: '운동 04', date: '2023-06-05' },
  { title: '운동 05', date: '2023-06-05' },
  { title: '운동 06', date: '2023-06-15' },
  { title: '운동 07', date: '2023-06-25' },
  { title: '운동 08', date: '2023-06-11' },
  { title: '운동 09', date: '2023-06-24' },
])



definePageMeta({
  layout: 'main-layout',
});
const moveDetail = () => {
  router.push({ path: 'weightDetail' });
};
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
  try {
    const { data, error } = await readWeightList({ stdDate: weightStore.getFocusDate });
    if(error.value !== null){
    }else if(data.value!==null){
      weightList.value = data
    }
  } catch (e) {
    console.error(e)
  }
}

// calendarList 조회
const getCalendarList = async () =>{
  try{
    const {data, error} = await readWeightCalendarList();
    if(error.value !== null){

    }else if(data.value!==null){
      weightList.value = data
    }
  }catch(e){
    setErrorMessage(e)
  }
}
onMounted(()=>{
  const today = commonStore.getToday;
  weightStore.setFocusDate(today);
  getWeightList();
  getCalendarList();
})
</script>
<style scoped lang="scss">
.weight-list-wrap-box {
  .title-wrap-box {
    display: flex;
    justify-content: space-between;
  }
}
</style>
