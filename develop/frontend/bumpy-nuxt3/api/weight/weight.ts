import { MessageResponse } from "~~/types/common";
import { DELETE, GET, POST, PUT } from "~~/types/method";
import { Weight, WeightCalendarResponseBody, WeightFavoriteListResponseBody, WeightItemRequestParam, WeightItemResponseBody, WeightReadRequestParam, WeightReadResponseBody, WeightRemoveRequestParams, WeightRequestBody } from "~~/types/weight";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/weight`;
const headers = {
  "Content-Type": "application/json"
}
/**
 * weight 운동 조회 (근력 운동 조회) - GET
 */
function readWeightItem(params: WeightItemRequestParam) {
  return useFetch<WeightItemResponseBody>("/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * weight 달력 조회 - GET
 */
function readWeightCalendarList(params: WeightReadRequestParam) {
  return useFetch<WeightCalendarResponseBody>("/calendar", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * Activity 조회 - GET
 */
function readWeightList(params: WeightReadRequestParam) {
  return useFetch<WeightReadResponseBody>("/activity", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * weight 운동 조회 (근력 운동 조회) - POST
 */
function createWeightItem(body: any) {
  return useFetch("/insert", {
    baseURL,
    method: POST,
    body: body,
  });
}

/**
 * 근력운동 삭제 - DELETE
 */
function deleteWeightItem(params: WeightRemoveRequestParams) {
  return useFetch("/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 근력운동 수정 - PUT
 */
function updateWeightItem(body: Weight) {
  return useFetch("/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}


/**
 * 즐겨찾기 조회(Weight)
 */
function readFavoritWeightList(){
  return useFetch<WeightFavoriteListResponseBody>("/favorite",{
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
