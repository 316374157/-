<template>
  <ve-line :data="chartData" :settings="chartSettings" :colors="colors" :mark-line="markLine"
           :mark-point="markPoint"></ve-line>
</template>

<script>
export default {
  data() {
    return {
      chartData: {
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
      chartSettings: {
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
        '#546570', '#c4ccd3']
    }

  }
}
</script>