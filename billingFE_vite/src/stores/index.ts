/** @format */

import { defineStore } from 'pinia'

// useStore 可以是 useUser、useCart 之类的任何东西
// 第一个参数是应用程序中 store 的唯一 id
export const userStore = defineStore('user', {
  // other options...
  state: () => {
    return {
      userId: null,
      userName: null
    }
  },
  getters: {
    getUserId: (state) => state.userId,
    getUserName: (state) => state.userName
  },
  actions: {
    settingUser(userId: string, userName: string) {
      this.userId = userId
      this.userName = userName
    },
    removeUser() {
      this.userId = null
      this.userName = null
    }
  }
  // // 开启数据缓存
  // persist: {
  //   enabled: true
  // }
})