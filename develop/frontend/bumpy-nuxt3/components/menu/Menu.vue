<template>
  <nav class="menu-wrap-list">
    <ul class="menu-list">
      <li class="menu-list-logo" @click="moveMain"></li>
      <template v-for="(item, idx) in menuList" :key="idx">
        <li class="menu-list-item"><NuxtLink :to="item.link" class="menu-list-link">{{ item.label }}</NuxtLink></li>
      </template>
    </ul>
  </nav>
</template>
<script setup lang="ts">
import {useRouter}from 'vue-router';
import {MenuList} from '~~/types/menu/menu';
const router = useRouter();

const menuList: MenuList =[
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
