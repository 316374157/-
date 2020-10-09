<template>
<div>
  <ProjectStep ref="child"/>
  <div style="text-align: left">

  </div><br/>
  <el-button type="success" @click="toMyCrowdfunding">完成</el-button>
</div>
</template>

<script>
import PayStep from "@/components/PayStep";
export default {
  name: "payStep4",
  components:{
    PayStep
  },
  methods:{
    toMyCrowdfunding(){

    }
  }
}
</script>

<style scoped>

</style>