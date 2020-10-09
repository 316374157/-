<template>
    <div style="border:2px solid lightslategray;border-radius: 4px">
        <el-container>
            <el-header>
               <el-row>
                   <el-col :span="7">
                       <el-input placeholder="请输入查询条件" v-model="keyWord">
                           <template slot="prepend">查询条件</template>
                       </el-input>
                   </el-col>
                   <el-col :span="2">
                       <el-button type="warning" icon="el-icon-search" @click="search(keyWord)">查询</el-button>
                   </el-col>
                   <el-col :span="2" :offset="10">
                       <el-button type="primary" icon="el-icon-plus" @click="toAddFrom">新增</el-button>
                   </el-col>
                   <el-col :span="2">
                       <el-button type="danger" icon="el-icon-close" @click="toggleSelection">删除</el-button>
                   </el-col>
               </el-row>
            </el-header>
            <el-container>
                <el-main>
                    <div>
                        <!-- 用户Form -->
                        <el-dialog :title="title" :visible.sync="dialogFormVisible">
                            <el-form style="width: 80%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                                <el-form-item label="账号" prop="loginAcct">
                                    <el-input v-model="ruleForm.loginAcct" :readonly="btnName === '修改'"></el-input>
                                </el-form-item>
                                <el-form-item label="密码" prop="userPswd">
                                    <el-input type="password" v-model="ruleForm.userPswd" :readonly="btnName === '修改'"></el-input>
                                </el-form-item>
                                <el-form-item label="姓名" prop="userName">
                                    <el-input  v-model.float="ruleForm.userName"></el-input>
                                </el-form-item>
                                <el-form-item label="邮箱" prop="email">
                                    <el-input  type="email" v-model.number="ruleForm.email"></el-input>
                                </el-form-item>
                                <el-form-item label="编号" prop="id" hidden>
                                    <el-input type="number" v-model.number="ruleForm.id" readonly hidden></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="submitForm('ruleForm')">{{btnName}}</el-button>
                                </el-form-item>
                            </el-form>
                        </el-dialog>

                        <!-- 角色Form -->
                        <el-dialog title="角色管理" :visible.sync="roleFormVisible">
                            <el-row>
                                <el-transfer style="text-align: left; display: inline-block"
                                             target-order="unshift"
                                             v-model="adminAssignRole"
                                             :data="adminAllRole"
                                             :titles="['未拥有的角色', '已拥有的角色']"
                                             :props="{
                                                key: 'id',
                                                label: 'name'
                                         }"
                                             :filterable="true"
                                ></el-transfer>
                            </el-row>
                            <el-row>
                                <el-button type="primary" @click="submitRoleForm">提交</el-button>
                            </el-row>
                        </el-dialog>

                        <el-table
                                ref="multipleTable"
                                :data="admins"
                                style="width: 100%"
                                height="650"
                                border
                        >
                            <el-table-column
                                    type="selection"
                                    width="60">
                            </el-table-column>
                            <el-table-column
                                    fixed="left"
                                    prop="id"
                                    label="编号"
                                    width="60">
                            </el-table-column>
                            <el-table-column
                                    prop="loginAcct"
                                    label="账号"
                                    width="200">
                            </el-table-column>
                            <el-table-column
                                    prop="userName"
                                    label="姓名"
                                    width="200">
                            </el-table-column>
                            <el-table-column
                                    prop="email"
                                    label="邮箱"
                                    width="200">
                            </el-table-column>
                            <el-table-column
                                    label="操作"
                                    width="500">
                                <template slot-scope="scope">
                                    <el-button @click="editRoleClick(scope.row)" type="success" icon="el-icon-edit-outline" >角色管理</el-button>
                                    <el-button @click="editClick(scope.row)" type="primary" icon="el-icon-edit" >编辑</el-button>
                                    <el-button @click="deleteClick(scope.row)" type="danger" icon="el-icon-delete">删除</el-button>
                                </template>
                            </el-table-column>
                        </el-table>

                        <el-pagination
                                background
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :page-sizes="[10,15]"
                                :page-size="pageSize"
                                :current-page="pageNow"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="total">
                        </el-pagination>
                    </div>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "User",
        methods: {

            submitRoleForm(){
                this.adminRole.roleIdList = this.adminAssignRole;
                axios.post(`http://localhost:8181/role/updateAdminRole`,this.adminRole).
                then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg("角色",response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                })
            },

            editRoleClick(row){
                axios.get(`http://localhost:8181/role/getAdminRole/${row.id}`)
                    .then(response =>{
                        if(response.data.result === 'SUCCESS'){
                            this.roleFormVisible = true;
                            this.adminRole.id = row.id;
                            this.adminAssignRole = response.data.data.adminAssignRole;
                            this.adminAllRole = response.data.data.adminAllRole;
                        }else {
                            this.showFiledMsg(response.data.msg);
                        }
                    });
                this.roleFormVisible = true;
            },

            /**
             * 模糊查询，附带分页
             * @param keyWord 查询条件
             */
            search(keyWord){
                this.getPage(keyWord,1,this.pageSize);
            },
            /**
             * 编辑该行
             * @param row 当前行
             */
            editClick(row) {
                this.btnName = "修改";
                this.title = "更新管理员信息";
                this.dialogFormVisible=true;
                this.ruleForm.id = row.id;
                this.ruleForm.loginAcct = row.loginAcct;
                this.ruleForm.userPswd = row.userPswd;
                this.ruleForm.userName = row.userName;
                this.ruleForm.email = row.email;
            },
            /**
             * 删除该行
             * @param row 当前行
             */
            deleteClick(row){
                this.$confirm(`此操作将删除"${row.userName}"管理员, 是否继续?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(() => {
                    let id=[];
                    id.push(row.id);
                    this.deleteFrom(id, row.userName);
                }).catch(() => {});
            },
            /**
             * 页面页数变化
             * @param val 新页数
             */
            handleSizeChange(val) {
                this.pageSize = val;
                this.getPage(this.keyWord,this.pageNow,this.pageSize);
            },
            /**
             * 页码发生变化
             * @param page 新页码
             */
            handleCurrentChange(page) {
                this.pageNow = page;
                this.getPage(this.keyWord,this.pageNow,this.pageSize);
            },
            /**
             * 获取分页数据
             * @param keyWord 条件
             * @param pageNow 页码
             * @param size 每页数据
             */
            getPage(keyWord,pageNow,size){
                axios.get(`http://localhost:8181/admin/getAdminPage?keyWord=${keyWord}&pageNum=${pageNow}&pageSize=${size}`).then(response => {
                    this.admins = response.data.data.records;
                    this.total = response.data.data.total;
                    this.pageSize = response.data.data.size;
                    this.pageNow = response.data.data.current;
                })
            },
            /**
             * 向后台提交表单数据
             * @param formName 提交的表单名称
             */
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (this.ruleForm.id === '' || this.ruleForm.id === null){
                            this.addAdminFrom();
                        }else {
                            this.updateAdminFrom();
                        }
                    }
                });
            },
            updateAdminFrom(){
                axios.put('http://localhost:8181/admin/update',this.ruleForm).then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.userName,response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },
            addAdminFrom(){
                axios.post('http://localhost:8181/admin/add',this.ruleForm).then(response =>{
                    this.dialogFormVisible=false;
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.userName,response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },
            deleteFrom(id,name){
                axios.delete(`http://localhost:8181/admin/delete`,
                    {data: id})
                    .then(response =>{
                        if(response.data.result === 'SUCCESS'){
                            this.alertConfirmMsg(name,response.data.msg);
                        }else {
                            this.showFiledMsg(response.data.msg);
                        }
                    });
            },
            /**
             * 调出添加表单
             */
            toAddFrom(){
                this.btnName = "添加";
                this.title = "添加管理员信息";
                this.keyWord = '';
                this.dialogFormVisible=true;
                this.ruleForm.id = '';
                this.ruleForm.loginAcct = '';
                this.ruleForm.userPswd = '';
                this.ruleForm.userName = '';
                this.ruleForm.email = '';
            },
            /**
             * 展示错误消息
             * @param msg 错误消息
             */
            showFiledMsg(msg){
                this.$message({
                    showClose: true,
                    message: msg,
                    type: 'error'
                });
            },
            /**
             * 弹出需要确认的提示消息
             * @param name 用户名称
             * @param msg 要显示的消息
             */
            alertConfirmMsg(name,msg){
                this.$alert(`管理员 ${name}${msg}`, "提示", {
                    confirmButtonText: '确定',
                    type:'success',
                    callback: action => {
                        this.getPage(this.keyWord,this.pageNow,this.pageSize);
                    }
                });
            },
            toggleSelection() {
                let id=[];
                this.$refs.multipleTable.selection.forEach(function(item){
                    id.push(item.id);
                });
                this.$confirm( `此操作将删除这${this.$refs.multipleTable.selection.length}个管理员, 是否继续?`,'提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(() => {
                    this.deleteFrom(id,"");
                }).catch(() => {});
            },
        },
        created() {
            this.getPage('',1,10);
        },
        data() {
            return {
                adminRole:{},
                adminAssignRole:[],
                adminAllRole:[],
                multipleTable:[],
                btnName:'修改',
                title:'更新管理员信息',
                keyWord:'',
                total:null,
                pageSize:null,
                admins: null,
                pageNow:null,
                dialogFormVisible:false,
                roleFormVisible:false,
                ruleForm: {
                    id:'',
                    loginAcct: '',
                    userPswd: '',
                    userName: '',
                    email: ''
                },
                rules: {
                    loginAcct: [
                        { required: true, message: '请输入账号', trigger: 'blur' },
                        { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'change' }
                    ],
                    userPswd: [
                        { required: true, message: '请输入密码', trigger: 'blur' }
                    ],
                    userName: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                    ]
                }
            }
        }
    }
</script>

<style scoped>
    .el-header {
        background: white;
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-main {
        color: #333;
        text-align: center;
        line-height: 40px;
    }

    body > .el-container {
        margin-bottom: 0;
    }
</style>