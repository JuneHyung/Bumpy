interface Aerobic{
  name: string,
  kcal?: number,
  time?: number,
  inclineStart?: number,
  inclineEnd?: number,
  speedStart?: number,
  speedEnd?: number,
  memo?: string,
}
type AerobicList = Aerobic[];

export {
  Aerobic,
  AerobicList
}