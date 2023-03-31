<template>
  <label class="input-wrap-box">
    <input type="text" :autofocus="false" :placeholder="data.placeholder" v-model="data.value" class="input-text"/>
  </label>
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
<style scoped lang="scss">

</style>