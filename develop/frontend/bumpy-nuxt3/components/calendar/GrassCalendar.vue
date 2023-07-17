<template>
  <table class="grass-calendar">
    <colgroup>
      <template v-for="() in 7">
        <col width="28" height="40" align="center">
      </template>
    </colgroup>
    <thead>
      <tr>
        <td align="center" class="arrow-button" @click="getGrassCalendarInfo('minus')">&lt</td>
        <td colspan="5" align="center" class="this-date">{{displayDate}}</td>
        <td align="center" class="arrow-button" @click="getGrassCalendarInfo('plus')">&gt</td>
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
          <template v-for="(day, dIdx) in line" :key="day.date">
            <td align="center">
              <div class="day-box" :class="{'hidden-box': !day.visible, 'active-box': day.active}"></div>
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
import { getGrassInfo } from '~~/api/main';
import { setErrorMessage } from '~~/api/alert/message';
import { GrassInfoResponseBody, GrassInfoItem } from '~~/types/main';
import { FetchResult } from 'nuxt/app';

const thisDate = ref('');
const displayDate = ref('');
const dayList= [ 'Sun','Mon','Thu','Wen','Thu','Fri','Sat' ]
const monthList = ['Jan','Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
const dateList = ref(Array.from({length: 6}, ()=>Array.from({length:7},()=>{return {date: '', active: false, visible: false}})));

const updateThisDate = async (flag: DateListFlag) =>{
  switch(flag){
    case 'default':
      return dayjs().format('YYYY-MM-DD');
    case 'plus':
      return dayjs(thisDate.value).add(1, 'M').format('YYYY-MM-DD');
    case 'minus':
      return dayjs(thisDate.value).subtract(1, 'M').format('YYYY-MM-DD');
  }
}

const getGrassCalendarInfo = async (flag: DateListFlag) => {
  thisDate.value = await updateThisDate(flag);
  const year = dayjs(thisDate.value).year(); // year
  const month = dayjs(thisDate.value).month()+1 < 10 ? `0${dayjs(thisDate.value).month()+1}` : dayjs(thisDate.value).month()+1; // month
  const first = `${year}-${month}-01`; // 첫째 날
  const firstDay = dayjs(first).day(); // 첫쨰 날 요일
  const last = dayjs(thisDate.value).endOf('month').format('YYYY-MM-DD') // 마지막 날
  const lastDate = dayjs(last).date(); // 마지막날 일수.

  try{
    const {data, error} = await getGrassInfo({stdDate: thisDate.value})
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const list = data.value as GrassInfoResponseBody;
      dateList.value = list.data;
    }
  }catch(e){
    setErrorMessage(e);
  }

  displayDate.value = `${year} ${monthList[Number(month)-1]}`
}

onMounted(async ()=>{
  // makeDateList('default')
  await getGrassCalendarInfo('default')
  // await makeDateList('default');

})
</script>
<style scoped lang="scss">

</style>