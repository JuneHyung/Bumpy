<template>
  <div class="wrapper-layout">
    <div class="main-layout">
      <Menu></Menu>
      <NuxtPage />
      <UserInfo></UserInfo>
    </div>
  </div>
</template>
<script>
import dayjs from 'dayjs';
import Menu from '~~/components/menu/Menu.vue';
import UserInfo from '~~/components/user/UserInfo.vue';
import { useCommonStore } from '~~/store/common';
export default {
  name: 'detail-layout',
};
</script>
<script setup>
onBeforeMount(()=>{
  const commonStore = useCommonStore();
  if(commonStore.getToday() !== dayjs().format('YYYY-MM-DD')) commonStore.setToday();
})
</script>
<style lang="scss" scoped>

</style>
