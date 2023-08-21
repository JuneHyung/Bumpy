import { OnlyMessageResponse, customError } from "~~/types/common";
import { POST } from "~~/types/method";
import { SignUpRequestBody, UserEmailCertificateRequestBody, UserEmailChkRequestBody, UserEmailChkResponseBody, UserIdChkRequestBody } from "~~/types/user";

const baseURL = `${import.meta.env.VITE_BACKEND_URL}/signup`;
async function createCheckDuplicateId(body: UserIdChkRequestBody){
  return useFetch<OnlyMessageResponse, customError, string, 'post'>('/user/check', {
    baseURL,
    method: POST,
    body,
  })
}
// email 발송
async function createEmailVerificationCode(body: UserEmailChkRequestBody){
  return await useFetch<UserEmailChkResponseBody, customError, string, 'post'>('/email/send', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
async function createCheckCertificateEmail(body: UserEmailCertificateRequestBody){
  return await useFetch<OnlyMessageResponse, customError, string, 'post'>('/email/validate', {
    baseURL,
    method: POST,
    body,
  })
}

// email 인증
async function createSignUp(body: SignUpRequestBody){
  return await useFetch<OnlyMessageResponse, customError, string, 'post'>('/submit', {
    baseURL,
    method: POST,
    body,
  })
}
export {createCheckDuplicateId, createEmailVerificationCode , createCheckCertificateEmail, createSignUp}