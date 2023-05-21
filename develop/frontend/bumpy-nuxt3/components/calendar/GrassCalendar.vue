<template>
  <table class="grass-calendar">
    <colgroup>
      <template v-for="() in 7">
        <col width="28" height="40" align="center">
      </template>
    </colgroup>
    <thead>
      <tr>
        <td align="center" class="arrow-button" @click="makeDateList('minus')">&lt</td>
        <td colspan="5" align="center" class="this-date">{{displayDate}}</td>
        <td align="center" class="arrow-button" @click="makeDateList('plus')">&gt</td>
      </tr>
      <tr>
        <template v-for="(day, dayIdx) in dayList" :key="dayIdx">
          <td align="center">{{ day }}</td>
        </template>
      </tr>
    </thead>
    <tbody class="t-body">
      <template v-for="(line, idx) in dateList" :key="idx">
        <tr>
          <template v-for="(day, dIdx) in line" :key="dIdx">
            <td align="center">
              <div class="day-box" :class="{'hidden-box': !day.flag, 'active-box': checkActiveDay(day.date)}"></div>
            </td>
          </template>
        </tr>
      </template>
    </tbody>
  </table>
</template>
<script setup lang="ts">
import { ref, onMounted} from 'vue';
import dayjs from 'dayjs';
import {DateListFlag} from '~~/types/calendar';
const props = defineProps({
  activeList:{type: Array},
});
const thisDate = ref('');
const displayDate = ref('');
const dayList= [ 'Sun','Mon','Thu','Wen','Thu','Fri','Sat' ]
const monthList = ['Jan','Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
const dateList = ref(Array.from({length: 6}, ()=>Array.from({length:7},()=>{return {date: '', flag: false}})));

const updateThisDate = (flag: DateListFlag) =>{
  switch(flag){
    case 'default':
      return dayjs().format('YYYY-MM-DD');
    case 'plus':
      return dayjs(thisDate.value).add(1, 'M').format('YYYY-MM-DD');
    case 'minus':
      return dayjs(thisDate.value).subtract(1, 'M').format('YYYY-MM-DD');
  }
}
const makeDateList = (flag: DateListFlag) => {
  thisDate.value = updateThisDate(flag);
  const year = dayjs(thisDate.value).year(); // year
  const month = dayjs(thisDate.value).month()+1 < 10 ? `0${dayjs(thisDate.value).month()+1}` : dayjs(thisDate.value).month()+1; // month
  const first = `${year}-${month}-01`; // 첫째 날
  const firstDay = dayjs(first).day(); // 첫쨰 날 요일
  const last = dayjs(thisDate.value).endOf('month').format('YYYY-MM-DD') // 마지막 날
  const lastDate = dayjs(last).date(); // 마지막날 일수.
  dateList.value = Array.from({length: 6}, ()=>Array.from({length:7},()=>{return {date: '', flag: false}})); // 초기화
  
  let curIdx = firstDay; // 시작 위치
  let cnt = 0; // 반복 횟수
  for(let i=0;i<6;i++){
    for(let j=curIdx;j<7;j++){
      dateList.value[i][j] = {date:`${dayjs(first).add(cnt,'d').format('YYYY-MM-DD')}`, flag:true};
      cnt++;
      if(j===6) curIdx = 0;
      if(cnt===lastDate) break;
    }
    if(cnt===lastDate) break;
  }

  displayDate.value = `${year} ${monthList[Number(month)-1]}`
}

const checkActiveDay = (day:string) => {
  if(props.activeList!==undefined && props.activeList.length!==0) return props.activeList.includes(day);
}
onMounted(()=>{
  makeDateList('default')
})
</script>
<style scoped lang="scss">

</style>