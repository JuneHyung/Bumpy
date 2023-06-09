interface Meal{
  seq: number,
  name: string,
  order?: number,
  time?: string,
  kcal?: number,
  water?: number,
  memo?: number,
}
type MealList = Meal[];
export {
  Meal,
  MealList
}