import { AerobicList } from "./aerobic";
import { ResponseBody } from "./common";
import { Inbody } from "./inbody";
import { MealList } from "./meal";
import { WeightList } from "./weight";

export interface GrassInfoItem {
  active: boolean;
  visible: boolean;
  date: string;
}
export type GrassInfoList = GrassInfoItem[][];
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

export interface MainUserInfo{
  weight:string,
  height: string,
  age: string,
  averageWater: string,
  continuity: string,
  inbodyData: MainUserInbodyData,
  lastActive: string,
  username: string,
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
export type MainUserInbodyData = Pick<Inbody, 'weight' | 'muscle' | 'fat' | 'bmi' | 'fatRate'>

export type GrassInfoResponseBody = ResponseBody<[GrassInfoItem[]]>;
export type MealInfoResponseBody = ResponseBody<MealList>;
export type WeightInfoResponseBody = ResponseBody<WeightList>;
export type AerobicInfoReasponseBody = ResponseBody<AerobicList>;
export type WeightChartInfoResponseBody = ResponseBody<MainWeightInfo>;
export type AerobicChartInfoResponseBody = ResponseBody<MainAerobicInfo>;
export type MainUserInfoResponseBody = ResponseBody<MainUserInfo>

