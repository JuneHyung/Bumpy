import dayjs from 'dayjs';
import _ from 'lodash';
import {defineStore} from 'pinia';
import { Aerobic } from '~~/types/aerobic';

export const useAerobicStore = defineStore({
  id:'aerobic-store',
  state:()=>{
    return {
      focusDate: '',
      isToday: true,
      calendarList: [],
      selectItem: {} as Aerobic,
    }
  },
  actions:{
    setFocusDate(date: string | Date){
      this.focusDate= dayjs(date).format('YYYY-MM-DD');
      this.setIsToday();
    },
    setIsToday(){
      this.isToday = this.focusDate===dayjs().format('YYYY-MM-DD');
    },
    setCalendarlist(list: any){
      if(list !== undefined){
        this.calendarList = list.slice();
      }
    },
    setSelectItem(item: Aerobic) {
      this.selectItem = _.cloneDeep(item);
    },
    resetSelectItem(){
      this.selectItem = {};
    }
  },
  getters:{
    getFocusDate: (state) => state.focusDate,
    getIsToday: (state) => state.isToday,
    getCalendarList: (state) => state.calendarList,
    getSelectItem: (state) => state.selectItem,
  }
})