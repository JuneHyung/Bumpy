<template>
  <main class="content-layout">
    <h1 class="content-title bp-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="load-wrap-box">
        <p class="bp-my-sm">불러오기</p>
        <LoadList :list="loadList" @initName="initName"></LoadList>
      </label>
      <label class="edit-input-label bp-my-sm">
        <span class="edit-label bp-mr-sm">이름</span>
        <TextInput :data="form.name" class="edit-input"></TextInput>
      </label>
      <label class="edit-input-label photo-wrap-box bp-my-sm">
        <p class="bp-mb-sm">사진 및 비디오</p>
        <FileUploader :list="form.picture"></FileUploader>
      </label>

      <template v-for="(list, idx) in numberList" :key="idx">
        <div class="edit-input-row bp-my-sm">
          <template v-for="(item, nIdx) in list" :key="nIdx">
            <label class="edit-input-label">
              <span class="edit-label bp-mr-sm">{{ item.label }}</span>
              <div class="edit-input">
                <TextInput :data="(form[item.key as keyof WeightFormData] as InputText)"></TextInput>
              </div>
            </label>
          </template>
        </div>
      </template>

      <label class="edit-input-label bp-my-sm">
        <span class="edit-label bp-mr-sm">단위</span>
        <div class="edit-input">
          <SelectboxInput :data="form.measure"></SelectboxInput>
        </div>
      </label>

      <div class="edit-input-label bp-mb-lg">
        <label class="edit-input-wrap">
          <span class="edit-label bp-mr-sm">메모</span>
          <div class="edit-input">
            <TextareaInput :data="form.memo"></TextareaInput>
          </div>
        </label>
      </div>

      <div class="edit-button-wrap bp-my-sm">
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
import SelectboxInput from "~~/components/form/SelectboxInput.vue";
import FileUploader from "~~/components/form/FileUploader.vue";
import { useCommonStore } from "~/store/common";
import { setErrorMessage } from "~~/api/alert/message";
import { useWeightStore } from "~~/store/weight";
import { readFavoritWeightList } from "~~/api/weight/weight";
import { WeightFormData, WeightRequestBody } from "~~/types/weight";
import _ from "lodash";
import { FavoriteListItem } from "~~/types/common";
import { InputText } from "~~/types/input";

definePageMeta({
  layout: "main-layout",
  middleware: 'custom-router-guard'
});

const commonStore = useCommonStore();
const weightStore = useWeightStore();
const router = useRouter();
const editFlag = computed(() => weightStore.getSelectItem().seq === '');

const loadList:Ref<FavoriteListItem[]> = ref([]);

const form: Ref<WeightFormData> = ref({
  name: { value: "", placeholder: "등록할 운동 이름을 작성해주세요." },
  weightStart: { value: "", isNumber:true, minlength: 0, maxlength: 4, placeholder: "0"},
  repsStart: { value: "", isNumber:true, minlength: 0, maxlength: 4, placeholder: "0" },
  weightEnd: { value: "", isNumber:true, minlength: 0, maxlength: 4, placeholder: "0"},
  repsEnd: { value: "", isNumber:true, minlength: 0, maxlength: 3, placeholder: "0"  },
  pollWeight: { value: "", isNumber:true, minlength: 0, maxlength: 2, placeholder: "0"  },
  setReps: { value: "", isNumber:true, minlength: 0, maxlength: 2, placeholder: "0"  },
  measure: {
    value: "1",
    list: [
      { dtlCd: "1", dtlNm: "kg" },
      { dtlCd: "2", dtlNm: "lb" },
    ],
  },
  memo: { value: "", rows: 10 },
  picture: { value: [] },
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

const makeBody = () => {
  const request: WeightRequestBody = {
    stdDate: weightStore.getFocusDate() === null || weightStore.getFocusDate().length === 0 ? commonStore.getToday() : weightStore.getFocusDate(),
    name: form.value.name.value,
    weightStart: form.value.weightStart.value,
    weightEnd: form.value.weightEnd.value,
    repsStart: form.value.repsStart.value,
    repsEnd: form.value.repsEnd.value,
    pollWeight: form.value.pollWeight.value,
    setReps: form.value.setReps.value,
    measure: form.value.measure.value,
    memo: form.value.memo.value,
    picture: form.value.picture?.value,
  };
  if (!editFlag.value) request.seq = weightStore.getSelectItem().seq;
  return request;
};

// 저장 버튼
const saveWeightItem = async () => {
  const body = makeBody();
  weightStore.postWeightItem(body);
  router.push({ path: "/weight/weightList" });
};

// 수정 버튼
const modifyWeightItem = async () => {
  const body = makeBody();
  weightStore.putWeightItem(body);
  router.push({ name: "weight-weightList" });
};

// 초기화 버튼
const resetWeightItem = () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    if(key==='measure'){form.value[key].value = '1'}
    else{
      form.value[key as keyof WeightFormData].value = "";
    }
  }
};

// 취소 버튼
const cancelWeightEdit = () => {
  router.back();
};

const initSelectedItem = async () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i] as keyof WeightFormData;
    form.value[key].value = weightStore.getSelectItem()[key];
    
  }
};

// 등록된 리스트 조회
const getFavroiteWeightList = async () => {
  try {
    const { data, error } = await readFavoritWeightList();
    if (error.value !== null) setErrorMessage(error.value);
    else if (data.value !== null) {
      const list = data.value.data;
      loadList.value = list;
    }
  } catch (e) {
    setErrorMessage(e);
  }
};

const initName = (name: string) => {
  form.value.name.value = name;
};

onMounted(async () => {
  await getFavroiteWeightList();
  if (!editFlag.value) {
    await initSelectedItem();
  } else {
    await weightStore.resetSelectItem();
  }
});
</script>
