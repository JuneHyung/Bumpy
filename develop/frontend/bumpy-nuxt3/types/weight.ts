import { CommonCalendarData, FavoriteListItem, ResponseBody } from "./common";
import { InputNumber, InputSelectbox, InputText, InputTextarea } from "./input";

// enum Measure { 
//   m1= 'kg',
//   m2= 'pd'
// }

interface Weight{
  seq?: string,
  name?: string,
  weightStart?: string,
  weightEnd?: string,
  repsStart?: string,
  repsEnd?: string,
  pollWeight?: string,
  setReps?: string,
  measure?: string,
  memo?: string,
  stdDate?: string,
  picture?: ImageData[],
}
type WeightList = Weight[];

type WeightFormData = {
  name: InputText,
  weightStart: InputText,
  weightEnd: InputText,
  repsStart: InputText,
  repsEnd: InputText,
  pollWeight: InputText,
  setReps: InputText,
  measure: InputSelectbox,
  memo: InputTextarea
  picture?: {value: ImageData[]}
}

interface WeightRemoveRequestParams {
  stdDate?: string,
  seq?: number
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
type WeightCalendarResponseBody = ResponseBody<CommonCalendarData[]>
type WeightReadResponseBody = ResponseBody<WeightList>
type WeightFavoriteListResponseBody = ResponseBody<FavoriteListItem[]>
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
  WeightFavoriteListResponseBody,
}