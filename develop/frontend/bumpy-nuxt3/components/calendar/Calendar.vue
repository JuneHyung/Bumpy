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

const calendar = ref(null) ;
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

const clickPrevYear = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).subtract(1, 'year').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().prevYear();
  calendar.value.getApi().setOption('events', weightStore.getCalendarList)
}
const clickPrevMonth = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).subtract(1, 'month').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().prev();
  calendar.value.getApi().setOption('events', weightStore.getCalendarList)
}
const clickNextYear = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).add(1, 'year').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().nextYear();
  calendar.value.getApi().setOption('events', weightStore.getCalendarList)
}
const clickNextMonth = () =>{
  const focusDate = weightStore.getFocusDate;
  const newDate = dayjs(focusDate).add(1, 'month').format('YYYY-MM-DD');
  weightStore.setFocusDate(newDate);
  calendar.value.getApi().next();
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