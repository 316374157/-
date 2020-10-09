<template>
<div>
  <PayStep ref="child"/>
  <div style="text-align: left">

  </div><br/>
  <el-button @click="pre">上一步</el-button>
  <el-button type="warning" @click="next">下一步</el-button>
</div>
</template>

<script>
import PayStep from "@/components/PayStep";

export default {
  name: "payStep3",
  components:{
    PayStep
  },
  methods:{
    next(){
      this.$refs.child.next();
      this.$router.push("/payStep4");
    },
    pre(){
      this.$refs.child.pre();
      this.$router.push("/payStep2")
    },
  },
  data(){
    return{
      form:{

      }
    }
  }

}
</script>

<style scoped>

</style>