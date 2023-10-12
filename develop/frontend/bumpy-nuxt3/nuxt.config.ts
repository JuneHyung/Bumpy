// https://nuxt.com/docs/api/configuration/nuxt-config
import { defineNuxtConfig } from 'nuxt/config'
export default defineNuxtConfig({
  app:{
    head:{
      title: 'Bumpy',
      link:[
        {rel: 'icon', type: 'iamge/png', href: "/favicon.png",}
      ],
      
    }
  },
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
