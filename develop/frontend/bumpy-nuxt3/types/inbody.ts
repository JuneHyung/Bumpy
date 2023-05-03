interface Inbody {
  height?: number,
  weight?: number,
  age?: number,
  muscle?: number,
  fat?: number,
  score?: number,
  bmi?: number,
  fatPercent?: number,
}

interface UserInfoItem{
  category: string,
  value: string | number,
  unit: string,
}
type UserInfoList = UserInfoItem[];

interface DegreeItem {
  name: string,
  degree: string
}
type DegreeList = DegreeItem[];

interface MeterItem {
  value: number,
  max: number,
  min: number,
  low: number,
  high: number,
  optimum: number,
  category: string,
  unit: string,
}
type MeterList = MeterItem[];

export {
  Inbody,
  UserInfoList,
  DegreeList,
  MeterList,
}