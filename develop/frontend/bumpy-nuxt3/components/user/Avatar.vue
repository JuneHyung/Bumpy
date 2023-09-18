<template>
  <div class="avatar-wrap-box">
    <img src="~/assets/images/beam.png" class="avatar-image" />
  </div>
</template>
<script setup lang="ts">
interface Props {
  name: string;
}
const props = defineProps<Props>();
</script>
<style lang="scss"></style>
