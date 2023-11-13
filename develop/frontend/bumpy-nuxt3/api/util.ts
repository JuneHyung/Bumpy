import {  } from 'pinia';
import { useAerobicStore } from '~~/store/aerobic';
import { useInbodyStore } from '~~/store/inbody';
import { useMealStore } from '~~/store/meal';
import { useUserStore } from '~~/store/user';
import { useWeightStore } from '~~/store/weight';
export const validateValue = (value?: string, pattern?: string): boolean => {
  if(value!==undefined && pattern!==undefined){
    const regex = new RegExp(pattern, 'g')
    return regex.test(value);
  }else return false;
}

export const inRange = (value: string, min: number ,max: number): boolean =>{
  const len = value.length;
  return len>=min && len <=max;
}

export const resetAllData = async () =>{
  const aerobicStore = useAerobicStore();
  const weightStore = useWeightStore();
  const inbodyStore = useInbodyStore();
  const mealStore = useMealStore();
  const userStore = useUserStore();
  const stores = [aerobicStore, weightStore, inbodyStore, mealStore, userStore];
  for(let i=0;i<stores.length;i++){
    const curStore = stores[i];
    await curStore.resetAllData();
  }
}