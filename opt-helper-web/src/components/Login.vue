<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/logo.png">
      </div>
<!--      表单-->
      <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0">
<!--        用户名-->
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" prefix-icon="iconfont icon-usercenter"></el-input>
        </el-form-item>
<!--        密码-->
        <el-form-item prop="password">
          <el-input v-model="loginForm.password" prefix-icon="iconfont icon-password" type="password"></el-input>
        </el-form-item>
<!--        按钮-->
        <el-form-item class="butns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="reset" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        username: "username",
        password: "password"
      },
      //校验
      loginRules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          { min: 6, max: 12, message: '长度在 6 到 12 个字符', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    //重置
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    //登录
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;
        const {data:res} = await this.$http.post("user/login", this.loginForm);
        if ( res.code == 0) {
          this.$message.success("登录成功");
          this.$router.push({path: "/home"});
          window.sessionStorage.setItem("user", res.user)
        }else {
          this.$message.error("登录失败");
        }
      })
    },
  }
}
</script>

<style lang="less" scoped>
.login_container{
  background-image: url(../assets/login-background.jpg);
  height: 100%;
}
.login_box{
  width: 450px;
  height: 300px;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
  .avatar_box{
    width: 130px;
    height: 130px;
    border: 1px solid #eeeeee;
    border-radius: 50%;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%,-120%);
    img{
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }
  .butns{
    display: flex;
    justify-content: flex-end;
    transform: translate(-30%,70%);
  }
  .login_form{
    position: absolute;
    bottom: 0%;
    width: 100%;
    padding: 0 10px;
    box-sizing: border-box;
  }
}
</style>
