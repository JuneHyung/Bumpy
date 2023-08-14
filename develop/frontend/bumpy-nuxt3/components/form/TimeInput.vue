<template>
  <div class="input-wrap-box">
    <label>
      <input
        type="time"
        :autofocus="false"
        :disabled="data.disabled"
        :readonly="data.readonly"
        v-model="data.value"
        class="input-time"
      />
    </label>
  </div>
</template>
<script setup lang="ts">
import { InputTime, MatchMessage } from '~~/types/input';

interface Props {
  data: InputTime;
}
const props = defineProps<Props>();
</script>