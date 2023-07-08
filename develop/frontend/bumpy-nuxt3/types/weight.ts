import { InputNumber, InputText, InputTextarea } from "./input";

// enum Measure { 
//   m1= 'kg',
//   m2= 'pd'
// }

interface Weight{
  seq?: number,
  name?: string,
  weightStart?: number,
  weightEnd?: number,
  repsStart?: number,
  repsEnd?: number,
  pollWeight?: number,
  setReps?: number,
  measure?: 0 | 1,
  memo?: string,
}
type WeightList = Weight[];

type WeightFormData = {
  name: InputText,
  weightStart: InputNumber,
  weightEnd: InputNumber,
  repsStart: InputNumber,
  repsEnd: InputNumber,
  pollWeight: InputNumber,
  setReps: InputNumber,
  measure: 0 | 1,
  memo: InputTextarea
}


interface WeightRequestBody extends Weight{
  stdDate?: string
}

interface WeightRemoveRequestParams {
  stdDate?: string,
  seq?: number
}

type WeightReadRequestParam{
  stdDate: string,
}

export {
  Weight,
  WeightList,
  WeightFormData,
  WeightRequestBody,
  WeightReadRequestParam,
  WeightRemoveRequestParams
}