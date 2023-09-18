<template>
  <ul class="youtube-list">
    <template v-for="(item, idx) in props.list" :key="item.videoId">
    <li class="youtube-item bp-px-sm bp-py-md" :style="{ 
      'background-image': `linear-gradient(to right, rgba(23, 33, 43, 1) 40%, rgba(23, 33, 43, 0) 80%), url(${item.thumbnail})`, 
      'background-position': 'right', 
      'background-repeat': 'no-repeat', 
      'background-color': 'black',
    }">
    <a :href="item.videoId" target="blank">
      <h3 class="bp-my-sm ellipsis"> {{ item.title }}</h3>
      <p class="bp-my-sm ellipsis"> {{ item.channelTitle }}</p>
      <p class="bp-my-sm ellipsis youtube-description"> {{ item.description }}</p>
    </a>
    </li>
    </template>
  </ul>
</template>
<script setup lang="ts">
import {YoutubeList} from '~/types/common'
interface Props {
  list: YoutubeList
}
const props = defineProps<Props>()
</script>