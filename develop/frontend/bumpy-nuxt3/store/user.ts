import {defineStore} from 'pinia';
import { setErrorMessage } from '~~/api/alert/message';
import { fetchUserPasswordCheck } from '~~/api/user/user';
export const useUserStore = defineStore('user-store',()=>{
  const isPass = ref(false);
  const userName = ref('');

  const checkPassword = async (body: any) =>{
    try{
      const {data, error} = await fetchUserPasswordCheck(body)
      if(error.value!==null) setErrorMessage('비밀번호를 다시 확인해주세요');
    }catch(e){
      setErrorMessage(e)
    }
  }

  const getIsPass = () => isPass.value;
  const getUserName = () => userName.value;
  
  const setIsPass = (value:boolean) => isPass.value = value;
  const setUserName = (value: string) => userName.value = value;

  return {
    checkPassword, 
    getIsPass, 
    getUserName,
    setIsPass,
    setUserName,

  }
})