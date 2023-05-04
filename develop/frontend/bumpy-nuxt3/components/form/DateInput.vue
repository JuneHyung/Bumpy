<template>
  <div class="input-wrap-box">
    <label>
      <input
        type="date"
        :autofocus="false"
        :disabled="data.disabled"
        :readonly="data.readonly"
        max="9999-12-31"
        v-model="data.value"
        class="input-date"
        pattern="\d{4}-\d{2}-\d{2}"
      />
      <span class="suffix bp-ml-xs" :class="{ 'hidden-box': isSuffix }">{{ data.suffix }}</span>
    </label>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue';
interface inputText {
  value?: string;
  autofocus?: boolean;
  disabled?: boolean;
  readonly?: boolean;
  suffix?: boolean;
  step?: string;
}

interface Props {
  data: inputText;
}

const props = defineProps<Props>();

const isSuffix = ref(false);

onMounted(() => {
  // isPattern.value = props.data.pattern ? false : true;
  isSuffix.value = props.data.suffix ? false : true;
});
</script>
<style scoped lang="scss">
</style>