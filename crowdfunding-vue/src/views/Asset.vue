<template>
  <div>
    <el-tabs tab-position="left" style="height: 600px;">
      <el-tab-pane label="我的钱包">
        <div class="text item">
          <ve-line :data="lineData" :settings="lineSettings" :colors="colors" :mark-line="markLine"
                   :mark-point="markPoint" width="800px" height="400px" :title="lineDateTitle"></ve-line>
        </div>
      </el-tab-pane>
      <el-tab-pane label="理财">
        <div class="text item">
          <ve-histogram :data="histogramDate" width="800px" height="400px" :title="histogramDateTitle"></ve-histogram>
        </div>
      </el-tab-pane>
      <el-tab-pane label="比例">
        <div class="text item">
          <ve-pie :data="pieDate" width="800px" height="400px" :title="pieDateTitle" :legend="legend"></ve-pie>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  name: "Asset",
  data() {
    return {
      lineData: {
        columns: ['日期', '基金', '股票'],
        rows: [
          {'日期': '2020-9-1', '基金': 1, '股票': 1},
          {'日期': '2020-9-2', '基金': 1, '股票': -2},
          {'日期': '2020-9-3', '基金': 5, '股票': 2},
          {'日期': '2020-9-4', '基金': 3, '股票': 5},
          {'日期': '2020-9-5', '基金': 2, '股票': 3},
          {'日期': '2020-9-6', '基金': 3, '股票': 2},
          {'日期': '2020-9-7', '基金': 2, '股票': 4},
        ]
      },
      lineDateTitle: {
        text: "七日年化收益率(%)"
      },
      lineSettings: {
        metrics: ['基金', '股票'],
        dimension: ['日期']
      },
      markLine: {
        data: [
          {
            name: '平均线',
            type: 'average'
          }
        ]
      },
      markPoint: {
        data: [
          {
            name: '最大值',
            type: 'max'
          },
          {
            name: '最小值',
            type: 'min'
          }
        ]
      },
      colors: ['#c23531', '#2f4554', '#61a0a8',
        '#d48265', '#91c7ae', '#749f83',
        '#ca8622', '#bda29a', '#6e7074',
        '#546570', '#c4ccd3'],
      histogramDate: {
        columns: ['类型', '直接访问'],
        rows: [
          {'类型': '基金', '直接访问': 10},
          {'类型': '票据', '直接访问': 52},
          {'类型': '定期理财', '直接访问': 200},
          {'类型': '变现贷', '直接访问': 334}
          ]
      },
      histogramDateTitle:{
        text: "收益柱状图"
      },
      pieDate: {
        columns: ['访问来源', '访问用户'],
        rows: [
          {'访问来源': '直接访问', '访问用户': 335},
          {'访问来源': '邮件营销', '访问用户': 310},
          {'访问来源': '联盟广告', '访问用户': 234},
          {'访问来源': '视频广告', '访问用户': 135},
          {'访问来源': '搜索引擎', '访问用户': 1548},
        ]
      },
      pieDateTitle: {
        text: '某站点用户访问来源',
        subtext: '纯属虚构',
        x:'center'
      },
      legend: {
        orient: 'vertical',
        left: '10%',  //图例距离左的距离
        y: 'center',  //图例上下居中
      },
    }
  }
}
</script>

<style scoped>

</style>