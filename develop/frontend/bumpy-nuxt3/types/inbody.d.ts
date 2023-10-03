import { CommonCalendarData, ResponseBody } from "./common";
import { InputText } from "./input";

export interface Inbody {
  height: string,
  weight: string,
  muscle: string,
  age: string,
  fat: string,
  score: string,
  bmi: string,
  fatRate: string,
  stdDate: string,
  picture: ImageData[]
}
export type InbodyList = Inbody[]
export type InbodyFormData = {
  height: InputText,
  weight: InputText,
  muscle: InputText,
  fat: InputText,
  score: InputText,
  bmi: InputText,
  fatRate: InputText,
  picture: {value: ImageData[]}
}
export type InbodyItemRequestBody = Partial<Inbody>;
// export type InbodyItemRequestParam = Pick<Inbody, 'stdDate' | 'seq'>
export type InbodyRequestParam = Pick<Inbody, 'stdDate'>
export type InbodyItemResponseBody = ResponseBody<Inbody>;
export type InbodyCalendarItemResponseBody = ResponseBody<CommonCalendarData[]>;
export type InbodyListResponseBody = ResponseBody<InbodyList>;


// UserInfo
export interface UserInfoItem{
  category: string,
  key: string,
  value: string | number,
  unit: string,
}
export type UserInfoList = UserInfoItem[];
export interface DegreeItem {
  name: string,
  degree: string
}
export type DegreeList = DegreeItem[];

export interface MeterItem {
  value: number,
  max?: number,
  min?: number,
  low?: number,
  high?: number,
  optimum?: number,
  key: string,
  category?: string,
  unit?: string,
}
export type MeterList = MeterItem[];
