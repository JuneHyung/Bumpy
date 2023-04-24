import { NuxtApp, defineNuxtPlugin } from 'nuxt/app';
import Apexchart from "vue3-apexcharts";
export default defineNuxtPlugin((nuxtApp: NuxtApp) => {
  nuxtApp.vueApp.use(Apexchart);
});