import { AerobicCalendarResponseBody, AerobicDeleteRequestParam, AerobicFavoriteListResponseBody, AerobicItemResponseBody, AerobicReadRequestParam, AerobicReadResponseBody, AerobicRequestBody } from "~~/types/aerobic";
import { OnlyMessageResponse, YoutubeKeywordRequest, YoutubeResponseBody, customError } from "~~/types/common";
import { GET, POST, PUT, DELETE } from "~~/types/method";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/aerobic`;
const headers = {
  "Content-Type": "application/json"
}
/**
 * 유산소 운동 조회 (근력 운동 조회) - GET
 */
export function readAerobicItem(params: AerobicReadRequestParam) {
return useFetch<AerobicItemResponseBody>("/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * 유산소 달력 조회 - GET
 */
export function readAerobicCalendarList(params: AerobicReadRequestParam) {
  return useFetch<AerobicCalendarResponseBody>("/calendar", {
    baseURL,
    method: GET,
    headers,
    params
  });
}

/**
 * Activity 조회 - GET
 */
export function readAerobicActivityList(params: AerobicReadRequestParam) {
  return useFetch<AerobicReadResponseBody>("/activity", {
    baseURL,
    method: GET,
    headers,
    params
  });
}

/**
 * 유산소운동 추가 - POST
 */
export function createAerobicItem(body: AerobicRequestBody) {
  return useFetch<OnlyMessageResponse, customError, string, 'post'>("/insert", {
    baseURL,
    method: POST,
    headers,
    body,
  });
}

/**
 * 유산소운동 삭제 - GET
 */
export function deleteAerobicItem(params: AerobicDeleteRequestParam) {
  return useFetch<OnlyMessageResponse, customError, string, 'delete'>("/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 유산소운동 수정 - POST
 */
export function updateAerobicItem(body: AerobicRequestBody) {
  return useFetch<OnlyMessageResponse, customError, string, 'put'>("/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}


export function readFavoritAerobicList(){
  return useFetch<AerobicFavoriteListResponseBody>("/favorite",{
    baseURL,
    method:GET,
    headers
  })
}

export function getAerobicYoutubeList(params: YoutubeKeywordRequest){
  return useFetch<YoutubeResponseBody>("/youtube", {
    baseURL,
    method:GET,
    headers,
    params
  })
}