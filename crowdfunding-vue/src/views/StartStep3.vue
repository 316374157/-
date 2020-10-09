<template>
<div>
  <ProjectStep ref="child"/>
  <div style="text-align: left">
    <el-row >
      <el-col :offset="1">
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
          <h2>
            确认信息（请填写易付宝企业账号用于收款及身份核实）
          </h2>
        </blockquote>
      </el-col>
      <el-col>
        <el-row >
          <el-col :span="11" :offset="1"><br/>
            <el-form ref="form" :model="form" label-width="130px" >
              <el-form-item label="易付宝企业账号">
                <el-input v-model="form.paynum"></el-input>
              </el-form-item>
              <el-form-item label="法人身份证号">
                <el-input v-model="form.cardnum"></el-input>
              </el-form-item>
            </el-form>
          </el-col>
          <el-col :span="10" :offset="1">
              <div  style="padding:40px;border:3px solid #99a9bf;">
                <i class="el-icon-s-custom"></i> 易购账户名：{{form.paynum}}<br><br><span style="margin-left:60px;">您正在使用该账号发起众筹项目</span>
              </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div><br/>
  <el-button @click="pre">上一步</el-button>
  <el-button type="warning" @click="next">下一步</el-button>
</div>
</template>

<script>
import ProjectStep from "@/components/ProjectStep";

export default {
  name: "startStep3",
  components:{
    ProjectStep
  },
  methods:{
    next(){
      //sessionStorage.setItem("project-step3",JSON.stringify(this.form));
      axios.post("http://localhost:9000/project/save/project/over",this.form).then(response =>{
        console.log(response)
        if(response.data.result === "SUCCESS"){
          sessionStorage.removeItem("project-step1");
          sessionStorage.removeItem("headerPicture");
          sessionStorage.removeItem("detailPicture");
          sessionStorage.removeItem("project-step2");
          sessionStorage.removeItem("project-step3");
          this.$refs.child.next();
          this.$router.push("/startStep4");
        }else {
          this.showFiledMsg(response.data.msg)
        }
      })
    },
    pre(){
      this.$refs.child.pre();
      this.$router.push("/startStep2")
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
      form:{
        // 易付宝账号
        paynum:'',
        // 法人身份证号
        cardnum:''
      }
    }
  }

}
</script>

<style scoped>

</style>