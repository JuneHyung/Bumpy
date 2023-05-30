import { CREATE, READ } from "~~/types/method";

const baseURL = "http://222.112.251.124:8899/api";

/**
 * 유산소 운동 조회 (근력 운동 조회) - GET
 * @param {{stdDate: string}} params
 * @returns
 */
function readCardioList(params) {
  return useFetch("/cardio/search", {
    baseURL,
    method: GET,
    params,
  });
}

/**
 * 유산소 달력 조회 - GET
 * @returns
 */
function readCardioCalendarList() {
  return useFetch("/cardio/calendar", {
    method: GET,
  });
}

/**
 * Activity 조회 - GET
 * @returns
 */
function readActivityList() {
  return useFetch("/cardio/activity", {
    method: GET,
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
function createCardioItem(body) {
  return useFetch("/cardio/insert", {
    baseURL,
    method: POST,
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
function deleteCardioItem(params) {
  return useFetch("/cardio/delete", {
    baseURL,
    method: GET,
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
function updateCardioItem(body) {
  return useFetch("/cardio/udpate", {
    baseURL,
    method: POST,
    body,
  });
}
export {
  readCardioList, 
  readCardioCalendarList, 
  readActivityList, 
  createCardioItem, 
  deleteCardioItem, 
  updateCardioItem
};
