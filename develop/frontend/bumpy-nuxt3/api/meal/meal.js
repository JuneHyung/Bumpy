import { GET, POST } from "~~/types/method";

/**
 * 식단 조회 - GET
 * @param {{
 * stdDate: string,
 * }} params
 * @returns
 */
function readMealItem(params) {
  return useFetch("/meal/search", {
    baseURL,
    method: GET,
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
function createMealItem(body) {
  return useFetch("/meal/insert", {
    baseURL,
    method: POST,
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
function deleteMealItem(params) {
  return useFetch("/meal/delete", {
    baseURL,
    method: GET,
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
function updateMealItem(body) {
  return useFetch("/meal/update", {
    baseURL,
    method: POST,
    body,
  });
}

export { readMealItem, createMealItem, deleteMealItem, updateMealItem };
