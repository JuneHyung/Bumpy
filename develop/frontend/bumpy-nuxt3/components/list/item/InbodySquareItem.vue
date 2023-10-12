<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ props.info.stdDate }} Inbody</p>
    <div class="bp-mt-md bp-mb-lg">
      <p class="bp-my-md">
        <span>{{ props.info.score }} 점</span>
      </p>
      <p class="bp-my-md">
        <span>{{ props.info.height }} cm</span> / <span>{{ props.info.weight }} kg</span>
      </p>
      
      <p class="bp-my-md">
        <span> 근육량 : {{ props.info.muscle }}</span>
      </p>
      <p class="bp-my-md">
        <span> bmi : {{ props.info.bmi }}</span>
      </p>
      <p class="bp-my-md">
        <span> fatPercent : {{ props.info.fatRate }}</span>
      </p>
    </div>
  </li>
</template>
<script setup lang="ts">
import { useInbodyStore } from '~~/store/inbody';
import { Inbody } from '~~/types/inbody';

interface Props {
  info: Inbody;
}
const props = defineProps<Props>();
const router=  useRouter();
const inbodyStore = useInbodyStore();

const moveDetail = async () => {
  await inbodyStore.getSelectItemByStdDate(inbodyStore.getFocusDate())
  await router.push({ name: 'inbody-inbodyDetail' });
};

</script>