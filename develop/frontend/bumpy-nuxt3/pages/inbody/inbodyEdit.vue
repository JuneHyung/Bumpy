<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box inbodyEdit-wrap-box">
      <div class="inbodyEdit-content-wrap">
        <label class="inbodyEdit-input-label photo-wrap-box bp-mt-sm">
          <p class="bp-mb-sm">사진 및 비디오</p>
          <div class="photo-wrap"></div>
        </label>
        <div class="inbodyEdit-input-wrap-box">
          <div class="inbodyEdit-input-label-wrap">
            <template v-for="(list, idx) in numberList" :key="idx">
              <div class="inbodyEdit-input-label">
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
        </div>
      </div>
      <div class="inbodyEdit-button-wrap">
        <button type="button" class="short-ghost-button" @click="cancelInbodyEdit">취소</button>
        <button type="button" class="short-ghost-button bp-mx-sm" @click="resetInbodyItem">초기화</button>
        <button type="button" class="short-filled-button" @click="saveInbodyItem" v-if="editFlag">저장</button>
        <button type="button" class="short-filled-button" @click="modifyInbodyItem" v-else>수정</button>
      </div>
    </form>
  </main>
</template>
<script setup lang="ts">
import LoadList from '~/components/list/LoadList.vue';
import TextInput from '~/components/form/TextInput.vue';
import NumberInput from '~/components/form/NumberInput.vue';
import { useInbodyStore } from '~~/store/inbody';
import { useCommonStore } from '~~/store/common';
import { createInbodyItem, updateInbodyItem } from '~~/api/inbody/inbody';
import { setErrorMessage, setMessage } from '~~/api/alert/message';
const info = { name: '벤치프레스' };
const commonStore = useCommonStore();
const inbodyStore = useInbodyStore();
const router = useRouter();
const editFlag = computed(()=>inbodyStore.getSelectItem.height===undefined)
const form = ref({
  name: { value: ''},
  height: {value:'',},
  weight: {value:'',},
  kcal: {value:''},
  muscle: {value:'',},
  fat: {value:'',},
  score: {value:'',},
  bmi: {value:'',},
  fatRate: {value:'',},
});

const numberList = ref([
  [
    // { key: 'date', label: '검사날짜'},
    { key: 'height', label: '키'},
    { key: 'weight', label: '체중'},
    { key: 'muscle', label: '골격근량'},
    { key: 'fat', label: '체지방량'},
    { key: 'score', label: '인바디점수'},
    { key: 'bmi', label: 'BMI'},
    { key: 'fatRate', label: '체지방률'},
  ],
]);


const makeBody = () =>{
  const result = {
    stdDate: inbodyStore.getFocusDate,
    height: form.value.height.value,
    weight: form.value.weight.value,
    muscle: form.value.muscle.value,
    fat: form.value.fat.value,
    score: form.value.score.value,
    bmi: form.value.bmi.value,
    fatRate: form.value.fatRate.value,
    // memo: form.value.memo.value,
    // picture: form.value.picture.value,
  }
  return result;
}

// 저장 버튼
const saveInbodyItem = async () =>{
  const body = makeBody()
  try{
    const {data, error} = await createInbodyItem(body)
    if(error.value!==null){
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    }else if(data.value !== null){
      setMessage(data.value.message);
      router.push({path: '/inbody/inbodyList'})
    }
  }catch (e){
    setErrorMessage(e);
  }
}

// 수정 버튼
const modifyInbodyItem = async () =>{
  const body = makeBody()
  // console.log(body)
  try{
    const {data, error} = await updateInbodyItem(body)
    if(error.value!==null){
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    }else if(data.value !== null){
      setMessage(data.value.message);
      router.push({path: '/inbody/inbodyList'})
    }
  }catch (e){
    setErrorMessage(e);
  }
}

// 초기화 버튼
const resetInbodyItem = () =>{
  const keys = Object.keys(form.value);
  for(let i=0;i<keys.length;i++){
    const key = keys[i];
    form.value[key].value = '';
  }
}

const initSelectItem = () =>{
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = inbodyStore.getSelectItem[key];
  }
}

// 취소 버튼
const cancelInbodyEdit = () =>{
  router.back();
}

onMounted(()=>{
  if (!editFlag.value) {
    initSelectItem();
  } else {
    inbodyStore.resetSelectItem();
  }
})

definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
