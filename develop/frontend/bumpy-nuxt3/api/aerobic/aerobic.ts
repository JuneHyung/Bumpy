// import { CREATE, READ } from "~~/types/method";

import { AerobicDeleteRequestParam, AerobicReadRequestParam, AerobicRequestBody } from "~~/types/aerobic";
import { GET, POST, PUT, DELETE } from "~~/types/method";

const baseURL = "http://222.112.251.124:8899/api";
const headers = {
  "Content-Type": "application/json"
}
/**
 * 유산소 운동 조회 (근력 운동 조회) - GET
 * @param {{stdDate: string}} params
 * @returns
 */
function readAerobicItem(params: AerobicReadRequestParam) {
return useFetch("/aerobic/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * 유산소 달력 조회 - GET
 * @returns
 */
function readAerobicCalendarList(params: AerobicReadRequestParam) {
  return useFetch("/aerobic/calendar", {
    baseURL,
    method: GET,
    headers,
    params
  });
}

/**
 * Activity 조회 - GET
 * @returns
 */
function readAerobicList(params: AerobicReadRequestParam) {
  return useFetch("/aerobic/activity", {
    baseURL,
    method: GET,
    headers,
    params
  });
}

/**
 * 유산소운동 추가 - POST
 * @param {{
 * "stdDate" : string,
 * "seq" : number,
 * "name" : string,
 * "kcal" : number
 * "time" : number,
 * "inclineStart" : number,
 * "inclineEnd" :number,
 * "speedStart" : number,
 * "speedEnd" : number,
 * "memo" : string,
 * "picture" : null
 * }} body
 * @returns
 */
function createAerobicItem(body: AerobicRequestBody) {
  return useFetch("/aerobic/insert", {
    baseURL,
    method: POST,
    headers,
    body,
  });
}

/**
 * 유산소운동 삭제 - GET
 * @param {{
 * stdDate: string,
 * seq: number,
 * }} body
 * @returns
 */
function deleteAerobicItem(params: AerobicDeleteRequestParam) {
  return useFetch("/aerobic/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 유산소운동 수정 - POST
 * @param {{
 * "stdDate" : string,
* "seq" : number,
* "name" : string,
* "kcal" : number
* "time" : number,
* "inclineStart" : number,
* "inclineEnd" :number,
* "speedStart" : number,
* "speedEnd" : number,
* "memo" : string,
* "picture" : null
* }} body
 * @returns
 */
function updateAerobicItem(body: AerobicRequestBody) {
  return useFetch("/aerobic/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}
export {
  readAerobicItem, 
  readAerobicCalendarList, 
  readAerobicList, 
  createAerobicItem, 
  deleteAerobicItem, 
  updateAerobicItem
};
