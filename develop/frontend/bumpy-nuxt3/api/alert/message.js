import Swal from 'sweetalert2';

export const setErrorMessage = (message) =>{
  return Swal.fire({
    title:'Error!',
    text: message,
    icon: 'error',
    backdrop: false
  })
}

export const setWarnMessage = (message) =>{
  return Swal.fire({
    // title:'Error!',
    text: message,
    icon: 'warning',
    backdrop: false
  })
}

export const setMessage = (message) =>{
  return Swal.fire({
    // title:'Error!',
    text: message,
    icon: 'success',
    backdrop: false
  })
}