import dayjs from "dayjs";
import _ from "lodash";
import { defineStore } from "pinia";
import { setErrorMessage, setMessage } from "~~/api/alert/message";
import { getMealInfoForMain } from "~~/api/main";
import { createMealItem, deleteMealItem, readMealActivityList, readMealCalendarList, readMealItem, updateMealItem } from "~~/api/meal/meal";
import { CommonCalendarData } from "~~/types/common";
import { Meal, MealItemRequestParam, MealList } from "~~/types/meal";

export const useMealStore = defineStore("meal-store", () => {
  const focusDate = ref("");
  const isToday = ref(true);
  const activityList: Ref<MealList> = ref([]);
  const calendarList: Ref<CommonCalendarData[]> = ref([]);
  const selectItem: Ref<Meal> = ref({});

  const todayMealList:Ref<MealList> = ref([]);

  // dispatch
  const getTodayMealInfo = async () => {
    try{
        const {data, error} = await getMealInfoForMain();
        if(error.value!==null){
          setErrorMessage(error.value);
        }else if(data.value!==null){
          
          const list = data.value.data;
          setTodayMealList(list);
        }
      }catch(e){
        setErrorMessage(e);
      }
    }

  const getActivityListByStdDate = async (stdDate: string) => {
    try {
      const { data, error } = await readMealActivityList({ stdDate: stdDate });
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value !== null) {
        const list = data.value.data;
        setActivityList(list);
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const getCalendarListByStdDate = async (stdDate: string) => {
    try {
      const { data, error } = await readMealCalendarList({ stdDate: stdDate });
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value?.data !== null && data.value?.data !== undefined) {
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
      const { data, error } = await readMealItem(params);
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value?.data !== null && data.value?.data !== undefined) {
        setSelectItem(data.value.data);
      } else {
        setSelectItem({});
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const postMealItem = async (body: Meal) =>{
    try{
      const {data, error} = await createMealItem(body)
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
  const putMealItem = async (body: Meal) =>{
    try{
      const {data, error} = await updateMealItem(body)
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

  const removeMealItem = async () => {
    try {
      const params: MealItemRequestParam = {
        stdDate: focusDate.value,
        seq: selectItem.value.seq,
      };
      const { data, error } = await deleteMealItem(params);
      if (error.value !== null) {
        await setErrorMessage(error.value?.message);
      } else if (data.value !== null && data.value !==undefined) {
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
  const setActivityList = async (list: MealList) => {
    if (list !== undefined) {
      activityList.value = list;
    } else activityList.value = [];
  };
  const setCalendarlist = async (list: CommonCalendarData[]) => {
    if (list !== undefined) {
      calendarList.value = list.slice();
    }
  };
  const setSelectItem = async (item: Meal) => {
    selectItem.value = _.cloneDeep(item);
  };
  const resetSelectItem = async () => {
    selectItem.value = {};
  };
  const setTodayMealList = async (data: any) => todayMealList.value = data;

  const getFocusDate = (): string => focusDate.value;
  const getIsToday = () => isToday.value;
  const getActivityList = () => activityList.value;
  const getCalendarList = () => calendarList.value;
  const getSelectItem = () => selectItem.value;
  const getTodayMealList = () => todayMealList.value;

  return {
    getTodayMealInfo,
    getActivityListByStdDate,
    getCalendarListByStdDate,
    getSelectItemByStdDateSeq,
    postMealItem,
    putMealItem,
    removeMealItem,

    setFocusDate,
    setIsToday,
    setActivityList,
    setCalendarlist,
    setSelectItem,
    setTodayMealList,
    resetSelectItem,

    getFocusDate,
    getIsToday,
    getActivityList,
    getCalendarList,
    getSelectItem,
    getTodayMealList,
  };
});
