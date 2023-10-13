import dayjs from "dayjs";
import _ from "lodash";
import { defineStore } from "pinia";
import { setErrorMessage, setMessage } from "~~/api/alert/message";
import { getWeightActivityForMain, getWeightChartInfoForMain } from "~~/api/main";
import { createWeightItem, deleteWeightItem, getWeightYoutubeList, readWeightCalendarList, readWeightItem, readWeightList, updateWeightItem } from "~~/api/weight/weight";
import { CommonCalendarData, YoutubeList } from "~~/types/common";
import { MainWeightChartData, MainWeightChartInfo } from "~~/types/main";
import { Weight, WeightList, WeightRemoveRequestParams, WeightRequestBody } from "~~/types/weight";

export const useWeightStore = defineStore("weight-store", () => {
  const focusDate = ref("");
  const isToday = ref(true);
  const activityList: Ref<WeightList> = ref([]);
  const calendarList: Ref<CommonCalendarData[]> = ref([]);
  const selectItem: Ref<Weight> = ref({
    seq: "",
    name: "",
    weightStart: "",
    weightEnd: "",
    repsStart: "",
    repsEnd: "",
    pollWeight: "",
    setReps: "",
    measure: "",
    memo: "",
    stdDate: "",
    picture: [],
  });

  const selectYoutubeList: Ref<YoutubeList> = ref([]);

  const mainWeightDate: Ref<string> = ref("");
  const lastWeightList: Ref<WeightList> = ref([]);

  const mainWeightInfo: Ref<MainWeightChartInfo> = ref({
    myBest: "",
    reps: "",
    sets: "",
    monthAverage: "",
  });
  const mainWeightChartInfo: Ref<MainWeightChartData> = ref({
    series: [],
    xAxis: [],
  });
  const resetAllData = async () =>{
    await setFocusDate('');
    await setIsToday();
    await setActivityList([]);
    await setCalendarlist([]);
    await setSelectItem({
      seq: "",
      name: "",
      weightStart: "",
      weightEnd: "",
      repsStart: "",
      repsEnd: "",
      pollWeight: "",
      setReps: "",
      measure: "",
      memo: "",
      stdDate: "",
      picture: [],
    })
    selectYoutubeList.value = [];
    await setMainWeightDate('')
    await setLastWeightList([])
    await setMainWeightInfo({
      myBest: "",
      reps: "",
      sets: "",
      monthAverage: "",
    });
    await setMainWeightChartInfo({
      series:[],
      xAxis:[]
    })
  }
  // dispatch
  const getLastWeightActivityInfo = async () => {
    try {
      const { data, error } = await getWeightActivityForMain();
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value !== null && Array.isArray(data.value.data)) {
        const list = data.value.data;
        setLastWeightList(list);
        setMainWeightDate(list[0].stdDate as string);
      } else {
        setLastWeightList([]);
        setMainWeightDate("");
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const getWeightChartInfo = async (name: string) => {
    try {
      const { data, error } = await getWeightChartInfoForMain({ name: name });
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value !== null) {
        const { myBest, monthAverage, reps, sets, xAxis, series } = data.value.data;
        const weightInfoResult: MainWeightChartInfo = { myBest, monthAverage, reps, sets };
        const weightChartInfoResult = { xAxis, series };
        const infoKeys = Object.keys(weightInfoResult);
        for (let i = 0; i < infoKeys.length; i++) {
          const key = infoKeys[i] as keyof MainWeightChartInfo;
          mainWeightInfo.value[key] = weightInfoResult[key];
        }
        mainWeightChartInfo.value.xAxis = weightChartInfoResult.xAxis;
        mainWeightChartInfo.value.series = weightChartInfoResult.series;
      }else{
        mainWeightChartInfo.value.xAxis = [];
        mainWeightChartInfo.value.series = [];
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const getYoutubeList = async (keyword: string) => {
    try {
      const { data, error } = await getWeightYoutubeList({ keyword });
      if (error.value !== null) setErrorMessage(error.value);
      else if (data.value !== null && Array.isArray(data.value.data)) {
        const result = data.value.data;
        selectYoutubeList.value = result;
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };
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
      } else if (data.value?.data !== null && data.value?.data !== undefined) {
        const list = data.value?.data;
        setCalendarlist(list);
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const getSelectItemByStdDateSeq = async (stdDate: string, seq: string) => {
    try {
      const params = {
        stdDate,
        seq,
      };
      const { data, error } = await readWeightItem(params);
      if (error.value !== null) {
        setErrorMessage(error.value);
      } else if (data.value !== null) {
        setSelectItem(data.value.data);
      } else {
        resetSelectItem();
      }
    } catch (e) {
      setErrorMessage(e);
    }
  };

  const postWeightItem = async (body: WeightRequestBody) => {
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
  };

  const putWeightItem = async (body: WeightRequestBody) => {
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
  };

  const removeWeightItem = async () => {
    try {
      const params: WeightRemoveRequestParams = {
        stdDate: focusDate.value,
        seq: selectItem.value.seq,
      };
      const { data, error } = await deleteWeightItem(params);
      if (error.value !== null) {
        await setErrorMessage(error.value?.message);
      } else if (data.value !== null) {
        const message = data.value.message;
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
    } else activityList.value = [];
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
    selectItem.value = {
      seq: "",
      name: "",
      weightStart: "",
      weightEnd: "",
      repsStart: "",
      repsEnd: "",
      pollWeight: "",
      setReps: "",
      measure: "",
      memo: "",
      stdDate: "",
      picture: [],
    };
  };

  const getMainWeightDate = () => mainWeightDate.value;
  const getLastWeightList = () => lastWeightList.value;
  const getMainWeightInfo = () => mainWeightInfo.value;
  const getMainWeightChartInfo = () => mainWeightChartInfo.value;

  const setMainWeightDate = (value: string) => (mainWeightDate.value = value);
  const setLastWeightList = (value: WeightList) => (lastWeightList.value = value);
  const setMainWeightInfo = (value: MainWeightChartInfo) => (mainWeightInfo.value = value);
  const setMainWeightChartInfo = (value: MainWeightChartData) => (mainWeightChartInfo.value = value);

  const getFocusDate = (): string => focusDate.value;
  const getIsToday = () => isToday.value;
  const getActivityList = () => activityList.value;
  const getCalendarList = () => calendarList.value;
  const getSelectItem = () => selectItem.value;
  const getSelectYoutubeList = () => selectYoutubeList.value;

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
    getMainWeightChartInfo,
    getYoutubeList,
    getSelectYoutubeList,
    resetAllData,
  };
});
