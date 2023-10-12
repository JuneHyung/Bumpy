<template>
  <div class="input-wrap-box">
    <label>
      <input type="date" :autofocus="false" :disabled="props.data.disabled" :readonly="props.data.readonly" max="9999-12-31" v-model="props.data.value" class="input-date" pattern="\d{4}-\d{2}-\d{2}" />
      <span class="suffix bp-ml-xs" :class="{ 'hidden-box': isSuffix }">{{ props.data.suffix }}</span>
    </label>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { InputDate } from '~~/types/input';

interface Props {
  data: InputDate;
}

const props = defineProps<Props>();

const isSuffix = ref(false);

onMounted(() => {
  // isPattern.value = props.data.pattern ? false : true;
  isSuffix.value = props.data.suffix ? false : true;
});
</script>