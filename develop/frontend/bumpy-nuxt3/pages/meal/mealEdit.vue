<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="mealEdit-input-label photo-wrap-box bp-mt-sm">
        <p class="bp-mb-sm">사진 및 비디오</p>
        <FileUploader :list="form.picture"></FileUploader>
      </label>
      <div class="mealEdit-input-wrap-box">
        <div class="mealEdit-input-label-wrap">
          <template v-for="(list, idx) in numberList" :key="idx">
            <div class="mealEdit-input-label">
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
        <div class="food-list-wrap-box">
          <FoodList :list="form.food.value" @remove="removeItem" @plus="plusItem"></FoodList>
        </div>
      </div>

      <label>
        <p>메모</p>
        <TextareaInput :data="form.memo"></TextareaInput>
      </label>
      <div class="mealEdit-button-wrap">
        <button type="button" class="short-ghost-button" @click="cancelMealEdit">취소</button>
        <button type="button" class="short-ghost-button bp-mx-sm" @click="resetSelectItem">초기화</button>
        <button type="button" class="short-filled-button" @click="saveMealItem" v-if="editFlag">저장</button>
        <button type="button" class="short-filled-button" @click="modifyMealItem" v-else>수정</button>
      </div>
    </form>
  </main>
</template>
<script setup lang="ts">
import TextInput from '~~/components/form/TextInput.vue';
import TextareaInput from '~~/components/form/TextareaInput.vue';
import { useMealStore } from '~~/store/meal';
import { useCommonStore } from '~~/store/common';
import FoodList from '~~/components/list/FoodList.vue';
import { MealFormData, MealItemRequestBody} from '~~/types/meal';
import FileUploader from '~~/components/form/FileUploader.vue'

definePageMeta({
  layout: 'main-layout',
});

const commonStore = useCommonStore();
const mealStore = useMealStore();
const router = useRouter();
const editFlag = computed(()=>mealStore.getSelectItem().seq===undefined);

const form: Ref<MealFormData> = ref({
  name: { value: '' },
  time: {   },
  kcal: {   },
  water: {   },
  food: {value: []},
  memo: {},
  picture: {value:[]}
});

const numberList = [
  [
    { key: 'name', label: '이름'},
    { key: 'time', label: 'Time'},
    { key: 'kcal', label: 'Kcal'},
    { key: 'water', label: 'Water'},
  ],
];

const makeBody = () =>{
  const foodList = form.value.food?.value.map(item=> item.value);
  const result: MealItemRequestBody = {
    stdDate: mealStore.getFocusDate() === null || mealStore.getFocusDate().length===0 ? commonStore.getToday() : mealStore.getFocusDate(),
    name: form.value.name.value as string,
    time: form.value.time?.value as string,
    kcal: form.value.kcal?.value as string,
    water: form.value.water?.value as string,
    food: foodList as string[],
    memo: form.value.memo?.value,
    picture: form.value.picture?.value,
  }
  if(mealStore.getSelectItem().seq){
    result.seq= mealStore.getSelectItem().seq
  }
  return result;
}

const plusItem = () =>{
  form.value.food.value.push({value: ''})
}
const removeItem = (idx: number) => {
  const arr = form.value.food.value.slice();
  arr.splice(idx, 1);
  form.value.food.value = arr;
}

// 저장 버튼
const saveMealItem = async () =>{
  const body = makeBody()
  mealStore.postMealItem(body);
  router.push({name: "meal-mealList"})
}

// 수정 버튼
const modifyMealItem = async () =>{
  const body = makeBody()
  mealStore.putMealItem(body);
  router.push({name: "meal-mealList"});
}

// 초기화 버튼
const resetSelectItem = () =>{
  const keys = Object.keys(form.value);
  for(let i=0;i<keys.length;i++){
    const key = keys[i] as keyof MealFormData;
    form.value[key].value = '';
  }
}

// 취소 버튼
const cancelMealEdit = () =>{
  router.back();
}

const initSelectItem = async () =>{
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = mealStore.getSelectItem()[key];
    if(key==='food'){
      const result = mealStore.getSelectItem()[key].map(el=>{return{value: el}});
      form.value[key].value = result;
    }
  }
}

onMounted(async () => {
  if (!editFlag.value) {
    await initSelectItem();
  } else {
    await mealStore.resetSelectItem();
  }
});

</script>
<style scoped lang="scss"></style>
