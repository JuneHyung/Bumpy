<template>
  <div class="input-wrap-box">
    <label>
      <input
        type="password"
        :autofocus="false"
        :placeholder="props.data.placeholder"
        :disabled="props.data.disabled"
        :readonly="props.data.readonly"
        :maxlength="props.data.maxlength"
        :minlength="props.data.minlength"
        @keyup="handleKeyup"
        @keydown="preventCopy"
        v-model="props.data.value"
        class="input-password"
      />
      <span class="suffix bp-ml-xs" :class="{ 'hidden-box': isSuffix }">{{ props.data.suffix }}</span>
    </label>
    <p :class="{ 'correct-message': validateFlag, 'hidden-box': isPattern }" class="validate-message bp-mt-xs ellipsis">{{ validateMessage }}</p>
  </div>
</template>
<script setup lang="ts">
import { ref, computed, ComputedRef, onMounted } from 'vue';
import { InputPassword, MatchMessage } from '~~/types/input';

interface Props {
  data: InputPassword;
}
const props = defineProps<Props>();
const validateMessage: ComputedRef<MatchMessage> = computed(() => (validateFlag.value ? 'Correct' : 'Not Match'));

const validateFlag = ref(false);
const isSuffix = ref(false);
const isPattern = ref(false);

const handleKeyup = () => {
  const regex = new RegExp(props.data.pattern as string, 'g');
  validateFlag.value = regex.test(props.data.value as string);
};

const preventCopy = (event: KeyboardEvent) => {
  if(event.ctrlKey && event.code == "KeyV" ){ 
    event.preventDefault();
  }
}

onMounted(() => {
  isPattern.value = props.data.pattern ? false : true;
  isSuffix.value = props.data.suffix ? false : true;
});
</script>
