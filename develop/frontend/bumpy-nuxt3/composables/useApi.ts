const config = useRuntimeConfig();
const BaseUrl = config.API_BASE_URL;
export default (url: string)=>{
  return useFetch(url, {
    baseURL: BaseUrl,
    // query: {param1, param2}
    onRequest:(context)=>{
      const isDev = process.env.NODE_ENV==='development'
      if(isDev){
        context.options.headers = new Headers(context.options.headers)
        context.options.headers.append('Authrization', 'Bearer TOKEN_FOR_DEV')
      }
    }
  })
}