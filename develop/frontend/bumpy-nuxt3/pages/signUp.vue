<template>
  <div class="signup-wrap-box">
    <div class="signup-introduce-box">
      <h1 class="introdue-title">Welcome Bumpy!</h1>
      <div class="introduce-box">
        <template v-for="(message, idx) in introduceMessageList" :key="idx">
          <p class="introduce-message bp-my-md">{{ message }}</p>
        </template>
        <p class="contact-info"><span class="bp-mr-sm">email : cjh951114@naver.com</span><span>phone: 010-7917-2614</span></p>
      </div>
    </div>
    <div class="signup-form-box">
      <h2 class="signup-form-title">SIGN UP</h2>
      <form class="signup-form" :action="target" @submit="moveLogin(userForm)">
        <div class="signup-id-wrap">
          <TextInput :data="userForm.id" class="id-input bp-my-sm bp-mr-sm" />
          <button class="short-filled-button duplicate-check-button">중복검사</button>
        </div>
        <PasswordInput :data="userForm.password" class="bp-my-sm" />
        <PasswordInput :data="userForm.passwordChk" class="bp-my-sm" />
        <div class="signup-email-wrap">
          <TextInput :data="userForm.email" class="email-input bp-my-sm bp-mr-sm" />
          <button class="short-filled-button">인증</button>
        </div>
        <TextInput :data="userForm.name" class="bp-my-sm" />
        <DateInput :data="userForm.birth" class="bp-my-sm" />
        <div class="signup-phone-wrap bp-my-sm">
          <NumberInput :data="userForm.phoneFirst" />
          <NumberInput :data="userForm.phoneSecond" class="bp-mx-sm" />
          <NumberInput :data="userForm.phoneThird" />
        </div>
        <TextInput :data="userForm.address" class="bp-my-sm" />
        <TextInput :data="userForm.detailAddress" class="bp-my-sm" />
        <div class="signup-button-wrap">
          <button class="short-ghost-button cancel-button bp-mr-sm">취소</button>
          <input type="submit" class="short-filled-button signup-button" value="회원가입" />
        </div>
      </form>
    </div>
  </div>
</template>
<script setup lang="ts">
import TextInput from '../components/form/TextInput.vue';
import PasswordInput from '../components/form/PasswordInput.vue';
import NumberInput from '../components/form/NumberInput.vue';
import DateInput from '../components/form/DateInput.vue';
import { ref, Ref } from 'vue';
const target = ref('/');

interface inputText {}
interface inputPassword {}
interface inputEmail {}

interface inputNumber {}
interface inputDate {}
interface inputPhone {}

interface userFormData {
  id: inputText;
  password: inputPassword;
  passwordChk: inputPassword;
  email: inputEmail;
  name: inputText;
  birth: inputDate;
  phoneFirst: inputPhone;
  phoneSecond: inputPhone;
  phoneThird: inputPhone;
  address: inputNumber;
  detailAddress: inputDate;
}

const moveLogin = (formData: userFormData) => {
  return true;
};
const userForm: Ref<userFormData> = ref({
  id: { placeholder: '아이디를 입력해주세요.' },
  password: { placeholder: '영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요.' },
  passwordChk: { placeholder: '영문, 숫자, 특수문자를 1자이상 포함해 8~20자로 입력해주세요.' },
  email: { placeholder: 'email형식을 지켜 작성해주세요.' },
  name: { placeholder: '이름을 입력해주세요.' },
  birth: { placeholder: '생일을 입력해주세요' },
  phoneFirst: { placeholder: '000' },
  phoneSecond: { placeholder: '0000' },
  phoneThird: { placeholder: '0000' },
  address: { placeholder: '주소를 입력해주세요.' },
  detailAddress: { placeholder: '상세주소를 입력해주세요.', disabled: true },
});

const introduceMessageList: string[] = ['This is Signup Page', 'Please enter your information.', 'You can sign up for membership', 'If you have any questions', 'Please call or email us'];
</script>
<style scoped lang="scss">
.login-form {
  display: flex;
  flex-direction: column;
}
</style>
