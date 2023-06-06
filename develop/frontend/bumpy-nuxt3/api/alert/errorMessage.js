import Swal from 'sweetalert2';

export const setErrorMessage = (message) =>{
  return Swal.fire({
    title:'Error!',
    text: message,
    icon: 'error',
    backdrop: false
  })
}

