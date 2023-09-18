import { OnlyMessageResponse, YoutubeKeywordRequest, YoutubeResponseBody, customError } from "~~/types/common";
import { DELETE, GET, POST, PUT } from "~~/types/method";
import { Weight, WeightCalendarResponseBody, WeightFavoriteListResponseBody, WeightItemRequestParam, WeightItemResponseBody, WeightReadRequestParam, WeightReadResponseBody, WeightRemoveRequestParams, WeightRequestBody } from "~~/types/weight";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/weight`;
const headers = {
  "Content-Type": "application/json"
}
/**
 * weight 운동 조회 (근력 운동 조회) - GET
 */
export function readWeightItem(params: WeightItemRequestParam) {
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
export function readWeightCalendarList(params: WeightReadRequestParam) {
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
export function readWeightList(params: WeightReadRequestParam) {
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
export function createWeightItem(body: WeightRequestBody) {
  return useFetch<OnlyMessageResponse, customError, string, 'post'>("/insert", {
    baseURL,
    method: POST,
    body: body,
  });
}

/**
 * 근력운동 삭제 - DELETE
 */
export function deleteWeightItem(params: WeightRemoveRequestParams) {
  return useFetch<OnlyMessageResponse, customError, string, 'delete'>("/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 근력운동 수정 - PUT
 */
export function updateWeightItem(body: Weight) {
  return useFetch<OnlyMessageResponse, customError, string, 'put'>("/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}


/**
 * 즐겨찾기 조회(Weight)
 */
export function readFavoritWeightList(){
  return useFetch<WeightFavoriteListResponseBody>("/favorite",{
    baseURL,
    method:GET,
    headers,
  })
}

export function getWeightYoutubeList(params: YoutubeKeywordRequest){
  return useFetch<YoutubeResponseBody>("/youtube", {
    baseURL,
    method:GET,
    headers,
    params
  })
}