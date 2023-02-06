<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<!-- @format -->

<script setup lang="ts">
  import * as echarts from 'echarts'
  import { onMounted, ref, markRaw } from 'vue'
  import {
    sevenBefore,
    sevenAfter,
    getWeek,
    getCurrentMonth,
    getLastMonth,
    getDate
  } from '@/utils/date'
  import { getOrderCount, getTypeCount, getWeekOrder } from '@/api/order'
  import { userStore } from '@/stores/user'
  import loading from '@/utils/loading'
  const user = userStore()

  let sevenEChartsData = ref(null)
  let sevenPieData = ref(null)
  let weekEChartsData = ref(null)
  let totalAmount = ref(null)

  const monthSelect = ref('近七日')

  let sevenUnRef = null
  let pieUnRef = null
  let weekUnRef = null
  let lineOption = {
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
      data: [],
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
        data: [],
        type: 'line',
        label: {
          show: true,
          position: 'top'
        },
        smooth: true
      }
    ]
  }

  let weekOption = {
    tooltip: {
      trigger: 'axis',
      formatter: '总计: ￥{c0}',
      axisPointer: {
        type: 'shadow'
      }
    },
    xAxis: {
      type: 'category',
      data: [],
      axisLabel: {
        margin: 15
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '总计',
        data: [],
        label: {
          show: true,
          fontSize: 13,
          fontWeight: 'bold',
          position: 'top'
        },
        type: 'bar'
      }
    ]
  }

  let pieOption = {
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
        emphasis: {
          label: {
            show: true,
            fontSize: '40',
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: true,
          normal: {
            length: 15, // 指示线长度
            lineStyle: {
              color: '#595959' // 指示线颜色
            }
          }
        },
        label: {
          show: true,
          position: 'center',
          normal: {
            textStyle: {
              color: '#595959', // 提示文字颜色
              fontSize: 18 // 提示文字大小
            }
          }
        },
        data: []
      }
    ]
  }

  const calculateTotal = data => {
    let total = 0
    if (data.length !== 0) {
      data.forEach(v => {
        total += v.totalAmount
      })
    }
    totalAmount.value = total.toFixed(2)
  }

  const getSevenEcharts = async () => {
    const sevenChartDom = document.getElementById('seven')

    let sevenECharts = echarts.init(sevenChartDom)

    //vue3一个坑：echarts动态渲染的数据不能是响应式的
    // sevenUnRef = markRaw(sevenECharts)

    sevenEChartsData.value = sevenECharts

    const seven = getDate(new Date())
    const one = sevenBefore(seven)
    loading(true)

    const res: any = await getOrderCount({
      userId: user.$state.userId,
      startDate: one,
      endDate: seven
    })
    loading(false)
    let xData = []
    let yData = []
    if (res.code === 0) {
      res.data.forEach(item => {
        xData.push(item.date)
        yData.push(item.totalAmount)
      })
      calculateTotal(res.data)
    }
    lineOption.xAxis.data = xData
    lineOption.series[0].data = yData
    lineOption.xAxis.axisLabel.rotate = xData.length > 15 ? -30 : 0

    sevenECharts.setOption(lineOption)
    window.addEventListener('resize', () => {
      sevenEChartsData.value.resize()
    })
  }

  const getSevenPie = async () => {
    const sevenPieDom = document.getElementById('type')
    let sevenPieCharts = echarts.init(sevenPieDom)

    pieUnRef = sevenPieCharts
    sevenPieData.value = sevenPieCharts

    const seven = getDate(new Date())
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
    // sevenPieData.value.setOption(option)
    pieOption.series[0].data = data
    pieUnRef.setOption(pieOption)
    window.addEventListener('resize', () => {
      pieUnRef.resize()
    })
  }

  const getWeekECharts = async () => {
    const weekChartDom = document.getElementById('week')

    weekUnRef = echarts.init(weekChartDom)

    // weekUnRef = markRaw(weekECharts)

    // weekEChartsData.value = weekECharts

    const [startDate, endDate] = getWeek(5)

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

    weekOption.xAxis.data = xData
    weekOption.series[0].data = yData
    weekUnRef.setOption(weekOption)

    window.addEventListener('resize', () => {
      weekUnRef.resize()
    })
  }

  const handleRadioChange = async value => {
    if (value === '近七日') {
      const seven = getDate(new Date())
      const one = sevenBefore(seven)
      setEchartsOption(one, seven)
    } else if (value === '本月') {
      const [start, end] = getCurrentMonth()
      setEchartsOption(start, end)
    } else if (value === '上月') {
      const [start, end] = getLastMonth()
      setEchartsOption(start, end)
    }
  }

  const setEchartsOption = async (start, end) => {
    const line: any = await getOrderCount({
      userId: user.$state.userId,
      startDate: start,
      endDate: end
    })
    let xData = []
    let yData = []
    if (line.code === 0) {
      line.data.forEach(item => {
        xData.push(item.date)
        yData.push(item.totalAmount)
      })
      calculateTotal(line.data)
      lineOption.xAxis.data = xData
      lineOption.series[0].data = yData
      lineOption.xAxis.axisLabel.rotate = xData.length > 15 ? -30 : 0
      // sevenEChartsData.value.setOption(lineOption)
      sevenUnRef.setOption(lineOption)
    }

    const pie: any = await getTypeCount({
      userId: user.$state.userId,
      startDate: start,
      endDate: end
    })

    let data = []
    if (pie.code === 0) {
      data = pie.data.map(item => {
        return {
          name: item.typeName,
          value: item.totalAmount
        }
      })
      pieOption.series[0].data = data
      // sevenPieData.value.setOption(pieOption)
      pieUnRef.setOption(pieOption)
    }
  }

  onMounted(async () => {
    getSevenEcharts()
    getSevenPie()
    getWeekECharts()
  })
</script>
<template>
  <el-row class="home" :gutter="20">
    <el-col :xs="24" :sm="24" :md="24" :lg="24">
      <el-card>
        <div>
          <h3 class="text_center">每日支出 总计:￥{{ totalAmount }}</h3>
          <el-radio-group
            v-model="monthSelect"
            class="radio-group"
            @change="handleRadioChange"
          >
            <el-radio-button label="近七日">近七日</el-radio-button>
            <el-radio-button label="本月">本月</el-radio-button>
            <el-radio-button label="上月">上月</el-radio-button>
          </el-radio-group>
        </div>
        <div id="seven" class="echarts"></div>
      </el-card>
    </el-col>
  </el-row>
  <el-row class="second" :gutter="20">
    <el-col :xs="24" :sm="24" :md="14" :lg="14">
      <el-card>
        <h3 class="text_center">每周消费</h3>
        <div id="week" class="echarts"></div>
      </el-card>
    </el-col>
    <el-col :xs="24" :sm="24" :md="10" :lg="10">
      <el-card>
        <h3 class="text_center">{{ monthSelect }}消费类型总计</h3>
        <div id="type" class="echarts"></div>
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
