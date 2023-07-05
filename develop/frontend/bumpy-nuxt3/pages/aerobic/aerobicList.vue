<template>
  <main class="content-layout aerobic-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="aerobic-list-box content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">{{ aerobicStore.getFocusDate }}</h3>
        <button @click="moveEdit" v-if="editFlag" class="short-filled-button edit-button">Edit</button>
      </div>
      <ActivityList type="square" listType="aerobic" :list="aerobicList"></ActivityList>
    </div>
    <div class="content-wrap-box bp-mt-xl aerobic-calendar">
      <Calendar @focusDate="getFocusDate" type="aerobic"></Calendar>
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
import { readAerobicCalendarList, readAerobicList } from '~~/api/aerobic/aerobic';
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
  const focusDate = aerobicStore.getFocusDate;
  try {
    const { data, error } = await readAerobicList({ stdDate: focusDate });
    if(error.value !== null){
      setErrorMessage(error.value)
    }else if(data.value!==null){
      const list = data.value.data
      aerobicList.value = list;
    }
  } catch (e) {
    setErrorMessage(e)
  }
}

// calendarList 조회
const getCalendarList = async () =>{
  const focusDate = aerobicStore.getFocusDate;
  try{
    const {data, error} = await readAerobicCalendarList({stdDate: focusDate});
    if(error.value !== null){
      setErrorMessage(error.value)
    }else if(data.value!==null){
      const list = data.value.data
      .map((el, i)=> {
        const keys = Object.keys(el);
        const key = keys[0];
        return  {title: el[key], date: key}
      })
      aerobicStore.setCalendarlist(list);
    }
  }catch(e){
    setErrorMessage(e)
  }
}

const moveEdit = () => {
  aerobicStore.resetSelectItem()
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