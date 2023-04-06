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
                    <NumberInput :data="item.data"></NumberInput>
                  </div>
                </label>
              </template>
            </div>
          </template>
        </div>
        <div class="food-list-wrap-box"></div>
      </div>

      <label>
        <p>메모</p>
        <textarea></textarea>
      </label>
      <div class="mealEdit-button-wrap">
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
    { key: 'name', label: '이름', data: { value: '', placeholder: '' } },
    { key: 'order', label: '차례', data: { value: '', placeholder: '' } },
    { key: 'time', label: 'Time', data: { value: '', placeholder: '' } },
    { key: 'kcal', label: 'Kcal', data: { value: '', placeholder: '' } },
    { key: 'water', label: 'Water', data: { value: '', placeholder: '' } },
  ],
];
definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
