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
        <el-col :span="18" :offset="3">
            <el-form-item label="用户名" prop="username">
              <el-input v-model="ruleForm.username" placeholder="请输入用户名或手机号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="18" :offset="3">
            <el-form-item label="密码" prop="passowrd">
              <el-input v-model="ruleForm.passowrd" placeholder="请输入密码"></el-input>
            </el-form-item>
          </el-col>
   
          <el-col :span="18" :offset="3">
            <el-form-item>
              <el-popover
                v-model="imgDisplay"
                placement="top"
                width="400"
                trigger="click">
                <div style="height:282px;width:400px;">
                  <SliderCheck v-if="imgData" :imgData="imgData" @change="onSlide" :height="252" :width="400" />
                </div>
                <el-button slot="reference" style="width:100%;" :loading="loading" :disabled="!ruleForm.username || !ruleForm.passowrd || imgDisplay"   type="primary" @click="submitForm('ruleForm')">登 录</el-button>
              </el-popover>

            </el-form-item>
          </el-col>
        </el-form>
      </div>
    </div>
  </div>

</template>

<script>
import http from "@/utils/http.utils";
import SliderCheck from "@/components/SliderCheck";
import { LOGIN_FAILE_KEY } from "@/utils/constant";
export default {
  components: {
    SliderCheck
  },
  data() {
    return {
      imgDisplay: false,
      imgData: null,
      loading: false,
      ruleForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户名或手机号", trigger: "blur" }
        ],
        passowrd: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
    };
  },

  methods: {
    onSlide(e) {
      this.doCheck(e);
    },
    doCheck(x) {
      http
        .post("/login/checkSlider", { distance: x.toFixed("1") })
        .then(res => {
          if (res.code == 0) {
            this.imgDisplay = false;
            this.doSubmit("1", "2");
          } else {
            this.getCode();
          }
        })
        .catch(err => {
          this.getCode();
        });
    },
    submitForm() {
      this.$refs["ruleForm"].validate(valid => {
        if (valid) {
          this.getCode();
        }
      });
    },
    getCode() {
      http.get("/login/getCode").then(res => {
        this.imgData = res.data;
      });
    },
    doSubmit(password, username) {
      this.loading = true;
      http
        .get("/login/signIn", {
          password,
          username
        })
        .then(res => {
          if (res.code == 0) {

          } else {
            this.$message.error(res.msg || "登录失败");
          }
        }).catch(err=>{
            this.$message.error( "登录失败");
        }).finally(()=>{
           this.loading = false;
        })
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
    min-height: 450px;
    background: #ffffff;
    border-radius: 5px;
    box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.5);
  }
  /deep/ .el-form--label-top .el-form-item__label {
    padding: 0;
  }
}
</style>