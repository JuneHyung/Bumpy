export const validateValue = (value?: string, pattern?: string): boolean => {
  if(value!==undefined && pattern!==undefined){
    const regex = new RegExp(pattern, 'g')
    return regex.test(value);
  }else return false;
}

// const baseURL = 'http://222.112.251.124:8899/api';
// // import { useFetch } from '@nuxtjs/composition-api';
// export const useCustomFetch = () =>{
//   // const {fetch} = useFetch();

//   const customFetch = async (url: string, options: any) =>{
//     const response = await fetch(url, options);
//     console.log(response)
//     if(response.ok){
//       if(response.status === 204) return null;
//       const data = await response.json();
//       return data;
//     }else{
//       const error = new Error('API 요청에 실패했습니다.');
//       error.status = response.status;
//       throw error;
//     }
//   }
//   return {fetch: customFetch}
// }
