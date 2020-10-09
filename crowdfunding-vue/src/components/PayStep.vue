<template>
    <el-steps :active="active" finish-status="success" space="25%" simple>
      <el-step title="1. 确认回报内容" ></el-step>
      <el-step title="2. 确认订单" ></el-step>
      <el-step title="3. 付款" ></el-step>
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