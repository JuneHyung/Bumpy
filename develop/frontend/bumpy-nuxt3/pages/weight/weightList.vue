<template>
  <main class="content-layout weight-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="weight-list-box content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title">{{ weightStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag" class="short-filled-button edit-button">Edit</button>
      </div>
      <ActivityList type="square" listType="weight" :list="weightList"></ActivityList>
    </div>
    <div class="content-wrap-box bp-mt-xl weight-calendar">
      <Calendar @focusDate="getFocusDate" type="weight"></Calendar>
    </div>
  </main>
</template>
<script setup lang="ts">
import Calendar from '/components/calendar/Calendar.vue';

import ActivityList from '~~/components/list/ActivityList.vue';
import { useRouter } from 'vue-router';
import { readWeightList } from '~/api/weight/weight'
import { useCommonStore } from '~~/store/common';
import { setErrorMessage } from '~~/api/alert/message';
import { readWeightCalendarList, readFavoritWeightList } from '~/api/weight/weight';
import { useWeightStore } from '~~/store/weight';
import { WeightList } from '~~/types/weight';

const commonStore = useCommonStore();
const weightStore = useWeightStore();
const router = useRouter();
const editFlag = computed(()=>commonStore.getToday===weightStore.getFocusDate)
const weightList: Ref<WeightList> = ref([])

definePageMeta({
  layout: 'main-layout',
});

const moveEdit = () => {
  weightStore.resetSelectItem()
  router.push({ path: 'weightEdit' });
};


// Calendar 클릭시 focusdate변경
const getFocusDate = (v: string) => {
  weightStore.setFocusDate(v);
  getWeightList();
}

// weightList 조회
const getWeightList = async() => {
  try {
    const { data, error } = await readWeightList({ stdDate: weightStore.getFocusDate });
    if(error.value !== null){
    }else if(data.value!==null){
      const list = data.value.data;
      weightList.value = list
    }
  } catch (e) {
    setErrorMessage(e)
  }
}

// calendarList 조회
const getCalendarList = async () =>{
  const focusDate = weightStore.getFocusDate
  
  try{
    const {data, error} = await readWeightCalendarList({stdDate: focusDate});
    if(error.value !== null){
      setErrorMessage(error.value);
    }else if(data.value?.data!==null){
      const list = data.value?.data;
      weightStore.setCalendarlist(list);
    }
  }catch(e){
    setErrorMessage(e)
  }
}

onMounted(async()=>{
  const today = commonStore.getToday;
  weightStore.setFocusDate(today);
  await getCalendarList();
  await getWeightList();
  // weightStore.resetSelectItem();

})
</script>
