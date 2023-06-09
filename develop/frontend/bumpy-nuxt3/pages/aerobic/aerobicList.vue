<template>
  <main class="content-layout aerobic-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">{{ aerobicStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag">Edit</button>
      </div>
      <ActivityList type="square" listType="aerobic" :list="aerobicList"></ActivityList>
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
import { setErrorMessage } from '~~/api/alert/message';
import { useCommonStore } from '~~/store/common';
import { useAerobicStore } from '~~/store/aerobic';
const commonStore = useCommonStore();
const aerobicStore = useAerobicStore();
const router = useRouter();
const editFlag = computed(()=>commonStore.getToday===aerobicStore.getFocusDate)
const aerobicList = ref([]);

definePageMeta({
  layout: 'main-layout',
});

// Calendar 클릭시 focusdate변경
const getFocusDate = (v) => {
  aerobicStore.setFocusDate(v);
  getAerobicList();
}

// aerobic List 조회
const getAerobicList = async() => {
  aerobicList.value = [
    {
      seq: 1,
      name:'벤치프레스01',
      kcal: 900,
      time: 20,
      inclineStart: 20,
      inclineEnd: 20,
      speedStart: 5.5,
      speedEnd: 5.5,
      memo: 'memo test01',
    },
    {
      seq: 1,
      name:'벤치프레스01',
      kcal: 900,
      time: 20,
      inclineStart: 20,
      inclineEnd: 20,
      speedStart: 5.5,
      speedEnd: 5.5,
      memo: 'memo test01',
    },
  ]
  // try {
  //   const { data, error } = await readAerobicList({ stdDate: aerobicStore.getFocusDate });
  //   if(error.value !== null){
  //   }else if(data.value!==null){
  //     aerobicList.value = data
  //   }
  // } catch (e) {
  //   console.error(e)
  // }
}

// calendarList 조회
const getCalendarList = async () =>{
  aerobicStore.setCalendarlist([
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

const moveEdit = () => {
  router.push({ path: 'aerobicEdit' });
};
onMounted(()=>{
  const today = commonStore.getToday;
  aerobicStore.setFocusDate(today);
  // aerobicStore.resetSelectItem();
  getAerobicList();
  getCalendarList();
})
</script>