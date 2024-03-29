import { CommonCalendarData, FavoriteListItem, ResponseBody } from "./common";
import { InputText, InputTextarea } from "./input";

interface FoodItem {
  value: string
}
export type FoodListType = FoodItem[];

export interface Meal{
  seq: string,
  name: string,
  time: string,
  kcal: string,
  water: string,
  stdDate: string,
  food: string[],
  memo: string,
  picture: ImageData[],
}
export type MealList = Meal[];

export type MealFormData = {
  name: InputText,
  time: InputText,
  kcal: InputText,
  water: InputText,
  food: {value: FoodListType}
  memo: InputTextarea,
  picture: {value: ImageData[]},
}

export type MealItemRequestBody = Partial<Meal>
export type MealListRequestParam = Pick<Meal, 'stdDate'>
export type MealItemRequestParam = Pick<Meal, 'stdDate' | 'seq'>

export type MealItemResposneBody = ResponseBody<Meal>
export type MealCalendarResponseBody = ResponseBody<CommonCalendarData[]>
export type MealListResponseBody = ResponseBody<MealList>