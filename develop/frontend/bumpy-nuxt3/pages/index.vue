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
      <div class="login-form">
        <input type="text" placeholder="아이디" />
        <input type="text" placeholder="비밀번호" />
        <button class="long-filled-button login-button" @click="moveMain">Login</button>
        <p class="move-sign-up-wrap-box">not a member? <NuxtLink to="/signin" class="text-button bp-ml-sm">Sign up</NuxtLink></p>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router';
const router = useRouter();
const moveMain = () => {
  router.push('main');
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
