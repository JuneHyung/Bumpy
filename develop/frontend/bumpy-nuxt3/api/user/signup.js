import { POST } from "~~/types/method";

const baseURL = 'http://222.112.251.124:8899/api';
function createCheckDuplicateId(body){
  return useFetch('/signup/user/check', {
    baseURL,
    method: POST,
    body,
  })
}
// email 발송
function createEmailVerificationCode(body){
  return useFetch('/signup/email/send', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
function createCheckCertificateEmail(body){
  return useFetch('/signup/email/validate', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
function createSignUp(body){
  return useFetch('/signup/submit', {
    baseURL,
    method: POST,
    body,
  })
}
export {createCheckDuplicateId, createEmailVerificationCode , createCheckCertificateEmail, createSignUp}