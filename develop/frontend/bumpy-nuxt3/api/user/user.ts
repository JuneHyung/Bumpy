import { useUserStore } from "~~/store/user";
import { OnlyMessageResponse, ResponseBody, customError } from "~~/types/common";
import { DELETE, GET, POST, PUT } from "~~/types/method";
import { UserUpdateRequestBody, passwordChkRequest, UserInfo } from "~~/types/user";

const baseURL = import.meta.env.VITE_BACKEND_URL as string;
const headers = {
  "Content-Type": "application/json"
}
export function fetchLogin(body: FormData){
  return useFetch<OnlyMessageResponse, Error, string, 'post'>('/auth/login', {
    baseURL,
    method: POST,
    body,
  })
}

export function fetchLogout(){
  return useFetch<OnlyMessageResponse, Error, string, 'post'>('/auth/logout', {
    baseURL,
    method: POST,
  })
}

export const fetchUserPasswordVerifyCheck = (body: passwordChkRequest) =>{
  return useFetch<OnlyMessageResponse, customError, string, 'post'>('/user/password/check', {
    baseURL,
    method: POST,
    body,
  })
}

export const fetchUserPasswordCheck = (body: passwordChkRequest) =>{
  const userStore = useUserStore();
  return useFetch<OnlyMessageResponse, customError, string, 'post'>('/user/check', {
    baseURL,
    method: POST,
    body,
    onResponse({ request, response, options }) {
      // Process the response data
      response.status===200 ? userStore.setIsPass(true) : userStore.setIsPass(false)
    },
  })
}

export const putUserInfo = (body: UserUpdateRequestBody) => {
  return useFetch<OnlyMessageResponse, Error, string, 'put'>('/user/update',{
    baseURL,
    method: PUT,
    headers,
    body,
  })
}

export const deleteUserInfo = () =>{
  return useFetch<OnlyMessageResponse, Error, string, 'delete'>('/user/delete', {
    baseURL,
    method: DELETE,
  })
}

export const getUserPageInfo = async () =>{
  return useFetch<ResponseBody<UserInfo>>('/user/info',{
    baseURL,
    method: GET,
    headers,
  })
}

export const getUserHeartBeat = async () => {
  return useFetch<OnlyMessageResponse>('/user/heartbeat',{
    baseURL,
    method: GET,
    headers,
  })
}