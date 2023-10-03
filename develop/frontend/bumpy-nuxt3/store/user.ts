import dayjs from 'dayjs';
import _ from 'lodash';
import {defineStore} from 'pinia';
import { setErrorMessage, setMessage } from '~~/api/alert/message';
import { getGrassInfo } from '~~/api/main';
import { deleteUserInfo, fetchUserPasswordCheck, fetchUserPasswordVerifyCheck, getUserPageInfo, putUserInfo } from '~~/api/user/user';
import { DateListFlag } from '~~/types/calendar';
import { GrassInfoItem, GrassInfoList, GrassInfoResponseBody } from '~~/types/main';
import { UserUpdateRequestBody, passwordChkRequest, UserPageInfo } from '~~/types/user';
export const useUserStore = defineStore('user-store',()=>{
  const isPass = ref(false);
  const userName = ref('');
  const dayList= [ 'Sun','Mon','Thu','Wen','Thu','Fri','Sat' ]
  const monthList = ['Jan','Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
  // grassCalendar
  const thisDate = ref('');
  const displayDate = ref('');
  const defaultDateList:GrassInfoItem[][] = Array.from({length: 6}, ()=>Array.from({length:7},():GrassInfoItem=>{return {date: '', active: false, visible: false}}));
  const dateList = ref<GrassInfoList>(defaultDateList);

  const userPageData = ref<UserPageInfo>(
    {
      userId: '',
      password: '',
      passwordChk: '',
      username: '',
      email: '',
      birth: '',
      gender: '',
      phoneNumber: '',
      phoneFirst: '',
      phoneSecond: '',
      phoneThird: '',
      zipCode: '',
      address: '',
      addressDetail: '',
    }
  );

  const fetchUserPageInfo = async () => {
    try{
      const {data, error} = await getUserPageInfo();
      if(error.value!==null) setErrorMessage(error.value);
      else if(data.value!==null){
        const result: UserPageInfo = _.cloneDeep(data.value.data);
        if(result.phoneNumber !==undefined){
          const phoneNumber= result.phoneNumber.split('-')
          result.phoneFirst = phoneNumber[0]
          result.phoneSecond = phoneNumber[1]
          result.phoneThird = phoneNumber[2]
        }
        setUserPageInfo(result)
      }
    }catch(e){ setErrorMessage(e) }
  }

  const deleteUserPageData = async () =>{
    try{
      const {data, error} = await deleteUserInfo()
    }catch(e){
      setErrorMessage(e);
    }
  }

  const updateUserPageData = async (body: UserUpdateRequestBody)=>{
    try{
      const {data, error} = await putUserInfo(body)
  
      if(error.value!==null) setErrorMessage(error.value);
      if(data.value!==null){ 
        setMessage(data.value.message);
      }
    }catch(e){ setErrorMessage(e)}
  }

  //-------------------------------------------------------------------------
  const checkPasswordVerify = async (body: passwordChkRequest) =>{
    try{
      const {data, error} = await fetchUserPasswordVerifyCheck(body)
      if(error.value!==null) setErrorMessage(error.value.data.message);
      else if(data.value!==null) {
        if(data.value.message==="OK"){
        setMessage('사용 가능한 비밀번호 입니다.'); 
        return true;
        }else {
          setErrorMessage(data.value.message);
        }
      }
    }catch(e){
      setErrorMessage(e)
    }
    return false;
  }
  const checkPassword = async (body: passwordChkRequest) =>{
    try{
      const {data, error} = await fetchUserPasswordCheck(body)
      if(error.value!==null) setErrorMessage("비밀번호를 다시 확인해주세요.");
    }catch(e){
      setErrorMessage(e)
    }
    return false;
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
      const list = data.value.data as GrassInfoList;
      setDateList(list)
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
  const getUserPageData = () => userPageData.value;
  
  const setThisDate = (value: string) => thisDate.value = value;
  const setDisplayDate = (value: string) => displayDate.value = value;
  const setDateList = (value: GrassInfoList) => dateList.value = value;
  const setIsPass = (value:boolean) => isPass.value = value;
  const setUserName = (value: string) => userName.value = value;
  const setUserPageInfo = (value: UserPageInfo) => userPageData.value = value;

  return {
    fetchUserPageInfo,
    checkPassword,
    checkPasswordVerify, 
    updateThisDate,

    getGrassCalendarInfo,

    getIsPass, 
    getUserName,
    getDayList,
    getThisDate,
    getDisplayDate,
    getDateList,
    getUserPageData,

    setIsPass,
    setUserName,
    setThisDate,
    setDisplayDate,
    setDateList,
    setUserPageInfo,

    deleteUserPageData,
    updateUserPageData,
  }
})