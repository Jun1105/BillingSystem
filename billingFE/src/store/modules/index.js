/**
 * @description demo
 */
// 状态树
const state = () => ({
    info: '',
})
// 获取
const getters = {
    getInfo: (state) => state.info,
}
// 修改
const mutations = {
    changeInfo(state, updateInfo) {
        state.info = updateInfo
    },
}
// 异步修改
const actions = {}
export default {
    state,
    getters,
    mutations,
    actions,
}

