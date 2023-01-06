<template>
<!--  container布局-->
  <el-container class="home-container">
<!--    头部-->
    <el-header>
      <div>
        <img class="logoStyle" src="../assets/logo.png" alt=""/>
      </div>
      <span>懒得选</span>
      <el-button type="info" @click="logout">登录</el-button>
    </el-header>
<!--    主体-->
    <el-container>
<!--      侧边栏-->
      <el-aside width="200px">
        <div class="toggle-button" @click="toggleCollapse">收起</div>
        <el-menu background-color="#545c64" text-color="#fff" active-text-color="#409eff"
        unique-opened :collapse="isCollapse" :collapse-transition="false" :router="true" :default-active="activePath">
<!--            一级菜单-->
          <el-submenu :index="item.id+''" v-for="item in menuList" :key="item.id">
            <template slot="title">
              <i class="el-icon-guide"></i>
              <span>{{ item.title }}</span>
            </template>
<!--            二级菜单-->
            <el-menu-item :index="it.path" v-for="it in item.subMenusList" :key="it.id" @click="saveNavState(it.path)">
              <template slot="title">
                <i :class="iconsObject[it.id]"></i>
                <span>{{ it.title }}</span>
              </template>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
<!--      主题内容-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      menuList:[],
      isCollapse: false,
      iconsObject: {
        '1' : 'iconfont icon-agriculture',
        '2' : 'iconfont icon-beizi',
        '3' : 'iconfont icon-hot-fill',
        '4' : 'iconfont icon-history',
      },
      activePath : '/food',
    }
  },
  created() {
    this.getMenuList();
    this.activePath = window.sessionStorage.getItem('activePath');
    },
  methods: {
    //登出
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    async getMenuList() {
      const {data: res} = await this.$http.get("menus");
      if (res.code != 0) return this.$message.console.error("获取列表失败");
      this.menuList = res.data;
    },
    toggleCollapse() {
      this.isCollapse = !this.isCollapse;
    },
    saveNavState() {
      window.sessionStorage.setItem('activePath', activePath);
      this.activePath = activePath;
    }
  }
};
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  background-color: rgb(44,58,58);
  display: flex;
  justify-content: space-between;
  padding-left: 0%;
  color: beige;
  font-size: 20px;
  .div {
    display: flex;
    align-items: center;
    span {
      margin-left: 15px;
    }
  }
}
.el-aside {
  background-color: rgb(13,20,18);
  .el-menu {
    border-right: none;
  }
}
.toggle-button {
  background-color: #404040;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
.el-amin {
  background-color: beige;
}
</style>
