<template>
<div>
  <el-row>
    <el-col :offset="4" :span="16">
      <el-tabs active-name="3">
        <el-tab-pane name="1">
          <span slot="label" @click="toHome"><i class="el-icon-s-home"></i> 众筹首页</span>
        </el-tab-pane>
        <el-tab-pane name="2">
          <span slot="label"><i class="el-icon-edit-outline"></i> 项目总览</span>
          <RouterView/>
        </el-tab-pane>
        <el-tab-pane name="3">
          <span slot="label" @click="toStart"><i class="el-icon-date"></i> 发起项目</span>
          <RouterView/>
        </el-tab-pane>
        <el-tab-pane name="4">
          <span slot="label" @click="toMyCrowdfunding"><i class="el-icon-user"></i> 我的众筹</span>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </el-row>
</div>
</template>

<script>
export default {
  name: "CreateCrowd",
  methods:{
    toHome(){
      this.$router.push("/")
    },
    toStart(){
      this.$router.push("/Start")
    },
    toMyCrowdfunding(){
      this.$router.push("/myCrowdfunding")
    }
  },
  created() {
    this.toStart()
  }
}
</script>

<style scoped>

</style>