<template>
  <div class="tabs">
    <scroll-pane ref="scrollPane" class="tabs-wrapper">
      <router-link
          v-for="tab in visitedViews"
          ref="tag"
          :key="tab.path"
          class="tabs-item"
          :class="isActive(tab)?'active':''"
          :to="{ path: tab.path, query: tab.query, fullPath: tab.fullPath }"
          tag="span"
      >
        {{ tab.title }}
        <span class="el-icon-close" />
      </router-link>
    </scroll-pane>
  </div>
</template>

<script>
import ScrollPane from './ScrollPane.vue'
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Tabs',
  components: { ScrollPane },
  computed: {
    visitedViews() {
      return this.$store.state.tabs.visitedViews
    }
  },
  watch: {
    $route() {
      this.addTab() // 路由一旦变化就会触发
    }
  },
  mounted() {
    this.addTab()
  },
  methods: {
    addTab() {
      const { name } = this.$route
      // 已存在的标签就不更新tabs状态
      // 就是点击过的菜单，在点击不触发行为。
      if (name) {
        console.log('this.router:', this.$route)
        this.$store.dispatch('tabs/addView', this.$route)
      }
      return false
    },
    isActive(route) {
      return route.path === this.$route.path
    }
  }
}
</script>
<style lang="scss">
.tabs {
  position: relative;
  height: 34px;
  width: 100%;
  background: #fff;
  border-bottom: 1px solid #d8dce5;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, .12), 0 0 3px 0 rgba(0, 0, 0, .04);
  .tabs-wrapper {
    .tabs-item {
      display: inline-block;
      position: relative;
      cursor: pointer;
      height: 30px;
      line-height: 30px;
      border: 1px solid #d8dce5;
      color: #495060;
      background: #fff;
      padding: 0 8px;
      font-size: 12px;
      margin-left: 5px;
      margin-top: 2px;
      &:first-of-type {
        margin-left: 15px;
      }
      &:last-of-type {
        margin-right: 15px;
      }
      &.active {
        background-color: #42b983;
        color: #fff;
        border-color: #42b983;
        &::before {
          content: '';
          background: #fff;
          display: inline-block;
          width: 8px;
          height: 8px;
          border-radius: 50%;
          position: relative;
          margin-right: 2px;
        }
      }
    }
  }
}
</style>
