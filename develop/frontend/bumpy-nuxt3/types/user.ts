import { InputDate, InputNumber, InputPassword, InputSelectbox, InputText } from "./input";

interface LoginResponse<T> {
  code: string,
  message: string,
}

interface userFormData {
  id: InputText;
  password: InputPassword;
  passwordChk: InputPassword;
  email: InputText;
  name: InputText;
  birth: InputDate;
  gender: InputSelectbox;
  phoneFirst: InputText;
  phoneSecond: InputText;
  phoneThird: InputText;
  zipCode: InputText;
  address: InputText;
  addressDetail: InputText;
}

type userLoginFormData = Pick<userFormData, "id" | "password">;


export {userLoginFormData, LoginResponse, userFormData}