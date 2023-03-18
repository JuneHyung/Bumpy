<template>
  <div class="content-layout">
    <h1>inbody List</h1>
    <NuxtLink to="/inbody/inbodyList">List</NuxtLink>
    <NuxtLink to="/inbody/inbodyEdit">Edit</NuxtLink>
    <NuxtLink to="/inbody/inbodyDetail">Detail</NuxtLink>
  </div>
</template>
<script setup lang="ts">
definePageMeta({
  layout: 'main-layout',
});
</script>
