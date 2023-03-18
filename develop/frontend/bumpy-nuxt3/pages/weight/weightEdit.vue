<template>
  <div class="content-layout">
    <h1>weight Edit</h1>
    <NuxtLink to="/weight/weightList">List</NuxtLink>
    <NuxtLink to="/weight/weightEdit">Edit</NuxtLink>
    <NuxtLink to="/weight/weightDetail">Detail</NuxtLink>
  </div>
</template>
<script setup lang="ts">
definePageMeta({
  layout: 'main-layout',
});
</script>
