<template>
    <div style="border:2px solid lightslategray;border-radius: 4px">

        <el-row>
            <el-button style="margin-left: 30px" type="success" icon="el-icon-plus" @click="addRootClick"></el-button>
        </el-row>
        <el-tree
                :expand-on-click-node="false"
                :data="data"
                node-key="id"
                default-expand-all
                @node-click="handleNodeClick">
          <span slot-scope="{ data }">
              <span>{{ data.label}}</span>
              <el-button style="margin-left: 30px" v-show="(showId === data.id) && (data.name === '' || data.children.length > 0 )" type="success"
                         icon="el-icon-plus" size="mini" @click="addClick(data)"></el-button>
              <el-button v-show="showId === data.id" type="primary" icon="el-icon-edit" size="mini" @click="editClick(data)" ></el-button>
              <el-button v-show="(showId === data.id) && (data.children.length === 0)" type="danger"
                         icon="el-icon-delete" size="mini" @click="deleteClick(data)"></el-button>
          </span>
        </el-tree>


        <!-- Form -->
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form style="width: 80%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="节点名称" prop="title">
                    <el-input v-model="ruleForm.title"></el-input>
                </el-form-item>
                <el-form-item label="节点权限" prop="name" v-show="showInput">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="编号" prop="id" hidden>
                    <el-input type="number" v-model.number="ruleForm.id" readonly hidden></el-input>
                </el-form-item>
                <el-form-item label="父节点" prop="categoryId" hidden>
                    <el-input type="number" v-model.number="ruleForm.categoryId" readonly hidden></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">{{btnName}}</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "Auth",
        data() {
            return {
                showId:'',
                showInput:'',
                data:[],
                btnName:'修改',
                title:'更新节点信息',
                dialogFormVisible:false,
                ruleForm: {
                    id:'',
                    categoryId:'',
                    name: '',
                    title:''
                },
                rules: {
                    title: [
                        { required: true, message: '请输入节点名称', trigger: 'blur' }
                    ]
                }
            };
        },
        methods: {
            handleNodeClick(data) {
                this.showId = data.id;
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (this.ruleForm.id === '' || this.ruleForm.id === null){
                            this.addAuthFrom();
                        }else {
                            this.updateAuthFrom();
                        }
                    }
                });
            },
            updateAuthFrom(){
                axios.put('http://localhost:8181/auth/update',this.ruleForm).then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.title,response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },
            addAuthFrom(){
                axios.post('http://localhost:8181/auth/add',this.ruleForm).then(response =>{
                    this.dialogFormVisible=false;
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.title,response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },
            showFiledMsg(msg){
                this.$message({
                    showClose: true,
                    message: msg,
                    type: 'error'
                });
            },
            alertConfirmMsg(name,msg){
                this.$alert(`节点 ${name}${msg}`, "提示", {
                    confirmButtonText: '确定',
                    type:'success',
                    callback: action => {
                        this.getAuthTree();
                    }
                });
            },
            addClick(data){
                this.showInput = true;
                this.btnName = "添加";
                this.title = "添加节点信息";
                this.dialogFormVisible=true;
                this.ruleForm.id = '';
                this.ruleForm.title = '';
                this.ruleForm.name = '';
                this.ruleForm.categoryId = data.id;
            },
            addRootClick(){
                this.showInput = false;
                this.btnName = "添加";
                this.title = "添加根节点信息";
                this.dialogFormVisible=true;
                this.ruleForm.id = '';
                this.ruleForm.title = '';
                this.ruleForm.name = '';
                this.ruleForm.categoryId = '';
            },
            editClick(data){
                if (data.children.length > 0 || data.name === null){
                    this.showInput = false;
                }
                this.btnName = "修改";
                this.title = "更新节点信息";
                this.dialogFormVisible=true;
                this.ruleForm.id = data.id;
                this.ruleForm.name = data.name;
                this.ruleForm.categoryId = data.categoryId;
                this.ruleForm.title = data.label;
            },
            deleteClick(data){
                this.$confirm(`此操作将删除"${data.label}"节点, 是否继续?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(() => {
                    axios.delete(`http://localhost:8181/auth/delete/${data.id}`, )
                        .then(response =>{
                            if(response.data.result === 'SUCCESS'){
                                this.alertConfirmMsg(data.label,response.data.msg);
                            }else {
                                this.showFiledMsg(response.data.msg);
                            }
                        });
                }).catch(() => {});
            },
            getAuthTree(){
                axios.get("http://localhost:8181/auth/getAuthTree").then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.data=[];
                        response.data.data.forEach((item,index) =>{
                            this.data.push(item);
                        });
                    }
                })
            }
        },
        created() {
            this.getAuthTree();
        }
    }
</script>

<style scoped>

</style>