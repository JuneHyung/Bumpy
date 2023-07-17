import { AsyncData, FetchResult } from "nuxt/app";
import { AerobicChartInfoResponseBody, AerobicInfoReasponseBody, ChartRequestParams, GrassInfoRequestParams, GrassInfoResponseBody, MealInfoResponseBody, WeightChartInfoResponseBody, WeightInfoResponseBody } from "~~/types/main";
import { MealList } from "~~/types/meal";
import { DELETE, GET, POST, PUT } from "~~/types/method";

const baseURL = "http://222.112.251.124:8899/api/main";
const headers = {
  "Content-Type": "application/json"
}

// 잔디조회
function getGrassInfo(params: GrassInfoRequestParams){
  return useFetch<GrassInfoResponseBody>('/grassinfo', {
    baseURL,
    method: GET,
    headers,
    params
  })
}
// 사용자정보(메인페이지용)
function getUserInfoForMain(){
  return useFetch('/userinfo', {
    baseURL,
    method: GET,
    headers,
  })
}
// 식단정보(메인페이지용)
function getMealInfoForMain(){
  return useFetch<MealInfoResponseBody>('/mealinfo', {
    baseURL,
    method: GET,
    headers,
  })
}
// 근력액티비티정보(메인페이지용)
function getWeightActivityForMain(){
  return useFetch<WeightInfoResponseBody>('/activityinfo/weight', {
    baseURL,
    method: GET,
    headers,
  })
}
// 유산소액티비티정보(메인페이지용)
function getAerobicActivityForMain(){
  return useFetch<AerobicInfoReasponseBody>('/activityinfo/aerobic', {
    baseURL,
    method: GET,
    headers,
  })
}
// 근력차트데이터
function getWeightChartInfoForMain(params: ChartRequestParams){
  return useFetch<WeightChartInfoResponseBody>('/chart/weight', {
    baseURL,
    method: GET,
    headers,
    params
  })
}
// 유산소차트데이터
function getAerobicChartInfoForMain(params: ChartRequestParams){
  return useFetch<AerobicChartInfoResponseBody>('/chart/aerobic', {
    baseURL,
    method: GET,
    headers,
    params
  })
}


export {
  getGrassInfo, 
  getUserInfoForMain, 
  getMealInfoForMain, 
  getWeightActivityForMain, 
  getAerobicActivityForMain, 
  getWeightChartInfoForMain, 
  getAerobicChartInfoForMain
};
