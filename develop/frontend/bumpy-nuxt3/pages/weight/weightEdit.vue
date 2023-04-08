<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="load-wrap-box">
        <p>불러오기</p>
        <LoadList></LoadList>
      </label>
      <label class="weightEdit-input-label bp-mt-sm">
        <span class="bp-mr-sm">이름</span>
        <TextInput :data="form.name"></TextInput>
      </label>
      <label class="weightEdit-input-label photo-wrap-box bp-mt-sm">
        <p class="bp-mb-sm">사진 및 비디오</p>
        <div class="photo-wrap"></div>
      </label>
      <div class="weightEdit-input-label-wrap">
        <template v-for="(list, idx) in numberList" :key="idx">
          <div class="weightEdit-input-label">
            <template v-for="(item, nIdx) in list" :key="nIdx">
              <label class="number-input-wrap">
                <span class="bp-mr-sm">{{ item.label }}</span>
                <div class="number-input">
                  <NumberInput :data="item.data"></NumberInput>
                </div>
              </label>
            </template>
          </div>
        </template>
      </div>
      <label>
        <p>메모</p>
        <textarea></textarea>
      </label>
      <div class="weightEdit-button-wrap">
        <button class="short-ghost-button">취소</button>
        <button class="short-ghost-button bp-mx-sm">초기화</button>
        <button class="short-filled-button">저장</button>
      </div>
    </form>
  </main>
</template>
<script setup>
import LoadList from '~/components/list/LoadList.vue';
import TextInput from '~/components/form/TextInput.vue';
import NumberInput from '~/components/form/NumberInput.vue';
const info = { name: '벤치프레스' };
const form = ref({
  name: { value: '', placeholder: '잠온다' },
});

const numberList = [
  [
    { key: 'startWeight', label: '시작 무게', data: { value: '', placeholder: '' } },
    { key: 'startReps', label: '시작 횟수', data: { value: '', placeholder: '' } },
  ],
  [
    { key: 'endWeight', label: '종료 무게', data: { value: '', placeholder: '' } },
    { key: 'endReps', label: '종료 횟수', data: { value: '', placeholder: '' } },
  ],
  [
    { key: 'barWeight', label: '봉 무게', data: { value: '', placeholder: '' } },
    { key: 'setReps', label: '세트 횟수', data: { value: '', placeholder: '' } },
  ],
  [{ key: 'barWeight', label: '단위', data: { value: '', placeholder: '' } }],
];
definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
