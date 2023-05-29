const baseURL = 'http://222.112.251.124:8899/api';
function fetchLogin(method, body){
  return useFetch('/auth/login', {
    baseURL,
    method,
    body,
  })
}

export {fetchLogin}