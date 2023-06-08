<template>
  <div>
    <FullCalendar ref="calendar" :options="calendarOptions"></FullCalendar>
  </div>
</template>
<script setup lang="ts">
import {Ref, ref, onMounted} from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
// import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import dayjs from 'dayjs';
const calendar:Ref  = ref(null);

interface EventListItem{
  title:string,
  date: string,
}
type EventList = EventListItem[];

interface Props {
  list?: EventList;
}
const props = defineProps<Props>();
const emits= defineEmits(['focusDate'])
const focusDate = (v: any) =>{
  emits('focusDate', dayjs(v.date).format('YYYY-MM-DD'))
}
const setDate = (v:any) =>{
  console.log(dayjs(v.date).format('YYYY-MM-DD'))
// console.log(calendar.value.getDate())
}
const calendarOptions = ref({
  plugins: [
    dayGridPlugin,
    // timeGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
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
  // editable: false,
  selectable: true,
  events: props.list,
  dayMaxEvents: 3,
  // contentHeight: 100
  // selectMirror: false,
  // dayMaxEvents: false,
  // weekends: false,
  // select: this.handleDateSelect,
  // eventClick: setDate,
  dateClick: focusDate,
  // eventsSet: handleEvents,
  /* you can update a remote database when these fire:
  eventAdd:
  eventChange:
  eventRemove:
*/});

</script>
<style scoped>


</style>