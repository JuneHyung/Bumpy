<template>
  <main class="content-layout">
    <h1 class="content-title bp-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <div class="flex bp-mb-lg">
        <label class="flex col flex-6 bp-mr-sm">
          <p class="bp-mb-sm">사진 및 비디오</p>
          <FileUploader :list="form.picture" class="full-height"></FileUploader>
        </label>
        <div class="flex flex-6 bp-mt-xl" style="justify-content: flex-end;">
            <template v-for="(list, idx) in numberList" :key="idx">
              <div class="edit-input-col">
                <template v-for="(item, nIdx) in list" :key="nIdx">
                  <label class="edit-input-label">
                    <span class="edit-label bp-mr-sm">{{ item.label }}</span>
                    <div class="edit-input">
                      <TextInput :data="form[item.key]"></TextInput>
                    </div>
                  </label>
                </template>
              </div>
            </template>
        </div>
      </div>
        <div class="edit-button-wrap">
          <button type="button" class="short-ghost-button" @click="cancelInbodyEdit">취소</button>
          <button type="button" class="short-ghost-button bp-mx-sm" @click="resetInbodyItem">초기화</button>
          <button type="button" class="short-filled-button" @click="saveInbodyItem" v-if="editFlag">저장</button>
          <button type="button" class="short-filled-button" @click="modifyInbodyItem" v-else>수정</button>
        </div>
    </form>
  </main>
</template>
<script setup lang="ts">
import TextInput from "~~/components/form/TextInput.vue";
import FileUploader from "~~/components/form/FileUploader.vue";
import { useInbodyStore } from "~~/store/inbody";
import { InbodyItemRequestBody } from "~~/types/inbody";

definePageMeta({
  layout: "main-layout",
  middleware: 'custom-router-guard'
});

const inbodyStore = useInbodyStore();
const router = useRouter();
const editFlag = computed(() => inbodyStore.getSelectItem().height === '');

const form = ref({
  name: { value: "" },
  height: { value: "", isNumber:true, minlength: 0, maxlength: 3 },
  weight: { value: "", isNumber:true, minlength: 0, maxlength: 3 },
  kcal: { value: "", isNumber:true, minlength: 0, maxlength: 4 },
  muscle: { value: "", isNumber:true, minlength: 0, maxlength: 4 },
  fat: { value: "", isNumber:true, minlength: 0, maxlength: 4 },
  score: { value: "", isNumber:true, minlength: 0, maxlength: 4 },
  bmi: { value: "", isNumber:true, minlength: 0, maxlength: 4 },
  fatRate: { value: "", isNumber:true, minlength: 0, maxlength: 4 },
  picture: { value: [] },
});

const numberList = ref([
  [
    { key: "height", label: "키" },
    { key: "weight", label: "체중" },
    { key: "muscle", label: "골격근량" },
    { key: "fat", label: "체지방량" },
    { key: "score", label: "인바디" },
    { key: "bmi", label: "BMI" },
    { key: "fatRate", label: "체지방률" },
  ],
]);

const makeBody = () => {
  const result: InbodyItemRequestBody = {
    stdDate: inbodyStore.getFocusDate(),
    height: form.value.height.value,
    weight: form.value.weight.value,
    muscle: form.value.muscle.value,
    fat: form.value.fat.value,
    score: form.value.score.value,
    bmi: form.value.bmi.value,
    fatRate: form.value.fatRate.value,
    // memo: form.value.memo.value,
    picture: form.value.picture.value,
  };
  return result;
};

// 저장 버튼
const saveInbodyItem = async () => {
  const body = makeBody();
  inbodyStore.postInbodyItem(body);
  router.push({ name: "inbody-inbodyList" });
};

// 수정 버튼
const modifyInbodyItem = async () => {
  const body = makeBody();
  inbodyStore.putInbodyItem(body);
  router.push({ name: "inbody-inbodyList" });
};

// 초기화 버튼
const resetInbodyItem = () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = "";
  }
};

// 취소 버튼
const cancelInbodyEdit = () => {
  router.back();
};

const initSelectItem = () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = inbodyStore.getSelectItem()[key];
  }
};

onMounted(() => {
  if (!editFlag.value) {
    initSelectItem();
  } else {
    inbodyStore.resetSelectItem();
  }
});
</script>
