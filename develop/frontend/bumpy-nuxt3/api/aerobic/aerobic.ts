// import { CREATE, READ } from "~~/types/method";

import { AerobicDeleteRequestParam, AerobicFavoriteListResponseBody, AerobicReadRequestParam, AerobicRequestBody } from "~~/types/aerobic";
import { GET, POST, PUT, DELETE } from "~~/types/method";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/aerobic`;
const headers = {
  "Content-Type": "application/json"
}
/**
 * 유산소 운동 조회 (근력 운동 조회) - GET
 */
function readAerobicItem(params: AerobicReadRequestParam) {
return useFetch("/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * 유산소 달력 조회 - GET
 */
function readAerobicCalendarList(params: AerobicReadRequestParam) {
  return useFetch("/calendar", {
    baseURL,
    method: GET,
    headers,
    params
  });
}

/**
 * Activity 조회 - GET
 */
function readAerobicActivityList(params: AerobicReadRequestParam) {
  return useFetch("/activity", {
    baseURL,
    method: GET,
    headers,
    params
  });
}

/**
 * 유산소운동 추가 - POST
 */
function createAerobicItem(body: AerobicRequestBody) {
  return useFetch("/insert", {
    baseURL,
    method: POST,
    headers,
    body,
  });
}

/**
 * 유산소운동 삭제 - GET
 */
function deleteAerobicItem(params: AerobicDeleteRequestParam) {
  return useFetch("/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 유산소운동 수정 - POST
 */
function updateAerobicItem(body: AerobicRequestBody) {
  return useFetch("/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}


function readFavoritAerobicList(){
  return useFetch<AerobicFavoriteListResponseBody>("/favorite",{
    baseURL,
    method:GET,
    headers
  })
}
export {
  readAerobicItem, 
  readAerobicCalendarList, 
  readAerobicActivityList, 
  createAerobicItem, 
  deleteAerobicItem, 
  updateAerobicItem,
  readFavoritAerobicList
};
