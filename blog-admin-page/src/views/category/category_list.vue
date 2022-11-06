<template>
  <div id="app">

    <!--搜索操作区-->
    <!-- gutter = 间距(控制每块col间距大小)-->
    <!--    <el-row :gutter="0">-->

    <!--      <el-col :span="24" style="margin-top: 10px">-->

    <!--        <el-card v-show="searchWorkspace == false" shadow="always">-->
    <!--          <i class="el-icon-search"/>-->
    <!--          <span> 搜索</span>-->
    <!--          <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">-->
    <!--            展示-->
    <!--          </el-button>-->
    <!--        </el-card>-->

    <!--        <el-card v-show="searchWorkspace == true" class="box-card" shadow="always">-->
    <!--          <div slot="header" class="clearfix">-->
    <!--            <i class="el-icon-search"/>-->
    <!--            <span> 搜索</span>-->
    <!--            <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">-->
    <!--              收起-->
    <!--            </el-button>-->
    <!--          </div>-->

    <!--          <el-form :inline="true" :model="seachForm" class="demo-form-inline" @submit.native.prevent>-->
    <!--            <el-form-item label="软件名称">-->
    <!--              <el-input v-model="seachForm.name" placeholder="软件名称" @keyup.enter.native="search"/>-->
    <!--            </el-form-item>-->
    <!--            <el-form-item>-->
    <!--              <el-button type="primary" @click="search">查询</el-button>-->
    <!--            </el-form-item>-->
    <!--          </el-form>-->

    <!--        </el-card>-->

    <!--      </el-col>-->

    <!--    </el-row>-->

    <!--表格展示(操作)区-->
    <el-row :gutter="0">

      <el-col :span="24" style="margin-top: 10px">

        <el-card v-show="workingArea == false" shadow="always">
          <i class="el-icon-edit"/>
          操作
          <el-button style="float: right; padding: 3px 0" type="text" @click="workingArea = !workingArea">
            展示
          </el-button>
        </el-card>

        <el-card v-show="workingArea" class="box-card" shadow="always">
          <div slot="header" class="clearfix">
            <i class="el-icon-edit"/>
            <span> 操作</span>
            <span style="color: #409EFF;cursor: pointer;margin-left: 20px" @click="search(true)">刷新数据</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="workingArea = !workingArea">
              收起
            </el-button>
          </div>

          <!--按钮操作区-->
          <el-button type="primary" @click="openForm"><i class="el-icon-plus"/> 添加</el-button>

          <!--表格展示区-->
          <el-table
            :data="tableData"
            :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
            border
            row-key="id"
            default-expand-all
            style="width: 100%;margin-top: 10px">
            <el-table-column
              prop="createAt"
              label="创建时间"
              align="center"
            />
            <el-table-column
              prop="updateAt"
              label="更新时间"
              align="center"
            />
            <el-table-column
              prop="name"
              align="center"
              label="分类名称"
            />
            <el-table-column
              prop="sort"
              align="center"
              label="排序"
            >
              <template slot-scope="scope">
                <el-tag type="success">{{ scope.row.sort }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              fixed="right"
              align="center"
              label="操作"
              width="200">

              <template slot-scope="scope">
                <el-button v-if="scope.row.pid === null || scope.row.pid === ''" type="text" size="small" @click="addRow(scope.row)">新增</el-button>
                <el-button type="text" size="small" @click="updateRow(scope.row)">编辑</el-button>
                <el-button type="text" size="small" style="color: red" @click="removeRow(scope.row)">删除</el-button>
              </template>

            </el-table-column>
          </el-table>

          <!--分页-->
          <!--          <el-pagination-->
          <!--            :page-sizes="tablePageSizes"-->
          <!--            :page-size="tablePageSize"-->
          <!--            :total="tableTotal"-->
          <!--            style="margin-top: 15px"-->
          <!--            background-->
          <!--            layout="total, sizes, prev, pager, next, jumper"-->
          <!--            @size-change="handleSizeChange"-->
          <!--            @current-change="handleCurrentChange"/>-->

        </el-card>

      </el-col>

    </el-row>

  </div>
</template>

<script>
export default {
  data() {
    return {
      // 控制两块区域是否显示
      searchWorkspace: true,
      workingArea: true,

      // 搜索表单
      seachForm: {
        name: ''
      },

      // 表格
      tableTotal: 0,
      tableData: [],
      tablePageNum: 1,
      tablePageSize: 10,
      tablePageSizes: [10, 50, 100, 200]
    }
  },
  mounted() {
    this.getTableData()
  },
  methods: {
    openForm(params) {
      params = params || {}
      params.id = params.id || null
      this.$router.push({
        name: 'CategoryForm',
        params: params
      })
    },
    getTableData() {
      // const data = this.seachForm
      // data.current = this.tablePageNum
      // data.size = this.tablePageSize

      this.$axios.post('/category/queryAll').then((rsp) => {
        const list = rsp.data.list
        const categoryTree = []
        list.forEach((item) => {
          if (item.pid === null || item.pid === '') {
            item.children = []
            categoryTree.push(item)
          }
        })
        categoryTree.forEach((item) => {
          for (let i = 0, arr = list; i < arr.length; i++) {
            if (item.id === arr[i].pid) {
              item.children.push(arr[i])
            }
          }
        })
        this.tableData = categoryTree
      })
    },
    handleSizeChange(val) {
      this.tablePageSize = val
      this.getTableData()
    },
    handleCurrentChange(val) {
      this.tablePageNum = val
      this.getTableData()
    },
    search(isPrompt) {
      if (isPrompt === true) {
        this.$message.success('执行刷新数据成功...')
      } else {
        this.tablePageNum = 1
      }
      this.getTableData()
    },
    addRow(row) {
      this.openForm({ id: null, pid: row.id })
    },
    updateRow(row) {
      const childInfo = {
        isChild: false,
        parentName: ''
      }
      let isBreak = false
      for (let i = 0; i < this.tableData.length; i++) {
        if (isBreak) {
          break
        }
        const parent = this.tableData[i]
        if (parent.children.length === 0) {
          continue
        }
        for (let c = 0; c < parent.children.length; c++) {
          const child = parent.children[c]
          if (child.id === row.id) {
            childInfo.isChild = true
            childInfo.parentName = parent.name
            isBreak = true
            break
          }
        }
      }
      this.openForm({ id: row.id, row: row, childInfo: childInfo })
    },
    removeRow(row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/category/delete', {
          categoryId: row.id
        }).then((rsp) => {
          this.search()
          this.$message(rsp.message)
        })
      }).catch(() => {
      })
    }
  }
}
</script>

<style scoped>
</style>
