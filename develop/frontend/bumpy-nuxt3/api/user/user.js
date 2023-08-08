import { useUserStore } from "~~/store/user";
import { POST } from "~~/types/method";

const baseURL = 'http://222.112.251.124:8899/api';

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