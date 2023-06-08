<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About My Routine</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ weightStore.getSelectItem.name }}</h2>
      <div class="weight-weightStore.getSelectItem-box">
        <div class="photo-list-wrap">
          <p>image</p>
        </div>
        <div class="weightStore.getSelectItem-list-wrap">
          <p class="bp-mr-sm">
            <span>시작 무게 : {{ weightStore.getSelectItem.weightStart }} kg</span>
            <span>시작 횟수 : {{ weightStore.getSelectItem.repsStart }} reps</span>
          </p>
          <p class="bp-mr-sm">
            <span>종료 무게 : {{ weightStore.getSelectItem.weightEnd }} kg</span>
            <span>종료 횟수 : {{ weightStore.getSelectItem.repsEnd }} reps</span>
          </p>
          <p class="bp-mr-sm">
            <span>봉 무게 : {{ weightStore.getSelectItem.pollWeight }} kg</span>
            <span>세트 횟수 : {{ weightStore.getSelectItem.setReps }} reps</span>
          </p>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <p>Youtube or chart</p>
        </div>
      </div>
      <div class="weightDetail-button-wrap">
        <button class="short-ghost-button" @click="moveWeightList">취소</button>
        <button class="short-filled-button bp-mx-sm" v-if="weightStore.getIsToday" @click="removeWeightItem">삭제</button>
        <button class="short-filled-button" v-if="weightStore.getIsToday">수정</button>
      </div>
    </div>
  </main>
</template>
<script setup>
import { setErrorMessage, setMessage } from '~~/api/alert/message';
import { deleteWeightItem } from '~~/api/weight/weight';
import { useWeightStore } from '~~/store/weight';
const router= useRouter();
const weightStore = useWeightStore();
const removeWeightItem = () =>{
  setMessage('삭제')
  // try{
  //   const body = {
  //     stdDate: weightStore.focusDate,
  //     seq: weightStore.getSelectItem.seq
  //   }
  //   const {data, error} = deleteWeightItem(body);
  //   if(error.value!==null){
  //     setErrorMessage(error.value.messaage)
  //   }else if(data.value!==null){
  //     setMessage('삭제 완료하였습니다.');
  //     moveWeightList();
  //   }
  // }catch(e){
  //   setErrorMessage(e)
  // }
}
const moveWeightList = () =>{
  router.back();
}
definePageMeta({
  layout: 'main-layout',
});
onMounted(()=>{
  console.log(weightStore.getSelectItem)
})
// const weightStore.getSelectItem =ref({})
</script>
