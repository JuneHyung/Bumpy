<template>
  <div class="login-wrap-box">
    <div class="login-introduce-box">
      <h1 class="introduce-title">Welcome Bumpy!</h1>
      <div class="introduce-box">
        <template v-for="(message, idx) in introduceMessageList" :key="idx">
          <p class="introduce-message bp-my-md">{{ message }}</p>
        </template>

        <p class="contact-info"><span class="bp-mr-sm">email : cjh951114@naver.com</span><span>phone: 010-7917-2614</span>
        </p>
      </div>
    </div>
    <div class="login-form-box">
      <h2 class="login-form-title">USER LOGIN</h2>
      <form class="login-form bp-pa-md">
        <div>
          <TextInput :data="formData.id" class="login-input bp-mb-xl"></TextInput>
          <PasswordInput :data="formData.password" class="login-input bp-mb-xl"></PasswordInput>
        </div>
        <div>
          <button type="button" class="long-filled-button login-button bp-mb-xl" @click="moveMain">Login</button>
          <p class="move-sign-up-wrap-box">not a member? <NuxtLink to="/signup" class="text-button bp-ml-sm">Sign up
            </NuxtLink>
          </p>
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
import { userFormData, LoginResponse } from '~~/types/user';
import {setErrorMessage} from '~~/api/alert/errorMessage'
import { fetchLogin } from '~~/api/user/user';
import { validateValue } from '~~/api/util';
const { $swal } = useNuxtApp()
const router = useRouter();

const formData: Ref<userFormData> = ref({
  id: { value: '', placeholder: '아이디를 입력해주세요.', maxlength: 20, minlength: 5, pattern: '^[a-z]+[a-z0-9]{4,20}$' },
  password: { value: '', placeholder: '비밀번호', maxlength: 20, minlength: 3, pattern: '^(?=.*\d)(?=.*[a-z])(?=.*[^\w\d\s:]){7,20}$' },
});

const moveMain = async () => {
  const {idValue, idPattern} = {idValue: formData.value.id.value, idPattern: formData.value.id.pattern}
  const {pwValue, pwPattern} = {pwValue: formData.value.id.value, pwPattern: formData.value.id.pattern}
  
  if(validateValue(idValue, idPattern) && validateValue(pwValue, pwPattern)){
    const formD = new FormData();
    formD.append('userId', formData.value.id.value as string);
    formD.append('password', formData.value.password.value as string);
    try {
      const { data } = await fetchLogin('POST', formD);
      const dataValue = data.value as {code: boolean, message: string}
      dataValue.code 
        ? router.push({path: 'main'}) 
        : setErrorMessage(`${dataValue.message}`)
    } catch (e) {
      console.error(e);
    }

  }else{
    setErrorMessage(`아이디 또는 패스워드를 확인해주세요.`)
  }
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
<style lang="scss" scoped>
</style>
