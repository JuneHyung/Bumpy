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
  phoneFirst: InputNumber;
  phoneSecond: InputNumber;
  phoneThird: InputNumber;
  zipCode: InputNumber;
  address: InputText;
  addressDetail: InputText;
}

type userLoginFormData = Pick<userFormData, "id" | "password">;


export {userLoginFormData, LoginResponse, userFormData}