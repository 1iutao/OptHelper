<template>
  <!-- 整体页面布局 -->
  <el-row class="app-wrapper">
    <el-container>
      <!-- 侧边栏 -->
      <el-aside :width="open ? '210px' : '0px'">
        <sidebar :open="open" />
      </el-aside>
      <el-container>
        <!-- 顶部 -->
        <el-header height="50px">
          <!-- 头部信息 -->
          <navbar @switchSidebar="switchSidebar" />
        </el-header>
        <!-- 主页面 -->
        <el-main>主页面</el-main>
      </el-container>
    </el-container>
  </el-row>
</template>

<script>
import { Navbar, Sidebar } from './components/'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Layout',
  components: {
    Sidebar,
    Navbar
  },
  data() {
    return {
      open: true
    }
  },
  methods: {
    switchSidebar() {
      this.open = !this.open
    }
  }
}
</script>
<style lang="scss" scoped>
.app-wrapper {
  position: relative;
  height: 100%;
  width: 100%;
  .el-aside{
    transition: .5s;
  }
  .el-header {
    padding: 0;
  }
  .el-main {
    margin-top: 32px;
  }
  .sidebar-switch {
    height: 100%;
    width: 50px;
    line-height: 50px;
    cursor: pointer;
    i {
      font-size: 22px;
    }
  }
}
</style>
