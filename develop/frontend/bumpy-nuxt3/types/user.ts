import { InputDate, InputPassword, InputSelectbox, InputText } from "./input";

export interface UserFormData {
  userId: InputText;
  password: InputPassword;
  passwordChk: InputPassword;
  email: InputText;
  username: InputText;
  birth: InputDate;
  gender: InputSelectbox;
  phoneFirst: InputText;
  phoneSecond: InputText;
  phoneThird: InputText;
  zipCode: InputText;
  address: InputText;
  addressDetail: InputText;
}
export interface UserInfo {
  userId: string,
  username: string,
  email: string,
  birth: string,
  gender: string,
  phoneNumber: string,
  zipCode: string
  address: string,
  addressDetail: string,
}

export interface UserPageInfo {
  userId: string,
  username: string,
  email: string,
  birth: string,
  gender: string,
  phoneNumber?: string,
  phoneFirst?: string,
  phoneSecond?: string,
  phoneThird?: string,
  zipCode: string
  address: string,
  addressDetail: string,
}

export type userLoginFormData = Pick<UserFormData, "userId" | "password">;
export type passwordChkRequest= {
  password:string,
}
export type UserUpdateRequestBody={
  password?: string,
  gender?: string,
  birth?: string,
  phoneNumber?: string,
  zipCode?: string,
  address?: string,
  addressDetail?: string,
}
export type UserIdChkRequestBody = Pick<UserInfo, "userId">
export type UserEmailChkRequestBody = Pick<UserInfo, "userId"|"email">
export type UserEmailChkResponseBody = {verificationCode: string}
export type UserEmailCertificateRequestBody = Pick<UserInfo, "userId"|"email"> & {verifyCode: string}

export type SignUpRequestBody = UserInfo & {password:string}