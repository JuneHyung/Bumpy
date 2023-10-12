export interface InputCommon { 
  autofocus?: boolean,
  disabled?: boolean,
  readonly?: boolean,
}

export interface InputText extends InputCommon{ 
  value: string,
  placeholder?: string,
  maxlength?: number,
  minlength?: number,
  suffix?: string,
  pattern?: string,
  isNumber?: boolean
}
export interface InputPassword extends InputCommon{
  value: string;
  placeholder?: string;
  maxlength?: number;
  minlength?: number;
  suffix?: boolean;
  pattern?: string;
}

export type SelectItem = {
  dtlCd: string;
  dtlNm: string;
}
export interface InputSelectbox extends InputCommon{
  value: string;
  placeholder?: string;
  listname?: string;
  list: SelectItem[];
}

export interface InputTextarea extends InputCommon {
  value: string;
  placeholder?: string;
  cols?: number;
  rows?: number;
  maxlength?: number;
  minlength?: number;
}

export interface InputDate extends InputCommon { 
  value: string,
  suffix?: string,
  step?: string,
}

export interface InputTime extends InputCommon{
  value: string,
}

export type MatchMessage = 'Not Match' | 'Correct';
