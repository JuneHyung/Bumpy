<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">About Last Inbody</h1>
    <div class="content-wrap-box">
      <h2 class="content-title">{{ infoName.value }}</h2>
      <div class="inbody-info-box">
        <div class="chart-wrap">
          <p>image</p>
        </div>
        <div class="info-list-wrap">
          <div class="info-item">
            <template v-for="(info, idx) in infoList" :key="idx">
              <p class="bp-mr-sm">
                <template v-for="(item, iIdx) in info" :key="iIdx">
                  <span>{{ item.label }} : {{ inbodyStore.setSelectItem[item.key] }} {{ item.unit }}</span>
                </template>
              </p>
            </template>
          </div>
        </div>
      </div>
      <div class="chart-wrap-box">
        <h3>Youtube or chart</h3>
        <div class="chart-wrap">
          <p>Youtube or chart</p>
        </div>
      </div>
      <div class="inbodyDetail-button-wrap">
        <button class="short-ghost-button" @click="moveInbodyList">취소</button>
        <button class="short-filled-button bp-mx-sm" v-if="inbodyStore.getIsToday" @click="removeInbodyItem">삭제</button>
        <button class="short-filled-button" v-if="inbodyStore.getIsToday">수정</button>
      </div>
    </div>
  </main>
</template>
<script setup>
import { useInbodyStore } from '~~/store/inbody';

const router= useRouter();
const inbodyStore = useInbodyStore();


const removeInbodyItem = () =>{
  setMessage('삭제')
  // try{
  //   const body = {
  //     stdDate: inbodyStore.focusDate,
  //     seq: inbodyStore.getSelectItem.seq
  //   }
  //   const {data, error} = deleteCardioItem(body);
  //   if(error.value!==null){
  //     setErrorMessage(error.value.messaage)
  //   }else if(data.value!==null){
  //     setMessage('삭제 완료하였습니다.');
  //     moveInbodyList();
  //   }
  // }catch(e){
  //   setErrorMessage(e)
  // }
}
const moveInbodyList = () =>{
  router.back();
}

definePageMeta({
  layout: 'main-layout',
});
const infoName = { key: 'name', label: '', value: '준형갓' };
const infoList = [
  [
    { key: 'kcal', label: '검사 날짜', value: '2023-01-05', unit: '' },
    { key: 'time', label: '키', value: 170, unit: 'cm' },
    { key: 'water', label: '체중', value: 67.3, unit: 'kg' },
    { key: 'kcal', label: '골격근량', value: 31.5, unit: 'kg' },
    { key: 'time', label: '체지방량', value: 21.1, unit: 'kg' },
    { key: 'water', label: '인바디 점수', value: 78, unit: '점' },
    { key: 'kcal', label: 'BMI', value: 26.5, unit: 'kg/m2' },
    { key: 'time', label: '체지방률', value: 28.5, unit: '%' },
  ],
];
</script>
