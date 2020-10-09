<template>
  <div>
    <el-container>
      <el-header>
        <HomeNavHeader/>
      </el-header>
      <el-container>
        <el-main>
          <RouterView/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import HomeNavHeader from "@/components/HomeNavHeader";
export default {
  name: "PreHome",
  components:{
    HomeNavHeader
  },
  data(){
    return{
    }
  },

}
</script>

<style scoped>


</style>