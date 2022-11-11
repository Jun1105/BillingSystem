<!-- eslint-disable vue/require-default-prop -->
<!-- eslint-disable @typescript-eslint/no-explicit-any -->
<script setup lang="ts">
  import { T } from 'unimport/dist/types-43c63a16'
  import { ref } from 'vue'
  interface tableColumnProp {
    prop: string
    label: string
    width?: string
    sort?: boolean
    formatter?: unknown
  }
  interface Props {
    tableData: Array<T>
    tableColumn: Array<tableColumnProp>
    isPagination?: boolean
    total?: number
    handleSortChange?: any
    handleCurrentChange?: any
    handleCurrentRowChange?: any
    handleSizeChange?: any
  }
  const props = withDefaults(defineProps<Props>(), {
    isPagination: () => true
  })

  const currentPage = ref(1)
  const pageSize = ref(10)
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
        :sortable="item.sort"
        :formatter="item.formatter"
      />
    </template>
  </el-table>
  <el-pagination
    v-if="props.isPagination"
    v-model:currentPage="currentPage"
    v-model:page-size="pageSize"
    :page-sizes="[10, 20, 40, 100]"
    layout="total, sizes, prev, pager, next, jumper"
    :total="props.total || 0"
    @size-change="props.handleSizeChange(currentPage, pageSize)"
    @current-change="props.handleCurrentChange(currentPage, pageSize)"
  />
</template>
