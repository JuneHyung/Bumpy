type MainWeightInfo = {
  myBest: number,
  reps: number,
  sets: number,
  monthAverage: number
}
type MainAerobicInfo = {
  bestKcal: number,
  bestTime: number,
  averageIncline: number,
  averageSpeed: number
}
type ChartInfo = {
  series: string[],
  xAxis: string[],
}

export { 
  MainWeightInfo,
  MainAerobicInfo,
  ChartInfo,
}
