<template>
  <div class="login-wrap-box">
    <div class="login-introduce-box">
      <h1 class="introduce-title">Welcome Bumpy!</h1>
      <div class="introduce-box">
        <template v-for="(message, idx) in introduceMessageList" :key="idx">
          <p class="introduce-message bp-my-md">{{ message }}</p>
        </template>

        <p class="contact-info"><span class="bp-mr-sm">email : cjh951114@naver.com</span><span>phone: 010-7917-2614</span></p>
      </div>
    </div>
    <div class="login-form-box">
      <h2 class="login-form-title">USER LOGIN</h2>
      <form class="login-form bp-pa-md" :action="target" @submit="moveMain(formData)">
        <div>
          <TextInput :data="formData.id" class="login-input bp-mb-xl"></TextInput>
          <PasswordInput :data="formData.password" class="login-input bp-mb-xl"></PasswordInput>
        </div>
        <div>
          <input type="submit" class="long-filled-button login-button bp-mb-xl" value="Login" />
          <p class="move-sign-up-wrap-box">not a member? <NuxtLink to="/signup" class="text-button bp-ml-sm">Sign up</NuxtLink></p>
        </div>
      </form>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import TextInput from '../components/form/TextInput.vue';
import PasswordInput from '../components/form/PasswordInput.vue';
const router = useRouter();
interface inputText {
  value?: string;
  placeholder?: string;
  autofocus?: boolean;
  maxlength?: number;
  minlength?: number;
  disabled?: boolean;
  readonly?: boolean;
  suffix?: boolean;
  pattern?: string;
}
interface inputPassword {
  value?: string;
  placeholder?: string;
  autofocus?: boolean;
  maxlength?: number;
  minlength?: number;
  disabeld?: boolean;
  readonly?: boolean;
}
interface userFormData {
  id: inputText;
  password: inputPassword;
}
const formData: userFormData = {
  id: { value: '', placeholder: '아이디를 입력해주세요.', maxlength: 20, minlength: 5, pattern: '^[a-z]+[a-z0-9]{5,19}$' },
  password: { value: '', placeholder: '비밀번호', maxlength: 20, minlength: 5 },
};
const target = ref('#');
const moveMain = (formData: userFormData) => {
  target.value = formData.id.value === formData.password.value ? 'main' : '#';
  return formData.id === formData.password;
};

const introduceMessageList: string[] = [
  'This is Login Page',
  'Please enter your information.',
  'If you have not signed up,',
  'plese click the sign up button',
  'to proceed with sign up.',
  'If you have any questions, please call or email us',
];
</script>
<style lang="scss" scoped></style>
