<template>
<div>
  <el-row>
    <el-col :offset="2" :span="4">
      <el-card :body-style="{ padding: '0px' }">
        <img src="../static/img/services-box1.jpg" class="image">
        <div style="padding: 14px;">
          <span style="font-size: 40px;margin: 10px;">{{user.username}}</span><br/>
          <el-tag type="danger">{{authStatus()}}</el-tag>
          <div class="bottom clearfix">
            <el-menu
                :default-active="active"
                class="el-menu-vertical-demo"
                router
            >
              <el-menu-item index="/">
                <span slot="title">首页<i class="el-icon-arrow-right" style="margin-left: 50px"></i></span>
              </el-menu-item>
              <el-menu-item index="/asset">
                <span slot="title">资产总览<i class="el-icon-arrow-right" style="margin-left: 50px"></i></span>
              </el-menu-item>
              <el-menu-item index="/myCrowdfunding">
                <span slot="title">我的众筹<i class="el-icon-arrow-right" style="margin-left: 50px"></i></span>
              </el-menu-item>
            </el-menu>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :offset="1" :span="16">
      <router-view/>
    </el-col>
  </el-row>
</div>
</template>

<script>
import EChartsTest from "@/components/EChartsTest";

export default {
  name: "UserHome",
  components:{
    EChartsTest
  },
  data(){
    return{
      active: this.$route.path,
      user: JSON.parse(sessionStorage.getItem("user"))
    }
  },
  methods: {
    authStatus() {
      /**
       * 实名认证状态 0 - 未实名认证， 1 - 实名认证申 请中， 2 - 已实名认证
       */
      if (this.user.authstatus === 0) {
        return "未实名认证";
      }
      if (this.user.authstatus === 1) {
        return "实名认证审核中";
      }
      if (this.user.authstatus === 2) {
        return "已实名认证";
      }
    },
  }
}
</script>

<style scoped>

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
  border: #99a9bf;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>