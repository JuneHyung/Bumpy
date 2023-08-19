import { POST } from "~~/types/method";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/signup`;
async function createCheckDuplicateId(body){
  return useFetch('/user/check', {
    baseURL,
    method: POST,
    body,
  })
}
// email 발송
async function createEmailVerificationCode(body){
  return await useFetch('/email/send', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
async function createCheckCertificateEmail(body){
  return await useFetch('/email/validate', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
async function createSignUp(body){
  return await useFetch('/submit', {
    baseURL,
    method: POST,
    body,
  })
}
export {createCheckDuplicateId, createEmailVerificationCode , createCheckCertificateEmail, createSignUp}