<template>
  <div class="avatar-wrap-box">
    <!-- <img src="~/assets/images/beam.png" class="avatar-image" /> -->
    <CustomIcon iconName="mdiAccountCircle" class="avatar-image"></CustomIcon>
  </div>
</template>
<script setup lang="ts">
import CustomIcon from '../icon/CustomIcon.vue';
interface Props {
  name: string;
}
const props = defineProps<Props>();
</script>
