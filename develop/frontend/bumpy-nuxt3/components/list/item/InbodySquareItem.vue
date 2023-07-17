<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ info.stdDate }} Inbody</p>
    <div class="bp-mt-md bp-mb-lg">
      <p class="bp-my-md">
        <span>{{ info.score }} 점</span>
      </p>
      <p class="bp-my-md">
        <span>{{ info.height }} cm</span> / <span>{{ info.weight }} kg</span>
      </p>
      
      <p class="bp-my-md">
        <span> 근육량 : {{ info.muscle }}</span>
      </p>
      <p class="bp-my-md">
        <span> bmi : {{ info.bmi }}</span>
      </p>
      <p class="bp-my-md">
        <span> fatPercent : {{ info.fatRate }}</span>
      </p>
    </div>
  </li>
</template>
<script setup lang="ts">
import { setErrorMessage } from '~~/api/alert/message';
import { readInbodyItem } from '~~/api/inbody/inbody';
import { useInbodyStore } from '~~/store/inbody';
import { Inbody } from '~~/types/inbody';

interface Props {
  info: Inbody;
}
const props = defineProps<Props>();
const router=  useRouter();
const inbodyStore = useInbodyStore();
const getDetailItem = async () =>{
  const params = {
    stdDate: inbodyStore.getFocusDate,
  }
  try{
    const { data, error } = await readInbodyItem(params)
    if(error.value!==null){
      setErrorMessage(error.value.message)
    }else if(data.value!==null){
      inbodyStore.setSelectItem(data.value.data)
    }
  } catch(e){
    setErrorMessage(e)
  }
}

const moveDetail = async () =>{
  await getDetailItem();
  await router.push({path: 'inbodyDetail'});
}

</script>
<style lang="scss" scoped></style>
