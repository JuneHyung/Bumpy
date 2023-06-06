import { NuxtApp, defineNuxtPlugin } from 'nuxt/app';
import Swal from 'sweetalert2'
export default defineNuxtPlugin((nuxtApp: NuxtApp) => {
  nuxtApp.provide('swal', Swal);
});