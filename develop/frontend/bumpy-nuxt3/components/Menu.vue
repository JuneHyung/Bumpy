<template>
  <nav class="menu-list">
    <NuxtLink to="/main" class="menu-list-logo"></NuxtLink>
    <template v-for="(item, idx) in menuList" :key="idx">
      <NuxtLink :to="item.link" class="menu-list-item" :class="{'current-list-item':idx===0}">{{ item.label }}</NuxtLink>
    </template>
  </nav>
</template>
<script setup lang="ts">
interface MenuItem {
  link: string;
  label: string;
}

const menuList: MenuItem[] = [
  {
    link: '/main',
    label: 'Main',
  },
  {
    link: '/weight/weightList',
    label: '웨이트',
  },
  {
    link: '/aerobic/aerobicList',
    label: '유산소',
  },
  {
    link: '/meal/mealList',
    label: '식단',
  },
  {
    link: '/inbody/inbodyList',
    label: '인바디',
  },
];
</script>
<style lang="scss" scoped>

</style>
