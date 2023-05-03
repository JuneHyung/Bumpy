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
import Menu from '~/components/menu/Menu.vue';
import UserInfo from '~/components/user/UserInfo.vue';
export default {
  name: 'detail-layout',
};
</script>
<style lang="scss" scoped>

</style>
