<template>
<div>
  <div style="margin-top: 10px">
    <el-row >
      <el-col :offset="2" :span="20" style="border: solid 3px #B3C0D1;background:#B3C0D1;padding: 0 50px 10px 50px">
        <div style="padding-top: 10px;" >
          <h2>
            {{projectDetail.projectName}}
          </h2>
          <div style="float:left;width:70%;">
            {{projectDetail.projectDesc}}
          </div>
          <div style="float:right;">
            <el-button type="success" ><i style="color:#f60" class="el-icon-present"></i> 关注 {{projectDetail.followerCount}}</el-button>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
  <br/>
  <div>
    <el-row >
      <el-col >
        <el-row >
          <el-col :span="13" :offset="2">
            <img alt="140x140" width="100%" :src="projectDetail.headerPicturePath" />
            <img alt="140x140" v-for="(item,index) in projectDetail.detailPicturePathList" width="100%" :src="item" :key="index" />
          </el-col>

          <el-col :span="6" :offset="1">
            <el-card :body-style="{ padding: '0px' }" >
              <div style="padding: 14px;">
                <el-tag type="success" effect="dark">{{projectDetail.statusText}}</el-tag>
                <h3 style="text-align: center">已筹资金：￥{{ projectDetail.supportMoney }}</h3>
                <div style="line-height:32px">
                  <div style="float:left;"> 目标金额 ： {{ projectDetail.money }} </div>
                  <div style="float:right;"> 达成 ： {{ projectDetail.percentage }}% </div>
                  <br>
                  <el-progress :text-inside="true" :stroke-width="24" :percentage="projectDetail.percentage" status="success"></el-progress>
                  <div>
                    剩余 {{ projectDetail.lastDay }} 天
                  </div>
                  <div>
                    已有{{ projectDetail.supporterCount }}人支持该项目
                  </div>
                  <el-button type="warning" style="width: 100%">立即支持</el-button>
                </div>
                <el-divider></el-divider>
                    <el-row >
                      <el-col :offset="1" :span="7">
                        <img alt="140x140" src="../assets/services-box2.jpg" style="width: 80px; height: 80px;">
                      </el-col>
                      <el-col :span="15">
                          <h4 style="margin-top: 0">
                            <b>福建省南安厨卫</b> <el-tag type="success" effect="dark" style="float:right;">已认证</el-tag>
                          </h4>
                          <p style="font-size:12px">
                            酷驰是一家年轻的厨卫科技公司，我们有一支朝气蓬勃，有激情、有想法、敢实践的团队。
                          </p>
                          <p style="font-size:12px">
                            客服电话:0595-86218855
                          </p>
                      </el-col>
                    </el-row>
                  </div>
            </el-card><br/>

            <el-card v-for="(item,index) in  projectDetail.detailReturnVOList" :key="index">
              <div slot="header" style="line-height: 0px">
                <h2 ><span style="float: left">￥{{ item.supportMoney }}</span> <span style="float:right;font-size:12px;">{{ item.signalPurchase === 0 ? "无限额":`有限额，限制 ${item.purchase}位`}}，目前有 {{ item.supproterCount }}位支持者</span></h2>
              </div>
              <div style="line-height:16px;font-size: 13px">
                <p>配送费用：{{item.freight === 0?"包邮": item.freight}}</p>
                <p>预计发放时间：项目筹款成功后的{{ item.returnDate}}天内</p>
                <el-button type="warning" style="width: 100%;" @click="payShow(item.returnId)">支持</el-button>
                <br><br>
                <p>{{ item.content }}</p>
              </div>
            </el-card><br/>
            <el-card>
              <div slot="header" style="line-height: 0px">
                <h2>风险提示</h2>
              </div>
              <div style="line-height:20px;font-size: 15px">
                <p>1.众筹并非商品交易，存在一定风险。支持者根据自己的判断选择、支持众筹项目，与发起人共同实现梦想并获得发起人承诺的回报。<br>
                  2.众筹平台仅提供平台网络空间及技术支持等中介服务，众筹仅存在于发起人和支持者之间，使用众筹平台产生的法律后果由发起人与支持者自行承担。<br>
                  3.本项目必须在2017-06-09之前达到￥10000.00 的目标才算成功，否则已经支持的订单将取消。订单取消或募集失败的，您支持的金额将原支付路径退回。<br>
                  4.请在支持项目后15分钟内付款，否则您的支持请求会被自动关闭。<br>
                  5.众筹成功后由发起人统一进行发货，售后服务由发起人统一提供；如果发生发起人无法发放回报、延迟发放回报、不提供回报后续服务等情况，您需要直接和发起人协商解决。<br>
                  6.如您不同意上述风险提示内容，您有权选择不支持；一旦选择支持，视为您已确认并同意以上提示内容。</p>
              </div>
            </el-card><br/>
            <el-card>
              <div slot="header" style="line-height: 0px">
                <h2>为你推荐</h2>
              </div>
              <div style="line-height:20px;font-size: 15px">
                <img src="../assets/product-3.png" width="100%" height="100%"><br/><br/>
                <img src="../assets/product-4.jpg" width="100%" height="100%">
              </div>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>

</div>

</template>

<script>
export default {
  name: "DetailProject",
  data(){
    return{
      projectDetail:{}
    }
  },
  created() {
    this.projectDetail = JSON.parse(sessionStorage.getItem("projectDetail"));
  },
  methods:{
    payShow(id){
      axios.get(`http://localhost:9000/order/confirm/return/info/${id}`).then(response =>{
        if (response.data.result === 'SUCCESS') {
          sessionStorage.setItem("order",JSON.stringify(response.data.data));
          this.$router.push("/payStep1");
        } else {
          this.showFiledMsg(response.data.msg);
        }
      })
    },
    showFiledMsg(msg){
      this.$message({
        showClose: true,
        message: msg,
        type: 'error'
      });
    }
  }
};
</script>

<style scoped>
</style>
