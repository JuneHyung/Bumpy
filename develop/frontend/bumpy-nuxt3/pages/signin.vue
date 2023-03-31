<template>
  <form class="login-form" :action="target" @submit="moveLogin(testForm)">
    <TextInput :data="testForm.text" class="bp-my-sm"/>
    <PasswordInput :data="testForm.password" class="bp-my-sm" />
    <NumberInput :data="testForm.number" class="bp-my-sm" />
    <DateInput :data="testForm.date" class="bp-my-sm" />
    <SelectboxInput :data="testForm.selectbox" class="bp-my-sm" />
    <SquareList type="rectangle" :list="testList"></SquareList>
  </form>
</template>
<script setup lang="ts">
import TextInput from '../components/form/TextInput.vue';
import PasswordInput from '../components/form/PasswordInput.vue';
import NumberInput from '../components/form/NumberInput.vue';
import DateInput from '../components/form/DateInput.vue';
import SelectboxInput from '../components/form/SelectboxInput.vue';
import SquareList from '../components/list/SquareList.vue';
import { ref, Ref } from 'vue';
const target = ref('#');

interface inputText {}
interface inputPassword {}
interface inputNumber {}
interface inputDate {}
interface listItem {
  dtlCd: string;
  dtlNm: string;
}
interface inputSelectbox {
  value?: string;
  listname: string;
  list?: listItem[];
  placeholder?: string;
  autofocus?: boolean;
  maxlength?: number;
  minlength?: number;
  disabeld?: boolean;
  readonly?: boolean;
  min?: number;
  max?: number;
}

interface userFormData {
  text: inputText;
  password: inputPassword;
  number: inputNumber;
  date: inputDate;
  selectbox: inputSelectbox;
}

interface ActivityList {
  name: string;
  startWeight: number;
  endWeight: number;
  barWeight: number;
  startReps: number;
  endReps: number;
  setReps: number;
  memo?: string;
}
const moveLogin = (formData: userFormData) => {
  return true;
};
const testForm: Ref<userFormData> = ref({
  text: {},
  password: {},
  number: {},
  date: {},
  selectbox: {
    listname: 'test',
    list: [
      { dtlCd: '01', dtlNm: '01번' },
      { dtlCd: '02', dtlNm: '02번' },
      { dtlCd: '03', dtlNm: '03번' },
    ],
  },
});

const testList: ActivityList[] = [
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps:5, memo: '메모메모' },
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps:5, memo: '메모메모' },
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps:5, memo: '메모메모' },
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps:5, memo: '메모메모' },
  { name: '벤치 프레스', startWeight: 10, endWeight: 30, barWeight: 20, startReps: 12, endReps: 8, setReps:5, memo: '메모메모' },
]
</script>
<style scoped lang="scss">
.login-form{
  display:flex;
  flex-direction: column;
}
</style>