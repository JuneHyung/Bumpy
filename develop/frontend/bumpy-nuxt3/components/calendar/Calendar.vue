<template>
  <div>
    <FullCalendar ref="calendar" :options="calendarOptions" class="list-calendar"></FullCalendar>
  </div>
</template>
<script setup lang="ts">
import {Ref, ref, onMounted} from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
// import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import dayjs from 'dayjs';
import { useWeightStore } from '~~/store/weight';
import { readWeightCalendarList } from '~~/api/weight/weight';
import { setErrorMessage } from '~~/api/alert/message';
const calendar = ref(null);
const weightStore = useWeightStore();
interface EventListItem{
  title:string,
  date: string,
}
type EventList = EventListItem[];

interface Props {
  list?: EventList;
}
const props = defineProps<Props>();
const emits= defineEmits(['focusDate']);
const focusDate = (v: any) =>{
  emits('focusDate', dayjs(v.date).format('YYYY-MM-DD'))
}

const getCalendarList = async () =>{
  weightStore.setCalendarlist([{ title: '운동 01', date: '2023-06-05' },
  { title: '운동 02', date: '2023-06-05' },
  { title: '운동 03', date: '2023-06-05' },])
  // try{
  //   const {data, error} = await readWeightCalendarList();
  //   if(error.value !== null){

  //   }else if(data.value!==null){
  //     weightStore.setCalendarlist(data);
  //   }
  // }catch(e){
  //   setErrorMessage(e)
  // }
}


const clickPrevYear = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).subtract(1, 'year').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().prevYear();
  // getCalendarList()
  weightStore.setCalendarlist([{ title: '운동 01', date: '2022-06-05' },
  { title: '운동 02', date: '2022-06-05' },
  { title: '운동 03', date: '2022-06-05' },])
  calendar.value.getApi().setOption('events', weightStore.getCalendarList)
}
const clickPrevMonth = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).subtract(1, 'month').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().prev();
  weightStore.setCalendarlist([{ title: '운동 05', date: '2022-05-05' },
  { title: '운동 06', date: '2022-05-15' },
  { title: '운동 07', date: '2022-05-25' },
  { title: '운동 08', date: '2022-05-11' },])
  calendar.value.getApi().setOption('events', weightStore.getCalendarList)
}
const clickNextYear = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).add(1, 'year').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().nextYear();
  weightStore.setCalendarlist([{ title: '운동 01', date: '2024-06-05' },
  { title: '운동 02', date: '2024-06-05' },
  { title: '운동 03', date: '2024-06-05' },])
  calendar.value.getApi().setOption('events', weightStore.getCalendarList)
}
const clickNextMonth = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).add(1, 'month').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().next();
  weightStore.setCalendarlist([{ title: '운동 05', date: '2024-07-05' },
  { title: '운동 06', date: '2024-07-15' },
  { title: '운동 07', date: '2024-07-25' },
  { title: '운동 08', date: '2024-07-11' },])
  calendar.value.getApi().setOption('events', weightStore.getCalendarList)
}
const clickToday = () => {
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs().format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().today();
}
const calendarOptions = ref({
  plugins: [
    dayGridPlugin,
    // timeGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
  customButtons:{
    prevYear:{
      click: ()=> clickPrevYear()
    },
    prev: {
      click: ()=>clickPrevMonth()
    },
    next:{
      click: ()=>clickNextMonth()
    },
    nextYear:{
      click:()=>clickNextYear()
    },
    today:{
      text: 'Today',
      click:()=>clickToday()
    }
  },
  headerToolbar: {
    left: 'prevYear prev',
    center: 'title',
    right:'next nextYear',
  },

  footerToolbar:{
    left:'',
    center: 'today',
    right:'',
  },
  initialView: 'dayGridMonth', // alternatively, use the `events` setting to fetch from a feed
  selectable: true,
  events: weightStore.getCalendarList,
  dayMaxEvents: 3,
  contentHeight: 500,
  dateClick: focusDate,
});

</script>
<style scoped>


</style>