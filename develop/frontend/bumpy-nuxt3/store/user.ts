import dayjs from 'dayjs';
import _ from 'lodash';
import {defineStore} from 'pinia';
import { setErrorMessage, setMessage } from '~~/api/alert/message';
import { getGrassInfo, getUserInfoForMain } from '~~/api/main';
import { deleteUserInfo, fetchUserPasswordCheck, fetchUserPasswordVerifyCheck, getUserPageInfo, putUserInfo } from '~~/api/user/user';
import { DateListFlag } from '~~/types/calendar';
import { DegreeList, MeterList, UserInfoList } from '~~/types/inbody';
import { GrassInfoItem, GrassInfoList, MainUserInbodyData, MainUserInfo } from '~~/types/main';
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

  const userBodyInfo = ref<UserInfoList>([
    {
      key: 'height',
      category: 'Height',
      value: '',
      unit: 'cm',
    },
    {
      key: 'weight',
      category: 'Weight',
      value: '',
      unit: 'kg',
    },
    {
      key: 'age',
      category: 'Age',
      value: '',
      unit: '',
    },
  ])

  const userActivityInfo = ref<UserInfoList>([
    {
      key: 'continuity',
      category: 'Continuity',
      value: '0',
      unit: 'days',
    },
    {
      key: 'lastActive',
      category: 'Last Active',
      value: '',
      unit: '',
    },
    {
      key: 'averageWater',
      category: 'Average Water',
      value: '',
      unit: 'L',
    },
  ])
  const degreeList = ref<DegreeList>([
    {
      name: '표준 이하',
      degree: 'low',
    },
    {
      name: '표준',
      degree: 'middle',
    },
    {
      name: '표준 이상',
      degree: 'high',
    }
  ])
  const userInbodyInfo = ref<MeterList>([
    {
      value: 0,
      max: 100,
      min: 0,
      low: 50,
      high: 75,
      optimum: 100,
      key: 'weight',
      category: '체중',
      unit: 'kg',
    },
    {
      value: 0,
      max: 70,
      min: 0,
      low: 20,
      high: 50,
      optimum: 70,
      key:'muscle',
      category: '골격근량',
      unit: 'kg',
    },
    {
      value: 0,
      max: 100,
      min: 0,
      low: 10,
      high: 30,
      optimum: 100,
      key:'fat',
      category: '체지방량',
      unit: 'kg',
    },
    {
      value: 0,
      max: 100,
      min: 0,
      low: 10,
      high: 40,
      optimum: 100,
      key:'bmi',
      category: 'BMI',
      unit: 'kg/m2',
    },
    {
      value: 0,
      max: 70,
      min: 0,
      low: 10,
      high: 40,
      optimum: 70,
      key: 'fatRate',
      category: '체지방률',
      unit: '%',
    },
  ])
  //---
  const resetAllData = async () =>{
    await setIsPass(false);
    await setUserName('');
    await setThisDate('');
    await setDisplayDate('');
    await setDateList(defaultDateList);
    await setUserPageInfo({
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
    })
  }

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

const initUserInfo = async (list: MainUserInfo) =>{
  setUserName(list.username);
  
  const {height, age, averageWater, continuity, lastActive, inbodyData} = list;
  const bodyInfoData = {height, age};
  const bodyInfoKeys = userBodyInfo.value.map(el=>el.key)
  const userActivityInfoKeys = userActivityInfo.value.map(el=>el.key)
  const userInbodyInfoKeys = userInbodyInfo.value.map(el=>el.key)

  const userActivityInfoData = {averageWater, continuity, lastActive}

  // 신체영역 init
  for(let i=0;i<bodyInfoKeys.length;i++){
    const key = bodyInfoKeys[i] as keyof Pick<MainUserInfo, 'weight' | 'height' | 'age'>;
    if(key==='weight') continue;
    const target = userBodyInfo.value.find(el=>el.key===key);
    if(target){
      target.value = bodyInfoData[key];
    }
  }
  // 활동영역 init
  for(let i=0;i<userActivityInfoKeys.length;i++){
    const key = userActivityInfoKeys[i] as keyof Pick<MainUserInfo, 'averageWater' | 'continuity' | 'lastActive'>;
      const target = userActivityInfo.value.find(el=>el.key===key);
    if(target){
      target.value = userActivityInfoData[key];
    }
  }

  if(inbodyData){
    const {weight, fat, muscle, bmi, fatRate} = inbodyData;
    const target = userBodyInfo.value.find(el=>el.key==='weight');
    if(target){
      target.value = weight;
    }

    const userInbodyInfoData = {weight, fat, muscle, bmi, fatRate};  

    // meter 영역 init
    for(let i=0;i<userInbodyInfoKeys.length;i++){
      const key = userInbodyInfoKeys[i] as keyof MainUserInbodyData;
      const target = userInbodyInfo.value.find(el=>el.key===key);
      if(target){
        target.value = Number(userInbodyInfoData[key]);
      }
    }
  }  
}

const getUserInfo = async () => {
  try{
    const {data, error} = await getUserInfoForMain();
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const list = data.value.data
      await initUserInfo(list);
    }
  }catch(e){
    setErrorMessage(e);
  }
}



  const getIsPass = () => isPass.value;
  const getUserName = () => userName.value;
  const getDayList = () => dayList;
  const getThisDate = () => thisDate.value;
  const getDisplayDate = () => displayDate.value;
  const getDateList = () => dateList.value;
  const getUserPageData = () => userPageData.value;
  
  const getUserBodyInfo = () => userBodyInfo.value;
  const getUserActivityInfo = () => userActivityInfo.value;
  const getDegreeList = () => degreeList.value;
  const getUserInbodyInfo = () => userInbodyInfo.value;

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
    getUserInfo,
    getUserBodyInfo,
    getUserActivityInfo,
    getDegreeList,
    getUserInbodyInfo,

    setIsPass,
    setUserName,
    setThisDate,
    setDisplayDate,
    setDateList,
    setUserPageInfo,

    deleteUserPageData,
    updateUserPageData,
    resetAllData,
  }
})