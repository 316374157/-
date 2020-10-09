<template>
  <div>
    <el-col :span="13" :offset="5">
      <el-tabs v-model="activeName">
        <el-tab-pane v-for="(portalTypeVO,index) in portalTypeVOList" :name="portalTypeVO.name" :key="index">
          <span slot="label"><h2>{{ portalTypeVO.name }} <small>{{ portalTypeVO.remark }}</small></h2></span>
          <el-row :gutter="20">
            <el-col :span="6" v-for="(item,childIndex) in portalTypeVO.portalProjectVOList" :key="childIndex">
              <el-card :body-style="{ padding: '0px' }" style="height: 320px">
                <img :src="item.headerPicturePath" style="width: 100%;height: 140px">
                <div style="padding: 14px;">
                  <el-link :underline="false" @click="showProjectDetails(item.projectId)"> <h3 style="text-align: center;color: steelblue">{{ item.projectName }}</h3></el-link>
                  <div class="bottom clearfix">
                    <p>
                    <div style="float:left;"><i class="el-icon-aim" title="目标金额"></i> ￥{{ item.money }}</div>
                    <div style="float:right;"><i title="截至日期" class="el-icon-date"></i> {{ item.deployDate }}</div>
                    </p>
                    <br>
                    <el-progress :text-inside="true" :stroke-width="24" :percentage="item.percentage"
                                 status="success"></el-progress>
                    <div>
                      <i class="el-icon-user-solid" title="支持人数"/>{{ item.supporter }}
                      <div style="float: right"><i class="el-icon-star-on" title="收藏项目"/></div>
                    </div>
                  </div>
                </div>
              </el-card>
            </el-col>
          </el-row>
        </el-tab-pane>
      </el-tabs>
    </el-col>
  </div>
</template>

<script>
export default {
  name: "center",
  data() {
    return {
      activeName: '科技',
      portalTypeVOList: [],
    };
  },
  methods: {
    showProjectDetails(id) {
      axios.get(`http://localhost:9000/project/get/project/detail/${id}`).then(response => {
        if (response.data.result === 'SUCCESS') {
          sessionStorage.setItem("projectDetail",JSON.stringify(response.data.data));
          this.$router.push("/detailProject")
        } else {
          console.log(response.data.msg);
        }
      })
    }
  },
  created() {
    axios.get("http://localhost:9000/").then(response => {
      if (response.data.result === 'SUCCESS') {
        this.portalTypeVOList = response.data.data;
      } else {
        console.log(response.data.msg);
      }
    })
  }

}
</script>

<style scoped>

</style>