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
                        <!-- 角色Form -->
                        <el-dialog :title="title" :visible.sync="dialogFormVisible">
                            <el-form style="width: 80%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                                <el-form-item label="角色名" prop="name">
                                    <el-input v-model="ruleForm.name"></el-input>
                                </el-form-item>
                                <el-form-item label="编号" prop="id" hidden>
                                    <el-input type="number" v-model.number="ruleForm.id" readonly hidden></el-input>
                                </el-form-item>
                                <el-form-item>
                                    <el-button type="primary" @click="submitForm('ruleForm')">{{btnName}}</el-button>
                                </el-form-item>
                            </el-form>
                        </el-dialog>
                        <!-- 权限Form -->
                        <el-dialog title="权限管理" :visible.sync="authFormVisible">
                            <el-row>
                                <el-tree
                                    ref="tree"
                                    :expand-on-click-node="false"
                                    :data="data"
                                    node-key="id"
                                    default-expand-all
                                    show-checkbox
                                >
                                </el-tree>
                            </el-row>
                            <el-row>
                                <el-button type="primary" @click="submitAuthForm">提交</el-button>
                            </el-row>
                        </el-dialog>

                        <el-table
                                ref="multipleTable"
                                :data="role"
                                style="width: 100%"
                                height="650"
                                border
                        >
                            <el-table-column
                                    type="selection"
                                    width="80">
                            </el-table-column>
                            <el-table-column
                                    fixed="left"
                                    prop="id"
                                    label="编号"
                                    width="80">
                            </el-table-column>
                            <el-table-column
                                    prop="name"
                                    label="角色名"
                                    width="350">
                            </el-table-column>
                            <el-table-column
                                    label="操作"
                                    width="350">
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
        name: "Role",
        methods: {
            submitAuthForm(){
                this.roleAuth.authIdList = this.$refs.tree.getCheckedKeys();
                axios.post(`http://localhost:8181/role/updateRoleAuth`,this.roleAuth).
                then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg("权限",response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },

            editRoleClick(row){
                axios.get("http://localhost:8181/auth/getAuthTree").then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.authFormVisible = true;
                        this.roleAuth.id = row.id;
                        this.data=[];
                        response.data.data.forEach((item,index) =>{
                            this.data.push(item);
                        });
                        axios.get(`http://localhost:8181/role/getSelectAuth/${this.roleAuth.id}`).then(response =>{
                            if(response.data.result === 'SUCCESS'){
                                this.$refs.tree.setCheckedKeys(response.data.data);
                            }else {
                                this.showFiledMsg(response.data.msg);
                            }
                        });
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
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
                this.title = "更新角色信息";
                this.dialogFormVisible=true;
                this.ruleForm.id = row.id;
                this.ruleForm.name = row.name;
            },
            /**
             * 删除该行
             * @param row 当前行
             */
            deleteClick(row){
                this.$confirm(`此操作将删除"${row.name}"角色, 是否继续?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(() => {
                    let id=[];
                    id.push(row.id);
                    this.deleteFrom(id,row.name);
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
                axios.get(`http://localhost:8181/role/getRolePage?keyWord=${keyWord}&pageNum=${pageNow}&pageSize=${size}`).then(response => {
                    this.role = response.data.data.records;
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
                            this.addRoleFrom();
                        }else {
                            this.updateRoleFrom();
                        }
                    }
                });
            },
            updateRoleFrom(){
                axios.put('http://localhost:8181/role/update',this.ruleForm).then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.name,response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },
            addRoleFrom(){
                axios.post('http://localhost:8181/role/add',this.ruleForm).then(response =>{
                    this.dialogFormVisible=false;
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.name,response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },
            deleteFrom(id,name){
                axios.delete(`http://localhost:8181/role/delete`,
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
                this.ruleForm.name = '';
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
                this.$alert(`角色 ${name}${msg}`, "提示", {
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
                this.$confirm( `此操作将删除这${this.$refs.multipleTable.selection.length}个角色, 是否继续?`,'提示', {
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
                roleAuth:{},
                data:[],
                authFormVisible:false,
                multipleTable:[],
                btnName:'修改',
                title:'更新角色信息',
                keyWord:'',
                total:null,
                pageSize:null,
                role: null,
                pageNow:null,
                dialogFormVisible:false,
                ruleForm: {
                    id:'',
                    name: ''
                },
                rules: {
                    name: [
                        { required: true, message: '请输入姓名', trigger: 'blur' }
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