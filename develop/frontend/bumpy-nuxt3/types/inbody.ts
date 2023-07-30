import { CommonCalendarData, ResponseBody } from "./common";
import { InputText } from "./input";

export interface Inbody {
  seq?: number,
  height?: string,
  weight?: string,
  age?: string,
  muscle?: string,
  fat?: string,
  score?: string,
  bmi?: string,
  fatRate?: string,
  stdDate?: string,
}
export type InbodyList = Inbody[]
export type InbodyFormData = {
  seq: InputText,
  height?: InputText,
  weight?: InputText,
  age?: InputText,
  muscle?: InputText,
  fat?: InputText,
  score?: InputText,
  bmi?: InputText,
  fatRate?: InputText,
}
export type InbodyItemRequestParam = Pick<Inbody, 'stdDate' | 'seq'>
export type InbodyListRequestParam = Pick<Inbody, 'stdDate'>
export type InbodyItemRequestBody = Inbody;
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
