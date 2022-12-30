<template>
  <div class="navbar">
    <!-- sidebar抽屉按钮 -->
    <div class="sidebar-switch" @click="switchSidebar">
      <i :class="open ? 'el-icon-s-fold':'el-icon-s-unfold'" />
    </div>
    <!-- breadcrumb -->
    <el-breadcrumb separator="/">
      <el-breadcrumb-item v-for="item in levelList" :key="item.path">
        {{ item.meta.title }}
      </el-breadcrumb-item>
    </el-breadcrumb>
    <!-- nav menu -->
    <el-dropdown class="nav-menu">
      <div class="avatar-wrapper">
        <img :src="'https://img1.baidu.com/it/u=3529371423,1726515455&fm=253&fmt=auto&app=138&f=JPEG?w=250&h=250'">
        <span>管理员</span>
        <i class="el-icon-caret-bottom" />
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item command="a">个人中心</el-dropdown-item>
          <el-dropdown-item command="e" divided>退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script>

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Navbar',
  props: {
    open: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      levelList: null
    }
  },
  watch: {
    $route() {
      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    switchSidebar() {
      this.$emit('switchSidebar')
    },
    getBreadcrumb() {
      // 获取路由对应title   && 存在返回右边，不存在返回左边
      const matched = this.$route.matched.filter(item => item.meta && item.meta.title)
      this.levelList = matched.filter(item => item.meta && item.meta.title && item.meta.breadcrumb !== false)
    }
  }
}
</script>
<style lang="scss">
.navbar {
  height: 50px;
  -webkit-box-shadow: 0 1px 4px rgba(0,21,41,0.08);
  box-shadow: 0 1px 4px rgba(0,21,41,0.08);
  .sidebar-switch {
    height: 100%;
    width: 50px;
    padding: 0 15px;
    line-height: 50px;
    float: left;
    cursor: pointer;
    i {
      font-size: 22px;
      line-height: 50px;
    }
  }
  .el-breadcrumb {
    float: left;
    height: 100%;
    line-height: 50px;
  }
  .nav-menu {
    float: right;
    cursor: pointer;
    height: 50px;
    line-height: 50px;
    .avatar-wrapper {
      display: flex;
      align-items: center;
    }
    img {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      margin-right: 5px;
    }
  }
}
</style>
