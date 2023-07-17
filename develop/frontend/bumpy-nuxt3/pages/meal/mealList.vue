<template>
  <main class="content-layout meal-list-wrap-box">
    <h1 class="content-title">Your Meal List</h1>
    <div class="meal-list-box content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title">{{ mealStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag" class="short-filled-button edit-button">Edit</button>
      </div>
      <ActivityList type="square" listType="meal" :list="mealList"></ActivityList>
    </div>
    <div class="content-wrap-box bp-mt-xl meal-calendar">
      <Calendar @focusDate="getFocusDate" type="meal"></Calendar>
    </div>
  </main>
</template>
<script setup lang="ts">
import Calendar from '/components/calendar/Calendar.vue';
import ActivityList from '~~/components/list/ActivityList.vue';
import { useRouter } from 'vue-router';
import { useCommonStore } from '~~/store/common';
import { useMealStore } from '~~/store/meal';
import { readMealActivityList, readMealCalendarList } from '~~/api/meal/meal';
import { MealList } from '~~/types/meal';
import { setErrorMessage } from '~~/api/alert/message';
const commonStore = useCommonStore();
const mealStore = useMealStore();
const router = useRouter();
const editFlag = computed(()=>commonStore.getToday===mealStore.getFocusDate)
const mealList:Ref<MealList> = ref([]);

definePageMeta({
  layout: 'main-layout',
});

const moveEdit = ()=>{
  router.push({path: 'mealEdit'})
}

// get MealList
const getMealList = async () =>{
  try {
    const { data, error } = await readMealActivityList({ stdDate: mealStore.getFocusDate });
    if(error.value !== null){
      setErrorMessage(error.value);
    } else if(data.value!==null){
      mealList.value = data.value.data
    }
  } catch (e) {
    setErrorMessage(e);
  }
}
const getCalendarList = async ()=> {
  try{
    const {data, error} = await readMealCalendarList({ stdDate: mealStore.getFocusDate });
    if(error.value !== null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      mealStore.setCalendarlist(data.value.data);
    }
  }catch(e){
    setErrorMessage(e)
  }
}

// Calendar 클릭시 focusdate변경
const getFocusDate = (v: string) => {
  mealStore.setFocusDate(v);
  getMealList();
}

onMounted(async ()=>{
  const today =commonStore.getToday;
  await mealStore.setFocusDate(today);
  await getCalendarList();
  await getMealList();
})

</script>
<style scoped lang="scss">

</style>