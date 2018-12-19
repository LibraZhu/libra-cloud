<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column label="Name" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="Url" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.url }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Code" align="center">
        <template slot-scope="scope">
          {{ scope.row.code }}
        </template>
      </el-table-column>
      <el-table-column label="ModularName" align="center">
        <template slot-scope="scope">
          {{ scope.row.modularName }}
        </template>
      </el-table-column>
      <el-table-column label="RequiredPermission" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.requiredPermission }}
        </template>
      </el-table-column>
      <el-table-column label="RequiredLogin" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.requiredLogin }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import { resourceList } from '@/api/resource'

  export default {
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'gray',
          deleted: 'danger'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        list: null,
        listLoading: true
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        resourceList().then(response => {
          this.list = response.data
          this.listLoading = false
        })
      }
    }
  }
</script>
