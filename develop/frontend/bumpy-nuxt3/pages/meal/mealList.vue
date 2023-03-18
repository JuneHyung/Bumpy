<template>
  <div class="content-layout">
    <h1>meal List</h1>
    <NuxtLink to="/meal/mealList">List</NuxtLink>
    <NuxtLink to="/meal/mealEdit">Edit</NuxtLink>
    <NuxtLink to="/meal/mealDetail">Detail</NuxtLink>
  </div>
</template>
<script setup lang="ts">
definePageMeta({
  layout: 'main-layout',
});
</script>
