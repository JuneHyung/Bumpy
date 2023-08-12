import dayjs from 'dayjs';
import {defineStore} from 'pinia';
import { setErrorMessage } from '~~/api/alert/message';
import { getGrassInfo } from '~~/api/main';
import { fetchUserPasswordCheck } from '~~/api/user/user';
import { DateListFlag } from '~~/types/calendar';
import { GrassInfoResponseBody } from '~~/types/main';
export const useUserStore = defineStore('user-store',()=>{
  const isPass = ref(false);
  const userName = ref('');
  const dayList= [ 'Sun','Mon','Thu','Wen','Thu','Fri','Sat' ]
  const monthList = ['Jan','Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
  // grassCalendar
  const thisDate = ref('');
  const displayDate = ref('');
  const dateList = ref(Array.from({length: 6}, ()=>Array.from({length:7},()=>{return {date: '', active: false, visible: false}})));

  const checkPassword = async (body: any) =>{
    try{
      const {data, error} = await fetchUserPasswordCheck(body)
      if(error.value!==null) setErrorMessage('비밀번호를 다시 확인해주세요');
    }catch(e){
      setErrorMessage(e)
    }
  }

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
  const updatedDate = await updateThisDate(flag);
  setThisDate(updatedDate);
  const thisDate = await getThisDate();
  const year = dayjs(thisDate).year(); // year
  const month = dayjs(thisDate).month()+1 < 10 ? `0${dayjs(thisDate).month()+1}` : dayjs(thisDate).month()+1; // month

  try{
    const {data, error} = await getGrassInfo({stdDate: thisDate})
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const list = data.value as GrassInfoResponseBody;
      setDateList(list.data)
    }
  }catch(e){
    setErrorMessage(e);
  }
  setDisplayDate(`${year} ${monthList[Number(month)-1]}`);
}



  const getIsPass = () => isPass.value;
  const getUserName = () => userName.value;
  const getDayList = () => dayList;
  const getThisDate = () => thisDate.value;
  const getDisplayDate = () => displayDate.value;
  const getDateList = () => dateList.value;
  
  const setThisDate = (value: string) => thisDate.value = value;
  const setDisplayDate = (value: string) => displayDate.value = value;
  const setDateList = (value: any) => dateList.value = value;
  const setIsPass = (value:boolean) => isPass.value = value;
  const setUserName = (value: string) => userName.value = value;
  return {
    checkPassword, 
    updateThisDate,

    getGrassCalendarInfo,

    getIsPass, 
    getUserName,
    getDayList,
    getThisDate,
    getDisplayDate,
    getDateList,
    setIsPass,
    setUserName,
    setThisDate,
    setDisplayDate,
    setDateList,


  }
})