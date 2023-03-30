<template>
  <input type="date" v-model="data.value" required pattern="\d{2}-\d{2}-\d{2}" />
</template>
<script setup lang="ts">
interface inputDate {
  value?: string;
  placeholder?: string;
  autofocus?: boolean;
  disabeld?: boolean;
  readonly?: boolean;
  min?: string;
  max?: string;
}

interface Props {
  data: inputDate;
}

const props = defineProps<Props>();
</script>
