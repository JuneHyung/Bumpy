<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="mealEdit-input-label photo-wrap-box bp-mt-sm">
        <p class="bp-mb-sm">사진 및 비디오</p>
        <div class="photo-wrap"></div>
      </label>
      <div class="mealEdit-input-wrap-box">
        <div class="mealEdit-input-label-wrap">
          <template v-for="(list, idx) in numberList" :key="idx">
            <div class="mealEdit-input-label">
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
        <div class="food-list-wrap-box">
          <FoodList :list="form.food.value" @remove="removeItem"></FoodList>
        </div>
      </div>

      <label>
        <p>메모</p>
        <textarea></textarea>
      </label>
      <div class="mealEdit-button-wrap">
        <button class="short-ghost-button" @click="cancelMealEdit">취소</button>
        <button class="short-ghost-button bp-mx-sm" @click="resetMealItem">초기화</button>
        <button class="short-filled-button" @click="saveMealItem" v-if="editFlag">저장</button>
        <button class="short-filled-button" @click="modifyMealItem" v-else>수정</button>
      </div>
    </form>
  </main>
</template>
<script setup>
import LoadList from '~/components/list/LoadList.vue';
import TextInput from '~/components/form/TextInput.vue';
import NumberInput from '~/components/form/NumberInput.vue';
import { useMealStore } from '~~/store/meal';
import { useCommonStore } from '~~/store/common';
import { setMessage } from '~~/api/alert/message';
import FoodList from '~~/components/list/FoodList.vue';

const commonStore = useCommonStore();
const mealStore = useMealStore();
const router = useRouter();
const editFlag = computed(()=>mealStore.getSelectItem.seq===undefined);
const form = ref({
  name: { value: '' },
  order: { value: '',  },
  time: { value: '',  },
  kcal: { value: '',  },
  water: { value: '',  },
  food: {value: ['닭가슴살01', '닭가슴살02', '닭가슴살03'],}
});

const numberList = [
  [
    { key: 'name', label: '이름'},
    { key: 'order', label: '차례'},
    { key: 'time', label: 'Time'},
    { key: 'kcal', label: 'Kcal'},
    { key: 'water', label: 'Water'},
  ],
];


const makeBody = () =>{
  const result = {
    stdDate: mealStore.getFocusDate,
    // seq: seq,
    seq: 1,
    name: form.value.name.value,
    order: form.value.order.value,
    time: form.value.time.value,
    kcal: form.value.kcal.value,
    water: form.value.water.value,
    // memo: form.value.memo.value,
    // picture: form.value.picture.value,
  }
  return result;
}


const removeItem = (idx) => {
  const arr = form.value.food.value.slice();
  arr.splice(idx, 1);
  form.value.food.value = arr;
}

// 저장 버튼
const saveMealItem = async () =>{
  const body = makeBody()
  console.log(body)
  try{
    const {data, error} = await createMealItem(body)
    if(error.value!==null){
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    }else if(data.value !== null){
      setMessage(data.value.message);
      router.push({path: '/meal/mealList'});
    }
  }catch (e){
    setErrorMessage(e);
  }
}

// 수정 버튼
const modifyMealItem = async () =>{
  const body = makeBody()
  // console.log(body)
  // try{
  //   const {data, error} = await updateMealItem(body)
  //   if(error.value!==null){
  //     const errorMessage = error.value?.data.message;
  //     setErrorMessage(errorMessage);
  //   }else if(data.value !== null){
  //     setMessage(data.value.message);
  //     router.push({path: '/meal/mealList'});
  //   }
  // }catch (e){
  //   setErrorMessage(e);
  // }
}

// 초기화 버튼
const resetMealItem = () =>{
  const keys = Object.keys(form.value);
  for(let i=0;i<keys.length;i++){
    const key = keys[i];
    form.value[key].value = '';
  }
}

// 취소 버튼
const cancelMealEdit = () =>{
  router.back();
}


onMounted(()=>{
  
})

definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
