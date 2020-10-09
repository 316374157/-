<template>
    <div>
        <h1 style="text-align: center">管理员登录</h1>
        <el-col :span="5" :offset="9">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="账号：" prop="loginAcct">
                    <el-input v-model="ruleForm.loginAcct" suffix-icon="el-icon-user-solid" placeholder="请输入账号"></el-input>
                </el-form-item>
                <el-form-item label="密码：" prop="userPswd">
                    <el-input type="password" v-model="ruleForm.userPswd" suffix-icon="el-icon-lock" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item>
                        <el-button style="width: 298px" type="primary" @click="submitForm('ruleForm')">登录</el-button>
                </el-form-item>
            </el-form>
        </el-col>
    </div>
</template>

<script>
    export default {
        name: "AdminLoginFrom",
        data() {
            return {
                ruleForm: {
                    loginAcct: '',
                    userPswd: '',
                },
                rules: {
                    loginAcct: [
                        { required: true, message: '请输入账号', trigger: 'change' },
                        { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'change' }
                    ],
                    userPswd: [
                        { required: true, message: '请输入密码', trigger: 'change' }
                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post('http://localhost:8181/admin/do/login',this.$qs.stringify(this.ruleForm)).then(response =>{
                          if(response.data.result === 'SUCCESS'){
                            sessionStorage.setItem("admin",JSON.stringify(response.data.data));
                                this.$router.push("/main");
                            }else {
                                this.showFiledMsg(response.data.msg);
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            showFiledMsg(msg){
                this.$message({
                    showClose: true,
                    message: msg,
                    type: 'error'
                });
            }
        }
    }
</script>

<style scoped>

</style>