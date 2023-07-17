interface ResponseBody<T> {
  code?: string,
  message?: string,
  data: T,
}

interface MessageResponse {
  code: string,
  message: string
}

export {
  ResponseBody,
  MessageResponse,
}