<template>
  <main class="content-layout">
    <div class="password-check-wrap-box content-wrap-box">
      <CustomIcon iconName="mdiLockCheck" style="width:60px; height:60px;"></CustomIcon>
      <h2 class="bp-my-lg">Enter Your Password</h2>
      <PasswordInput :data="password"/>
      <button class="bp-my-lg" @click="checkPassword">Check</button>
    </div>
  </main>
</template>
<script setup lang="ts">
import PasswordInput from '~~/components/form/PasswordInput.vue';
import CustomIcon from '~~/components/icon/CustomIcon.vue';
import { useUserStore } from '~~/store/user';
definePageMeta({
  layout: 'main-layout',
});
const userStore = useUserStore();
const router = useRouter();

const password = ref({})
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