<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- @format -->

<script setup lang="ts">
  import * as echarts from 'echarts'
  import { onMounted, ref, markRaw } from 'vue'
  import { yesterday, sevenBefore, sevenAfter, getWeek } from '@/utils/date'
  import { getOrderCount, getTypeCount, getWeekOrder } from '@/api/order'
  import { userStore } from '@/stores/user'
  const user = userStore()
  type EChartsOption = echarts.EChartsOption

  let sevenEChartsData = ref(null)
  let sevenPieData = ref(null)
  let weekEChartsData = ref(null)

  const getSevenEcharts = async () => {
    const sevenChartDom = document.getElementById('seven')

    let sevenECharts = echarts.init(sevenChartDom)

    sevenEChartsData.value = sevenECharts

    const seven = yesterday(null)
    const one = sevenBefore(seven)

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
        formatter: '{b0}: ￥{c0}',
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
          rotate: -15,
          margin: 15
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
    const sevenPieDom = document.getElementById('type')
    let sevenPieCharts = echarts.init(sevenPieDom)

    sevenPieData.value = sevenPieCharts

    let option: EChartsOption

    const seven = yesterday(null)
    const one = sevenBefore(seven)

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
        trigger: 'item',
        formatter: '总计: ￥{c0}'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: '类型',
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
    sevenPieData.value.setOption(option)
    window.addEventListener('resize', () => {
      sevenPieData.value.resize()
    })
  }

  const getWeekECharts = async () => {
    const weekChartDom = document.getElementById('week')

    let weekECharts = echarts.init(weekChartDom)

    const week = markRaw(weekECharts)

    weekEChartsData.value = weekECharts

    const [startDate, endDate] = getWeek(4)
    const res: any = await getWeekOrder({
      userId: user.$state.userId,
      startDate: startDate,
      endDate: endDate
    })
    let xData = []
    let yData = []
    if (res.code === 0) {
      res.data.forEach(item => {
        xData.push(`${item.date}~${sevenAfter(item.date)}`)
        yData.push(item.totalAmount)
      })
    }

    let option: EChartsOption
    option = {
      tooltip: {
        trigger: 'axis',
        formatter: '总计: ￥{c0}',
        axisPointer: {
          type: 'shadow'
        }
      },
      xAxis: {
        type: 'category',
        data: xData,
        axisLabel: {
          rotate: -15,
          margin: 15
        }
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '总计',
          data: yData,
          type: 'bar'
        }
      ]
    }
    week.setOption(option)

    window.addEventListener('resize', () => {
      week.resize()
    })
  }

  onMounted(async () => {
    await getSevenEcharts()
    await getSevenPie()
    await getWeekECharts()
  })
</script>
<template>
  <el-row class="home" :gutter="20">
    <el-col :xs="24" :sm="24" :md="12" :lg="12">
      <el-card>
        <h3 class="text_center">每日支出</h3>
        <div id="seven" class="echarts"></div>
      </el-card>
    </el-col>
    <el-col :xs="24" :sm="24" :md="12" :lg="12">
      <el-card>
        <h3 class="text_center">七日消费类型总计</h3>
        <div id="type" class="echarts"></div>
      </el-card>
    </el-col>
  </el-row>
  <el-row class="second" :gutter="20">
    <el-col :xs="24" :sm="24" :md="12" :lg="12">
      <el-card>
        <h3 class="text_center">每周消费</h3>
        <div id="week" class="echarts"></div>
      </el-card>
    </el-col>
    <el-col :xs="24" :sm="24" :md="12" :lg="12">
      <el-card>
        <!-- <h3 class="text_center">周账单</h3>
        <div id="week"></div> -->
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
  .home,
  .second {
    margin: 1px !important;
    .echarts {
      height: 40vh;
    }
  }
  .second {
    margin-top: 20px !important;
  }
</style>
