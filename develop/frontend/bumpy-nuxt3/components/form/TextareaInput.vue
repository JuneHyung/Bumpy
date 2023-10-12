<template>
  <div class="input-wrap-box">
    <label>
      <textarea
        :value="props.data.value"
        :placeholder="props.data.placeholder"
        :autofocus="props.data.autofocus"
        :cols="props.data.cols"
        :rows="props.data.rows"
        :maxlength="props.data.maxlength"
        :minlength="props.data.minlength"
        :disabled="props.data.disabled"
        :readonly="props.data.readonly"
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
