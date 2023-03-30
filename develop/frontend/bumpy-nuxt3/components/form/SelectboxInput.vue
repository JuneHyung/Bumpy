<template>
  <div class="search-box">
    <select :id="data.listname">
      <template v-for="(item, idx) in data.list" :key="idx">
        <option :value="item.dtlCd">{{ item.dtlNm }}</option>
      </template>
    </select>
  </div>
</template>
<script setup lang="ts">
interface listItem {
  dtlCd: string;
  dtlNm: string;
}
interface inputSelectbox {
  value?: string;
  listname: string;
  list?: listItem[];
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
  data: inputSelectbox;
}

const props = defineProps<Props>();
</script>
<style lang="scss" scoped>
.search-box {
  input {
    background-color: green;
  }
  select {
    width: 100%;
    option {
      background-color: blue;
      width: 100px;
    }
  }
}
</style>
