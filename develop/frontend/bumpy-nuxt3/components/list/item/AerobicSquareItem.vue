<template>
  <li class="square-item bp-pa-sm bp-ma-sm" @click="moveDetail">
    <p>{{ props.info.name }}</p>
    <div class="bp-mt-md bp-mb-lg">
      <p class="bp-my-md">
        <span>Time : {{ props.info.time }} m</span> / <span>Kcal : {{ props.info.kcal }} kcal</span>
      </p>
      <p class="bp-my-md">
        Incline : <span>{{ props.info.inclineStart }}</span> ~ <span>{{ props.info.inclineEnd }}</span>
      </p>
      <p class="bp-my-md">
        Speed : <span>{{ props.info.speedStart }}</span> ~ <span>{{ props.info.speedEnd }}</span>
      </p>
    </div>
    <p class="ellipsis-3 item-memo">{{ props.info.memo }}</p>
  </li>
</template>
<script setup lang="ts">
import { useAerobicStore } from '~~/store/aerobic';
import { Aerobic } from '~~/types/aerobic';
interface Props {
  info: Aerobic;
}
const props = defineProps<Props>();
const router= useRouter();
const aerobicStore = useAerobicStore();

const moveDetail = async () => {
  await aerobicStore.getSelectItemByStdDateSeq(aerobicStore.getFocusDate(), props.info.seq)
  await router.push({ name: 'aerobic-aerobicDetail' });
};
</script>
