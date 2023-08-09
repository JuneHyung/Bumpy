import { useUserStore } from "~~/store/user";
import { DELETE, POST, PUT } from "~~/types/method";

const baseURL = 'http://222.112.251.124:8899/api';
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