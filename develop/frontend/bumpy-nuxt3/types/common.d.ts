export interface ResponseBody<T> {
  code?: string,
  message?: string,
  data: T,
}

export interface OnlyMessageResponse {
  message: string,
}

export interface CommonCalendarData {
  title:string,
  date: string,
}

export interface FavoriteListItem{
  title: string,
  list: string[],
}

export interface ImageData{
  name: string,
  data: string,
  size: number
}


export interface customError extends Error{
  data: {message: string}
}

export interface VideoItem {
  videoId: string,
  title: string,
  description: string,
  thumbnail: string,
  channelTitle: string,
}
export type YoutubeList = VideoItem[];
export type YoutubeResponseBody = {
  data: YoutubeList,
}
export type YoutubeKeywordRequest = {
  keyword: string,
}