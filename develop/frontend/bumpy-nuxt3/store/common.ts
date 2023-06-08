import dayjs from 'dayjs';
import {defineStore} from 'pinia';
export const useCommonStore = defineStore({
  id:'common-store',
  state:()=>{
    return {
      today: ''
    }
  },
  actions:{
    setToday(){
      this.today= dayjs().format('YYYY-MM-DD');
    }
  },
  getters:{
    getToday: (state) => state.today
  }
})