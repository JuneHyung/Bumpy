import { MealCalendarResponseBody, MealItemRequestBody, MealItemRequestParam, MealItemResposneBody, MealListRequestParam, MealListResponseBody } from "~~/types/meal";
import { DELETE, GET, POST, PUT } from "~~/types/method";

/**
 * 식단 조회 - GET
 * @param {{
 * stdDate: string,
 * }} params
 * @returns
 */
const baseURL = "http://222.112.251.124:8899/api";
const headers = {
  "Content-Type": "application/json"
}

export function readMealItem(params: MealItemRequestParam) {
  return useFetch<MealItemResposneBody>("/meal/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

export function readMealCalendarList(params: MealListRequestParam){
  return useFetch<MealCalendarResponseBody>("/meal/calendar", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

export function readMealActivityList(params: MealListRequestParam){
  return useFetch<MealListResponseBody>("/meal/activity", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * 식단 추가 - POST
 * @param {{
 * "stdDate" : string,
 * "seq" : number,
 * "name" : string,
 * "order" : number,
 * "time" : string,
 * "kcal" : number,
 * "water" : number,
 * "memo" : string,
 * "picture" : null,
 * "food" : []
 * }} params
 * @returns
 */
export function createMealItem(body: MealItemRequestBody) {
  return useFetch("/meal/insert", {
    baseURL,
    method: POST,
    headers,
    body,
  });
}

/**
 * 식단 삭제 - GET
 * @param {{
 * "stdDate" : string,
 * "seq" : number,
 * }} params
 * @returns
 */
export function deleteMealItem(params: MealItemRequestParam) {
  return useFetch("/meal/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 식단 수정 - POST
 * @param {{
 * "stdDate" : string,
 * "seq" : number,
 * "name" : string,
 * "order" : number,
 * "time" : string,
 * "kcal" : number,
 * "water" : number,
 * "memo" : string,
 * "picture" : null,
 * "food" : []
 * }} params
 * @returns
 */
export function updateMealItem(body: MealItemRequestBody) {
  return useFetch("/meal/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}
