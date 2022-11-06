<template>
  <div id="app">

    <!--搜索操作区-->
    <!-- gutter = 间距(控制每块col间距大小)-->
    <el-row :gutter="0">

      <el-col :span="24" style="margin-top: 10px">

        <el-card v-show="searchWorkspace == false" shadow="always">
          <i class="el-icon-search"/>
          <span> 搜索</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
            展示
          </el-button>
        </el-card>

        <el-card v-show="searchWorkspace == true" class="box-card" shadow="always">
          <div slot="header" class="clearfix">
            <i class="el-icon-search"/>
            <span> 搜索</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
              收起
            </el-button>
          </div>

          <el-form :inline="true" :model="seachForm" class="demo-form-inline" @submit.native.prevent>
            <el-form-item label="文章标题">
              <el-input v-model="seachForm.title" placeholder="文章标题" clearable @keyup.enter.native="search"/>
            </el-form-item>

            <el-form-item label="分类选择">
              <el-select
                v-model="seachForm.categoryId"
                placeholder="请选择分类"
                clearable>
                <el-option
                  v-for="item in categoryList"
                  :label="item.label"
                  :key="item.value"
                  :value="item.value"/>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="search">查询</el-button>
            </el-form-item>
          </el-form>

        </el-card>

      </el-col>

    </el-row>

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
            border
            style="width: 100%;margin-top: 10px">
            <el-table-column type="expand">
              <template slot-scope="props">
                <span>内容简单描述</span>
                <br>
                <div v-html="props.row.description"/>
                <br><br><br>
                <span>文章内容：</span>
                <br>
                <div v-html="props.row.contentHtml"/>
              </template>
            </el-table-column>
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
              prop="masterImageUrl"
              align="center"
              label="主图"
            >
              <template slot-scope="scope">
                <img :src="scope.row.masterImageUrl" class="openImage" height="100px" width="100px" >
              </template>
            </el-table-column>
            <el-table-column
              prop="uri"
              align="center"
              label="URI"
            />
            <el-table-column
              prop="title"
              align="center"
              label="标题"
            />
            <el-table-column
              prop="isShow"
              align="center"
              label="是否显示"
              width="100px"
            >
              <template slot-scope="scope">
                <el-tag v-if="scope.row.isShow === 1" type="success">显示</el-tag>
                <el-tag v-if="scope.row.isShow === 2" type="danger">不显示</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              prop="isRecommend"
              align="center"
              label="推荐到首页"
              width="100px"
            >
              <template slot-scope="scope">
                <el-tag v-if="scope.row.isRecommend === 1" type="success">推荐</el-tag>
                <el-tag v-if="scope.row.isRecommend === 2" type="danger">不推荐</el-tag>
              </template>
            </el-table-column>
            <el-table-column
              fixed="right"
              align="center"
              label="操作"
              width="200">

              <template slot-scope="scope">
                <el-button type="text" size="small" @click="updateRow(scope.row)">编辑</el-button>
                <el-button type="text" size="small" style="color: red" @click="removeRow(scope.row)">删除</el-button>
              </template>

            </el-table-column>
          </el-table>

          <!--分页-->
          <el-pagination
            :page-sizes="tablePageSizes"
            :page-size="tablePageSize"
            :total="tableTotal"
            style="margin-top: 15px"
            background
            layout="total, sizes, prev, pager, next, jumper"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"/>

        </el-card>

      </el-col>

    </el-row>

  </div>
</template>

<script>
import 'viewerjs/dist/viewer.css'
import Viewer from 'viewerjs'
export default {
  data() {
    return {
      // 控制两块区域是否显示
      searchWorkspace: true,
      workingArea: true,

      categoryList: [],

      // 搜索表单
      seachForm: {
        title: '',
        categoryId: ''
      },

      // 表格
      tableTotal: 0,
      tableData: [],
      tablePageNum: 1,
      tablePageSize: 10,
      tablePageSizes: [10]
    }
  },
  mounted() {
    this.$axios.post('/category/queryAll').then((rsp) => {
      rsp.data.list.forEach((item) => {
        item.label = item.name
        item.value = item.id
      })
      this.categoryList = rsp.data.list
    })
    this.getTableData()
  },
  methods: {
    openForm(params) {
      params = params || {}
      params.id = params.id || null
      this.$router.push({
        name: 'ArticleForm',
        params: params
      })
    },
    getTableData() {
      const data = this.seachForm
      data.offset = this.tablePageNum
      data.limit = this.tablePageSize

      this.$axios.post('/article/search', data).then((rsp) => {
        this.tableTotal = rsp.data.total
        this.tableData = rsp.data.list
        this.openImage()
      })
    },
    openImage() {
      this.$nextTick(function() {
        const openImageClass = document.getElementsByClassName('openImage')
        for (let i = 0; i < openImageClass.length; i++) {
          const e = new Viewer(openImageClass[i], {
            inline: false,
            navbar: false,
            toolbar: false,
            zIndex: 999999
          })
          e.full()
        }
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
    updateRow(row) {
      console.log(row)
      this.openForm({ id: row.id, row: row })
    },
    removeRow(row) {
      this.$confirm('此操作将永久删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post('/article/delete', {
          id: row.id
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
  .openImage {
    cursor: pointer;
  }
</style>
