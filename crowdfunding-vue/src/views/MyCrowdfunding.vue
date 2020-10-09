<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="我的众筹">
        <el-tabs type="card">
          <el-tab-pane label="我支持的">
            <el-button-group style="float: left">
              <el-button type="primary" >全部</el-button>
              <el-button type="success" >已支付</el-button>
              <el-button type="warning" >未支付</el-button>
            </el-button-group>
            <el-table
                border
                :data="tableData"
                style="width: 100%">
              <el-table-column
                  label="项目信息"
                  width="345">
                <template slot-scope="scope">
                  <el-card :body-style="{ padding: '0px' }" style="height: 200px">
                    <div style="padding: 14px;">
                      <h3 style="text-align: center;color: steelblue">活性富氢净水直饮机</h3>
                      <h6 style="text-align: center">订单编号:2x002231111</h6>
                      <div class="bottom clearfix">
                        <p>
                        <div style="float:left;"><i class="el-icon-aim" title="目标金额"></i> ￥20,000</div>
                        <div style="float:right;"><i title="截至日期" class="el-icon-date"></i> 剩余 8 天</div>
                        </p>
                        <br>
                        <el-progress :text-inside="true" :stroke-width="24" :percentage="40"
                                     status="success"></el-progress>
                        <div>
                          <i class="el-icon-user-solid" title="支持人数"/>12345
                          <div style="float: right"><i class="el-icon-star-on" title="收藏项目"/></div>
                        </div>

                      </div>
                    </div>
                  </el-card>
                </template>
              </el-table-column>
              <el-table-column
                  label="支持日期"
                  width="180">
                2017-05-23 11:31:22
              </el-table-column>
              <el-table-column
                  label="支持金额（元）"
                  width="180">
                1.00<br>(运费：0.00 )
              </el-table-column>
              <el-table-column
                  label="回报数量"
                  width="100">
                1
              </el-table-column>
              <el-table-column
                  label="交易状态"
                  width="100">
                交易关闭
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">交易详情
                  </el-button>
                  <el-button
                      size="mini"
                      type="danger"
                      @click="handleDelete(scope.$index, scope.row)">删除订单
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="我关注的">
            <el-table
                border
                :data="tableData"
                style="width: 100%">
              <el-table-column
                  label="项目信息"
                  width="345">
                <template slot-scope="scope">
                  <el-card :body-style="{ padding: '0px' }" style="height: 200px">
                    <div style="padding: 14px;">
                      <h3 style="text-align: center;color: steelblue">活性富氢净水直饮机</h3>
                      <h6 style="text-align: center">订单编号:2x002231111</h6>
                      <div class="bottom clearfix">
                        <p>
                        <div style="float:left;"><i class="el-icon-aim" title="目标金额"></i> ￥20,000</div>
                        <div style="float:right;"><i title="截至日期" class="el-icon-date"></i> 剩余 2 天</div>
                        </p>
                        <br>
                        <el-progress :text-inside="true" :stroke-width="24" :percentage="80"
                                     status="success"></el-progress>
                        <div>
                          <i class="el-icon-user-solid" title="支持人数"/>12345
                          <div style="float: right"><i class="el-icon-star-on" title="收藏项目"/></div>
                        </div>
                      </div>
                    </div>
                  </el-card>
                </template>
              </el-table-column>
              <el-table-column
                  label="支持人数"
                  width="180">
                1
              </el-table-column>
              <el-table-column
                  label="关注人数"
                  width="100">
                1
              </el-table-column>
              <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">取消关注
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="我发起的">
            <el-table
                border
                :data="tableData"
                style="width: 100%">
              <el-table-column
                  label="项目信息"
                  width="345">
                <template slot-scope="scope">
                  <el-card :body-style="{ padding: '0px' }" style="height: 200px">
                    <div style="padding: 14px;">
                      <h3 style="text-align: center;color: steelblue">活性富氢净水直饮机</h3>
                      <h6 style="text-align: center">订单编号:2x002231111</h6>
                      <div class="bottom clearfix">
                        <p>
                        <div style="float:left;"><i class="el-icon-aim" title="目标金额"></i> ￥20,000</div>
                        <div style="float:right;"><i title="截至日期" class="el-icon-date"></i> 剩余 2 天</div>
                        </p>
                        <br>
                        <el-progress :text-inside="true" :stroke-width="24" :percentage="80"
                                     status="success"></el-progress>
                        <div>
                          <i class="el-icon-user-solid" title="支持人数"/>12345
                          <div style="float: right"><i class="el-icon-star-on" title="收藏项目"/></div>
                        </div>
                      </div>
                    </div>
                  </el-card>
                </template>
              </el-table-column>
              <el-table-column
                  label="募集金额（元）"
                  width="240">
                1.00<br>(运费：0.00 )
              </el-table-column>
              <el-table-column
                  label="当前状态"
                  width="100">
                草稿
              </el-table-column>
              <el-table-column label="操作" width="200">
                <template slot-scope="scope">
                  <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">项目预览
                  </el-button><br/>
                  <el-button
                      size="mini"
                      @click="handleDelete(scope.$index, scope.row)">修改项目
                  </el-button><br/>
                  <el-button
                      size="mini"
                      @click="handleEdit(scope.$index, scope.row)">删除项目
                  </el-button><br/>
                  <el-button
                      size="mini"
                      @click="handleDelete(scope.$index, scope.row)">项目管理
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane>
            <span slot="label"><el-button type="warning" @click="toCreateCrowd">发起众筹</el-button></span>
          </el-tab-pane>
        </el-tabs>

      </el-tab-pane>
      <el-tab-pane label="众筹资产">
        <div class="text item" style="margin-left: 18%">
          <ve-pie :data="pieDate" width="800px" height="400px" :title="pieDateTitle" :legend="legend"></ve-pie>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "MyCrowdfunding",
  data() {
    return {
      pieDate: {
        columns: ['资金来源', '金额'],
        rows: [
          {'资金来源': '筹集', '金额': 11335},
          {'资金来源': '入股', '金额': 12310},
          {'资金来源': '投资', '金额': 12234},
          {'资金来源': '收益', '金额': 7135},
          {'资金来源': '个人', '金额': 31548},
        ]
      },
      pieDateTitle: {
        text: '众筹资产',
        subtext: '纯属虚构',
        x: 'center'
      },
      legend: {
        orient: 'vertical',
        left: '10%',  //图例距离左的距离
        y: 'center',  //图例上下居中
      },
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄'
        }
      ]
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    toCreateCrowd(){
      this.$router.push("/createCrowd")
    }
  }
}
</script>

<style scoped>

</style>