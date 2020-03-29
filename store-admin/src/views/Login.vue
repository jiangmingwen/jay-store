<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-logo">
        <img src="@/assets/imgs/logo.png" alt="">
      </div>
      <div class="login-title">
        Jay Store 后台管理系统
      </div>
      <div class="login-form">
        <el-form :model="ruleForm" :rules="rules" label-position="top" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-col :span="18" :offset="2">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="ruleForm.username" placeholder="请输入用户名或手机号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="18" :offset="2">
            <el-form-item label="密码" prop="passowrd">
              <el-input v-model="ruleForm.passowrd" placeholder="请输入密码"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="18" :offset="2">
            <el-form-item>
              <el-button style="width:100%;" :loading="loading" type="primary" @click="submitForm('ruleForm')">登 录</el-button>
            </el-form-item>
          </el-col>
        </el-form>
      </div>
    </div>
   <!-- <SliderCheck :imgData="imgData" @change="onSlide" :height="120" :width="250" /> -->
  </div>

</template>

<script>
import http from "@/utils/http.utils";
import SliderCheck from "@/components/SliderCheck";
import {LOGIN_FAILE_KEY} from '@/utils/constant';
export default {
  components: {
    SliderCheck
  },
  data() {
    return {
      imgData: {},
      loading:false,
      ruleForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名或手机号", trigger: "blur" }
        ],
        passowrd: [{ required: true, message: "请输入密码", trigger: "blur" }]
      }
    };
  },
  mounted() {
    // http.get("/api/get-slider-img").then(res => {
    //   console.log(res);
    //   this.imgData = res.data;
    // });


  },
  methods: {
    onSlide(e) {
      console.log(e, "滑动");
    },
    submitForm() {
      this.$refs["ruleForm"].validate(valid => {
        console.log(valid,'valid')
        if(valid){
          http.get('/')
        }
      });
    }
  }
};
</script>

<style lang="scss">
@import "~@/element-variables.scss";
.login-container {
  height: 100%;
  background: linear-gradient(#409eff, rgb(81, 168, 255), rgb(111, 181, 252));
  display: flex;
  align-items: center;
  justify-content: center;
  .login-logo {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .login-title {
    margin: 20px 0;
    font-size: 26px;
    text-align: center;
    color: $--color-primary;
    font-weight: bold;
  }
  .login-box {
    width: 360px;
    padding: 32px;
    height: 450px;
    background: #ffffff;
    border-radius: 5px;
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.5);
  }
  /deep/ .el-form--label-top .el-form-item__label {
    padding: 0;
  }
}
</style>