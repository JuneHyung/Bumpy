<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p>
        <span>{{ info.weightStart }}{{ info.measure }}</span> ~ <span>{{ info.weightEnd }}{{ info.measure }}</span> / <span>{{ info.pollWeight }}kg</span>
      </p>
      <p>
        <span>{{ info.repsStart }}reps</span> ~ <span>{{ info.repsEnd }}reps</span> / <span>{{ info.setReps }}set</span>
      </p>
    </div>
    <p class="ellipsis-3 item-memo">{{ info.memo }}</p>
  </li>
</template>
<script setup lang="ts">
import { setErrorMessage } from '~~/api/alert/message';
import { readWeightItem } from '~~/api/weight/weight';
import { useWeightStore } from '~~/store/weight';
import { Weight } from '~~/types/weight';

interface Props {
  info: Weight;
}
const props = defineProps<Props>();
const router=  useRouter();
const weightStore = useWeightStore();

const getDetailItem = async () =>{
  try{
  const params = {
    stdDate: weightStore.getFocusDate,
    seq: props.info.seq, 
  }
  const {data, error} = await readWeightItem(params);
  if(error.value!==null){
    setErrorMessage(error.value)
  }else if(data.value.data !==null){
    weightStore.setSelectItem(data.value.data)
  }
  }catch(e){
    setErrorMessage(e)
  }
} 

const moveDetail = async () => {
  await getDetailItem();
  await router.push({ path: 'weightDetail' });
};
</script>
<style lang="scss" scoped></style>
