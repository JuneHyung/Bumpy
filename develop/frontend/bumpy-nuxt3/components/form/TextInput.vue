<template>
  <div class="input-wrap-box">
    <label>
      <input
        type="text"
        :autofocus="false"
        :placeholder="data.placeholder"
        :disabled="data.disabled"
        :readonly="data.readonly"
        :maxlength="data.maxlength"
        :minlength="data.minlength"
        @keyup="handleKeyup"
        v-model="data.value"
        class="input-text"
      />
      <span class="suffix bp-ml-xs" :class="{ 'hidden-box': isSuffix }">{{ data.suffix }}</span>
    </label>
    <p class="validate-message bp-mt-xs ellipsis" :class="{ 'correct-message': validateFlag, 'hidden-box': isPattern }">{{ validateMessage }}</p>
  </div>
</template>
<script setup lang="ts">
import { ComputedRef, Ref, computed, onMounted, ref } from 'vue';
interface inputText {
  value?: string;
  placeholder?: string;
  autofocus?: boolean;
  maxlength?: number;
  minlength?: number;
  disabled?: boolean;
  readonly?: boolean;
  suffix?: boolean;
  pattern?: string;
}

interface Props {
  data: inputText;
}
type matchMessage = 'Not Match' | 'Correct';
const props = defineProps<Props>();

const validateMessage: ComputedRef<matchMessage> = computed(() => (validateFlag.value ? 'Correct' : 'Not Match'));
const validateFlag = ref(false);
const isSuffix = ref(false);
const isPattern = ref(false);

const handleKeyup = () => {
  const regex = new RegExp(props.data.pattern as string, 'g');
  validateFlag.value = regex.test(props.data.value as string);
};

onMounted(() => {
  isPattern.value = props.data.pattern ? false : true;
  isSuffix.value = props.data.suffix ? false : true;
});
</script>
<style scoped lang="scss"></style>
