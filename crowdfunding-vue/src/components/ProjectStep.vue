<template>
  <el-steps :active="active" finish-status="success" space="25%" simple>
    <el-step title="1. 项目及发起人信息" ></el-step>
    <el-step title="2. 回报设置" ></el-step>
    <el-step title="3. 确认信息" ></el-step>
    <el-step title="4. 完成" ></el-step>
  </el-steps>
</template>

<script>
export default {
  name: "ProjectStep",
  data() {
    return {
      active: this.$store.getters.getIndex
    };
  },
  methods: {
    next(){
      if(this.active<4){
        this.active++;
        this.$store.dispatch("setIndex",this.active);
      }else {
        this.active = 0;
      }
    },
    pre(){
      if(this.active>0){
        this.active--;
        this.$store.dispatch("setIndex",this.active);
      }
    }
  },
}
</script>

<style scoped>

</style>