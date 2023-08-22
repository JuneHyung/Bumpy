import { useUserStore } from "~~/store/user";
import { MessageResponse, OnlyMessageResponse, ResponseBody, customError } from "~~/types/common";
import { DELETE, GET, POST, PUT } from "~~/types/method";
import { UserUpdateRequestBody, passwordChkRequest, UserInfo } from "~~/types/user";

const baseURL = import.meta.env.VITE_BACKEND_URL as string;
const headers = {
  "Content-Type": "application/json"
}
export function fetchLogin(body: FormData){
  return useFetch<MessageResponse, Error, string, 'post'>('/auth/login', {
    baseURL,
    method: POST,
    body,
  })
}

export function fetchLogout(){
  return useFetch<MessageResponse, Error, string, 'post'>('/auth/logout', {
    baseURL,
    method: POST,
  })
}

export const fetchUserPasswordVerifyCheck = (body: passwordChkRequest) =>{
  return useFetch<MessageResponse, customError, string, 'post'>('/user/password/check', {
    baseURL,
    method: POST,
    body,
  })
}

export const fetchUserPasswordCheck = (body: passwordChkRequest) =>{
  const userStore = useUserStore();
  return useFetch<MessageResponse, customError, string, 'post'>('/user/check', {
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