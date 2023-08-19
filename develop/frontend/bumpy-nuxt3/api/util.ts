export const validateValue = (value?: string, pattern?: string): boolean => {
  if(value!==undefined && pattern!==undefined){
    const regex = new RegExp(pattern, 'g')
    return regex.test(value);
  }else return false;
}

export const inRange = (value: string, min: number ,max: number): boolean =>{
  const len = value.length;
  return len>=min && len <=max;
}