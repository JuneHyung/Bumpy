<template>
  <ul class="load-list-wrap-box">
    <template v-for="(item, idx) in props.list">
      <div class="load-list-box">
        <p class="load-list-category">{{ item.title.toUpperCase() }}</p>
        <ul class="load-list">
          <template v-for="(name, idx) in item.list">
            <li class="load-item ellipsis-1" @click="setFormName(name)">{{ name }}</li>
          </template>
        </ul>
      </div>
    </template>
  </ul>
</template>
<script setup lang="ts">
const props = defineProps({
  list: Object,
})
const emits = defineEmits(['initName'])

const setFormName = (name: string) =>{
  emits('initName', name)
}
</script>
