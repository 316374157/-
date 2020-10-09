<template>
<div>
  <ProjectStep ref="child"/>
  <div style="text-align: left">
    <el-row>
      <el-col :offset="1">
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
          <h2>
            回报设置
          </h2>
        </blockquote>
      </el-col>
      <el-col :offset="2">
        <el-table
            :data="tableData"
            border
            style="width: 100%">
          <el-table-column
              prop="supportmoney"
              label="支付金额"
              width="120"
          >
          </el-table-column>
          <el-table-column
              prop="count"
              label="名额"
              width="120">
            <template slot-scope="scope">
              <span>{{ scope.row.count ===0?"不限": scope.row.count}}</span>
            </template>
          </el-table-column>
          <el-table-column
              prop="signalpurchase"
              label="单笔限购"
              width="140">
            <template slot-scope="scope">
              <span>{{scope.row.signalpurchase ===0?"不限购": "限购"+scope.row.purchase}}</span>
            </template>
          </el-table-column>
          <el-table-column
              prop="content"
              label="回报内容"
              width="220">
          </el-table-column>
          <el-table-column
              prop="returndate"
              label="回报时间"
              width="140">
            <template slot-scope="scope">
              <span>{{scope.row.returndate+"天以后返还"}}</span>
            </template>
          </el-table-column>
          <el-table-column
              prop="freight"
              label="运费"
              width="120">
            <template slot-scope="scope">
              <span>{{scope.row.freight ===0?"包邮": "邮费"+scope.row.freight}}</span>
            </template>
          </el-table-column>
          <el-table-column
              prop="invoice"
              label="发票"
              width="120">
            <template slot-scope="scope">
              <span>{{scope.row.invoice ===0?"不开发票": "开发票"}}</span>
            </template>
          </el-table-column>
          <el-table-column
              label="操作"
              width="200">
            <template slot-scope="scope">
              <el-button @click="editClick(scope.row)" type="primary" icon="el-icon-edit" size="mini"></el-button>
              <el-button @click="deleteClick(scope.row)" type="danger" icon="el-icon-delete" size="mini"></el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-collapse>
          <el-collapse-item>
            <template slot="title">
              <el-button type="primary"><i class="el-icon-plus"></i> 添加回报</el-button>
            </template>
            <el-col :span="21">
            <div style="border:1px solid #99a9bf;background-color:#eceeef">
                <div >
                  <el-form ref="form" :model="form" label-width="120px" >
                    <el-form-item label="回报类型">
                      <el-radio-group v-model="form.type">
                        <el-radio :label="0" >实物回报</el-radio>
                        <el-radio :label="1" >虚拟物品回报</el-radio>
                      </el-radio-group>
                    </el-form-item>
                    <el-form-item label="支持金额（元）" style="width: 500px">
                      <el-input v-model="form.supportmoney"></el-input>
                    </el-form-item>
                    <el-form-item label="回报内容" style="width: 500px">
                      <el-input type="textarea" v-model="form.content"></el-input>
                    </el-form-item>
                    <el-form-item label="说明图片">
                      <el-upload
                          action="http://localhost:9000/project/upload/one/image"
                          list-type="picture-card"
                          :on-remove="describeRemove"
                          :multiple="false"
                          :limit="1"
                          :on-success="onDescribeSuccess"
                      >
                        <i class="el-icon-plus"></i>
                      </el-upload>
                      <label>支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：760*510px选择文件</label>
                    </el-form-item>
                    <el-form-item label="回报数量（名）" style="width: 500px">
                      <el-input v-model="form.count"></el-input>
                      <label>“0”为不限回报数量</label>
                    </el-form-item>
                    <el-form-item label="单笔限购">
                      <el-radio-group v-model="form.signalpurchase">
                        <el-radio :label="0" >不限购</el-radio>
                        <el-radio :label="1" >限购</el-radio>
                      </el-radio-group>
                      <div v-show="form.signalpurchase === 1">
                        <el-input v-model="form.purchase" style="width: 100px;margin-left: 20px;margin-right: 20px"></el-input>
                        <label>默认数量为1，且不超过上方已设置的回报数量</label>
                      </div>
                    </el-form-item>
                    <el-form-item label="运费(元)"  >
                      <el-input v-model="form.freight" style="width: 200px;margin-right: 20px"></el-input>
                      <label>“0”为包邮</label>
                    </el-form-item>
                    <el-form-item label="发票">
                      <el-radio-group v-model="form.invoice">
                        <el-radio :label="0" >不可开发票</el-radio>
                        <el-radio :label="1" >可开发票（包括个人发票和自定义抬头发票）</el-radio>
                      </el-radio-group>
                    </el-form-item>
                    <el-form-item label="回报时间">
                      <label>
                        项目结束后
                      </label>
                      <el-input v-model="form.returndate" style="width: 100px;margin-left: 20px;margin-right: 20px"></el-input>
                      <label>天，向支持者发送回报</label>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="create">立即创建</el-button>
                    </el-form-item>
                  </el-form>
                </div>
            </div>
            </el-col>
          </el-collapse-item>
        </el-collapse>

      </el-col>

      <div>
        <el-col :offset="1">
          <div>
            <blockquote style="border-left: 5px solid #99a9bf;padding: 0 0 0 20px;">
              <p >
                <i class="el-icon-info" style="color:#2a6496;"></i> 提示
              </p> <small>3个以上的回报：多些选择能提高项目的支持率。几十、几百、上千元的支持：3个不同档次的回报，能让你的项目更快成功。回报最好是项目的衍生品：<br>与项目内容有关的回报更能吸引到大家的支持。</small>
            </blockquote>
          </div>
        </el-col>
      </div>


    </el-row>
  </div>
  <el-button  @click="pre">上一步</el-button>
  <el-button type="warning" @click="next">下一步</el-button>
