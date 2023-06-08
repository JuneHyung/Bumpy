import dayjs from 'dayjs';
import {defineStore} from 'pinia';
export const useWeightStore = defineStore({
  id:'weight-store',
  state:()=>{
    return {
      focusDate: '',
      isToday: true,
    }
  },
  actions:{
    setFocusDate(date: string | Date){
      this.focusDate= dayjs(date).format('YYYY-MM-DD');
    },
    setIsToday(flag: boolean){
      this.isToday = flag;
    }
  },
  getters:{
    getFocusDate: (state) => state.focusDate,
    getIsToday: (state) => state.isToday
  }
})