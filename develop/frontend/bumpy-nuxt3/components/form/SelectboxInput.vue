<template>
  <div class="input-wrap-box" @click="toggleList">
    <p class="select-list-button" :class="{'placeholder': isEmpty(props.data.value)}">
      
        {{ isEmpty(props.data.value) ? props.data.placeholder : props.data.value }}
      
    </p>
    <ul class="select-list-box" :class="{ 'hidden-box': closeFlag }">
      <template v-for="(item, idx) in data.list" :key="idx">
        <li :value="item.dtlCd" @click="setValue(item)">{{ item.dtlNm }}</li>
      </template>
    </ul>
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue";
interface listItem {
  dtlCd: string;
  dtlNm: string;
}
interface inputSelectbox {
  value?: string;
  listname?: string;
  list: listItem[];
  placeholder?: string;
  autofocus?: boolean;
  disabeld?: boolean;
  readonly?: boolean;
}
interface Props {
  data: inputSelectbox;
}
const props = defineProps<Props>();
const closeFlag = ref(true);
const toggleList = () => {
  closeFlag.value = !closeFlag.value;
};
const setValue = (item: any) => {
  // console.log(item)
  props.data.value = item.dtlCd;
};
const isEmpty = (val: any) => {
  const arr = props.data.list?.map((el) => el.dtlCd);
  return val === undefined || val === null || !arr.includes(val);
};
const origin = ref(props.data.value);
</script>
<style lang="scss" scoped></style>
