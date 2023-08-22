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

      <template v-for="(list, idx) in numberList" :key="idx">
        <div class="edit-input-row bp-my-sm">
          <template v-for="(item, nIdx) in list" :key="nIdx">
            <label class="edit-input-label">
              <span class="edit-label bp-mr-sm">{{ item.label }}</span>
              <div class="edit-input">
                <TimeInput :data="form[item.key]" v-if="item.key === 'time'" />
                <TextInput :data="form[item.key]" v-else />
              </div>
            </label>
          </template>
        </div>
      </template>

      <label class="edit-input-label bp-mb-lg">
        <p class="edit-label bp-mr-sm">메모</p>
        <TextareaInput :data="form.memo"></TextareaInput>
      </label>
      <div class="edit-button-wrap">
        <button type="button" class="short-ghost-button" @click="cancelAerobicEdit">취소</button>
        <button type="button" class="short-ghost-button bp-mx-sm" @click="resetAerobicItem">초기화</button>
        <button type="button" class="short-filled-button" @click="saveAerobicItem" v-if="editFlag">저장</button>
        <button type="button" class="short-filled-button" @click="modifyAerobicItem" v-else>수정</button>
      </div>
    </form>
  </main>
</template>
<script setup lang="ts">
import LoadList from "~~/components/list/LoadList.vue";
import TextInput from "~~/components/form/TextInput.vue";
import TextareaInput from "~~/components/form/TextareaInput.vue";

import { useCommonStore } from "~/store/common";
import { setErrorMessage, setMessage } from "~~/api/alert/message";
import { createAerobicItem, updateAerobicItem, readFavoritAerobicList } from "~~/api/aerobic/aerobic";
import { useAerobicStore } from "~~/store/aerobic";
import { AerobicFormData, AerobicRequestBody } from "~~/types/aerobic";
import { FavoriteListItem } from "~~/types/common";
import TimeInput from "~~/components/form/TimeInput.vue";
const commonStore = useCommonStore();
const aerobicStore = useAerobicStore();
const router = useRouter();
const editFlag = computed(() => aerobicStore.getSelectItem().seq === '');
const loadList: Ref<FavoriteListItem[]> = ref([]);
const form: Ref<AerobicFormData> = ref({
  name: { value: "", placeholder: "잠온다" },
  kcal: { value: "",},
  inclineStart: { value: "",},
  speedStart:  {value: ""},
  time: { value: "",},
  inclineEnd: { value: "",},
  speedEnd: { value: "",},
  memo: {  value: "",rows: 10 },
});

const numberList = [
  [
    { key: "inclineStart", label: "시작 Incline" },
    { key: "speedStart", label: "시작 Speed" },
    { key: "kcal", label: "Kcal" },
  ],
  [
    { key: "inclineEnd", label: "종료 Incline" },
    { key: "speedEnd", label: "종료 Speed" },
    { key: "time", label: "Time" },
  ],
];

const makeBody = () => {
  const request: AerobicRequestBody = {
    stdDate: aerobicStore.getFocusDate() === null || aerobicStore.getFocusDate().length === 0 ? commonStore.getToday() : aerobicStore.getFocusDate(),
    name: form.value.name.value,
    kcal: form.value.kcal.value,
    inclineStart: form.value.inclineStart.value,
    speedStart: form.value.speedStart.value,
    time: form.value.time.value,
    inclineEnd: form.value.inclineEnd.value,
    speedEnd: form.value.speedEnd.value,
    memo: form.value.memo.value,
  };
  if (!editFlag.value) request.seq = aerobicStore.getSelectItem().seq;
  return request;
};
// 저장 버튼
const saveAerobicItem = async () => {
  const body = makeBody();
  try {
    const { data, error } = await createAerobicItem(body);
    if (error.value !== null) {
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    } else if (data.value !== null) {
      setMessage(data.value.message);
      router.push({ name: "aerobic-aerobicList" });
    }
  } catch (e) {
    setErrorMessage(e);
  }
};

// 수정 버튼
const modifyAerobicItem = async () => {
  const body = makeBody();
  try {
    const { data, error } = await updateAerobicItem(body);
    if (error.value !== null) {
      const errorMessage = error.value?.data.message;
      setErrorMessage(errorMessage);
    } else if (data.value !== null) {
      setMessage(data.value.message);
      router.push({ name: "aerobic-aerobicList" });
    }
  } catch (e) {
    setErrorMessage(e);
  }
};
// 초기화 버튼
const resetAerobicItem = () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = "";
  }
};

// 취소 버튼
const cancelAerobicEdit = () => {
  router.back();
};

const initSelectedItem = async () => {
  const keys = Object.keys(form.value);
  for (let i = 0; i < keys.length; i++) {
    const key = keys[i];
    form.value[key].value = aerobicStore.getSelectItem()[key];
  }
};

const getFavroiteAerobicList = async () => {
  try {
    const { data, error } = await readFavoritAerobicList();
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
  await getFavroiteAerobicList();
  if (!editFlag.value) {
    await initSelectedItem();
  } else {
    await aerobicStore.resetSelectItem();
  }
  console.log( aerobicStore.getSelectItem())
});
definePageMeta({
  layout: "main-layout",
});
</script>
<style scoped lang="scss"></style>
