<template>
  <div class="default-layout">
    <NuxtPage />
  </div>
</template>

<script setup>
import Menu from '~/components/Menu.vue';
</script>
<style lang="scss" scoped>
.default-layout {
  display: flex;
  height: 100vh;
  justify-content: center;
  align-items: center;
}
</style>
