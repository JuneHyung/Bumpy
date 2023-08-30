<template>
  <ul>
    <li>{{ props.list }}</li>
  </ul>
</template>
<script setup lang="ts">
import {YoutubeList} from '~/types/common'
interface Props {
  list: YoutubeList
}
const props = defineProps<Props>()
</script>