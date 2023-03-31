<template>
  <label class="input-wrap-box">
    <input type="password" :autofocus="false" :placeholder="data.placeholder" v-model="data.value" maxlength="20" class="input-password"/>
  </label>
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
}
interface Props {
  data: inputPassword;
}

const props = defineProps<Props>();
</script>
