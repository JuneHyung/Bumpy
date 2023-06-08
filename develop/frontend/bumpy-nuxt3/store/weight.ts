import dayjs from 'dayjs';
import _ from 'lodash';
import {defineStore} from 'pinia';
export const useWeightStore = defineStore({
  id:'weight-store',
  state:()=>{
    return {
      focusDate: '',
      isToday: true,
      calendarList: [],
      selectItem: {},
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
      this.calendarList = list.slice();
    },
    setSelectItem(item: any) {
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