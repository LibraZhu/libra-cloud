<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button v-waves class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">{{
        $t('table.add')}}
      </el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column label="Name" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column label="Title" width="200" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.title }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Description" align="center">
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{ $t('table.edit') }}</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">{{ $t('table.delete') }}</el-button>
          <el-button type="primary" size="mini" @click="handleResourceCreate(scope.row)">{{ $t('table.resource') }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :title="$t(textMap[dialogStatus])" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="100px"
               style="width: 400px; margin-left:50px;">
        <el-form-item label="Name" prop="name">
          <template v-if="dialogStatus==='update'">
            <el-input v-model="temp.name" disabled="true"/>
          </template>
          <template v-else>
            <el-input v-model="temp.name"/>
          </template>
        </el-form-item>
        <el-form-item label="Title" prop="title">
          <el-input v-model="temp.title"/>
        </el-form-item>
        <el-form-item label="Description" prop="phone">
          <el-input v-model="temp.description"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">{{ $t('table.confirm') }}
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :title="$t('table.resource')" :visible.sync="dialogResourceVisible">
      <el-checkbox v-model="checkAll" @change="handleCheckAllChange">{{ $t('table.checkAll') }}</el-checkbox>
      <div style="margin: 15px 0;"></div>
      <el-checkbox-group v-model="tempResource.resourceCodeList" @change="handleCheckedResource">
        <el-checkbox v-for="item in resourceList" :label="item.code" :key="item.url">{{
          createResourceTitle(item.name,item.url) }}
        </el-checkbox>
      </el-checkbox-group>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogResourceVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button type="primary" @click="modifyRoleResourceList()">{{ $t('table.confirm') }}
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { roleAdd, roleDelete, roleList, roleResourceList, roleResourceModify, roleUpdate } from '@/api/role'
  import { resourceList } from '@/api/resource'

  export default {
    data() {
      return {
        list: null,
        resourceList: null,
        resourceAllCodeList: [],
        checkAll: false,
        temp: {
          roleId: undefined,
          name: '',
          title: '',
          description: '',
        },
        tempResource: {
          roleId: undefined,
          resourceCodeList: [],
          resourceList: null
        },
        dialogResourceVisible: false,
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'table.edit',
          create: 'table.add'
        },
        rules: {
          name: [{ required: true, message: 'name is required', trigger: 'blur' }]
        },
        listLoading: true
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        roleList().then(response => {
          this.list = response.data
          this.listLoading = false
        })
        resourceList().then(response => {
          this.resourceList = response.data
          this.resourceList.forEach(item => {
            this.resourceAllCodeList.push(item.code)
          })
        })
      },
      resetTemp() {
        this.temp = {
          roleId: undefined,
          name: '',
          title: '',
          description: '',
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            roleAdd(this.temp).then(response => {
              this.list.push(response.data)
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleUpdate(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            roleUpdate(this.temp).then(response => {
              for (const v of this.list) {
                if (v.roleId === response.data.roleId) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, response.data)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(row) {
        roleDelete(row).then(() => {
          this.dialogFormVisible = false
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
          const index = this.list.indexOf(row)
          this.list.splice(index, 1)
        })
      },
      handleCheckAllChange(val) {
        this.tempResource.resourceCodeList = val ? this.resourceAllCodeList : [];
      },
      handleCheckedResource(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.resourceAllCodeList.length;
      },
      createResourceTitle(name, url) {
        return name + "(" + url + ")"
      },
      handleResourceCreate(row) {
        this.tempResource.roleId = row.roleId
        this.listLoading = true
        roleResourceList(row.roleId).then(response => {
          this.tempResource.resourceList = response.data
          this.listLoading = false
          this.dialogFormVisible = false
          this.dialogResourceVisible = true
          this.tempResource.resourceCodeList = []
          this.checkAll = false
          response.data.forEach(item => {
            this.tempResource.resourceCodeList.push(item.code)
          })
          this.checkAll = this.tempResource.resourceCodeList.length === this.resourceAllCodeList.length;
          console.log.call(console, "----" + this.tempResource.resourceCodeList.length)
        })
      },
      modifyRoleResourceList() {
        roleResourceModify(this.tempResource.roleId, this.tempResource.resourceCodeList).then(response => {
          this.dialogResourceVisible = false
          this.$notify({
            title: '成功',
            message: '修改成功',
            type: 'success',
            duration: 2000
          })
        })
      }
    }
  }
</script>
