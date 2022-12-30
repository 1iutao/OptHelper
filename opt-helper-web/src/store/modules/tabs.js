const state = {
  visitedViews: [], // 标签组
  cachedViews: [] // 需要缓存的标签组，根据这个数组，确定是否缓存页面，暂时没用到
}

const mutations = {
  ADD_VISITED_VIEW(state, view) {
    // 如果标签跳转的路由存在就不添加
    // 名字不同，路径相同的。也加入标签组
    if (state.visitedViews.some(v => v.path === view.path)) return
    state.visitedViews.push(
      Object.assign({}, view, {
        title: view.meta.title || 'no-name'
      })
    )
  },
  ADD_CACHED_VIEW(state, view) {
    // 已存在缓存就不缓存了
    if (state.cachedViews.includes(view.name)) return
    if (!view.meta.noCache) {
      state.cachedViews.push(view.name)
    }
  }
}
const actions = {
  addView({ commit }, view) {
    // view == this.$router
    commit('ADD_VISITED_VIEW', view)
    commit('ADD_CACHED_VIEW', view)
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
