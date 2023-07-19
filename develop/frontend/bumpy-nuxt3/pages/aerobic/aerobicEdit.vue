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
                  <TextInput :data="form[item.key]"></TextInput>
                </div>
              </label>
            </template>
          </div>
        </template>
      </div>
      <label>
        <p>메모</p>
        <TextareaInput :data="form.memo"></TextareaInput>
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
<script setup lang="ts">
import LoadList from '~~/components/list/LoadList.vue';
import TextInput from '~~/components/form/TextInput.vue';
import TextareaInput from '~~/components/form/TextareaInput.vue';
// import NumberInput from '~/components/form/NumberInput.vue';

import {useCommonStore} from '~/store/common'
import { setErrorMessage, setMessage } from '~~/api/alert/message';
import { createAerobicItem, updateAerobicItem } from '~~/api/aerobic/aerobic';
import { useAerobicStore } from '~~/store/aerobic';
import { AerobicFormData, AerobicRequestBody } from '~~/types/aerobic';
const commonStore = useCommonStore();
const aerobicStore = useAerobicStore();
const router = useRouter();
const editFlag = computed(()=>aerobicStore.getSelectItem.seq===undefined)

const form: Ref<AerobicFormData> = ref({
  name: { value: '', placeholder: '잠온다' },
  kcal: {}, 
  inclineStart: {}, 
  speedStart: {}, 
  time: {}, 
  inclineEnd: {}, 
  speedEnd: {}, 
  memo: {}
});

const numberList = [
  [
    { key: 'inclineStart', label: '시작 Incline', },
    { key: 'speedStart', label: '시작 Speed', },
    { key: 'kcal', label: 'Kcal', },
  ],
  [
    { key: 'inclineEnd', label: '종료 Incline', },
    { key: 'speedEnd', label: '종료 Speed', },
    { key: 'time', label: 'Time', },
  ],
];

const makeBody = () =>{
  const result: AerobicRequestBody = {
    stdDate: aerobicStore.getFocusDate===null || aerobicStore.getFocusDate.length===0 ? commonStore.getToday : aerobicStore.getFocusDate,
    seq: 2,
    name: form.value.name.value,
    kcal: form.value.kcal.value,
    inclineStart: form.value.inclineStart.value,
    speedStart: form.value.speedStart.value,
    time: form.value.time.value,
    inclineEnd: form.value.inclineEnd.value,
    speedEnd: form.value.speedEnd.value,
    memo: form.value.memo.value,
    // picture: form.value.picture.value,
  }
  return result;
}
// 저장 버튼
const saveAerobicItem = async () =>{
  const body = makeBody()
  try{
    const {data, error} = await createAerobicItem(body)
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
  try{
    const {data, error} = await updateAerobicItem(body)
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

const initSelectedItem = () =>{
  const keys = Object.keys(form.value);
  for(let i=0;i<keys.length;i++){
    const key = keys[i];
    form.value[key].value = aerobicStore.getSelectItem[key]
  }
}
onMounted(()=>{
  if(!editFlag.value){
    initSelectedItem()
  }else{
    aerobicStore.resetSelectItem()
  }
})
definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
