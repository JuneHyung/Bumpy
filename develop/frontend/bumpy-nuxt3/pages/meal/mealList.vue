<template>
  <main class="content-layout meal-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">{{ mealStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag">Edit</button>
      </div>
      <ActivityList type="square" listType="meal" :list="mealList"></ActivityList>
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
import { useCommonStore } from '~~/store/common';
import { useMealStore } from '~~/store/meal';
const commonStore = useCommonStore();
const mealStore = useMealStore();
const router = useRouter();
const editFlag = computed(()=>commonStore.getToday===mealStore.getFocusDate)
const mealList = ref([]);

definePageMeta({
  layout: 'main-layout',
});
// const moveDetail = ()=>{
//   router.push({path: 'mealDetail'})
// }

const moveEdit = ()=>{
  router.push({path: 'mealEdit'})
}

// get MealList
const getMealList = () =>{
  mealList.value = [
    {
      name: '오늘 아침',
      order: 1,
      time: '',
      kcal: 265,
      water: 0,
      memo: '아침'
    }
  ];
  // try {
  //   const { data, error } = await readMealList({ stdDate: mealStore.getFocusDate });
  //   if(error.value !== null){
  //   } else if(data.value!==null){
  //     mealList.value = data
  //   }
  // } catch (e) {
  //   console.error(e)
  // }
}
const getCalendarList = ()=> {
  mealStore.setCalendarlist([
  { title: '식단 01', date: '2023-06-05' },
  { title: '식단 02', date: '2023-06-05' },
  { title: '식단 03', date: '2023-06-05' }
  ])
  // Meal Calendar List 조회 API 추가.
  // try{
  //   const {data, error} = await readWeightCalendarList();
  //   if(error.value !== null){

  //   }else if(data.value!==null){
  //     mealStore.setCalendarlist(data);
  //     // weightList.value = data
  //   }
  // }catch(e){
  //   setErrorMessage(e)
  // }
}

// Calendar 클릭시 focusdate변경
const getFocusDate = (v) => {
  mealStore.setFocusDate(v);
  getMealList();
}

// const testList = [
//   { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
//   { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모' },
//   // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
//   // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
//   // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
// ];

onMounted(()=>{
  const today =commonStore.getToday;
  mealStore.setFocusDate(today);
  getMealList();
  getCalendarList();
})

</script>
<style scoped lang="scss">

</style>