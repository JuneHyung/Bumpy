import { POST } from "~~/types/method";

const baseURL = 'http://222.112.251.124:8899/api';
function fetchLogin(body){
  return useFetch('/auth/login', {
    baseURL,
    method: POST,
    body,
  })
}

export {fetchLogin}