import dayjs from 'dayjs';
import _ from 'lodash';
import {defineStore} from 'pinia';
import { setErrorMessage, setMessage } from '~~/api/alert/message';
import { createInbodyItem, deleteInbodyItem, getInbodyYoutubeList, readInbodyActivityList, readInbodyCalendarList, readInbodyItem, updateInbodyItem } from '~~/api/inbody/inbody';
import { CommonCalendarData, YoutubeList } from '~~/types/common';
import { Inbody, InbodyItemRequestBody, InbodyList, InbodyRequestParam } from '~~/types/inbody';

export const useInbodyStore = defineStore('inbody-store',()=>{
  const focusDate = ref("");
  const isToday = ref(true);
  const activityList: Ref<InbodyList> = ref([]);
  const calendarList: Ref<CommonCalendarData[]> = ref([]);
  const selectItem: Ref<Inbody> = ref({
    height: '',
    weight: '',
    age: '',
    muscle: '',
    fat: '',
    score: '',
    bmi: '',
    fatRate: '',
    stdDate: '',
    picture: [],  
  });
  const selectYoutubeList: Ref<YoutubeList> = ref([]);
  // dispatch
  const getYoutubeList = async () => {
    try{
      const {data, error} = await getInbodyYoutubeList();
      if(error.value!==null) setErrorMessage(error.value);
      else if(data.value!==null && Array.isArray(data.value.data)){
        const result = data.value.data;
        selectYoutubeList.value = result;
      }
    }
    catch(e){
      setErrorMessage(e);
    }
  }


  const getActivityListByStdDate = async (stdDate: string) => {
    try {
      const { data, error } = await readInbodyActivityList({ stdDate: stdDate });
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value !== null && Array.isArray(data.value.data)) {
        const list = data.value.data;
        setActivityList(list);
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const getCalendarListByStdDate = async (stdDate: string) => {
    try {
      const { data, error } = await readInbodyCalendarList({ stdDate: stdDate });
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value?.data !== null && Array.isArray(data.value.data)) {
        const list = data.value?.data;
        setCalendarlist(list);
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const getSelectItemByStdDateSeq = async (stdDate: string, seq: number) => {
    try {
      const params = {
        stdDate,
        seq,
      };
      const { data, error } = await readInbodyItem(params);
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value?.data !== null && data.value?.data !== undefined) {
        setSelectItem(data.value.data);
      } else {
        resetSelectItem();
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };
  const postInbodyItem = async (body: InbodyItemRequestBody) =>{
    try{
      const {data, error} = await createInbodyItem(body)
      if(error.value!==null){
        const errorMessage = error.value?.data.message;
        setErrorMessage(errorMessage);
      }else if(data.value !== null){
        setMessage(data.value.message);
      }
    }catch (e){
      setErrorMessage(e);
    }
  }

  const putInbodyItem = async (body: InbodyItemRequestBody) =>{
    try{
      const {data, error} = await updateInbodyItem(body)
      if(error.value!==null){
        const errorMessage = error.value?.data.message;
        setErrorMessage(errorMessage);
      }else if(data.value !== null){
        setMessage(data.value.message);
      }
    }catch (e){
      setErrorMessage(e);
    }
  }

  const removeInbodyItem = async () => {
    try {
      const params: InbodyRequestParam = {
        stdDate: focusDate.value,
      };
      const { data, error } = await deleteInbodyItem(params);
      if (error.value !== null) {
        await setErrorMessage(error.value?.message);
      } else if (data.value !== null) {
        const message = data.value?.message;
        await setMessage(message);
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  // getter & setter
  const setFocusDate = async (date: string | Date) => {
    focusDate.value = dayjs(date).format("YYYY-MM-DD");
    setIsToday();
  };
  const setIsToday = async () => {
    isToday.value = focusDate.value === dayjs().format("YYYY-MM-DD");
  };
  const setActivityList = async (list: InbodyList) => {
    if (list !== undefined) {
      activityList.value = list;
    } else activityList.value = [];
  };
  const setCalendarlist = async (list: CommonCalendarData[]) => {
    if (list !== undefined) {
      calendarList.value = list.slice();
    }
  };
  const setSelectItem = async (item: Inbody) => {
    selectItem.value = _.cloneDeep(item);
  };
  const resetSelectItem = async () => {
    selectItem.value = {
      height: '',
      weight: '',
      age: '',
      muscle: '',
      fat: '',
      score: '',
      bmi: '',
      fatRate: '',
      stdDate: '',
      picture: [],  
    };
  };

  const getFocusDate = (): string => focusDate.value;
  const getIsToday = () => isToday.value;
  const getActivityList = () => activityList.value;
  const getCalendarList = () => calendarList.value;
  const getSelectItem = () => selectItem.value;
  const getSelectYoutubeList = () => selectYoutubeList.value;
  return {
    getActivityListByStdDate,
    getCalendarListByStdDate,
    getSelectItemByStdDateSeq,
    postInbodyItem,
    putInbodyItem,
    removeInbodyItem,

    setFocusDate,
    setIsToday,
    setActivityList,
    setCalendarlist,
    setSelectItem,
    resetSelectItem,

    getFocusDate,
    getIsToday,
    getActivityList,
    getCalendarList,
    getSelectItem,
    getYoutubeList,
    getSelectYoutubeList
  };
})