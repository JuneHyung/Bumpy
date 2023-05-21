<template>
  <li class="square-item bp-pa-sm bp-ma-sm">
    <p>{{ info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p>
        <span>{{ info.time }}</span> / <span>{{ info.kcal }}</span>
      </p>
      <p>
        <span>{{ info.inclineStart }}</span> ~ <span>{{ info.inclineEnd }}</span>
      </p>
      <p>
        <span>{{ info.speedStart }}</span> ~ <span>{{ info.speedEnd }}</span>
      </p>
    </div>
    <p class="ellipsis-3 item-memo">{{ info.memo }}</p>
  </li>
</template>
<script setup lang="ts">
import { Aerobic } from '~~/types/aerobic';

interface Props {
  info: Aerobic;
}
const props = defineProps<Props>();
</script>
<style lang="scss" scoped></style>
