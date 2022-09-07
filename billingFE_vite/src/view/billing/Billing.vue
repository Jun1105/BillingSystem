<script setup lang="ts">
  import { onMounted, reactive, ref } from 'vue'
  import { Search, Edit, Delete } from '@element-plus/icons-vue'
  import Table from '@/components/table'
  import { ElMessage, ElMessageBox } from 'element-plus'
  import DialogFormVue from './component/DialogForm.vue'
  import { getAllOrder } from '@/api/order'
  import { userStore } from '@/stores/user'
  const user = userStore()
  const order = reactive({
    typeId: null,
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

  const total = ref(5)
  const tableData = ref([])
  const tableColumn = ref([
    {
      prop: 'index',
      label: '',
      width: '50'
    },
    {
      prop: 'typeName',
      label: '类型'
    },
    {
      prop: 'description',
      label: '描述'
    },
    {
      prop: 'amount',
      label: '金额',
      sort: true
    },
    {
      prop: 'date',
      label: '时间',
      sort: true
    }
  ])

  const getOrderData = async body => {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const res: any = await getAllOrder(body)
    if (res.code === 0) {
      tableData.value = res.data.map((item, index) => {
        return { index: index + 1, ...item }
      })
      total.value = res.data.length
    }
  }

  onMounted(() => {
    getOrderData({ userId: user.$state.userId })
  })
  let isClick = ref(true)

  const disabledDate = (time: Date) => {
    return time.getTime() > Date.now()
  }

  const shortcuts = [
    {
      text: 'Last week',
      value: () => {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
        return [start, end]
      }
    },
    {
      text: 'Last month',
      value: () => {
        const end = new Date()
        const start = new Date()
        start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
        return [start, end]
      }
    }
  ]

  const searchForm = () => {
    getOrderData({
      userId: user.$state.userId,
      ...order
    })
  }

  const handleCurrentChange = async (currentPage, pageSize) => {
    getOrderData({
      userId: user.$state.userId,
      page: currentPage,
      size: pageSize
    })
  }

  const handleSizeChange = (currentPage, size) => {
    getOrderData({
      userId: user.$state.userId,
      page: currentPage,
      size: size
    })
  }

  const handleSortChange = ({ prop, order }) => {
    getOrderData({
      userId: user.$state.userId,
      orderBy: prop,
      sort: order === 'ascending'
    })
  }

  const currentRow = ref()
  const saveRow = ref()
  const handleCurrentRowChange = val => {
    isClick.value = false
    currentRow.value = val
    saveRow.value = val
    ElMessage({
      message: '已选中当前数据,可进行操作',
      type: 'success'
    })
    console.log(val)
  }

  const dialogFormVisible = ref(false)
  const handleCloseDialog = value => {
    dialogFormVisible.value = value
  }

  const handleUpdate = () => {
    dialogFormVisible.value = true
    currentRow.value = { ...saveRow.value }
  }
  const handleAdd = () => {
    dialogFormVisible.value = true
    currentRow.value = {}
  }
  const handleDelete = () => {
    ElMessageBox.confirm('确认要删除吗?', 'Warning', {
      confirmButtonText: 'Confirm',
      cancelButtonText: 'Cancel',
      type: 'warning'
    })
      .then(() => {
        console.log(currentRow.value)
        ElMessage({
          type: 'success',
          message: 'Delete completed'
        })
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: 'Delete canceled'
        })
      })
  }
</script>
<template>
  <el-card>
    <el-form :model="order">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :md="10" :lg="8" :xl="6">
          <el-form-item label="时间：">
            <el-date-picker
              v-model="order.dates"
              type="daterange"
              unlink-panels
              range-separator="To"
              start-placeholder="Start date"
              end-placeholder="End date"
              :shortcuts="shortcuts"
              :disabled-date="disabledDate"
              value-format="YYYY-MM-DD"
            />
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="12" :md="10" :lg="6" :xl="4">
          <el-form-item label="类型：">
            <el-select v-model="order.typeId" placeholder="please select type">
              <el-option
                v-for="item in typeList"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :xs="24" :sm="12" :md="10" :lg="8" :xl="8">
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
        <el-col :xs="24" :sm="12" :md="10" :lg="8" :xl="6">
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
      </el-row>
      <el-row justify="end">
        <el-col :xs="24" :sm="24" :md="12" :lg="10" :xl="6">
          <el-button type="primary" :icon="Search" @click="searchForm">
            搜索
          </el-button>
          <el-button type="success" @click="handleAdd">
            <el-icon><CirclePlus /></el-icon>
            添加
          </el-button>
          <el-button
            type="success"
            :icon="Edit"
            :disabled="isClick"
            @click="handleUpdate"
          >
            修改
          </el-button>
          <el-button
            type="danger"
            :icon="Delete"
            :disabled="isClick"
            @click="handleDelete"
          >
            删除
          </el-button>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
  <el-card :style="{ marginTop: '20px' }">
    <Table
      :table-data="tableData"
      :table-column="tableColumn"
      :total="total"
      :handle-current-change="handleCurrentChange"
      :handle-size-change="handleSizeChange"
      :handle-current-row-change="handleCurrentRowChange"
      :handle-sort-change="handleSortChange"
    />
  </el-card>
  <DialogFormVue
    :dialog-form-visible="dialogFormVisible"
    @close-dialog="handleCloseDialog"
    :current-row="currentRow"
    :type-list="typeList"
  />
</template>
<style lang="scss"></style>
