import { OnlyMessageResponse, customError } from "~~/types/common";
import { MealCalendarResponseBody, MealItemRequestBody, MealItemRequestParam, MealItemResposneBody, MealListRequestParam, MealListResponseBody } from "~~/types/meal";
import { DELETE, GET, POST, PUT } from "~~/types/method";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/meal`;
const headers = {
  "Content-Type": "application/json"
}

/**
 * 식단 조회 - GET
*/
export function readMealItem(params: MealItemRequestParam) {
  return useFetch<MealItemResposneBody>("/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

export function readMealCalendarList(params: MealListRequestParam){
  return useFetch<MealCalendarResponseBody>("/calendar", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

export function readMealActivityList(params: MealListRequestParam){
  return useFetch<MealListResponseBody>("/activity", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

/**
 * 식단 추가 - POST
 */
export function createMealItem(body: MealItemRequestBody) {
  return useFetch<OnlyMessageResponse, customError, string, 'post'>("/insert", {
    baseURL,
    method: POST,
    headers,
    body,
  });
}

/**
 * 식단 삭제 - GET
 */
export function deleteMealItem(params: MealItemRequestParam) {
  return useFetch<OnlyMessageResponse, customError, string, 'delete'>("/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * 식단 수정 - POST
 */
export function updateMealItem(body: MealItemRequestBody) {
  return useFetch<OnlyMessageResponse, customError, string, 'put'>("/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}
