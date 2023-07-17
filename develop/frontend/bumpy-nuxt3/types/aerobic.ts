import { InputNumber, InputText, InputTextarea } from "./input";

interface Aerobic{
  seq?: number,
  name?: string,
  kcal?: number,
  time?: number,
  inclineStart?: number,
  inclineEnd?: number,
  speedStart?: number,
  speedEnd?: number,
  memo?: string,
  stdDate?: string,
}
type AerobicList = Aerobic[];

type AerobicFormData = {
  name: InputText,
  kcal: InputNumber,
  time: InputNumber,
  inclineStart: InputNumber,
  inclineEnd: InputNumber,
  speedStart: InputNumber,
  speedEnd: InputNumber,
  memo: InputTextarea,
}

interface AerobicRequestBody extends Aerobic{
  stdDate?: string
}

type AerobicReadRequestParam {
  stdDate: string,
}
type AerobicDeleteRequestParam{
  stdDate: string,
  seq: string
}
export {
  Aerobic,
  AerobicList,
  AerobicFormData,
  AerobicReadRequestParam,
  AerobicDeleteRequestParam,
  AerobicRequestBody
}