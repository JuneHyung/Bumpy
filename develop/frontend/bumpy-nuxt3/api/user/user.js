import { POST } from "~~/types/method";

const baseURL = 'http://222.112.251.124:8899/api';

function fetchLogin(body){
  return useFetch('/auth/login', {
    baseURL,
    method: POST,
    body,
  })
}

function fetchLogout(){
  return useFetch('/auth/logout', {
    baseURL,
    method: POST,
  })
}
export {fetchLogin, fetchLogout}