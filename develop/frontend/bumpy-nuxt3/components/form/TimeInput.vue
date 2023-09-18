<template>
  <div class="input-wrap-box">
    <label>
      <input
        type="time"
        :autofocus="false"
        :disabled="props.data.disabled"
        :readonly="props.data.readonly"
        v-model="props.data.value"
        class="input-time"
      />
    </label>
  </div>
</template>
<script setup lang="ts">
import { InputTime } from '~~/types/input';

interface Props {
  data: InputTime;
}
const props = defineProps<Props>();
</script>