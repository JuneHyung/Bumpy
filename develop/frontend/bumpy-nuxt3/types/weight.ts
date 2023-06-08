enum Measure { 
  m1= 'kg',
  m2= 'pd'
}
interface Weight{
  seq: number,
  name: string,
  weightStart?: number,
  weightEnd?: number,
  repsStart?: number,
  repsEnd?: number,
  pollWeight?: number,
  setReps?: number,
  measure?: Measure,
  memo?: string,
}
type WeightList = Weight[];

export {
  Weight,
  WeightList
}