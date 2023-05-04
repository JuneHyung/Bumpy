<template>
  <li class="rectangle-item bp-pa-sm bp-ma-sm">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md">
      <p>
        <span>{{ info.order }}번째 식사</span> - <span>{{ info.time }}</span> / <span>{{ info.kcal }}kcal</span>
      </p>
      <p>
        <span>{{ info.water }} L </span>섭취
      </p>
    </div>
  </li>
</template>
<script setup lang="ts">
import { Meal } from '~~/types/meal';

interface Props {
  info: Meal;
}
const props = defineProps<Props>();
</script>
<style lang="scss" scoped></style>
