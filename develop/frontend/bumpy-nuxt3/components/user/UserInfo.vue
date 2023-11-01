<template>
  <div class="user-info-box">
    <ul class="user-info-button-list">
      <li class="logout-button text-button" @click="Logout">Logout</li>
      <li class="setting-button text-button" @click="moveUserPage">Setting</li>
    </ul>

    <div class="user-profile-wrap-box">
      <Avatar name="jh"></Avatar>
      <p class="user-name bp-mt-sm">{{ userStore.getUserName() }}</p>
    </div>

    <ul class="user-body-info-box">
      <template v-for="(body, idx) in userStore.getUserBodyInfo()" :key="idx">
        <li>
            <p class="user-body-info-value bp-mb-xs">{{ body.value }} {{ body.unit }}</p>
            <p class="user-body-info-category">{{ body.category }}</p>
        </li>
      </template>
    </ul>

    <ul class="user-activity-info-box">
      <template v-for="(activity, idx) in userStore.getUserActivityInfo()" :key="idx">
        <li class="bp-my-sm">
          <span class="user-activity-info-category">{{ activity.category }} </span>
          <span class="user-activity-info-value"> {{ activity.value }} {{ activity.unit }}</span>
        </li>
      </template>
    </ul>

    <ul class="user-inbody-info-box">
      <p class="user-inbody-title-wrap bp-mb-sm">
        <h4>Your Last Inbody</h4>
        <!-- <span class="last-inbody-date"></span> -->
      </p>
      <ul class="user-inbody-degree-wrap">
        <template v-for="(degree, idx) in userStore.getDegreeList()" :key="idx">
          <li class="bp-mr-lg">
            <div class="degree-box bp-mr-xs" :class="{'high-degree':degree.degree==='high','middle-degree':degree.degree==='middle','low-degree':degree.degree==='low'}"></div>
            <p class="degree-name">{{ degree.name }}</p>
          </li>
        </template>
      </ul>
      
      <ul class="user-inbody-info-wrap">
        <template v-for="(inbody, idx) in userStore.getUserInbodyInfo()" :key="idx">
          <li class="bp-my-md">
            <p class="inbody-title-wrap bp-mb-sm">
              <h4 class="bp-mr-xs">{{ inbody.category }}</h4>
              <span class="inbody-unit">( {{ inbody.unit }} )</span>
            </p>
            <MeterBar :info="inbody"></MeterBar>
          </li>
        </template>
      </ul>
    </ul>
  </div>
</template>
<script setup lang="ts">
import MeterBar from '~~/components/meter/MeterBar.vue';
import Avatar from '~~/components/user/Avatar.vue';
import { useRouter } from 'vue-router';
import {fetchLogout} from '~~/api/user/user';
import { UserInfoList, DegreeList, MeterList } from '~~/types/inbody';
import { getUserInfoForMain } from '~~/api/main';
import { setErrorMessage } from '~~/api/alert/message';
import { useUserStore } from '~~/store/user';
import { MainUserInbodyData, MainUserInfo } from '~~/types/main';
import {resetAllData} from '~~/api/util';
const router = useRouter();
const userStore = useUserStore();

const moveUserPage = () => { 
  router.push({name:'user-passwordCheck'})
}

const Logout = async () =>{
  await fetchLogout();
  userStore.setIsPass(false);
  router.push({name: 'index'})
}

onMounted(async ()=>{
  await resetAllData();
  await userStore.getUserInfo();
})
</script>
