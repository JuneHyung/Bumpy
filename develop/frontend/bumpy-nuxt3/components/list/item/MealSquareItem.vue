<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p class="bp-my-md">
        <span>{{ info.order }}번째 식사</span>
        <span>{{ info.time }} m</span>
      </p>
      <p class="bp-my-md">
        <span>{{ info.kcal }}kcal</span>
      </p>
      <p class="bp-my-md">
        <span> Water : {{ info.water }}L</span>
      </p>
    </div>
    <p class="ellipsis-3 item-memo">{{ info.memo }}</p>
  </li>
</template>
<script setup lang="ts">
import { setErrorMessage } from '~~/api/alert/message';
import { useMealStore } from '~~/store/meal';
import { Meal } from '~~/types/meal';

interface Props {
  info: Meal;
}
const props = defineProps<Props>();
const router=  useRouter();
const mealStore = useMealStore();
const getDetailItem = async () =>{
  const params = {
    stdDate: mealStore.getFocusDate,
    seq: props.info.seq,
  }
  // try{
  //   const { data, error } = await readMealItem(params)
  //   if(error.value!==null){
      // setErrorMessage(error.value.message)
  //   }else if(data.value!==null){
  //     console.log(data.value)
  //      mealStore.setSelectItem(data.value)
  //   }
  // } catch(e){
  //   setErrorMessage(e)
  // }
}

const moveDetail = async () =>{
  await getDetailItem();
  await router.push({path: 'mealDetail'});
}
</script>
<style lang="scss" scoped></style>
