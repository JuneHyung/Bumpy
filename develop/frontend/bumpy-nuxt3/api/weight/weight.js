import { DELETE, GET, POST, PUT } from "~~/types/method";

const baseURL = "http://222.112.251.124:8899/api";

/**
 * weight 운동 조회 (근력 운동 조회) - GET
 * @param {{stdDate: string}} params
 * @returns
 */
function readWeightItem(params) {
  return useFetch("/weight/search", {
    baseURL,
    method: GET,
    params,
  });
}

/**
 * weight 달력 조회 - GET
 * @returns
 */
function readWeightCalendarList() {
  return useFetch("/weight/calendar", {
    method: GET,
  });
}

/**
 * Activity 조회 - GET
 * @returns
 */
function readWeightList() {
  return useFetch("/weight/activity", {
    method: GET,
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
function createWeightItem(body) {
  return useFetch("/weight/insert", {
    baseURL,
    method: POST,
    body,
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
function deleteWeightItem(body) {
  return useFetch("/weight/delete", {
    baseURL,
    method: DELETE,
    body,
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
function updateWeightItem(body) {
  return useFetch("/weight/update", {
    baseURL,
    method: PUT,
    body,
  });
}
export {
  readWeightItem, 
  readWeightCalendarList, 
  readWeightList, 
  createWeightItem, 
  deleteWeightItem, 
  updateWeightItem
};
