<!-- @format -->

<script setup lang="ts">
  import * as echarts from 'echarts'
  import { onMounted, ref } from 'vue'
  import { yesterday, lastWeek } from '@/utils/date'
  type EChartsOption = echarts.EChartsOption

  let sevenEChartsData = ref(null)
  // const weekChartDom = ref(null)

  // const weekChartDom = document.getElementById('week')
  // let weekECharts = echarts.init(weekChartDom)
  const getSevenEcharts = () => {
    const sevenChartDom = document.getElementById('seven')

    let sevenECharts = echarts.init(sevenChartDom)

    sevenEChartsData.value = sevenECharts

    const seven = yesterday(null)
    const six = yesterday(seven)
    const five = yesterday(six)
    const four = yesterday(five)
    const three = yesterday(four)
    const two = yesterday(three)
    const one = lastWeek(seven)

    let sevenOption: EChartsOption
    sevenOption = {
      xAxis: {
        type: 'category',
        data: [one, two, three, four, five, six, seven],
        axisLabel: {
          rotate: -15
        }
      },
      yAxis: {
        type: 'value'
      },
      tooltip: {
        show: true,
        formatter: '{b0}: {c0}'
      },
      series: [
        {
          data: [820, 932, 901, 934, 1290, 1330, 1320],
          type: 'line',
          smooth: true
        }
      ]
    }

    sevenECharts.setOption(sevenOption)
    window.addEventListener('resize', () => {
      // // 重新设置echarts数据demo
      // let option = {
      //   xAxis: {
      //     type: 'category',
      //     data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      //   },
      //   yAxis: {
      //     type: 'value'
      //   },
      //   series: [
      //     {
      //       data: [50, 30, 50, 20, 50, 40, 80],
      //       type: 'line',
      //       smooth: true
      //     }
      //   ]
      // }
      // sevenEChartsData.value.setOption(option)
      sevenEChartsData.value.resize()
    })
  }

  onMounted(() => {
    getSevenEcharts()
  })
</script>
<template>
  <el-row class="home" :gutter="20">
    <el-col :xs="24" :sm="24" :md="12" :lg="12">
      <el-card>
        <h3 class="text_center">七日账单</h3>
        <div id="seven"></div>
      </el-card>
    </el-col>
    <el-col :xs="24" :sm="24" :md="12" :lg="12">
      <el-card>
        <h3 class="text_center">周账单</h3>
        <div id="week"></div>
      </el-card>
    </el-col>
  </el-row>
</template>
<style lang="scss">
  .text_center {
    text-align: center;
  }
  h3 {
    margin: 0;
  }
  .home {
    margin: 1px !important;
    #seven,
    #week {
      height: 40vh;
    }
  }
</style>
