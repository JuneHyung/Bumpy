<template>
  <div class="input-wrap-box">
    <label>
      <input
        type="number"
        :autofocus="false"
        :placeholder="data.placeholder"
        :disabled="data.disabled"
        :readonly="data.readonly"
        :max="data.max"
        :min="data.min"
        :step="data.step"
        @keydown="preventArrow"
        v-model="data.value"
        class="input-number"
        :class="{'hidden-arrow': data.noStep}"
      />
      <span class="suffix bp-ml-xs" :class="{ 'hidden-box': isSuffix }">{{ data.suffix }}</span>
    </label>
  </div>
</template>
<script setup lang="ts">
import { ref, computed, ComputedRef, onMounted } from "vue";

interface inputNumber {
  value: number;
  placeholder?: string;
  autofocus?: boolean;
  max?: number;
  min?: number;
  disabled?: boolean;
  readonly?: boolean;
  suffix?: boolean;
  step?: number;
  noStep?: boolean;
}
interface Props {
  data: inputNumber;
}

const props = defineProps<Props>();
const isSuffix = ref(false);

const preventArrow = (e: KeyboardEvent) => {
  if (props.data.noStep) {
    switch (e.key) {
      case "ArrowUp":
      case "ArrowDown":
        e.preventDefault();
        break;
    }
  }
};

onMounted(() => {
  isSuffix.value = props.data.suffix ? false : true;
});
</script>
