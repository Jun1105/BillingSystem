<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<script setup lang="ts">
  import { T } from 'unimport/dist/types-43c63a16'
  import { ref } from 'vue'
  interface Props {
    tableData: Array<T>
    tableColumn: Array<T>
    total: number
    handleSortChange?: any
    handleCurrentChange?: any
    handleCurrentRowChange?: any
    handleSizeChange?: any
  }
  const props = defineProps<Props>()

  const currentPage4 = ref(1)
  const pageSize4 = ref(5)
</script>
<template>
  <el-table
    :data="props.tableData"
    highlight-current-row
    stripe
    style="width: 100%"
    max-height="800"
    @sort-change="props.handleSortChange"
    @current-change="props.handleCurrentRowChange"
  >
    <template v-for="(item, index) in props.tableColumn" :key="index">
      <el-table-column
        :prop="item.prop"
        :label="item.label"
        :width="item.width"
        :min-width="item.width"
        :sortable="item.sort || false"
        :formatter="item.formatter"
      />
    </template>
  </el-table>
  <el-pagination
    v-model:currentPage="currentPage4"
    v-model:page-size="pageSize4"
    :page-sizes="[5, 10, 20, 50]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="props.total"
    @size-change="props.handleSizeChange"
    @current-change="props.handleCurrentChange"
  />
</template>
