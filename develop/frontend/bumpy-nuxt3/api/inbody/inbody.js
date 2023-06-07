import { GET, POST } from "~~/types/method";

/**
 * inbody 조회 - GET
 * @param {{
 * stdDate: string,
 * }} params
 * @returns
 */
function readInbodyList(params) {
  return useFetch("/inbody/search", {
    baseURL,
    method: GET,
    params,
  });
}

/**
 * inbody 추가 - POST
 * @param {{
 * "stdDate" :string,
 * "height" : number,
 * "weight" : number,
 * "muscle" : number,
 * "fat" : number,
 * "score" : number,
 * "bmi" : number,
 * "fatPercent" : number,
 * "picture" : null
 * }} params
 * @returns
 */
function createInbodyItem(body) {
  return useFetch("/inbody/insert", {
    baseURL,
    method: POST,
    body,
  });
}

/**
 * inbody 삭제 - GET
 * @param {{
 * "stdDate" : string,
 * }} params
 * @returns
 */
function deleteInbodyItem(params) {
  return useFetch("/inbody/delete", {
    baseURL,
    method: GET,
    params,
  });
}

/**
 * inbody 수정 - POST
 * @param {{
 * "stdDate" :string,
 * "height" : number,
 * "weight" : number,
 * "muscle" : number,
 * "fat" : number,
 * "score" : number,
 * "bmi" : number,
 * "fatPercent" : number,
 * "picture" : null
 * }} params
 * @returns
 */
function updateInbodyItem(body) {
  return useFetch("/inbody/update", {
    baseURL,
    method: POST,
    body,
  });
}

export { readInbodyList, createInbodyItem, deleteInbodyItem, updateInbodyItem };
