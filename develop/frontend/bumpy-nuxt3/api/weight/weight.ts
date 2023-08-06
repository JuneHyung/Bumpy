import { MessageResponse } from "~~/types/common";
import { DELETE, GET, POST, PUT } from "~~/types/method";
import { Weight, WeightCalendarResponseBody, WeightFavoriteListResponseBody, WeightItemRequestParam, WeightItemResponseBody, WeightReadRequestParam, WeightReadResponseBody, WeightRemoveRequestParams, WeightRequestBody } from "~~/types/weight";

const baseURL = "http://222.112.251.124:8899/api";
const headers = {
  "Content-Type": "application/json"
}
/**
 * weight 운동 조회 (근력 운동 조회) - GET
 * @param {{stdDate: string}} params
 * @returns
 */
function readWeightItem(params: WeightItemRequestParam) {
  return useFetch<WeightItemResponseBody>("/weight/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * weight 달력 조회 - GET
 * @returns
 */
function readWeightCalendarList(params: WeightReadRequestParam) {
  return useFetch<WeightCalendarResponseBody>("/weight/calendar", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * Activity 조회 - GET
 * @returns
 */
function readWeightList(params: WeightReadRequestParam) {
  return useFetch<WeightReadResponseBody>("/weight/activity", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * weight 운동 조회 (근력 운동 조회) - POST
 * @param {{
 * "stdDate" : "2023-05-22",
 * "seq" : 1,
 * "name" : "벤치프레스",
 * "weightStart" : 20,
 * "weightEnd" : 30,
 * "repsStart" : 5,
 * "repsEnd" : 5,
 * "pollWeight" : 10,
 * "setReps" : 3,
 * "measure" : 1,
 * "memo" : "테스트",
 * "picture" : null
 * }} body
 * @returns
 */
function createWeightItem(body: any) {
  // console.log(body)
  // const formData = new FormData();
  //  // files를 FormData에 추가합니다.
  //  for (const file of body.files) {
  //   console.log(file)
  //   formData.append('files', file);
  // }

  // // request를 JSON 문자열로 직렬화하여 FormData에 추가합니다.
  // formData.append('request', JSON.stringify(body.request));

  return useFetch("/weight/insert", {
    baseURL,
    method: POST,
    body: body,
  });
}

/**
 * 근력운동 삭제 - DELETE
 * @param {{
 * stdDate: string,
 * seq: number,
 * }} body
 * @returns
 */
function deleteWeightItem(params: WeightRemoveRequestParams) {
  return useFetch("/weight/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 근력운동 수정 - PUT
 * @param {{
 * stdDate: string,
 * seq: number,
 * }} body
 * @returns
 */
function updateWeightItem(body: Weight) {
  return useFetch("/weight/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}


/**
 * 즐겨찾기 조회(Weight)
 * @returns
 */
function readFavoritWeightList(){
  return useFetch<WeightFavoriteListResponseBody>("/weight/favorite",{
    baseURL,
    method:GET,
    headers,
  })
}

export {
  readWeightItem, 
  readWeightCalendarList, 
  readWeightList, 
  createWeightItem, 
  deleteWeightItem, 
  updateWeightItem,
  readFavoritWeightList
};
