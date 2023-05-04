<template>
  <div class="input-wrap-box">
    <label>
      <textarea
        :value="data.value"
        :placeholder="data.placeholder"
        :autofocus="data.autofocus"
        :cols="data.cols"
        :rows="data.rows"
        :maxlength="data.maxlength"
        :minlength="data.minlength"
        :disabled="data.disabled"
        :readonly="data.readonly"
        class="input-textarea"
      ></textarea>
    </label>
  </div>
</template>
<script setup lang="ts">
import { InputTextarea } from '~~/types/input';

interface Props {
  data: InputTextarea;
}
const props = defineProps<Props>();
</script>
<style scoped></style>
