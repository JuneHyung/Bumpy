import { CommonCalendarData, FavoriteListItem, ResponseBody } from "./common";
import { InputNumber, InputText, InputTextarea, InputTime } from "./input";

export interface Aerobic{
  seq: string,
  name: string,
  kcal: string,
  time: string,
  inclineStart: string,
  inclineEnd: string,
  speedStart: string,
  speedEnd: string,
  memo: string,
  stdDate: string,
}
export type AerobicList = Aerobic[];

export type AerobicFormData = {
  name: InputText,
  kcal: InputText,
  time: InputTime,
  inclineStart: InputText,
  inclineEnd: InputText,
  speedStart: InputText,
  speedEnd: InputText,
  memo: InputTextarea,
}

export type AerobicRequestBody = Partial<Aerobic>;

export type AerobicReadRequestParam=Pick<Aerobic, 'stdDate'>
export type AerobicDeleteRequestParam=Pick<Aerobic, 'stdDate' | 'seq'>

export type AerobicItemResponseBody = ResponseBody<Aerobic>
export type AerobicCalendarResponseBody = ResponseBody<CommonCalendarData[]>
export type AerobicReadResponseBody = ResponseBody<AerobicList>
export type AerobicFavoriteListResponseBody = ResponseBody<FavoriteListItem[]>
