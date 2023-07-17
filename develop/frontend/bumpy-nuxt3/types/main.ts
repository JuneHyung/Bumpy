import { AerobicList } from "./aerobic";
import { ResponseBody } from "./common";
import { MealList } from "./meal";
import { WeightList } from "./weight";

type MainWeightInfo = {
  myBest: number;
  reps: number;
  sets: number;
  monthAverage: number;
};
type MainAerobicInfo = {
  bestKcal: number;
  bestTime: number;
  averageIncline: number;
  averageSpeed: number;
};
type ChartInfo = {
  series: string[];
  xAxis: string[];
};

interface GrassInfoItem {
  active: boolean;
  visible: boolean;
  date: string;
}

interface ChartData {
  data: string[];
  name: string;
}

interface MainWeightChartInfo {
  myBest: number;
  monthAverage: string;
  reps: number;
  sets: number;
  xAxis: string[];
  series: ChartData[];
}
interface MainAerobicChartInfo {
  bestKcal: string;
  bestTime: string;
  averageIncline: number;
  averageSpeed: number;
  xAxis: string[];
  series: ChartData[];
}

interface GrassInfoRequestParams {
  stdDate: string;
}
interface ChartRequestParams {
  name: string;
}


type GrassInfoResponseBody = ResponseBody<[GrassInfoItem[]]>
type MealInfoResponseBody = ResponseBody<MealList>
type WeightInfoResponseBody = ResponseBody<WeightList>
type AerobicInfoReasponseBody = ResponseBody<AerobicList>;
type WeightChartInfoResponseBody = ResponseBody<MainWeightChartInfo>;
type AerobicChartInfoResponseBody = ResponseBody<MainAerobicChartInfo>;

export {
  MainWeightInfo,
  MainAerobicInfo,
  ChartInfo,
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
