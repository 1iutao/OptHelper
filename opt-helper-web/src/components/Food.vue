<template>
  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/">food</a> </el-breadcrumb-item>
    </el-breadcrumb>
<!--    主体-->
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input placeholder="请输入搜索内容">
            <el-button slot="append" icon="iconfont icon-nav_icon_shujuchaxun"></el-button>
          </el-input>
        </el-col>

        <el-col :span="4">
          <el-button type="primary" @click="addDialogVisible = true">添加信息</el-button>
        </el-col>
      </el-row>
<!--      列表渲染-->
      <el-table :data="foodList" border stripe>
<!--        索引列-->
        <el-table-column type="index"></el-table-column>
        <el-table-column label="名称" prop="foodName"></el-table-column>
        <el-table-column label="创建时间" prop="createTime"></el-table-column>
        <el-table-column label="更新时间" prop="updateTime"></el-table-column>
        <el-table-column label="赞" prop="likeNum"></el-table-column>
        <el-table-column label="踩" prop="unlikeNum"></el-table-column>
<!--        作用域插槽 scope.row存储了当前行信息-->
        <template slot-scope="scope">
          <el-switch v-model="scope.row.state"></el-switch>
        </template>

        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" icon="el-icon-add" size="mini"></el-button>
            <el-button type="danger" icon="el-icon-delete" size="mini"></el-button>
            <el-button type="primary" icon="el-icon-edit" size="mini"></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  created() {
    this.getFoodList();
  },
  data() {
    return {
      queryInfo: {
        query:"",
      },
      foodList: [],
    };
  },
  methods: {
    async getFoodList() {
      const {data: res} = await this.$http.post("food/queryFoodList", {params:this.queryInfo})
      this.foodList = res.data;
    }
  }
}
</script>

<style lang="less" scoped>
.el-breadcrumb {
  margin-bottom: 15px;
  font-size: 12px;
}
.el-card {
  box-shadow: 0 1px 1px rgba(0,8,10,0.15) !important;
}
</style>
