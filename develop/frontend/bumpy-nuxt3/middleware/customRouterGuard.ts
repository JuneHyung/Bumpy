import { setErrorMessage, setWarnMessage } from "~~/api/alert/message";
import { getUserHeartBeat } from "~~/api/user/user";
import { resetAllData } from "~~/api/util";

export default defineNuxtRouteMiddleware(async (to, from) =>{
  if(to.path==='/login' || to.path==='/signup' || to.path==='/'){
    await resetAllData();
  }else{
    try{
      const {data, error} = await getUserHeartBeat();
      if(error.value!==null) {
        setErrorMessage('로그인 후 사용가능 합니다 :)')
        return await navigateTo('/login'); 
      }
      else if(data.value!==null){
        const {message} = data.value;
        
        if(message!=='OK'){ 
          setWarnMessage('로그인 후 사용가능 합니다 :)')
          return await navigateTo('/login'); 
        }
      }
    } catch(e) {
      setErrorMessage(e)
    }
  }
})