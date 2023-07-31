import dayjs from 'dayjs';
import {defineStore} from 'pinia';
export const useCommonStore = defineStore('common-store',()=>{
  const today = ref('');

  const setToday = () =>{
    today.value= dayjs().format('YYYY-MM-DD');
  }
  const getToday = () => today.value
    return {
      setToday,
      getToday,
    }
})