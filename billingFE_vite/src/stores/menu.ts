import { defineStore } from 'pinia'

import 'pinia'

declare module 'pinia' {
  // eslint-disable-next-line @typescript-eslint/no-unused-vars
  export interface DefineStoreOptionsBase<S, Store> {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    persist?: any
  }
}

export const useMenuStore = defineStore({
  id: 'menu',
  state: () => {
    return {
      menuList: []
    }
  },
  getters: {},
  actions: {
    settingMenu(menuList) {
      this.menuList = menuList
    },
    removeMenuList() {
      window.sessionStorage.removeItem('menu')
    }
  },
  // // 开启数据缓存
  persist: {
    enabled: true
  }
})
