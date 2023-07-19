import { CommonCalendarData, ResponseBody } from "./common";
import { InputNumber, InputText, InputTextarea } from "./input";

interface FoodItem {
  value: string
}
type FoodListType = FoodItem[];

export interface Meal{
  seq?: number,
  name: string,
  time: string,
  kcal: string,
  water: string,
  memo?: string,
  stdDate?: string,
  picture?: string[],
  food?: string[],
}
export type MealList = Meal[];

export type MealFormData = {
  name: InputText,
  order?: InputNumber,
  time?: InputText,
  kcal?: InputText,
  water?: InputText,
  memo?: InputTextarea,
  food?: {value: FoodListType}
  // picture?: string[],
}

export type MealItemRequestParam = Pick<Meal, 'stdDate' | 'seq'>
export type MealListRequestParam = Pick<Meal, 'stdDate'>
export type MealItemRequestBody = Meal
export type MealItemResposneBody = ResponseBody<Meal>
export type MealCalendarResponseBody = ResponseBody<CommonCalendarData>
export type MealListResponseBody = ResponseBody<MealList>