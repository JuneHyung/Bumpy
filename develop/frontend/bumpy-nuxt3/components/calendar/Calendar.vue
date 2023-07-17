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
import { setErrorMessage } from '~~/api/alert/message';
import { useAerobicStore } from '~~/store/aerobic';
import { useMealStore } from '~~/store/meal';
import { useInbodyStore } from '~~/store/inbody';

const calendar = ref(null) ;
const weightStore = useWeightStore();
const aerobicStore = useAerobicStore();
const mealStore = useMealStore();
const inbodyStore = useInbodyStore();
interface EventListItem{
  title:string,
  date: string,
}
type EventList = EventListItem[];

interface Props {
  type?: String; 
  list?: EventList;
}
const store = computed(()=>props.type==='weight' ? weightStore : props.type==='aerobic' ? aerobicStore : props.type==='meal' ? mealStore : props.type==='inbody' ? inbodyStore : weightStore);
const props = defineProps<Props>();
const emits= defineEmits(['focusDate']);
const focusDate = (v: any) =>{
  emits('focusDate', dayjs(v.date).format('YYYY-MM-DD'))
}

const clickPrevYear = () =>{
  const focusDate = store.value.getFocusDate;
  const newDate = dayjs(focusDate).subtract(1, 'year').format('YYYY-MM-DD');
  store.value.setFocusDate(newDate);
  calendar.value.getApi().prevYear();
  calendar.value.getApi().setOption('events', store.value.getCalendarList)
}
const clickPrevMonth = () =>{
  const focusDate = store.value.getFocusDate;
  const newDate = dayjs(focusDate).subtract(1, 'month').format('YYYY-MM-DD');
  store.value.setFocusDate(newDate);
  calendar.value.getApi().prev();
  calendar.value.getApi().setOption('events', store.value.getCalendarList)
}
const clickNextYear = () =>{
  const focusDate = store.value.getFocusDate;
  const newDate = dayjs(focusDate).add(1, 'year').format('YYYY-MM-DD');
  store.value.setFocusDate(newDate);
  calendar.value.getApi().nextYear();
  calendar.value.getApi().setOption('events', store.value.getCalendarList)
}
const clickNextMonth = () =>{
  const focusDate = store.value.getFocusDate;
  const newDate = dayjs(focusDate).add(1, 'month').format('YYYY-MM-DD');
  store.value.setFocusDate(newDate);
  calendar.value.getApi().next();
  calendar.value.getApi().setOption('events', store.value.getCalendarList)
}
const clickToday = () => {
  const focusDate = store.value.getFocusDate;
  const newDate = dayjs().format('YYYY-MM-DD');
  store.value.setFocusDate(newDate);
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
  events: store.value.getCalendarList,
  dayMaxEvents: 3,
  contentHeight: 500,
  dateClick: focusDate,
});

</script>
<style scoped>


</style>