interface InputCommon { 
  autofocus?: boolean,
  disabled?: boolean,
  readonly?: boolean,
}

interface InputText extends InputCommon{ 
  value?: string,
  placeholder?: string,
  maxlength?: number,
  minlength?: number,
  suffix?: string,
  pattern?: string,
}
interface InputNumber extends InputCommon{
  value?: number,
  placeholder?: string,
  max?: number,
  min?: number,
  suffix?: string,
  step?: number,
  noStep?: boolean
}

interface InputPassword extends InputCommon{
  value?: string;
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
  value?: string;
  placeholder?: string;
  listname?: string;
  list: SelectItem[];
}

interface InputTextarea extends InputCommon {
  value?: string;
  placeholder?: string;
  cols?: number;
  rows?: number;
  maxlength?: number;
  minlength?: number;
}

interface InputDate extends InputCommon { 
  value?: string,
  suffix?: string,
  step?: string,
}

type MatchMessage = 'Not Match' | 'Correct';

export { 
  InputText,
  InputNumber,
  InputPassword,
  InputSelectbox,
  SelectItem,
  InputDate,
  InputTextarea,
  MatchMessage,
}
