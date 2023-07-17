<template>
  <li class="rectangle-item bp-pa-sm bp-ma-sm">
    <p>{{ info.stdDate }} Inbody</p>
    <div class="bp-mt-md">
      <p>
        <span>{{ info.height }} cm</span> / <span>{{ info.weight }} kg</span> 
      </p>
      <p>
        <span> 근육량 : {{ info.muscle }}</span>
        <span> bmi : {{ info.bmi }}</span>
        <span> fatPercent : {{ info.fatRate }}</span>
      </p>
    </div>
  </li>
</template>
<script setup lang="ts">
import { Inbody } from '~~/types/inbody';

interface Props {
  info: Inbody;
}
const props = defineProps<Props>();
</script>
<style lang="scss" scoped></style>
