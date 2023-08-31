import { CommonCalendarData, FavoriteListItem, ResponseBody } from "./common";
import { InputSelectbox, InputText, InputTextarea } from "./input";
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
  seq?: string
}

type WeightRequestBody = Weight;
type WeightItemRequestParam = {
  stdDate?: string,
  seq?: string,
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