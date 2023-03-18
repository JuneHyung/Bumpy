<template>
  <nav class="menu-list">
    <NuxtLink to="/main" class="menu-list-logo"></NuxtLink>
    <template v-for="(item, idx) in menuList" :key="idx">
      <NuxtLink :to="item.link" class="menu-list-item">{{ item.label }}</NuxtLink>
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
.menu-list {
  display: flex;
  flex-direction: column;
  /* justify-content: start; */
  align-items: center;
  height: 100%;
  width: 200px;
  box-sizing: border-box;
  border: 1px solid black;
  padding: 20px 10px;
  .menu-list-logo {
    display: flex;
    width: 100%;
    height: 150px;
    border: 1px solid black;
    margin-bottom: 20px;
  }
  .menu-list-item {
    height: 100px;
    display: flex;
    align-items: center;
  }
}
</style>
