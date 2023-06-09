<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="load-wrap-box">
        <p>불러오기</p>
        <LoadList></LoadList>
      </label>
      <label class="aerobicEdit-input-label bp-mt-sm">
        <span class="bp-mr-sm">이름</span>
        <TextInput :data="form.name"></TextInput>
      </label>
      <div class="aerobicEdit-input-label-wrap">
        <template v-for="(list, idx) in numberList" :key="idx">
          <div class="aerobicEdit-input-label">
            <template v-for="(item, nIdx) in list" :key="nIdx">
              <label class="number-input-wrap">
                <span class="bp-mr-sm">{{ item.label }}</span>
                <div class="number-input">
                  <NumberInput :data="form[item.key]"></NumberInput>
                </div>
              </label>
            </template>
          </div>
        </template>
      </div>
      <label>
        <p>메모</p>
        <textarea></textarea>
      </label>
      <div class="aerobicEdit-button-wrap">
        <button type="button" class="short-ghost-button" @click="cancelAerobicEdit">취소</button>
        <button type="button" class="short-ghost-button bp-mx-sm" @click="resetAerobicItem">초기화</button>
        <button type="button" class="short-filled-button" @click="saveAerobicItem" v-if="editFlag">저장</button>
        <button type="button" class="short-filled-button" @click="modifyAerobicItem" v-else>수정</button>
      </div>
    </form>
  </main>
</template>
<script setup>
import LoadList from '~/components/list/LoadList.vue';
import TextInput from '~/components/form/TextInput.vue';
import NumberInput from '~/components/form/NumberInput.vue';

import {useCommonStore} from '~/store/common'
import { setErrorMessage } from '~~/api/alert/message';
import { useWeightStore } from '~~/store/weight';
const commonStore = useCommonStore();
const weightStore = useWeightStore();
const router = useRouter();
const editFlag = computed(()=>weightStore.getSelectItem.seq===undefined)
const info = { name: '벤치프레스' };
const form = ref({
  name: { value: '', placeholder: '잠온다' },
  kcal: {value:''}, 
  startIncline: {value:''}, 
  startSpeed: {value:''}, 
  time: {value:''}, 
  endIncline: {value:''}, 
  endSpeed: {value:''}, 
});

const numberList = [
  [
    { key: 'kcal', label: 'Kcal', },
    { key: 'startIncline', label: '시작 Incline', },
    { key: 'startSpeed', label: '시작 Speed', },
  ],
  [
    { key: 'time', label: 'Time', },
    { key: 'endIncline', label: '종료 Incline', },
    { key: 'endSpeed', label: '종료 Speed', },
  ],
];

const makeBody = () =>{
  const result = {
    stdDate: weightStore.getFocusDate,
    // seq: seq,
    seq: 1,
    name: form.value.name.value,
    weightStart: form.value.weightStart.value,
    weightEnd: form.value.weightEnd.value,
    repsStart: form.value.repsStart.value,
    repsEnd: form.value.repsEnd.value,
    pollWeight: form.value.pollWeight.value,
    setReps: form.value.setReps.value,
    measure: form.value.measure.value,
    // memo: form.value.memo.value,
    // picture: form.value.picture.value,
  }
  return result;
}
// 저장 버튼
const saveAerobicItem = async () =>{
  const body = makeBody()
  console.log(body)
  try{
    const {data, error} = await createCardioItem(body)
    if(error.value!==null){
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    }else if(data.value !== null){
      setMessage(data.value.message);
      router.push({path: '/aerobic/aerobicList'})
    }
  }catch (e){
    setErrorMessage(e);
  }
}

// 수정 버튼
const modifyAerobicItem = async () =>{
  const body = makeBody()
  // console.log(body)
  // try{
  //   const {data, error} = await updateCardioItem(body)
  //   if(error.value!==null){
  //     const errorMessage = error.value?.data.message;
  //     setErrorMessage(errorMessage);
  //   }else if(data.value !== null){
  //     setMessage(data.value.message);
  //     router.push({path: '/aerobic/aerobicList'})
  //   }
  // }catch (e){
  //   setErrorMessage(e);
  // }
}
// 초기화 버튼
const resetAerobicItem = () =>{
  const keys = Object.keys(form.value);
  for(let i=0;i<keys.length;i++){
    const key = keys[i];
    form.value[key].value = '';
  }
}

// 취소 버튼
const cancelAerobicEdit = () =>{
  router.back();
}
definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
