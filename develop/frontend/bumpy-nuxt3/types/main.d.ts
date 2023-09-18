import { AerobicList } from "./aerobic";
import { ResponseBody } from "./common";
import { MealList } from "./meal";
import { WeightList } from "./weight";

interface GrassInfoItem {
  active: boolean;
  visible: boolean;
  date: string;
}

interface ChartData {
  data: string[];
  name: string;
}

interface MainWeightInfo {
  myBest: string;
  monthAverage: string;
  reps: string;
  sets: string;
  xAxis: string[];
  series: ChartData[];
}
interface MainAerobicInfo {
  bestKcal: string;
  bestTime: string;
  averageIncline: string;
  averageSpeed: string;
  xAxis: string[];
  series: ChartData[];
}

interface GrassInfoRequestParams {
  stdDate: string;
}
interface ChartRequestParams {
  name: string;
}

type MainWeightChartInfo = Pick<MainWeightInfo, "myBest" | "monthAverage" | "reps" | "sets">;
type MainWeightChartData = Pick<MainWeightInfo, "xAxis" | "series">;
type MainAerobicChartInfo = Pick<MainAerobicInfo, "bestKcal" | "bestTime" | "averageIncline" | "averageSpeed">;
type MainAerobicChartData = Pick<MainAerobicInfo, "xAxis" | "series">;

type GrassInfoResponseBody = ResponseBody<[GrassInfoItem[]]>;
type MealInfoResponseBody = ResponseBody<MealList>;
type WeightInfoResponseBody = ResponseBody<WeightList>;
type AerobicInfoReasponseBody = ResponseBody<AerobicList>;
type WeightChartInfoResponseBody = ResponseBody<MainWeightChartData>;
type AerobicChartInfoResponseBody = ResponseBody<MainWeightChartData>;

export {
  MainWeightInfo,
  MainAerobicInfo,
  MainWeightChartInfo,
  MainWeightChartData,
  MainAerobicChartInfo,
  MainAerobicChartData,
  GrassInfoItem,
  GrassInfoRequestParams,
  ChartRequestParams,
  GrassInfoResponseBody,
  MealInfoResponseBody,
  WeightInfoResponseBody,
  AerobicInfoReasponseBody,
  WeightChartInfoResponseBody,
  AerobicChartInfoResponseBody,
};
