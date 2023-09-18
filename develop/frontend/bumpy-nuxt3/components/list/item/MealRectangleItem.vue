<template>
  <li class="rectangle-item bp-pa-sm bp-ma-sm">
    <p>{{ props.info.name }}</p>
    <div class="bp-mt-md">
      <p>
        <span>{{ props.info.seq }}번째 식사</span> - <span>{{ props.info.time }}</span> / <span>{{ props.info.kcal }}kcal</span>
      </p>
      <p>
        <span>{{ props.info.water }} L </span>섭취
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

