<template>
<div>
  <ProjectStep ref="child"/>
  <div style="text-align: left">
    <el-row>
      <el-col :offset="1">
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
          <h2>
            完成
          </h2>
        </blockquote>
      </el-col>
      <el-col :offset="1" :span="20">
          <div style="padding:60px;border:3px solid #99a9bf;">
            <b><i class="el-icon-check" style="color:green;font-size:50px;font-weight: 900"></i></b> <span style="margin-left:20px;">你发起的众筹项目信息已经提交完毕，我们会在5~7个工作日内对项目进行审核，请耐心等候</span>
            <p style="margin-left:60px;">你能在会员中心-我的众筹- 发起的项目中查看</p>
          </div>
      </el-col>
    </el-row>
  </div><br/>
  <el-button type="success" @click="toMyCrowdfunding">完成</el-button>
</div>
</template>

<script>
import ProjectStep from "@/components/ProjectStep";
export default {
  name: "startStep4",
  components:{
    ProjectStep
  },
  methods:{
    toMyCrowdfunding(){
      this.$refs.child.next();
      this.$router.push("/myCrowdfunding")
    }
  }
}
</script>

<style scoped>

</style>