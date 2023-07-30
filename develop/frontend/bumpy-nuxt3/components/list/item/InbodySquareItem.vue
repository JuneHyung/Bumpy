<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ info.stdDate }} Inbody</p>
    <div class="bp-mt-md bp-mb-lg">
      <p class="bp-my-md">
        <span>{{ info.score }} 점</span>
      </p>
      <p class="bp-my-md">
        <span>{{ info.height }} cm</span> / <span>{{ info.weight }} kg</span>
      </p>
      
      <p class="bp-my-md">
        <span> 근육량 : {{ info.muscle }}</span>
      </p>
      <p class="bp-my-md">
        <span> bmi : {{ info.bmi }}</span>
      </p>
      <p class="bp-my-md">
        <span> fatPercent : {{ info.fatRate }}</span>
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
  await inbodyStore.getSelectItemByStdDateSeq(inbodyStore.getFocusDate(), props.info.seq as number)
  await router.push({ path: 'inbodyDetail' });
};

</script>
<style lang="scss" scoped></style>
