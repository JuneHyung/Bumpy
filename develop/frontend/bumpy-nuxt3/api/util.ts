export const validateValue = (value?: string, pattern?: string): boolean => {
  if(value!==undefined && pattern!==undefined){
    const regex = new RegExp(pattern, 'g')
    return regex.test(value);
  }else return false;
}