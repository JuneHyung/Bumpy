<template>
  <li class="rectangle-item bp-pa-sm bp-ma-sm" @click="getSelectAerobicChartInfo(props.info.name as string)">
    <p>{{ props.info.name }}</p>
    <div class="bp-mt-md">
      <p>
        <span>{{ props.info.time }}</span> / <span>{{ props.info.kcal }}</span>
      </p>
      <p>
        <span>{{ props.info.inclineStart }}</span> ~ <span>{{ props.info.inclineEnd }}</span>
      </p>
      <p>
        <span>{{ props.info.speedStart }}</span> ~ <span>{{ props.info.speedEnd }}</span>
      </p>
    </div>
  </li>
</template>
<script setup lang="ts">
import { useAerobicStore } from '~~/store/aerobic';
import { Aerobic } from '~~/types/aerobic';
interface Props {
  info: Aerobic;
}
const props = defineProps<Props>();
const aerobicStore = useAerobicStore();
const getSelectAerobicChartInfo = async (name: string) =>{
  await aerobicStore.getAerobicChartInfo(name)
} 
</script>
