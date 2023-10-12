import { CommonCalendarData, FavoriteListItem, ResponseBody } from "./common";
import { InputSelectbox, InputText, InputTextarea } from "./input";
export interface Weight{
  seq: string,
  name: string,
  weightStart: string,
  weightEnd: string,
  repsStart: string,
  repsEnd: string,
  pollWeight: string,
  setReps: string,
  measure: string,
  memo: string,
  stdDate: string,
  picture: ImageData[],
}

export type WeightFormData = {
  name: InputText,
  weightStart: InputText,
  weightEnd: InputText,
  repsStart: InputText,
  repsEnd: InputText,
  pollWeight: InputText,
  setReps: InputText,
  measure: InputSelectbox,
  memo: InputTextarea
  picture: {value: ImageData[]}
}
export type WeightList = Weight[];

export interface WeightRemoveRequestParams {
  stdDate?: string,
  seq?: string
}

export type WeightRequestBody = Partial<Weight>;
export type WeightItemRequestParam = {
  stdDate?: string,
  seq?: string,
}
export type WeightReadRequestParam={
  stdDate: string,
}
export type WeightItemResponseBody = ResponseBody<Weight>
export type WeightCalendarResponseBody = ResponseBody<CommonCalendarData[]>
export type WeightReadResponseBody = ResponseBody<WeightList>
export type WeightFavoriteListResponseBody = ResponseBody<FavoriteListItem[]>