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
interface inputTextarea {
  value?: string;
  placeholder?: string;
  autofocus?: boolean;
  cols?: number;
  rows?: number;
  maxlength?: number;
  minlength?: number;
  disabled?: boolean;
  readonly?: boolean;
}

interface Props {
  data: inputTextarea;
}
const props = defineProps<Props>();
</script>
<style scoped></style>
