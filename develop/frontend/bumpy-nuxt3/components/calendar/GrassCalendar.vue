<template>
  <table class="grass-calendar">
    <colgroup>
      <template v-for="() in 7">
        <col width="28" height="40" align="center" />
      </template>
    </colgroup>
    <thead>
      <tr>
        <td align="center" class="arrow-button" @click="userStore.getGrassCalendarInfo('minus')">&lt</td>
        <td colspan="5" align="center" class="this-date">{{userStore.getDisplayDate()}}</td>
        <td align="center" class="arrow-button" @click="userStore.getGrassCalendarInfo('plus')">&gt</td>
      </tr>
      <tr>
        <template v-for="(day, dayIdx) in userStore.getDayList()" :key="dayIdx">
          <td align="center">{{ day }}</td>
        </template>
      </tr>
    </thead>
    <tbody class="t-body">
      <template v-for="(line, idx) in userStore.getDateList()" :key="idx">
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
import { onMounted} from 'vue';
import { useUserStore } from '~~/store/user';
const userStore = useUserStore();

onMounted(async ()=>{
  await userStore.getGrassCalendarInfo('default')
})
</script>
<style scoped lang="scss">

</style>