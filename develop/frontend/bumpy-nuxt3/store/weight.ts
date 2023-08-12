import dayjs from "dayjs";
import _ from "lodash";
import { defineStore } from "pinia";
import { setErrorMessage, setMessage } from "~~/api/alert/message";
import { getWeightActivityForMain, getWeightChartInfoForMain } from "~~/api/main";
import { createWeightItem, deleteWeightItem, readWeightCalendarList, readWeightItem, readWeightList, updateWeightItem } from "~~/api/weight/weight";
import { CommonCalendarData } from "~~/types/common";
import { MainWeightChartData, MainWeightChartInfo } from "~~/types/main";
import { Weight, WeightList, WeightRemoveRequestParams } from "~~/types/weight";

export const useWeightStore = defineStore("weight-store", () => {
  const focusDate = ref("");
  const isToday = ref(true);
  const activityList: Ref<WeightList> = ref([]);
  const calendarList:Ref<CommonCalendarData[]> = ref([]);
  const selectItem: Ref<Weight> = ref({});

  const mainWeightDate: Ref<string> = ref('');
  const lastWeightList: Ref<WeightList> = ref([]);
  
  const mainWeightInfo: Ref<MainWeightChartInfo> = ref({
    myBest:'',
    reps:'',
    sets:'',
    monthAverage:'',
  })
  const mainWeightChartInfo: Ref<MainWeightChartData> = ref({
    series: [],
    xAxis: [],
  })

  // dispatch
  const getLastWeightActivityInfo = async () =>{
    try{
        const {data, error} = await getWeightActivityForMain();
        if(error.value!==null){
          setErrorMessage(error.value);
        }else if(data.value!==null){
          const list = data.value.data
          setLastWeightList(list);
          setMainWeightDate(list[0].stdDate as string);
        }
      }catch(e){
        setErrorMessage(e);
      }
    }

    
const getWeightChartInfo = async () =>{
  try{
      const {data, error} = await getWeightChartInfoForMain({name: lastWeightList.value[0].name as string});
      // const {data, error} = await getWeightChartInfoForMain({name: 'TEST-4'});
      if(error.value!==null){
        setErrorMessage(error.value);
      }else if(data.value!==null){
        const result = data.value.data
        const infoKeys = Object.keys(mainWeightInfo.value)
        const chartKeys = Object.keys(mainWeightChartInfo.value)
        for(const key of infoKeys) mainWeightInfo.value[key] = result[key];
        
        mainWeightChartInfo.value.xAxis = result.xAxis;
        mainWeightChartInfo.value.series = result.series;
      }
    }catch(e){
      setErrorMessage(e);
    }
  }
  //--------------
  const getActivityListByStdDate = async (stdDate: string) => {

    try {
      const { data, error } = await readWeightList({ stdDate: stdDate });
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
      const { data, error } = await readWeightCalendarList({ stdDate: stdDate });
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value?.data !== null &&data.value?.data !== undefined ) {
        const list = data.value?.data;
        setCalendarlist(list);
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const getSelectItemByStdDateSeq = async (stdDate: string, seq: number) => {
    try{
      const params = {
        stdDate,
        seq,
      }
      const {data, error} = await readWeightItem(params);
      if(error.value!==null){
        setErrorMessage(error.value)
      }else if(data.value?.data !==null && data.value?.data !==undefined ){
        setSelectItem(data.value.data)
      }else {
        setSelectItem({})
      }
      }catch(e){
        setErrorMessage(e)
      }
  }


  const postWeightItem = async (body: Weight) => {
    try {
      const { data, error } = await createWeightItem(body);
      if (error.value !== null) {
        const errorMessage = error.value?.data.message;
        setErrorMessage(errorMessage);
      } else if (data.value !== null) {
        setMessage(data.value?.message);
      }
    } catch (e) {
      setErrorMessage(e);
    }
  }

  const putWeightItem = async (body: Weight) =>{
    try {
    const { data, error } = await updateWeightItem(body);
    if (error.value !== null) {
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    } else if (data.value !== null) {
      setMessage(data.value?.message);
    }
  } catch (e) {
    setErrorMessage(e);
  }
  }

  const removeWeightItem = async () => {
    try {
      const params: WeightRemoveRequestParams = {
        stdDate: focusDate.value,
        seq: selectItem.value.seq,
      };
      const { data, error } = await deleteWeightItem(params);
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
  const setActivityList = async (list: WeightList) => {
    if (list !== undefined) {
      activityList.value = list;
    }else activityList.value = [];
  };
  const setCalendarlist = async (list: CommonCalendarData[]) => {
    if (list !== undefined) {
      calendarList.value = list.slice();
    }
  };
  const setSelectItem = async (item: Weight) => {
    selectItem.value = _.cloneDeep(item);
  };
  const resetSelectItem = async () => {
    selectItem.value = {};
  };


  const getMainWeightDate = () => mainWeightDate.value;
  const getLastWeightList = () => lastWeightList.value;
  const getMainWeightInfo = () => mainWeightInfo.value;
  const getMainWeightChartInfo = () => mainWeightChartInfo.value;

  const setMainWeightDate = (value: string) => mainWeightDate.value = value;
  const setLastWeightList = (value: WeightList) => lastWeightList.value = value;
  const setMainWeightInfo = (value: MainWeightChartInfo) => mainWeightInfo.value = value;
  const setMainWeightChartInfo = (value: MainWeightChartData) => mainWeightChartInfo.value = value;

  const getFocusDate = (): string => focusDate.value;
  const getIsToday = () => isToday.value;
  const getActivityList = () => activityList.value;
  const getCalendarList = () => calendarList.value;
  const getSelectItem = () => selectItem.value;

  return {
    getLastWeightActivityInfo,
    getWeightChartInfo,


    getActivityListByStdDate,
    getCalendarListByStdDate,
    getSelectItemByStdDateSeq,
    postWeightItem,
    putWeightItem,
    removeWeightItem,

    setFocusDate,
    setIsToday,
    setActivityList,
    setCalendarlist,
    setSelectItem,
    resetSelectItem,
    setMainWeightDate,
    setLastWeightList,
    setMainWeightInfo,
    setMainWeightChartInfo,

    getFocusDate,
    getIsToday,
    getActivityList,
    getCalendarList,
    getSelectItem,
    getMainWeightDate,
  getLastWeightList,
  getMainWeightInfo,
  getMainWeightChartInfo
  }
});
