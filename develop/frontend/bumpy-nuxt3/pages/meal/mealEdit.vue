<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="edit-input-label photo-wrap-box bp-mt-sm">
        <p class="bp-mb-sm">사진 및 비디오</p>
        <FileUploader :list="form.picture" />
      </label>
      <div class="flex bp-my-sm">
        <div class="flex flex-4 bp-my-sm">
          <template v-for="(list, idx) in numberList" :key="idx">
            <div class="edit-input-col">
              <template v-for="(item, nIdx) in list" :key="nIdx">
                <label class="edit-input-label">
                  <span class="edit-label bp-mr-sm">{{ item.label }}</span>
                  <div class="edit-input">
                    <TimeInput :data="form[item.key]" v-if="item.key==='time'" />
                    <TextInput :data="form[item.key as keyof Omit<MealFormData, 'food' | 'picture'>]" v-else /> 
                  </div>
                </label>
              </template>
            </div>
          </template>
        </div>
        <div class="flex flex-6">
          <FoodList :list="form.food.value" @remove="removeItem" @plus="plusItem" />
        </div>
      </div>

      <label class="edit-input-label bp-mb-lg">
        <p class="edit-label bp-mr-sm" style="width:60px;">메모</p>
        <TextareaInput :data="form.memo" />
      </label>
      <div class="edit-button-wrap bp-my-sm">
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
import TimeInput from '~~/components/form/TimeInput.vue';
import { useUserStore } from '~~/store/user';

definePageMeta({
  layout: 'main-layout',
  middleware: 'custom-router-guard'
});

const commonStore = useCommonStore();
const mealStore = useMealStore();
const userStore = useUserStore();
const router = useRouter();
const editFlag = computed(()=>mealStore.getSelectItem().seq==='');

const form: Ref<MealFormData> = ref({
  name: { value: '' },
  time: { value:''},
  kcal: { value:'', isNumber:true, minlength: 0, maxlength: 4 },
  water: { value:'', isNumber:true, minlength: 0, maxlength: 4 },
  food: { value: []},
  memo: {value:'', rows: 10, maxlength: 500},
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
  const request: MealItemRequestBody = {
    stdDate: mealStore.getFocusDate() === null || mealStore.getFocusDate().length===0 ? commonStore.getToday() : mealStore.getFocusDate(),
    name: form.value.name.value ,
    time: form.value.time?.value ,
    kcal: form.value.kcal?.value ,
    water: form.value.water?.value,
    food: foodList,
    memo: form.value.memo?.value,
    picture: form.value.picture?.value,
  }
  if (!editFlag.value) request.seq = mealStore.getSelectItem().seq;
  return request;
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
  await mealStore.postMealItem(body);
  await userStore.getUserInfo();
  await router.push({name: "meal-mealList"})
}

// 수정 버튼
const modifyMealItem = async () =>{
  const body = makeBody()
  await mealStore.putMealItem(body);
  await userStore.getUserInfo();
  await router.push({name: "meal-mealList"});
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
    const key = keys[i] as keyof MealFormData;
    if(key==='food'){
      const result = mealStore.getSelectItem()[key].map(el=>{return{value: el}});
      form.value[key].value = result;
    }else{
      form.value[key].value = mealStore.getSelectItem()[key];
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
