<template>
  <nav class="menu-list">
    <div class="menu-list-logo" @click="moveMain"></div>
    <template v-for="(item, idx) in menuList" :key="idx">
      <NuxtLink :to="item.link" class="menu-list-item">{{ item.label }}</NuxtLink>
    </template>
  </nav>
</template>
<script setup lang="ts">
import {useRouter}from 'vue-router';
const router = useRouter();
interface MenuItem {
  link: string;
  label: string;
  isActive: boolean;
}

const menuList: MenuItem[] =[
  {
    link: '/main',
    label: 'Main',
    isActive: false,
  },
  {
    link: '/weight/weightList',
    label: '웨이트',
    isActive: false,
  },
  {
    link: '/aerobic/aerobicList',
    label: '유산소',
    isActive: false,
  },
  {
    link: '/meal/mealList',
    label: '식단',
    isActive: false,
  },
  {
    link: '/inbody/inbodyList',
    label: '인바디',
    isActive: false,
  },
]
const moveMain = () =>{
  router.push({path: '/main'})
}
</script>
<style lang="scss" scoped>

</style>
