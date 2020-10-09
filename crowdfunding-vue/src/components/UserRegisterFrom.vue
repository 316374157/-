<template>
  <div>
    <h1 style="text-align: center">用户注册</h1>
    <el-col :span="5" :offset="9">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="账号：" prop="loginacct">
          <el-input v-model="ruleForm.loginacct" suffix-icon="el-icon-user-solid" placeholder="请输入账号"></el-input>
        </el-form-item>
        <el-form-item label="密码：" prop="userpswd">
          <el-input type="password" v-model="ruleForm.userpswd" suffix-icon="el-icon-lock"
                    placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item label="邮箱：" prop="email">
          <el-input type="email" v-model="ruleForm.email" suffix-icon="el-icon-message" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="姓名：" prop="username">
          <el-input type="username" v-model="ruleForm.username" suffix-icon="el-icon-info"
                    placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="手机号：" prop="phoneNum">
          <el-row>
            <el-col :span="14">
              <el-input v-model="ruleForm.phoneNum" suffix-icon="el-icon-phone" placeholder="请输入手机号"></el-input>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-button type="success" @click="sendCodeMessage">获取验证码</el-button>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item label="验证码：" prop="code">
          <el-row>
            <el-col :span="14">
              <el-input v-model="ruleForm.code" suffix-icon="el-icon-s-comment" placeholder="请输入验证码"></el-input>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-input :value="codeMessage" readonly v-show="codeMessage.length === 4"></el-input>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 298px" type="primary" @click="toLogin">返回登录</el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 298px" type="success" @click="submitForm('ruleForm')">注册</el-button>
        </el-form-item>
      </el-form>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "UserRegisterFrom",
  data() {
    return {
      codeMessage: '',
      ruleForm: {
        loginacct: '',
        userpswd: '',
        email: '',
        phoneNum: '',
        username: '',
        code: ''
      },
      rules: {
        loginacct: [
          {required: true, message: '请输入账号', trigger: 'change'},
          {min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'change'}
        ],
        userpswd: [
          {required: true, message: '请输入密码', trigger: 'change'}
        ],
        email: [
          {}
        ],
        phoneNum: [
          {required: true, message: '请输入手机号', trigger: 'change'}
        ],
        username: [
          {}
        ],
        code: [
          {required: true, message: '请输入验证码', trigger: 'change'}
        ]
      }
    };
  },
  methods: {
    toLogin() {
      this.$router.push("/userLogin");
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:9000/auth/do/member/register', this.ruleForm).then(response => {
            if (response.data.result === 'SUCCESS') {
              this.$router.push("/userLogin");
            } else {
              this.showMsg(response.data.msg, 'error');
            }
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    showMsg(msg, type) {
      this.$message({
        showClose: true,
        message: msg,
        type: type
      });
    },
    sendCodeMessage() {
      if (this.ruleForm.phoneNum.length > 0) {
        axios.get(`http://localhost:9000/auth/member/send/code/Message?phoneNum=${this.ruleForm.phoneNum}`).then(response => {
          if (response.data.result === 'SUCCESS') {
            this.codeMessage = response.data.data;
            this.showMsg(response.data.msg, 'success');
          } else {
            this.showMsg(response.data.msg, 'error');
          }
        })
      } else {
        this.showFiledMsg('手机号不能为空！', 'error')
      }
    }
  }
}
</script>

<style scoped>

</style>