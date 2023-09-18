<template>
  <li class="rectangle-item bp-pa-sm bp-ma-sm">
    <p>{{ props.info.stdDate }} Inbody</p>
    <div class="bp-mt-md">
      <p>
        <span>{{ props.info.height }} cm</span> / <span>{{ props.info.weight }} kg</span> 
      </p>
      <p>
        <span> 근육량 : {{ props.info.muscle }}</span>
        <span> bmi : {{ props.info.bmi }}</span>
        <span> fatPercent : {{ props.info.fatRate }}</span>
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
