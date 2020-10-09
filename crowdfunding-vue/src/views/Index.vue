<template>
  <div>
    <el-container>
      <el-header>
        <NavHeader v-if="!isLogin"/>
        <HomeNavHeader v-if="isLogin"/>
      </el-header>
      <el-container>
        <el-main v-if="this.$route.path === '/' ">
          <Carousel/>
          <Card/>
          <CentersItem/>
        </el-main>
        <el-main v-if="this.$route.path !== '/' ">
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import HomeNavHeader from "@/components/HomeNavHeader";
import NavHeader from '../components/NavHeader';
import Carousel from '../components/Carousel';
import Card from "../components/Card";
import CentersItem from "../components/CentersItem";

export default {
  name: "index",
  data(){
    return{
      isLogin: false
    }
  },
  components: {
    NavHeader,
    Carousel,
    Card,
    CentersItem,
    HomeNavHeader
  },
  created() {
    this.isLogin = sessionStorage.getItem("user") !== null || sessionStorage.getItem("admin") !== null;
  }
}
</script>

<style scoped>

</style>