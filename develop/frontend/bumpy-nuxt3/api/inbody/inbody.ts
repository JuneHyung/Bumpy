import { InbodyCalendarItemResponseBody, InbodyItemRequestBody, InbodyItemRequestParam, InbodyItemResponseBody, InbodyListRequestParam, InbodyListResponseBody, } from "~~/types/inbody";
import { DELETE, GET, POST, PUT } from "~~/types/method";

const baseURL = "http://222.112.251.124:8899/api";
const headers = {
  "Content-Type": "application/json"
}


/**
 * inbody 조회 - GET
 * @param {{
 * stdDate: string,
 * }} params
 * @returns
 */
export function readInbodyItem(params: InbodyListRequestParam) {
  return useFetch<InbodyItemResponseBody>("/inbody/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

export function readInbodyCalendarList(params: InbodyListRequestParam){
  return useFetch<InbodyCalendarItemResponseBody>("/inbody/calendar", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}


export function readInbodyActivityList(params: InbodyListRequestParam){
  return useFetch<InbodyListResponseBody>("/inbody/activity", {
    baseURL,
    method: GET,
    headers,
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
export function createInbodyItem(body: InbodyItemRequestBody) {
  return useFetch("/inbody/insert", {
    baseURL,
    method: POST,
    headers,
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
export function deleteInbodyItem(params: InbodyListRequestParam) {
  return useFetch("/inbody/delete", {
    baseURL,
    method: DELETE,
    headers,
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
export function updateInbodyItem(body: InbodyItemRequestBody) {
  return useFetch("/inbody/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}