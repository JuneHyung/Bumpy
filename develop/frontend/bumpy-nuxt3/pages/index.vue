<template>
  <div class="login-wrap-box">
    <div class="login-introduce-box">
      <h1 class="introduce-title">Welcome Bumpy!</h1>
      <div class="introduce-box">
        <p>This is Login Page</p>
        <p>Please enter your information.</p>
        <p>If you have not signed up,</p>
        <p>please click the sign up button</p>
        <p>to proceed with sign up.</p>
        <p>If you have any questions, please call or email us</p>
      </div>
    </div>
    <div class="login-form-box">
      <p class="login-form-title">USER LOGIN</p>
      <form class="login-form">
        <input type="text" placeholder="아이디" />
        <input type="text" placeholder="비밀번호" />
        <button class="login-button" @click="moveMain">Login</button>
        <p class="move-sign-up-wrap-box">not a member? <NuxtLink to="/signin" class="move-sign-up-button">Sign up</NuxtLink></p>
      </form>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useRouter } from 'vue-router';
const router = useRouter();
const moveMain = () => {
  router.push('main');
};
</script>
<style lang="scss" scoped>
.login-wrap-box {
  width: 1000px;
  height: 420px;
  display: flex;
  .login-introduce-box {
    padding: 30px;
    flex: 1 1 600px;
    height: 100%;
    color: #fff;
    background: linear-gradient(to bottom, #fb8c00, #e65100);
    .introduce-title {
      font-weight: 900;
      font-size: 36px;
    }
    .introduce-box {
      font-size: 20px;
      font-weight: 500;
    }
  }
  .login-form-box {
    display: flex;
    flex-direction: column;
    flex: 1 1 400px;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.8);

    .login-form-title {
      display: flex;
      text-align: center;
      align-items: center;
      justify-content: center;
      color: #fb8c00;
      height: 80px;
      font-size: 24px;
    }
    .login-form {
      padding: 16px;
      height: calc(100% - 80px);
      flex-wrap: wrap;
      display: flex;
      input {
        width: 100%;
      }

      .login-button {
        margin-top: 60px;
        background-color: #fb8c00;
        height: 60px;
        border-radius: 50px;
        color: #fff;
        font-weight: 900;
        width: 100%;
        font-size: 16px;
        justify-content: center;
        align-items: center;
        justify-self: flex-end;
        align-self: end;
        border: none;
      }

      .move-sign-up-wrap-box {
        flex: 1;
        display: flex;
        color: #fff;
        justify-content: center;
        align-items: center;
        .move-sign-up-button {
          margin-left: 8px;
          color: #fb8c00;
        }
      }
    }
  }
}
</style>
