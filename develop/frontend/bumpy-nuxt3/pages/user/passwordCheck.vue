<template>
  <main class="content-layout">
    <div class="password-check-wrap-box content-wrap-box">
      <CustomIcon iconName="mdiLockCheck" style="width:60px; height:60px;"></CustomIcon>
      <h2 class="bp-my-lg">Enter Your Password</h2>
      <div>
      <PasswordInput :data="password"/>
      <button class="bp-my-lg short-filled-button" @click="checkPassword" type="button" style="margin: 1rem auto;">Check</button>
    </div>
    </div>
  </main>
</template>
<script setup lang="ts">
import PasswordInput from '~~/components/form/PasswordInput.vue';
import CustomIcon from '~~/components/icon/CustomIcon.vue';
import { useUserStore } from '~~/store/user';
definePageMeta({
  layout: 'main-layout',
  middleware: 'custom-router-guard'
});
const userStore = useUserStore();
const router = useRouter();

const password = ref({value: '',})
const checkPassword = async () => {
  await userStore.checkPassword({password: password.value.value})
  if(userStore.getIsPass()) router.push({name: 'user-userPage'})
}
</script>

<style lang="scss">
.password-check-wrap-box{
  align-items: center;
}
</style>