</div>
</template>

<script>
import ProjectStep from "@/components/ProjectStep";
export default {
  name: "startStep2",
  components:{
    ProjectStep
  },
  methods:{
    create(){
      axios.post("http://localhost:9000/project/save/project/return",this.form).then(response =>{
        if(response.data.result === "SUCCESS"){
          this.getTable();
        }else {
          this.showFiledMsg(response.data.msg)
        }
      })
    },
    describeRemove(file, fileList) {
      this.form.describPicPath = '';
    },
    onDescribeSuccess(response,file,fileList){
      this.form.describPicPath = response.data;
    },
    next(){
      this.$refs.child.next();
      this.$router.push("/startStep3")
    },
    pre(){
      this.$refs.child.pre();
      this.$router.push("/startStep1")
    },
    editClick(row){

    },
    deleteClick(row){

    },
    getTable(){
      axios.get("http://localhost:9000/project/get/project/returnlist").then(response =>{
        if(response.data.result === "SUCCESS"){
          this.tableData = response.data.data
        }else {
          this.showFiledMsg(response.data.msg)
        }
      })
    },
    showFiledMsg(msg){
      this.$message({
        showClose: true,
        message: msg,
        type: 'error'
      });
    }
  },
  data(){
    return{
      tableData:[],
      form:{
        // 回报类型：0 - 实物回报， 1 虚拟物品回报
        type:0,
        // 支持金额
        supportmoney:0,
        // 回报内容介绍
        content:'',
        // 总回报数量，0 为不限制
        count:0,
        // 是否限制单笔购买数量，0 表示不限购，1 表示限购
        signalpurchase:0,
        // 如果单笔限购，那么具体的限购数量
        purchase:0,
        // 运费，“0”为包邮
        freight:0,
        // 是否开发票，0 - 不开发票， 1 - 开发票
        invoice:0,
        // 众筹结束后返还回报物品天数
        returndate:0,
        // 说明图片路径
        describPicPath:'',
      }
    }
  },
  created() {
    this.getTable()
  }
}
</script>

<style scoped>

</style>