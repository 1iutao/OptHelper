const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  //打开浏览器窗口
  devServer :{
    host :'localhost',
    port : 8080,
    open : true
  },
})
