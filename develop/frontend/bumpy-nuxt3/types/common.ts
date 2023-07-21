export interface ResponseBody<T> {
  code?: string,
  message?: string,
  data: T,
}

export interface MessageResponse {
  code: string,
  message: string
}

export interface CommonCalendarData {
  title:string,
  date: string,
}

export interface FavoriteListItem{
  title: string,
  list: string[],
}