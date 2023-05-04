import {defineStore} from 'pinia';
export const useTestStore = defineStore({
  id:'test-store',
  state:()=>{
    return {
      tList: ['test01', 'test02']
    }
  },
  actions:{},
  getters:{
    testList: (state) => state.tList
  }
})