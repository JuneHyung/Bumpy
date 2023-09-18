interface InputCommon { 
  autofocus?: boolean,
  disabled?: boolean,
  readonly?: boolean,
}

interface InputText extends InputCommon{ 
  value: string,
  placeholder?: string,
  maxlength?: number,
  minlength?: number,
  suffix?: string,
  pattern?: string,
  isNumber?: boolean
}
interface InputPassword extends InputCommon{
  value: string;
  placeholder?: string;
  maxlength?: number;
  minlength?: number;
  suffix?: boolean;
  pattern?: string;
}

type SelectItem = {
  dtlCd: string;
  dtlNm: string;
}
interface InputSelectbox extends InputCommon{
  value: string;
  placeholder?: string;
  listname?: string;
  list: SelectItem[];
}

interface InputTextarea extends InputCommon {
  value: string;
  placeholder?: string;
  cols?: number;
  rows?: number;
  maxlength?: number;
  minlength?: number;
}

interface InputDate extends InputCommon { 
  value: string,
  suffix?: string,
  step?: string,
}

interface InputTime extends InputCommon{
  value: string,
}

type MatchMessage = 'Not Match' | 'Correct';

export { 
  InputText,
  InputPassword,
  InputSelectbox,
  SelectItem,
  InputDate,
  InputTextarea,
  InputTime,
  MatchMessage,
}
