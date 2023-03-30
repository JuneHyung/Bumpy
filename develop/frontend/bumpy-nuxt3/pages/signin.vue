<template>
  <h1>회원가입페이지</h1>

  <form class="login-form" :action="target" @submit="moveLogin(testForm)">
    <TextInput :data="testForm.text" />
    <PasswordInput :data="testForm.password" />
    <NumberInput :data="testForm.number" />
    <DateInput :data="testForm.date" />
    <SelectboxInput :data="testForm.selectbox" />
  </form>
</template>
<script setup lang="ts">
import TextInput from '../components/form/TextInput.vue';
import PasswordInput from '../components/form/PasswordInput.vue';
import NumberInput from '../components/form/NumberInput.vue';
import DateInput from '../components/form/DateInput.vue';
import SelectboxInput from '../components/form/SelectboxInput.vue';
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
</script>
