import { OnlyMessageResponse, customError } from "~~/types/common";
import { InbodyCalendarItemResponseBody, InbodyItemRequestBody, InbodyItemResponseBody, InbodyListResponseBody, InbodyRequestParam, } from "~~/types/inbody";
import { DELETE, GET, POST, PUT } from "~~/types/method";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/inbody`;
const headers = {
  "Content-Type": "application/json"
}

/**
 * inbody 조회 - GET
 */
export function readInbodyItem(params: InbodyRequestParam) {
  return useFetch<InbodyItemResponseBody>("/search", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}

export function readInbodyCalendarList(params: InbodyRequestParam){
  return useFetch<InbodyCalendarItemResponseBody>("/calendar", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}


export function readInbodyActivityList(params: InbodyRequestParam){
  return useFetch<InbodyListResponseBody>("/activity", {
    baseURL,
    method: GET,
    headers,
    params,
  });
}
/**
 * inbody 추가 - POST
 */
export function createInbodyItem(body: InbodyItemRequestBody) {
  return useFetch<OnlyMessageResponse, customError, string, 'post'>("/insert", {
    baseURL,
    method: POST,
    headers,
    body,
  });
}

/**
 * inbody 삭제 - GET
 */
export function deleteInbodyItem(params: InbodyRequestParam) {
  return useFetch<OnlyMessageResponse, customError, string, 'delete'>("/delete", {
    baseURL,
    method: DELETE,
    headers,
    params,
  });
}

/**
 * inbody 수정 - POST
 */
export function updateInbodyItem(body: InbodyItemRequestBody) {
  return useFetch<OnlyMessageResponse, customError, string, 'put'>("/update", {
    baseURL,
    method: PUT,
    headers,
    body,
  });
}