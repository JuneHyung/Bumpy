interface User{
  userId: string,
  password: string,
  email:string,
  useYn: string,
  gender: 1 | 2,
  birth: string,
  phoneNumber ?:string,
  addressMail?: number,
  address?: string
}

export {
  User
}