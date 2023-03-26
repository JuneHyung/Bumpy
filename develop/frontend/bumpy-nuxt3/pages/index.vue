<template>
  <div class="login-wrap-box">
    <div class="login-introduce-box">
      <h1 class="introduce-title">Welcome Bumpy!</h1>
      <div class="introduce-box">
        <template v-for="(message, idx) in introduceMessageList" :key="idx">
          <p>{{message}}</p>
        </template>
      </div>
    </div>
    <div class="login-form-box">
      <p class="login-form-title">USER LOGIN</p>
      <form class="login-form" :action="target" @submit="moveMain(formData)">
        <input type="text" placeholder="아이디" v-model="formData.id"/>
        <input type="text" placeholder="비밀번호" v-model="formData.pw" />
        <input type="submit" class="long-filled-button login-button" value="Login"/>
        <p class="move-sign-up-wrap-box">not a member? <NuxtLink to="/signin" class="text-button bp-ml-sm">Sign up</NuxtLink></p>
      </form>
    </div>
  </div>
</template>
<script setup lang="ts">
import {ref} from 'vue'
import { useRouter } from 'vue-router';
const router = useRouter();
interface userFormData {
  id: string,
  pw: string,
}
const formData:userFormData = {
  id: '',
  pw: '',
}
const target = ref('#');
const moveMain = (formData: userFormData) => {
  target.value = formData.id === formData.pw ? 'main' : '#';
  return formData.id === formData.pw;
};

const introduceMessageList: string[] = [
  'This is Login Page',
  'Please enter your information.',
  'If you have not signed up,',
  'plese click the sign up button',
  'to proceed with sign up.',
  'If yout have any questions, please call or email us',
]
</script>
<style lang="scss" scoped>

</style>
