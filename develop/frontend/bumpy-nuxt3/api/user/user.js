import { useUserStore } from "~~/store/user";
import { DELETE, GET, POST, PUT } from "~~/types/method";

const baseURL = import.meta.env.VITE_BACKEND_URL;
const headers = {
  "Content-Type": "application/json"
}
export function fetchLogin(body){
  return useFetch('/auth/login', {
    baseURL,
    method: POST,
    body,
  })
}

export function fetchLogout(){
  return useFetch('/auth/logout', {
    baseURL,
    method: POST,
  })
}

export const fetchUserPasswordVerifyCheck = (body) =>{
  return useFetch('/user/password/check', {
    baseURL,
    method: POST,
    body,
  })
}

export const fetchUserPasswordCheck = (body) =>{
  const userStore = useUserStore();
  return useFetch('/user/check', {
    baseURL,
    method: POST,
    body,
    onResponse({ request, response, options }) {
      // Process the response data
      response.status===200 ? userStore.setIsPass(true) : userStore.setIsPass(false)
    },
  })
}

export const putUserInfo = (body) => {
  return useFetch('/user/update',{
    baseURL,
    method: PUT,
    headers,
    body,
  })
}

export const deleteUserInfo = () =>{
  return useFetch('/user/delete', {
    baseURL,
    method: DELETE,
  })
}

export const getUserPageInfo = async () =>{
  return useFetch('/user/info',{
    baseURL,
    method: GET,
    headers,
  })
}