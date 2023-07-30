import dayjs from 'dayjs';
import _ from 'lodash';
import {defineStore} from 'pinia';
import { deleteAerobicItem, readAerobicActivityList, readAerobicCalendarList, readAerobicItem } from '~~/api/aerobic/aerobic';
import { setErrorMessage, setMessage } from '~~/api/alert/message';
import { Aerobic, AerobicDeleteRequestParam, AerobicList } from '~~/types/aerobic';
import { CommonCalendarData } from '~~/types/common';

export const useAerobicStore = defineStore('aerobic-store',()=>{
  const focusDate = ref("");
  const isToday = ref(true);
  const activityList: Ref<AerobicList> = ref([]);
  const calendarList: Ref<CommonCalendarData[]> = ref([]);
  const selectItem: Ref<Aerobic> = ref({});

  // dispatch
  const getActivityListByStdDate = async (stdDate: string) => {
    try {
      const { data, error } = await readAerobicActivityList({ stdDate: stdDate });
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
      const { data, error } = await readAerobicCalendarList({ stdDate: stdDate });
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
      const { data, error } = await readAerobicItem(params);
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

  const removeAerobicItem = async () => {
    try {
      const params: AerobicDeleteRequestParam = {
        stdDate: focusDate.value,
        seq: selectItem.value.seq,
      };
      const { data, error } = await deleteAerobicItem(params);
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
  const setActivityList = async (list: AerobicList) => {
    if (list !== undefined) {
      activityList.value = list;
    } else activityList.value = [];
  };
  const setCalendarlist = async (list: CommonCalendarData[]) => {
    if (list !== undefined) {
      calendarList.value = list.slice();
    }
  };
  const setSelectItem = async (item: Aerobic) => {
    selectItem.value = _.cloneDeep(item);
  };
  const resetSelectItem = async () => {
    selectItem.value = {};
  };

  const getFocusDate = (): string => focusDate.value;
  const getIsToday = () => isToday.value;
  const getActivityList = () => activityList.value;
  const getCalendarList = () => calendarList.value;
  const getSelectItem = () => selectItem.value;

  return {
    getActivityListByStdDate,
    getCalendarListByStdDate,
    getSelectItemByStdDateSeq,
    removeAerobicItem,

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
  };
})