<template>
  <div>
    <FullCalendar ref="calendar" :options="calendarOptions" class="list-calendar"></FullCalendar>
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue";
import FullCalendar from "@fullcalendar/vue3";
import dayGridPlugin from "@fullcalendar/daygrid";
import interactionPlugin from "@fullcalendar/interaction";
import dayjs from "dayjs";
import { useWeightStore } from "~~/store/weight";
import { useAerobicStore } from "~~/store/aerobic";
import { useMealStore } from "~~/store/meal";
import { useInbodyStore } from "~~/store/inbody";

const calendar = ref();

interface Props {
  type?: String;
}

const switchStore = () => {
  switch (props.type) {
    case "weight":
      return useWeightStore();
    case "aerobic":
      return useAerobicStore();
    case "meal":
      return useMealStore();
    case "inbody":
      return useInbodyStore();
    default:
      return useWeightStore();
  }
};
const store = switchStore();
const props = defineProps<Props>();
const emits = defineEmits(["focusDate"]);

const focusDate = (v: any) => {
  emits("focusDate", dayjs(v.date).format("YYYY-MM-DD"));
};


const clickDateShift = (shiftType: string) => {
  const focusDate = store.getFocusDate();
  let newDate = "";
  const api = calendar.value.getApi();
  switch (shiftType) {
    case "prevYear":
      newDate = dayjs(focusDate).subtract(1, "year").format("YYYY-MM-DD");
      api.prevYear();
      break;
    case "prevMonth":
      newDate = dayjs(focusDate).subtract(1, "month").format("YYYY-MM-DD");
      api.prev();
      break;
    case "nextMonth":
      newDate = dayjs(focusDate).add(1, "month").format("YYYY-MM-DD");
      api.next();
      break;
    case "nextYear":
      newDate = dayjs(focusDate).add(1, "year").format("YYYY-MM-DD");
      api.nextYear();
      break;
    case 'today':
      newDate = dayjs().format('YYYY-MM-DD');
      api.today();
      break;
  }
  api.setOption("events", store.getCalendarList());
  store.setFocusDate(newDate);
};

const calendarOptions = ref({
  plugins: [
    dayGridPlugin,
    interactionPlugin, // needed for dateClick
  ],
  customButtons: {
    prevYear: {
      click: () => clickDateShift("prevYear"),
    },
    prev: {
      click: () => clickDateShift("prevMonth"),
    },
    next: {
      click: () => clickDateShift("nextMonth"),
    },
    nextYear: {
      click: () => clickDateShift("nextYear"),
    },
    today: {
      text: "Today",
      click: () => clickDateShift('today'),
    },
  },
  headerToolbar: {
    left: "prevYear prev",
    center: "title",
    right: "next nextYear",
  },

  footerToolbar: {
    left: "",
    center: "today",
    right: "",
  },
  initialView: "dayGridMonth", // alternatively, use the `events` setting to fetch from a feed
  selectable: true,
  events: [],
  dayMaxEvents: 3,
  contentHeight: 500,
  dateClick: focusDate,
});

const initCalendarEvents = () =>{
  const api = calendar.value.getApi();
  api.setOptions("events", store.getCalendarList())
}
onMounted(()=>{
  initCalendarEvents();
})
</script>
