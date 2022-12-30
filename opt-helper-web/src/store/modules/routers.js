import { routes } from '@/router'
const state = {
  routers: [] // 路由数组，存放菜单栏的路由数据
}

const mutations = {
  SET_ROUTERS: (state, routers) => {
    state.routers = routers
  }
}
// function getMenuList(routes) { }
const actions = {
  getRouters({ commit }) {
    return new Promise(resolve => {
      // 处理路由信息
      // const res = getMenuList(routes)
      // 这里由于暂时还没有对 路由进行 任何限制。
      // 所以不需要加工过滤某些需要权限才能展示的路由
      const res = routes
      // 更新状态
      commit('SET_ROUTERS', res)
      // 返回数据
      resolve(res)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
