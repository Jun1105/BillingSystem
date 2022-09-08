<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- @format -->

<script setup lang="ts">
  import * as echarts from 'echarts'
  import { onMounted, ref } from 'vue'
  import { yesterday, lastWeek } from '@/utils/date'
  import { getOrderCount, getTypeCount } from '@/api/order'
  import { userStore } from '@/stores/user'
  const user = userStore()
  type EChartsOption = echarts.EChartsOption

  let sevenEChartsData = ref(null)
  const weekEChartsData = ref(null)

  const getSevenEcharts = async () => {
    const sevenChartDom = document.getElementById('seven')

    let sevenECharts = echarts.init(sevenChartDom)

    sevenEChartsData.value = sevenECharts

    const seven = yesterday(null)
    const one = lastWeek(seven)

    const res: any = await getOrderCount({
      userId: user.$state.userId,
      startDate: one,
      endDate: seven
    })
    let xData = []
    let yData = []
    if (res.code === 0) {
      res.data.forEach(item => {
        xData.push(item.date)
        yData.push(item.totalAmount)
      })
    }

    let option: EChartsOption
    option = {
      tooltip: {
        show: true,
        formatter: '金额: {c0}',
        trigger: 'axis',
        axisPointer: {
          label: {
            backgroundColor: '#6a7985'
          }
        }
      },
      xAxis: {
        type: 'category',
        data: xData,
        axisLabel: {
          rotate: -15
        }
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: yData,
          type: 'line',
          smooth: true
        }
      ]
    }

    sevenECharts.setOption(option)
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

  const getSevenPie = async () => {
    const weekChartDom = document.getElementById('week')
    let weekECharts = echarts.init(weekChartDom)

    weekEChartsData.value = weekECharts

    let option: EChartsOption

    const seven = yesterday(null)
    const one = lastWeek(seven)

    const res: any = await getTypeCount({
      userId: user.$state.userId,
      startDate: one,
      endDate: seven
    })

    let data = []
    if (res.code === 0) {
      data = res.data.map(item => {
        return {
          name: item.typeName,
          value: item.totalAmount
        }
      })
    }

    option = {
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: '40',
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
          data
        }
      ]
    }
    weekEChartsData.value.setOption(option)
    window.addEventListener('resize', () => {
      weekEChartsData.value.resize()
    })
  }

  onMounted(() => {
    getSevenEcharts()
    getSevenPie()
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
