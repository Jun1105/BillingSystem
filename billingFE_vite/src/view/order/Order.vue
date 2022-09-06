<script setup lang="ts">
  import { reactive, ref } from 'vue'
  import { Search } from '@element-plus/icons-vue'
  import Table from '@/components/table'
  const order = reactive({
    type: null,
    description: null,
    amount: 0,
    date: null
  })
  const typeList = ref([
    {
      label: '餐饮',
      value: 1
    }
  ])

  const tableData = ref([
    {
      date: '2016-05-03',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles'
    },
    {
      date: '2016-05-02',
      name: 'Tom',
      address: 'No. 189, Grove St, Los Angeles'
    }
  ])
  const tableColumn = ref([
    {
      prop: 'date',
      label: 'Date'
    },
    {
      prop: 'name',
      label: 'Name'
    },
    {
      prop: 'address',
      label: 'Address'
    }
  ])

  const disabledDate = (time: Date) => {
    return time.getTime() > Date.now()
  }

  const searchForm = () => {
    console.log(order)
  }

  const handleCurrentChange = (val: number) => {
    console.log(`current page: ${val}`)
  }
  const handleSizeChange = (val: number) => {
    console.log(`${val} items per page`)
  }
</script>
<template>
  <el-card>
    <el-form :model="order">
      <el-row :gutter="20">
        <el-col :span="6">
          <el-form-item label="类型：">
            <el-select v-model="order.type" placeholder="please select type">
              <el-option
                v-for="item in typeList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="描述：">
            <el-input
              type="text"
              v-model="order.description"
              placeholder="Such as breakfast"
              maxlength="20"
              show-word-limit
            />
          </el-form-item>
        </el-col>
        <el-col :span="4">
          <el-form-item label="金额：">
            <el-input-number
              v-model="order.amount"
              class="mx-4"
              :min="0"
              :precision="2"
              controls-position="right"
            />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="时间：">
            <el-date-picker
              v-model="order.date"
              type="date"
              placeholder="Pick a day"
              :disabled-date="disabledDate"
            />
          </el-form-item>
        </el-col>
      </el-row>
      <el-row justify="end">
        <el-col :span="4">
          <el-button type="success">
            <el-icon><CirclePlus /></el-icon>
            添加
          </el-button>
          <el-button type="primary" :icon="Search" @click="searchForm">
            搜索
          </el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
  <el-card margin="1">
    <Table
      :table-data="tableData"
      :table-column="tableColumn"
      :total="5"
      :handle-current-change="handleCurrentChange"
      :handle-size-change="handleSizeChange"
    ></Table>
  </el-card>
</template>
<style lang="scss"></style>
