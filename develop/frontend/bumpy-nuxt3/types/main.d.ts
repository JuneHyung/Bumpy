import { AerobicList } from "./aerobic";
import { ResponseBody } from "./common";
import { MealList } from "./meal";
import { WeightList } from "./weight";

export interface GrassInfoItem {
  active: boolean;
  visible: boolean;
  date: string;
}

export interface ChartData {
  data: string[];
  name: string;
}

export interface MainWeightInfo {
  myBest: string;
  monthAverage: string;
  reps: string;
  sets: string;
  xAxis: string[];
  series: ChartData[];
}
export interface MainAerobicInfo {
  bestKcal: string;
  bestTime: string;
  averageIncline: string;
  averageSpeed: string;
  xAxis: string[];
  series: ChartData[];
}

export interface GrassInfoRequestParams {
  stdDate: string;
}
export interface ChartRequestParams {
  name: string;
}

export type MainWeightChartInfo = Pick<MainWeightInfo, "myBest" | "monthAverage" | "reps" | "sets">;
export type MainWeightChartData = Pick<MainWeightInfo, "xAxis" | "series">;
export type MainAerobicChartInfo = Pick<MainAerobicInfo, "bestKcal" | "bestTime" | "averageIncline" | "averageSpeed">;
export type MainAerobicChartData = Pick<MainAerobicInfo, "xAxis" | "series">;

export type GrassInfoResponseBody = ResponseBody<[GrassInfoItem[]]>;
export type MealInfoResponseBody = ResponseBody<MealList>;
export type WeightInfoResponseBody = ResponseBody<WeightList>;
export type AerobicInfoReasponseBody = ResponseBody<AerobicList>;
export type WeightChartInfoResponseBody = ResponseBody<MainWeightChartData>;
export type AerobicChartInfoResponseBody = ResponseBody<MainAerobicChartData>;
