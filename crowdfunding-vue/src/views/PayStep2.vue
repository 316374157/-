<template>
<div>
  <PayStep ref="child"/>
  <div >
    <br/>
      <el-row >
        <el-col :offset="6" :span="10" >
          <el-alert
              title="请在下单后15分钟内付款，否则您的订单会被自动关闭。"
              type="error"
              show-icon
              center>
          </el-alert>
        </el-col>
      </el-row>
      <el-row>
        <el-col :offset="2" :span="18">
          <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
            <h2>
              收货地址
            </h2>
          </blockquote>
        </el-col>
        <el-col :offset="4" :span="15">
          <h2 v-if="addressList.length === 0">无收货地址，请先添加！</h2>
          <el-radio-group v-model="addressRadio" style="line-height: 30px" v-if="addressList.length !== 0">
            <div v-for="(item,index) in addressList" :key="index">
              <el-radio :label="item.id">{{item.receiveName}} {{item.phoneNum}} {{item.address}}</el-radio ><br/>
            </div>
          </el-radio-group>
          <el-collapse>
            <el-collapse-item>
              <template slot="title">
                <el-button type="primary"><i class="el-icon-plus"></i> 新地址</el-button>
              </template>
              <el-col :span="15">
                <div style="border:1px solid #99a9bf;background-color:#eceeef">
                  <div style="margin-top: 30px">
                    <el-form ref="form" :model="form" label-width="120px" >
                      <el-form-item label="收货人" style="width: 500px">
                        <el-input v-model="form.receiveName"></el-input>
                      </el-form-item>
                      <el-form-item label="手机" style="width: 500px">
                        <el-input v-model="form.phoneNum"></el-input>
                      </el-form-item>
                      <el-form-item label="收货地址" style="width: 500px">
                        <el-input type="textarea" v-model="form.address"></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="addAddress">立即创建</el-button>
                      </el-form-item>
                    </el-form>
                  </div>
                </div>
              </el-col>
            </el-collapse-item>
          </el-collapse>
        </el-col>
      </el-row>
    <el-row>
      <el-col :offset="2" :span="18">
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
          <h2>
            发票信息
          </h2>
        </blockquote>
      </el-col>
      <el-col :offset="4" :span="15">
        <el-radio-group v-model="invoiceRadio" style="line-height: 30px">
          <el-radio :label="1">不要发票</el-radio><br/>
          <el-radio :label="2">需要发票</el-radio>
          <el-col :span="21" v-show="invoiceRadio === 2">
              <div style="border:1px solid #99a9bf;background-color:#eceeef;width: 500px">
                <el-form label-width="120px" >
                  <el-form-item label="发票抬头" style="width: 500px;margin-top: 20px">
                    <el-input v-model="title" style="width: 300px"></el-input>
                  </el-form-item>
                </el-form>
              </div>
          </el-col>
        </el-radio-group>

      </el-col>
    </el-row>
    <el-row>
      <el-row>
        <el-col :offset="2" :span="18">
          <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
            <h2>
              项目信息
            </h2>
          </blockquote>
        </el-col>
        <el-col :offset="3" :span="15">
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
      </el-row><br/>

      <el-row>
        <el-col :offset="4" :span="1">
          <span>备注</span>
        </el-col>
        <el-col :offset="2" :span="13">
          <el-input type="textarea" v-model="remeber"></el-input>
        </el-col>
      </el-row>

    </el-row>
    <el-row>
      <el-col :offset="2" :span="18">
        <blockquote style="border-left: 5px solid #f60;color:#f60;padding: 0 0 0 20px;">
          <h2>
            账单
          </h2>
        </blockquote>
      </el-col>
      <el-col :offset="4" :span="15">
          <div style="text-align:right; border:2px solid #ffc287;padding-right: 40px;background: rgba(255,0,3,0.08)">
            <ul style="list-style:none;" >
              <li style="margin-top:10px;">支持金额：<span style="color:red;">￥{{tableData[0].supportPrice}} x {{tableData[0].returnCount}}</span></li>
              <li style="margin-top:10px;">配送费用：<span style="color:red;">￥{{tableData[0].freight}}</span></li>
              <li style="margin-top:10px;">优惠金额：<span style="color:red;">-￥0.00</span></li>
              <li style="margin-top:10px;margin-bottom:10px;"><h2>支付总金额：<span style="color:red;">￥{{tableData[0].supportPrice*tableData[0].returnCount+tableData[0].freight}}</span></h2></li>
              <li style="margin-top:10px;padding:5px;border:1px solid #F00;display:initial;background:#FFF;" v-if="addressList.length === 0">
                <i class="el-icon-info"></i> <strong>您需要先设置配送信息 ,再提交订单</strong>
              </li>
              <li style="margin-top:10px;">
                请在下单后15分钟内付款，否则您的订单会被自动关闭。
              </li>
              <li style="margin-top:10px;">
                <el-button :disabled="(!checked) && (addressList.length>0)" type="warning" @click="pay" ><i class="el-icon-postcard"></i> 立即付款</el-button>
              </li>
              <li style="margin-top:10px;">
                <div class="checkbox">
                  <label>
                    <el-checkbox v-model="checked"> 我已了解风险和规则</el-checkbox>
                  </label>
                </div>
              </li>
            </ul>
          </div>
      </el-col>
    </el-row>
    <div>
      <el-col :offset="2">
        <div style="text-align: left;float: left">
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
  </div>

  
</div>
</template>

<script>
import PayStep from "@/components/PayStep";
export default {
  name: "payStep2",
  components:{
    PayStep
  },
  methods:{
    pay(){
      this.order.addressId = this.addressRadio;
      this.order.invoice = this.invoiceRadio;
      this.order.invoiceTitle = this.title;
      this.order.orderProjectVO = this.tableData[0];
      this.order.orderRemark = this.remeber;
      axios.post(`http://localhost:9000/pay/generate/order`,this.order).then(response =>{
        if (response.data.result === 'SUCCESS') {
          console.log(response.data);
        } else {
          this.showFiledMsg(response.data.msg);
        }
      })
      // this.next()
    },
    next(){
      this.$refs.child.next();
      this.$router.push("/payStep3")
    },
    getAddressList(){
      axios.get(`http://localhost:9000/order/get/address/vo/list`).then(response =>{
        if (response.data.result === 'SUCCESS') {
          console.log(response.data);
          this.addressList = response.data.data;
        } else {
          this.showFiledMsg(response.data.msg);
        }
      })
    },
    addAddress(){
      axios.post(`http://localhost:9000/order/add/address/vo`,this.form).then(response =>{
        if (response.data.result === 'SUCCESS') {
          console.log(response.data);
          this.getAddressList();
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
  },
  data(){
    return{
      addressRadio:1,
      invoiceRadio:1,
      addressList:[],
      form:{
        receiveName:'',
        phoneNum:'',
        address:''
      },
      checked:false,
      title:'',
      remeber:'',
      tableData:[],
      order:{
        invoice:0,
        invoiceTitle:'',
        orderRemark:'',
        addressId:0,
        orderProjectVO:{}
      }
    }
  },
  created() {
    this.getAddressList();
    this.tableData = [];
    this.tableData.push(JSON.parse(sessionStorage.getItem("order")));
  }
}
</script>

<style scoped>

</style>