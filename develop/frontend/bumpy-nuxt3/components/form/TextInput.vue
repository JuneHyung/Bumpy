<template>
  <input type="text" :autofocus="false" :placeholder="data.placeholder" v-model="data.value" />
</template>
<script setup lang="ts">
interface inputText {
  value?: string;
  placeholder?: string;
  autofocus?: boolean;
  maxlength?: number;
  minlength?: number;
  disabeld?: boolean;
  readonly?: boolean;
}

interface Props {
  data: inputText;
}

const props = defineProps<Props>();
</script>
