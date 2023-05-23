import { api } from "../axios"

function fetchLogin(params){
  return api.post('/api/auth/login', params)
}

export {fetchLogin}