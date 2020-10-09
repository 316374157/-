<template>
<div>
  <PayStep ref="child"/>
  <div style="text-align: left">
    <el-row>
      <el-col :offset="1" :span="18">
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
          <h2>
            请确认您所选择的回报项信息和购买数量
          </h2>
        </blockquote>
      </el-col>
      <el-col :offset="2" :span="15">
        <el-table
            :data="tableData"
            border
            style="width: 100%">
          <el-table-column
              prop="projectName"
              label="项目名称"
              width="120"
          >
          </el-table-column>
          <el-table-column
              prop="launchName"
              label="发起人"
              width="120">
          </el-table-column>
          <el-table-column
              prop="returnContent"
              label="回报内容"
              width="140">
          </el-table-column>
          <el-table-column
              prop="returnCount"
              label="回报数量"
              width="220">
            <template slot-scope="scope">
              <el-input v-model="scope.row.returnCount"/>
            </template>
          </el-table-column>
          <el-table-column
              prop="supportPrice"
              label="支持单价"
              width="140">
          </el-table-column>
          <el-table-column
              prop="freight"
              label="配送费用"
              width="120">
            <template slot-scope="scope">
              <span>{{scope.row.freight ===0?"包邮": "邮费"+scope.row.freight}}</span>
            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <div>
        <el-col :offset="1">
          <div>
            <blockquote style="border-left: 5px solid #99a9bf;padding: 0 0 0 20px;">
              <p >
                <i class="el-icon-info" style="color:#2a6496;"></i> 提示
              </p>
              <span style="font-size:12px;">1.众筹并非商品交易，存在一定风险。支持者根据自己的判断选择、支持众筹项目，与发起人共同实现梦想并获得发起人承诺的回报。<br>
                                2.众筹平台仅提供平台网络空间及技术支持等中介服务，众筹仅存在于发起人和支持者之间，使用众筹平台产生的法律后果由发起人与支持者自行承担。<br>
                                3.本项目必须在2017-06-04之前达到 ￥1000000.00 的目标才算成功，否则已经支持的订单将取消。订单取消或募集失败的，您支持的金额将原支付路径退回。<br>
                                4.请在支持项目后15分钟内付款，否则您的支持请求会被自动关闭。<br>
                                5.众筹成功后由发起人统一进行发货，售后服务由发起人统一提供；如果发生发起人无法发放回报、延迟发放回报、不提供回报后续服务等情况，您需要直接和发起人协商解决。<br>
                                6.如您不同意上述风险提示内容，您有权选择不支持；一旦选择支持，视为您已确认并同意以上提示内容。</span>
            </blockquote>
          </div>
        </el-col>
      </div>


    </el-row>
  </div>
  <el-button type="warning" @click="next" style="float: right">结算</el-button>
</div>
</template>

<script>
import PayStep from "@/components/PayStep";
export default {
  name: "payStep1",
  components:{
    PayStep
  },
  methods:{
    next(){
      sessionStorage.setItem("order",JSON.stringify(this.tableData[0]))
      this.$refs.child.next();
      this.$router.push("/payStep2")
    },
  },
  data() {
    return {
      tableData:[]
    }
  },
  created() {
    this.tableData=[];
    this.tableData.push(JSON.parse(sessionStorage.getItem("order")));
    this.tableData[0].returnCount = 1;
  }
}
</script>

<style scoped>

</style>