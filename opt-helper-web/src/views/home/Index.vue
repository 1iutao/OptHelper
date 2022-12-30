<template>
  <el-row>
    <el-col>----信息----</el-col>
    <el-col>name2: {{ name2 }}</el-col>
    <el-col>年龄2： {{ sex2 }}</el-col>
    <el-col>name1： {{ name1 }}</el-col>
    <el-col>年龄： {{ sex }}</el-col>
  </el-row>
</template>

<script>
import { mapState } from 'vuex'
// 模块化的调用
export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Home',
  data() {
    return {
      // 第一种方式获取
      name1: this.$store.state.dog.name
    }
  },
  computed: {
    // 第二种方式获取   对象
    ...mapState({
      name2: state => state.dog.name
    })
  }
}
</script>
