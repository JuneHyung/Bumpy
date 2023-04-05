<template>
  <main class="content-layout weight-list-wrap-box">
    <h1 class="content-title">Your Activity List</h1>
    <div class="content-wrap-box bp-my-lg">
      <div class="title-wrap-box">
        <h3 class="content-title" @click="moveDetail">2022-02-01</h3>
        <button @click="moveEdit">Edit</button>
      </div>
      <SquareList type="square" :list="testList"></SquareList>
    </div>
    <div class="content-wrap-box bp-mt-xl">
      <FullCalendar :options="calendarOptions"></FullCalendar>
    </div>
  </main>
</template>
<script setup>
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
// import { INITIAL_EVENTS, createEventId } from './event-utils';
import SquareList from '~~/components/list/SquareList.vue';
import { useRouter } from 'vue-router';
const router = useRouter();

const calendarOptions = {
  plugins: [
    dayGridPlugin,
    timeGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
  headerToolbar: {
    left: 'prev,next today',
    center: 'title',
    right: 'dayGridMonth,timeGridWeek,timeGridDay',
  },
  initialView: 'dayGridMonth', // alternatively, use the `events` setting to fetch from a feed
  editable: true,
  selectable: true,
  selectMirror: true,
  dayMaxEvents: true,
  weekends: true,
  // select: this.handleDateSelect,
  // eventClick: this.handleEventClick,
  // eventsSet: this.handleEvents,
  /* you can update a remote database when these fire:
  eventAdd:
  eventChange:
  eventRemove:
  */
};
definePageMeta({
  layout: 'main-layout',
});
const moveDetail = () => {
  router.push({ path: 'weightDetail' });
};
const moveEdit = () => {
  router.push({ path: 'weightEdit' });
};
const testList = [
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  {
    name: '벤치 프레스',
    startWeight: 10,
    endWeight: 30,
    barWeight: 20,
    startReps: 12,
    endReps: 8,
    setReps: 5,
    memo: '메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모메모',
  },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
  // { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps: 5, memo: '메모메모' },
];
</script>
<style scoped lang="scss">
.weight-list-wrap-box {
  .title-wrap-box {
    display: flex;
    justify-content: space-between;
  }
}
</style>
