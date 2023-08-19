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
      <form class="login-form bp-pa-md">
        <div>
          <TextInput :data="formData.id" class="login-input bp-mb-xl"></TextInput>
          <PasswordInput :data="formData.password" class="login-input bp-mb-xl"></PasswordInput>
        </div>
        <div>
          <button type="button" class="long-filled-button login-button bp-mb-xl" @click="moveMain">Login</button>
          <p class="move-sign-up-wrap-box">not a member? <NuxtLink to="/signup" class="text-button bp-ml-sm">Sign up </NuxtLink></p>
        </div>
      </form>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue";
import { useRouter } from "vue-router";
import TextInput from "../components/form/TextInput.vue";
import PasswordInput from "../components/form/PasswordInput.vue";
import { userLoginFormData } from "~~/types/user";
import { setErrorMessage, setWarnMessage } from "~~/api/alert/message";
import { fetchLogin } from "~~/api/user/user";
import { inRange } from "~~/api/util";
import { useUserStore } from "~~/store/user";

const router = useRouter();
const userStore = useUserStore();
const formData: Ref<userLoginFormData> = ref({
  id: { value: "", placeholder: "아이디", minlength: 0, maxlength: 20 },
  password: { value: "", placeholder: "비밀번호", minlength: 8, maxlength: 20 },
});

// Login
const moveMain = async () => {
  const { idValue, idMin, idMax } = { idValue: formData.value.id.value, idMin: formData.value.id.minlength, idMax: formData.value.id.maxlength };
  const { pwValue, pwMin, pwMax } = { pwValue: formData.value.password.value, pwMin: formData.value.password.minlength, pwMax: formData.value.password.maxlength };
  if (!inRange(idValue as string, idMin as number, idMax as number) || !inRange(pwValue as string, pwMin as number, pwMax as number)) {
    setWarnMessage("아이디 또는 비밀번호를 확인해주세요.");
  } else {
    const formD = new FormData();
    formD.append("userId", formData.value.id.value as string);
    formD.append("password", formData.value.password.value as string);
    try {
      const { data } = await fetchLogin(formD);
      const dataValue = data.value as { code: boolean; message: string };
      if(dataValue.code){
        userStore.setIsPass(true);
        router.push({name: 'main'});
      }else setErrorMessage(`${dataValue.message}`);
    } catch (e) {
      setErrorMessage(`${e}`);
    }
  }
};

const introduceMessageList: string[] = [
  "This is Login Page",
  "Please enter your information.",
  "If you have not signed up,",
  "plese click the sign up button",
  "to proceed with sign up.",
  "If you have any questions, please call or email us",
];

definePageMeta({
  layout: 'start-layout',
});
</script>
<style lang="scss" scoped></style>
