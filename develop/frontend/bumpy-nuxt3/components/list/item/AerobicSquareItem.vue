<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p class="bp-my-md">
        <span>Time : {{ info.time }} m</span> / <span>Kcal : {{ info.kcal }} kcal</span>
      </p>
      <p class="bp-my-md">
        Incline : <span>{{ info.inclineStart }}</span> ~ <span>{{ info.inclineEnd }}</span>
      </p>
      <p class="bp-my-md">
        Speed : <span>{{ info.speedStart }}</span> ~ <span>{{ info.speedEnd }}</span>
      </p>
    </div>
    <p class="ellipsis-3 item-memo">{{ info.memo }}</p>
  </li>
</template>
<script setup lang="ts">
import { useAerobicStore } from '~~/store/aerobic';
import { Aerobic } from '~~/types/aerobic';
interface Props {
  info: Aerobic;
}
const props = defineProps<Props>();
const router= useRouter();
const aerobicStore = useAerobicStore();
const getDetailItem = async () =>{
  const params = {
    stdDate: aerobicStore.focusDate,
    seq: props.info.seq,
  }
  aerobicStore.setSelectItem(props.info);
  // try{
  //   const { data, error } = await readCardioItem(params)
  //   if(error.value!==null){
  //     setErrorMessage(error.value.message)
  //   }else if(data.value!==null){
  //     console.log(data.value)
  //      aerobicStore.setSelectItem(data.value)
  //   }
  // } catch(e){
  //   setErrorMessage(e)
  // }
}
const moveDetail = async () => {
  await getDetailItem();
  await router.push({ path: 'aerobicDetail' });
};
</script>
<style lang="scss" scoped></style>
