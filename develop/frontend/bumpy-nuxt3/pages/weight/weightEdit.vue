<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="load-wrap-box">
        <p>불러오기</p>
        <LoadList :list="loadList" @initName="initName"></LoadList>
      </label>
      <label class="weightEdit-input-label bp-mt-sm">
        <span class="bp-mr-sm">이름</span>
        <TextInput :data="form.name"></TextInput>
      </label>
      <label class="weightEdit-input-label photo-wrap-box bp-mt-sm">
        <p class="bp-mb-sm">사진 및 비디오</p>
        <FileUploader></FileUploader>
      </label>
      <div class="weightEdit-input-label-wrap">
        <template v-for="(list, idx) in numberList" :key="idx">
          <div class="weightEdit-input-label">
            <template v-for="(item, nIdx) in list" :key="nIdx">
              <label class="number-input-wrap bp-mr-sm">
                <span class="number-label bp-mr-sm">{{ item.label }}</span>
                <div class="number-input">
                  <TextInput :data="form[item.key]"></TextInput>
                </div>
              </label>
            </template>
          </div>
        </template>
      </div>
      <div class="weightEdit-input-label-wrap">
        <div class="weightEdit-input-label">
          <label class="number-input-wrap">
            <span class="number-label">단위</span>
            <div class="number-input">
              <TextInput :data="form.measure"></TextInput>
            </div>
          </label>
        </div>
      </div>
      <div class="weightEdit-input-label-wrap">
        <div class="weightEdit-input-label">
          <label class="number-input-wrap">
            <span class="number-label">메모</span>
            <div class="number-input">
              <TextareaInput :data="form.memo"></TextareaInput>
            </div>
          </label>
        </div>
      </div>
      <div class="weightEdit-button-wrap">
        <button type="button" class="short-ghost-button" @click="cancelWeightEdit">취소</button>
        <button type="button" class="short-ghost-button bp-mx-sm" @click="resetWeightItem">초기화</button>
        <button type="button" class="short-filled-button" @click="saveWeightItem" v-if="editFlag">저장</button>
        <button type="button" class="short-filled-button" @click="modifyWeightItem" v-else>수정</button>
      </div>
    </form>
  </main>
</template>
<script setup lang="ts">
import LoadList from "~~/components/list/LoadList.vue";
import TextInput from "~~/components/form/TextInput.vue";
import TextareaInput from "~~/components/form/TextareaInput.vue";
// import NumberInput from "~/components/form/NumberInput.vue";
import FileUploader from "~~/components/form/FileUploader.vue";
import { useCommonStore } from "~/store/common";
import { setErrorMessage, setMessage } from "~~/api/alert/message";
import { useWeightStore } from "~~/store/weight";
import { createWeightItem, readFavoritWeightList, updateWeightItem } from "~~/api/weight/weight";
import {WeightFormData, WeightRequestBody} from '~~/types/weight';

const commonStore = useCommonStore();
const weightStore = useWeightStore();
const router = useRouter();
const editFlag = computed(() => weightStore.getSelectItem.seq === undefined);
const info = { name: "벤치프레스" };
const loadList = ref([]);
const form:Ref<WeightFormData> = ref({
  name: { value: "", placeholder: "잠온다" },
  weightStart: {value: "",  },
  repsStart: {value: "",  },
  weightEnd: { value: "", },
  repsEnd: {value: "",  },
  pollWeight: {value: "",  },
  setReps: {value: "",  },
  measure: {value: "",  },
  memo: { value: "" },
  // picture: {value: '', placeholder: ''},
});

const numberList = ref([
  [
    { key: "weightStart", label: "시작 무게", type: "number" },
    { key: "weightEnd", label: "종료 무게", type: "number" },
    { key: "pollWeight", label: "봉 무게", type: "number" },
  ],
  [
    { key: "repsStart", label: "시작 횟수", type: "number" },
    { key: "repsEnd", label: "종료 횟수", type: "number" },
    { key: "setReps", label: "세트 횟수", type: "number" },
  ],
]);

const measure = ref({ key: "memo", label: "메모", type: "textarea" });
const memo = ref({});
const makeBody = () => {
  const result: WeightRequestBody = {
    stdDate: weightStore.getFocusDate === null || weightStore.getFocusDate.length === 0 ? commonStore.getToday : weightStore.getFocusDate,
    seq: 2,
    name: form.value.name.value,
    weightStart: form.value.weightStart.value,
    weightEnd: form.value.weightEnd.value,
    repsStart: form.value.repsStart.value,
    repsEnd: form.value.repsEnd.value,
    pollWeight: form.value.pollWeight.value,
    setReps: form.value.setReps.value,
    measure: form.value.measure.value,
    memo: form.value.memo.value,
    // picture: form.value.picture.value,
  };
  return result;
};

// 저장 버튼
const saveWeightItem = async () => {
  const body = makeBody();
  try {
    const { data, error } = await createWeightItem(body);
    if (error.value !== null) {
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    } else if (data.value !== null) {
      setMessage(data.value.message);
      router.push({ path: "/weight/weightList" });
    }
  } catch (e) {
    setErrorMessage(e);
  }
};

// 수정 버튼
const modifyWeightItem = async () => {
  const body = makeBody();

  try {
    const { data, error } = await updateWeightItem(body);
    if (error.value !== null) {
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    } else if (data.value !== null) {
      setMessage(data.value.message);
      router.push({ path: "/weight/weightList" });
    }
  } catch (e) {
    setErrorMessage(e);
  }
};

// 초기화 버튼
const resetWeightItem = () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = "";
  }
};

// 취소 버튼
const cancelWeightEdit = () => {
  router.back();
};

const initSelectedItem = async () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = weightStore.getSelectItem[key];
  }
};

// 등록된 리스트 조회
const getFavroiteWeightList = async () =>{
  try{
    const {data, error} = await readFavoritWeightList();
    if(error.value!==null) setErrorMessage(error.value)
    else if(data.value!==null){
      const list = data.value.data;
      console.log(list)
      loadList.value = list;
    }
  }catch(e){
    setErrorMessage(e)
  }
}

const initName = (name: string) =>{
  form.value.name.value = name;
}
onMounted(async () => {
  await getFavroiteWeightList();
  if (!editFlag.value) {
    await initSelectedItem();
  } else {
    await weightStore.resetSelectItem();
  }
});

definePageMeta({
  layout: "main-layout",
});
</script>
<style scoped lang="scss"></style>
