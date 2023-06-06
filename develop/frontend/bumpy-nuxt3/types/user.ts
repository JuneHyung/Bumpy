import { InputPassword, InputText } from "./input";

interface LoginResponse<T> {
  code: string,
  message: string,
}

interface userFormData {
  id: InputText;
  password: InputPassword;
}

export {userFormData, LoginResponse}