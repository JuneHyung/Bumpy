interface Weight{
  name: string,
  weightStart?: number,
  weightEnd?: number,
  repsStart?: number,
  repsEnd?: number,
  pollWeight?: number,
  setCount?: number,
  measure: 1 | 2,
  memo?: string,
}

export {
  Weight,
}