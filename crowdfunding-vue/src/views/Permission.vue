<template>
    <div style="border:2px solid lightslategray;border-radius: 4px">
        <el-tree
                :expand-on-click-node="false"
                :data="data"
                node-key="id"
                default-expand-all
                @node-click="handleNodeClick">
          <span slot-scope="{ data }">
              <span><i :class="data.icon"></i>{{ data.label}}</span>
              <el-button style="margin-left: 30px" v-show="(showId === data.id) && (data.pid === 1 || data.pid === null)" type="success"
                         icon="el-icon-plus" size="mini" @click="addClick(data)"></el-button>
              <el-button v-show="showId === data.id" type="primary" icon="el-icon-edit" size="mini" @click="editClick(data)" ></el-button>
              <el-button v-show="(showId === data.id) && (data.children.length === 0)" type="danger"
                         icon="el-icon-delete" size="mini" @click="deleteClick(data)"></el-button>
          </span>
        </el-tree>


        <!-- Form -->
        <el-dialog :title="title" :visible.sync="dialogFormVisible">
            <el-form style="width: 80%" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item label="菜单节点名称" prop="name">
                    <el-input v-model="ruleForm.name"></el-input>
                </el-form-item>
                <el-form-item label="菜单节点url地址" prop="url">
                    <el-input v-model="ruleForm.url"></el-input>
                </el-form-item>
                <el-form-item label="菜单节点显示图标" prop="icon">
                    <el-radio-group v-model="ruleForm.icon">
                        <el-radio label="el-icon-s-grid" ><i class="el-icon-s-grid"></i></el-radio>
                        <el-radio label="el-icon-s-platform"><i class="el-icon-s-platform"></i></el-radio>
                        <el-radio label="el-icon-s-fold"><i class="el-icon-s-fold"></i></el-radio>
                        <el-radio label="el-icon-user-solid"><i class="el-icon-user-solid"></i></el-radio>
                        <el-radio label="el-icon-s-custom"><i class="el-icon-s-custom"></i></el-radio>
                        <el-radio label="el-icon-shopping-bag-2"><i class="el-icon-shopping-bag-2"></i></el-radio>
                        <el-radio label="el-icon-check"><i class="el-icon-check"></i></el-radio>
                        <el-radio label="el-icon-document-checked"><i class="el-icon-document-checked"></i></el-radio>
                        <el-radio label="el-icon-menu"><i class="el-icon-menu"></i></el-radio>
                        <el-radio label="el-icon-picture"><i class="el-icon-picture"></i></el-radio>
                        <el-radio label="el-icon-s-data"><i class="el-icon-s-data"></i></el-radio>
                        <el-radio label="el-icon-edit"><i class="el-icon-edit"></i></el-radio>
                        <el-radio label="el-icon-s-shop"><i class="el-icon-s-shop"></i></el-radio>
                        <el-radio label="el-icon-s-comment"><i class="el-icon-s-comment"></i></el-radio>
                        <el-radio label="el-icon-notebook-2"><i class="el-icon-notebook-2"></i></el-radio>
                        <el-radio label="el-icon-price-tag"><i class="el-icon-price-tag"></i></el-radio>
                        <el-radio label="el-icon-document"><i class="el-icon-document"></i></el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="编号" prop="id" hidden>
                    <el-input type="number" v-model.number="ruleForm.id" readonly hidden></el-input>
                </el-form-item>
                <el-form-item label="父节点" prop="pid" hidden>
                    <el-input type="number" v-model.number="ruleForm.pid" readonly hidden></el-input>
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
        name: "Permission",
        data() {
            return {
                showId:'',
                data:[],
                btnName:'修改',
                title:'更新菜单节点信息',
                dialogFormVisible:false,
                ruleForm: {
                    id:'',
                    pid:'',
                    name: '',
                    url:'',
                    icon:''
                },
                rules: {
                    name: [
                        { required: true, message: '请输入菜单节点名称', trigger: 'blur' }
                    ],
                    url: [
                        { required: true, message: '请输入菜单节点url地址', trigger: 'blur' }
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
                            this.addMenuFrom();
                        }else {
                            this.updateMenuFrom();
                        }
                    }
                });
            },
            updateMenuFrom(){
                console.log(this.ruleForm);
                axios.put('http://localhost:8181/menu/update',this.ruleForm).then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.name,response.data.msg);
                    }else {
                        this.showFiledMsg(response.data.msg);
                    }
                });
            },
            addMenuFrom(){
                console.log(this.ruleForm);
                axios.post('http://localhost:8181/menu/add',this.ruleForm).then(response =>{
                    this.dialogFormVisible=false;
                    if(response.data.result === 'SUCCESS'){
                        this.alertConfirmMsg(this.ruleForm.name,response.data.msg);
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
                this.$alert(`菜单节点 ${name}${msg}`, "提示", {
                    confirmButtonText: '确定',
                    type:'success',
                    callback: action => {
                        this.getWholeTree();
                    }
                });
            },
            addClick(data){
                this.btnName = "添加";
                this.title = "添加菜单节点信息";
                this.dialogFormVisible=true;
                this.ruleForm.id = '';
                this.ruleForm.name = '';
                this.ruleForm.url = '';
                this.ruleForm.icon = '';
                this.ruleForm.pid = data.id;
            },
            editClick(data){
                this.btnName = "修改";
                this.title = "更新菜单节点信息";
                this.dialogFormVisible=true;
                this.ruleForm.id = data.id;
                this.ruleForm.name = data.label;
                this.ruleForm.pid = data.pid;
                this.ruleForm.url = data.url;
                this.ruleForm.icon = data.icon;
            },
            deleteClick(data){
                this.$confirm(`此操作将删除"${data.label}"节点, 是否继续?`, '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'error'
                }).then(() => {
                    axios.delete(`http://localhost:8181/menu/delete/${data.id}`, )
                        .then(response =>{
                            if(response.data.result === 'SUCCESS'){
                                this.alertConfirmMsg(data.label,response.data.msg);
                            }else {
                                this.showFiledMsg(response.data.msg);
                            }
                        });
                }).catch(() => {});
            },
            getWholeTree(){
                axios.get("http://localhost:8181/menu/getWholeTree").then(response =>{
                    if(response.data.result === 'SUCCESS'){
                        this.data = [];
                        this.data.push(response.data.data);
                    }
                })
            }
        },
        created() {
            this.getWholeTree();
        }
    }
</script>

<style scoped>

</style>