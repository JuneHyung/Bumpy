interface Weight{
  name: string,
  weightStart?: number,
  weightEnd?: number,
  repsStart?: number,
  repsEnd?: number,
  pollWeight?: number,
  setReps?: number,
  measure?: 1 | 2,
  memo?: string,
}
type WeightList = Weight[];

export {
  Weight,
  WeightList
}