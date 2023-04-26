<template>
  <div class="main-layout">
    <Menu></Menu>
    <NuxtPage />
    <UserInfo></UserInfo>
  </div>
</template>
<script>
import Menu from '~/components/Menu.vue';
import UserInfo from '~/components/UserInfo.vue';
export default {
  name: 'detail-layout',
};
</script>
<style lang="scss" scoped>

</style>
