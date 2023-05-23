// https://nuxt.com/docs/api/configuration/nuxt-config
import { defineNuxtConfig } from 'nuxt/config'
export default defineNuxtConfig({
  ssr:false,
  modules: [
    // ...
    '@pinia/nuxt',
  ],
  css:[
    // global scss 파일
    '~/assets/scss/app.scss'
  ],
  alias:{
    "components": "~/components",
    "types": "~/types",
  },
})
