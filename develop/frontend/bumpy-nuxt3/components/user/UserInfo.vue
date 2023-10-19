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
      <template v-for="(body, idx) in userBodyInfo" :key="idx">
        <li>
            <p class="user-body-info-value bp-mb-xs">{{ body.value }} {{ body.unit }}</p>
            <p class="user-body-info-category">{{ body.category }}</p>
        </li>
      </template>
    </ul>

    <ul class="user-activity-info-box">
      <template v-for="(activity, idx) in userActivityInfo" :key="idx">
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
        <template v-for="(degree, idx) in degreeList" :key="idx">
          <li class="bp-mr-lg">
            <div class="degree-box bp-mr-xs" :class="{'high-degree':degree.degree==='high','middle-degree':degree.degree==='middle','low-degree':degree.degree==='low'}"></div>
            <p class="degree-name">{{ degree.name }}</p>
          </li>
        </template>
      </ul>
      
      <ul class="user-inbody-info-wrap">
        <template v-for="(inbody, idx) in userInbodyInfo" :key="idx">
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

const userBodyInfo: Ref<UserInfoList>=ref([
  {
    key: 'height',
    category: 'Height',
    value: '',
    unit: 'cm',
  },
  {
    key: 'weight',
    category: 'Weight',
    value: '',
    unit: 'kg',
  },
  {
    key: 'age',
    category: 'Age',
    value: '',
    unit: '',
  },
]);
const userActivityInfo: Ref<UserInfoList>=ref([
  {
    key: 'continuity',
    category: 'Continuity',
    value: '0',
    unit: 'days',
  },
  {
    key: 'lastActive',
    category: 'Last Active',
    value: '',
    unit: '',
  },
  {
    key: 'averageWater',
    category: 'Average Water',
    value: '',
    unit: 'L',
  },
]);
const degreeList:Ref<DegreeList> = ref([
  {
    name: '표준 이하',
    degree: 'low',
  },
  {
    name: '표준',
    degree: 'middle',
  },
  {
    name: '표준 이상',
    degree: 'high',
  }
])
const userInbodyInfo:Ref<MeterList> = ref([
  {
    value: 0,
    max: 100,
    min: 0,
    low: 50,
    high: 75,
    optimum: 100,
    key: 'weight',
    category: '체중',
    unit: 'kg',
  },
  {
    value: 0,
    max: 70,
    min: 0,
    low: 20,
    high: 50,
    optimum: 70,
    key:'muscle',
    category: '골격근량',
    unit: 'kg',
  },
  {
    value: 0,
    max: 100,
    min: 0,
    low: 10,
    high: 30,
    optimum: 100,
    key:'fat',
    category: '체지방량',
    unit: 'kg',
  },
  {
    value: 0,
    max: 100,
    min: 0,
    low: 10,
    high: 40,
    optimum: 100,
    key:'bmi',
    category: 'BMI',
    unit: 'kg/m2',
  },
  {
    value: 0,
    max: 70,
    min: 0,
    low: 10,
    high: 40,
    optimum: 70,
    key: 'fatRate',
    category: '체지방률',
    unit: '%',
  },
]);

const initUserInfo = async (list: MainUserInfo) =>{
  userStore.setUserName(list.username);

  const {height, age, averageWater, continuity, lastActive, inbodyData} = list;
  const bodyInfoData = {height, age};
  const bodyInfoKeys = userBodyInfo.value.map(el=>el.key)
  const userActivityInfoKeys = userActivityInfo.value.map(el=>el.key)
  const userInbodyInfoKeys = userInbodyInfo.value.map(el=>el.key)

  const userActivityInfoData = {averageWater, continuity, lastActive}

  // 신체영역 init
  for(let i=0;i<bodyInfoKeys.length;i++){
    const key = bodyInfoKeys[i] as keyof Pick<MainUserInfo, 'weight' | 'height' | 'age'>;
    if(key==='weight') continue;
    const target = userBodyInfo.value.find(el=>el.key===key);
    if(target){
      target.value = bodyInfoData[key];
    }
  }
  // 활동영역 init
  for(let i=0;i<userActivityInfoKeys.length;i++){
    const key = userActivityInfoKeys[i] as keyof Pick<MainUserInfo, 'averageWater' | 'continuity' | 'lastActive'>;
      const target = userActivityInfo.value.find(el=>el.key===key);
    if(target){
      target.value = userActivityInfoData[key];
    }
  }

  if(inbodyData){
    const {weight, fat, muscle, bmi, fatRate} = inbodyData;
    const target = userBodyInfo.value.find(el=>el.key==='weight');
    if(target){
      target.value = weight;
    }

    const userInbodyInfoData = {weight, fat, muscle, bmi, fatRate};  

    // meter 영역 init
    for(let i=0;i<userInbodyInfoKeys.length;i++){
      const key = userInbodyInfoKeys[i] as keyof MainUserInbodyData;
      const target = userInbodyInfo.value.find(el=>el.key===key);
      if(target){
        target.value = Number(userInbodyInfoData[key]);
      }
    }
  }  
}

const getUserInfo = async () => {
  try{
    const {data, error} = await getUserInfoForMain();
    if(error.value!==null){
      setErrorMessage(error.value);
    }else if(data.value!==null){
      const list = data.value.data
      await initUserInfo(list);
    }
  }catch(e){
    setErrorMessage(e);
  }
}

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
  await getUserInfo();
})

onUpdated(async ()=>{
  const curName = router.currentRoute.value.name;
  console.log(curName);
  if(curName==='inbody-inbodyList'){
    await getUserInfo();
  }
})
</script>
