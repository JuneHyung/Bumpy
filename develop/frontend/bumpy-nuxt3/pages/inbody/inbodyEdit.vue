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
                      <NumberInput :data="item.data"></NumberInput>
                    </div>
                  </label>
                </template>
              </div>
            </template>
          </div>
        </div>
      </div>
      <div class="inbodyEdit-button-wrap">
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
    { key: 'date', label: '검사날짜', data: { value: '', placeholder: '' } },
    { key: 'height', label: '키', data: { value: '', placeholder: '' } },
    { key: 'weight', label: '체중', data: { value: '', placeholder: '' } },
    { key: 'kcal', label: '골격근량', data: { value: '', placeholder: '' } },
    { key: 'water', label: '체지방량', data: { value: '', placeholder: '' } },
    { key: 'weight', label: '인바디점수', data: { value: '', placeholder: '' } },
    { key: 'kcal', label: 'BMI', data: { value: '', placeholder: '' } },
    { key: 'water', label: '체지방률', data: { value: '', placeholder: '' } },
  ],
];
definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
