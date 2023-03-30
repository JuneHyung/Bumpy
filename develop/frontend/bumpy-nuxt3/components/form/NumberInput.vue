<template>
  <input type="number" :autofocus="false" :placeholder="data.placeholder" v-model="data.value" maxlength="20" />
</template>
<script setup lang="ts">
interface inputPassword {
  value?: string;
  placeholder?: string;
  autofocus?: boolean;
  maxlength?: number;
  minlength?: number;
  disabeld?: boolean;
  readonly?: boolean;
  min?: number;
  max?: number;
}
interface Props {
  data: inputPassword;
}

const props = defineProps<Props>();
</script>
