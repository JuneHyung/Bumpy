import { POST } from "~~/types/method";

const baseURL = 'http://222.112.251.124:8899/api';
async function createCheckDuplicateId(body){
  return useFetch('/signup/user/check', {
    baseURL,
    method: POST,
    body,
  })
}
// email 발송
async function createEmailVerificationCode(body){
  return await useFetch('/signup/email/send', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
async function createCheckCertificateEmail(body){
  return await useFetch('/signup/email/validate', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
async function createSignUp(body){
  return await useFetch('/signup/submit', {
    baseURL,
    method: POST,
    body,
  })
}
export {createCheckDuplicateId, createEmailVerificationCode , createCheckCertificateEmail, createSignUp}