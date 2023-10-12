<template>
  <div class="image-list-outter-wrap">
    <div class="cur-image-box">
      <img :src="`data:image/jpg;base64, ${curUrl}`" class="cur-image-box" v-if="props.list.length !== 0" />
      <p v-else>No Image</p>
    </div>
    <ul class="image-list-box" ref="imageList">
      <template v-for="(url, idx) in props.list">
        <li class="image-item" @click="changeCurIdx(idx)" :class="{'active-item': curIdx===idx}" ref="imageItem">
          <img :src="`data:image/jpg;base64, ${url}`" alt="test 사진" />
        </li>
      </template>
    </ul>
  </div>
</template>
<script setup lang="ts">
interface Props {
  list: Uint8ClampedArray[]
}
const props = defineProps<Props>();

const curIdx = ref(0);
const curUrl = computed(() => props.list[curIdx.value]);
const imageList = ref<HTMLUListElement>();
const imageItem = ref<HTMLLIElement[]>();
const startPos = ref({x:0, y:0})

const changeCurIdx = (idx: number) => {
  curIdx.value = idx;
  if(imageItem.value && imageList.value){
    const nextPos = imageItem.value[idx].getBoundingClientRect();
    const nextX = nextPos.x - startPos.value.x;
    
    imageList.value.scrollTo({
      top:0,
      left: nextX,
      behavior: 'smooth'
    });
    
  }
};

const initStartPos = () =>{
  if(imageList.value){
    const rect = imageList.value.getBoundingClientRect();
    startPos.value = {x: rect.x, y: rect.y};
  }
}

onMounted(() => {
  initStartPos();
});
</script>
