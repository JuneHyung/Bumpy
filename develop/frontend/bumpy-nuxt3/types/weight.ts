import { ResponseBody } from "./common";
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
  stdDate?: string,
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

interface WeightRemoveRequestParams {
  stdDate?: string,
  seq?: number
}

interface CalendarData {
  title:string,
  date: string,
}

type WeightRequestBody = Weight;
type WeightItemRequestParam = {
  stdDate?: string,
  seq?: number,
}
type WeightReadRequestParam={
  stdDate: string,
}
type WeightItemResponseBody = ResponseBody<Weight>
type WeightCalendarResponseBody = ResponseBody<CalendarData>
type WeightReadResponseBody = ResponseBody<WeightList>
export {
  Weight,
  WeightList,
  WeightFormData,
  WeightRequestBody,
  WeightItemRequestParam,
  WeightReadRequestParam,
  WeightRemoveRequestParams,
  WeightItemResponseBody,
  WeightCalendarResponseBody,
  WeightReadResponseBody,
}