<template>
  <main class="content-layout">
    <h1 class="content-title q-mb-lg">Edit My Routine</h1>
    <form class="content-wrap-box">
      <label class="load-wrap-box">
        <p>불러오기</p>
        <LoadList></LoadList>
      </label>
      <label class="aerobicEdit-input-label bp-mt-sm">
        <span class="bp-mr-sm">이름</span>
        <TextInput :data="form.name"></TextInput>
      </label>
      <div class="aerobicEdit-input-label-wrap">
        <template v-for="(list, idx) in numberList" :key="idx">
          <div class="aerobicEdit-input-label">
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
      <div class="aerobicEdit-button-wrap">
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
    { key: 'kcal', label: 'Kcal', data: { value: '', placeholder: '' } },
    { key: 'startIncline', label: '시작 Incline', data: { value: '', placeholder: '' } },
    { key: 'startSpeed', label: '시작 Speed', data: { value: '', placeholder: '' } },
  ],
  [
    { key: 'time', label: 'Time', data: { value: '', placeholder: '' } },
    { key: 'endIncline', label: '종료 Incline', data: { value: '', placeholder: '' } },
    { key: 'endSpeed', label: '종료 Speed', data: { value: '', placeholder: '' } },
  ],
];
definePageMeta({
  layout: 'main-layout',
});
</script>
<style scoped lang="scss"></style>
