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
      <el-table-column align="center" label="ID" width="90">
        <template slot-scope="scope">
          {{ scope.row.userId }}
        </template>
      </el-table-column>
      <el-table-column label="Username" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.account }}
        </template>
      </el-table-column>
      <el-table-column label="Realname" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Sex" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.row.sex | sexFilter }}
        </template>
      </el-table-column>
      <el-table-column label="Phone" width="200" align="center">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>
      <el-table-column label="email" align="center">
        <template slot-scope="scope">
          {{ scope.row.email }}
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="Display_time" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time"/>
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="150" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
          {{ scope.row.status | statusDesFilter }}
        </template>
      </el-table-column>

      <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{ $t('table.edit') }}</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">{{ $t('table.delete') }}</el-button>
          <el-button type="primary" size="mini" @click="handleRoleCreate(scope.row)">{{ $t('table.role') }}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="$t(textMap[dialogStatus])" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px"
               style="width: 400px; margin-left:50px;">
        <el-form-item :label="$t('table.username')" prop="account">
          <template v-if="dialogStatus==='update'">
            <el-input v-model="temp.account" disabled="true"/>
          </template>
          <template v-else>
            <el-input v-model="temp.account"/>
          </template>
        </el-form-item>
        <el-form-item :label="$t('table.password')" prop="password">
          <template v-if="dialogStatus==='update'">
            <el-input v-model="temp.password" disabled="true"/>
          </template>
          <template v-else>
            <el-input v-model="temp.password"/>
          </template>
        </el-form-item>
        <el-form-item :label="$t('table.realname')" prop="name">
          <el-input v-model="temp.name"/>
        </el-form-item>
        <el-form-item :label="$t('table.sex')">
          <el-select v-model="temp.sex" class="filter-item" placeholder="Please select">
            <el-option v-for="item in sexOptions" :key="item" :label="item" :value="item | sexFilterUn"/>
          </el-select>
        </el-form-item>
        <el-form-item :label="$t('table.phone')" prop="phone">
          <el-input v-model="temp.phone"/>
        </el-form-item>
        <el-form-item :label="$t('table.email')" prop="email">
          <el-input v-model="temp.email"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">{{ $t('table.confirm') }}
        </el-button>
      </div>
    </el-dialog>

    <el-dialog :title="$t('table.role')" :visible.sync="dialogRoleVisible">
      <el-form>
        <el-form-item label="">
          <el-select v-model="roleTemp.roleId">
            <el-option v-for="item in roleList"
                       :label="item.name"
                       :key="item.name"
                       :value="item.roleId">
            </el-option>
          </el-select>
          <el-button v-waves type="primary" @click="handleRoleAdd">{{ $t('table.add')}}
          </el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="roleTemp.roleList"
        border
        fit
        highlight-current-row>
        <el-table-column label="name" width="110" align="center">
          <template slot-scope="scope">
            {{ scope.row.name }}
          </template>
        </el-table-column>
        <el-table-column label="title" width="110" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.title }}</span>
          </template>
        </el-table-column>
        <el-table-column label="description" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column :label="$t('table.actions')" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button type="danger" size="mini" @click="handleRoleDelete(scope.row)">{{ $t('table.delete') }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>

</template>

<script>
  import { userAdd, userAddRole, userDelete, userDeleteRole, userEdit, userList, userRoleList } from '@/api/user'
  import { roleList } from '@/api/role'

  export default {
    filters: {
      sexFilter(status) {
        const statusMap = {
          0: '',
          1: '男',
          2: '女'
        }
        return statusMap[status]
      },
      sexFilterUn(status) {
        const statusMap = {
          '': 0,
          '男': 1,
          '女': 2
        }
        return statusMap[status]
      },
      statusFilter(status) {
        const statusMap = {
          1: 'success',
          2: 'gray',
          3: 'danger'
        }
        return statusMap[status]
      },
      statusDesFilter(status) {
        const statusMap = {
          1: '正常',
          2: '锁定',
          3: '删除'
        }
        return statusMap[status]
      }
    },
    data() {
      return {
        list: null,
        roleList: null,

        sexOptions: ['男', '女'],
        temp: {
          userId: undefined,
          account: '',
          password: '',
          name: '',
          phone: '',
          sex: 1,
          email: '',
        },
        roleTemp: {
          userId: undefined,
          roleId: null,
          roleList: null,
        },
        dialogRoleVisible: false,
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'table.edit',
          create: 'table.add'
        },
        rules: {
          account: [{ required: true, message: 'account is required', trigger: 'blur' }],
          password: [{ required: true, message: 'password is required', trigger: 'blur' }],
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
        userList().then(response => {
          this.list = response.data
          this.listLoading = false
        })
        roleList().then(response => {
          this.roleList = response.data
        })
      },
      resetTemp() {
        this.temp = {
          userId: undefined,
          username: '',
          password: '',
          realname: '',
          phone: '',
          sex: 1,
          email: ''
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
            userAdd(this.temp).then(response => {
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
            userEdit(this.temp).then(response => {
              for (const v of this.list) {
                if (v.userId === response.data.userId) {
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
        userDelete(row).then(() => {
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
      handleRoleCreate(row) {
        this.roleTemp.userId = row.userId
        this.listLoading = true
        userRoleList(row.userId).then(response => {
          this.roleTemp.roleList = response.data
          this.listLoading = false
          this.dialogFormVisible = false
          this.dialogRoleVisible = true
        })
      },
      handleRoleAdd() {
        if (this.roleTemp.roleId) {
          userAddRole(this.roleTemp.userId, this.roleTemp.roleId).then(response => {
            this.$notify({
              title: '成功',
              message: '添加成功',
              type: 'success',
              duration: 2000
            })
            userRoleList(this.roleTemp.userId).then(response => {
              this.roleTemp.roleList = response.data
            })
          })
        }
      },
      handleRoleDelete(row) {
        userDeleteRole(this.roleTemp.userId, row.roleId).then(() => {
          this.$notify({
            title: '成功',
            message: '删除成功',
            type: 'success',
            duration: 2000
          })
          const index = this.roleTemp.roleList.indexOf(row)
          this.roleTemp.roleList.splice(index, 1)
        })
      }
    }
  }
</script>
