<template>

  <div id="app">
    <el-row :gutter="0">

      <el-col :span="24">

        <el-card v-show="searchWorkspace == false" shadow="always" style="text-align: center">
          <i class="el-icon-upload"/>
          <span> 操作</span>
          <span style="color: #409EFF;cursor: pointer;margin-left: 20px" @click="openExpress"> 上一页</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
            展示
          </el-button>
        </el-card>

        <el-card v-show="searchWorkspace == true" class="box-card" shadow="always">
          <div slot="header" class="clearfix">
            <i class="el-icon-upload"/>
            <span> 操作</span>
            <span style="color: #409EFF;cursor: pointer;margin-left: 20px" @click="openExpress"> 上一页</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
              收起
            </el-button>
          </div>

          <el-form
            ref="form"
            :model="form"
            :status-icon="true"
            label-width="120px"
            class="demo-ruleForm">

            <el-form-item label="顶级分类选择">
              <el-select
                v-model="form.categoryId"
                placeholder="请选择顶级分类"
                @change="categoryInput">
                <el-option
                  v-for="item in categoryList"
                  :label="item.label"
                  :key="item.value"
                  :value="item.value"/>
              </el-select>
            </el-form-item>

            <el-form-item v-if="childrenCategoryList.length > 0" label="下级分类选择">
              <el-select
                v-model="form.secondaryCategoryId"
                placeholder="请选择下级分类"
                @change="categoryInput">
                <el-option
                  v-for="item in childrenCategoryList"
                  :label="item.label"
                  :key="item.value"
                  :value="item.value"/>
              </el-select>
            </el-form-item>

            <el-form-item label="主图" required>
              <single-upload-image :url.sync="form.mainImgUrl"/>
            </el-form-item>

            <el-form-item label="文章标题" required>
              <el-input v-model="form.title" class="common-width" maxlength="200" show-word-limit/>
            </el-form-item>

            <el-form-item label="文章简单描述" required>
              <el-input
                v-model="form.description"
                :autosize="{ minRows: 4, maxRows: 5}"
                type="textarea"
                class="common-width"
                maxlength="300"
                show-word-limit/>
            </el-form-item>

            <el-form-item label="文章副内容">
              <el-input
                v-model="form.childContentHtml"
                :autosize="{ minRows: 4, maxRows: 5}"
                type="textarea"
                class="common-width"
                show-word-limit/>
            </el-form-item>

            <el-form-item label="文章内容" required>
              <tinymce v-model="form.contentHtml" />
            </el-form-item>

            <el-form-item label="是否显示" required>
              <el-radio-group v-model="form.isShow" size="medium">
                <el-radio :label="1" border >显示</el-radio>
                <el-radio :label="2" border >不显示</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="推荐到首页" required>
              <el-radio-group v-model="form.isRecommend" size="medium">
                <el-radio :label="1" border >推荐</el-radio>
                <el-radio :label="2" border >不推荐</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('form')">{{ formButtonName }}</el-button>
            </el-form-item>

          </el-form>

        </el-card>

      </el-col>

    </el-row>

  </div>

</template>

<script>
export default {
  data() {
    return {
      // 收起放下
      searchWorkspace: true,

      formButtonName: '立即创建',

      emailNotificatio: true,
      registerStatus: true,
      serviceStatus: true,

      categoryList: [],
      childrenCategoryList: [],

      // 表单配置
      form: {
        title: '',
        description: '',
        isShow: 1,
        isRecommend: 2,
        contentHtml: '',
        categoryId: '',
        secondaryCategoryId: '',
        mainImgUrl: ''
      }

    }
  },
  mounted() {
    this.$axios.post('/category/getAll').then((rsp) => {
      const list = rsp.data.list
      const categoryTree = []
      list.forEach((item) => {
        item.label = item.name
        item.value = item.id
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
      this.categoryList = categoryTree
      if (this.$route.params.id != null) {
        this.form = this.$route.params.row
        delete this.form.createAt
        delete this.form.updateAt
        delete this.form.pv
        for (let i = 0; i < this.categoryList.length; i++) {
          if (this.categoryList[i].value === this.form.categoryId) {
            this.childrenCategoryList = []

            if (this.categoryList[i].children.length > 0) {
              for (let p = 0; p < this.categoryList[i].children.length; p++) {
                this.childrenCategoryList.push({
                  label: this.categoryList[i].children[p].name,
                  value: this.categoryList[i].children[p].id
                })
              }
            }
          }
        }
        this.formButtonName = '立即保存'
      }
    })
  },
  methods: {
    // 上一页
    openExpress() {
      this.$router.push({
        name: 'ArticleList'
      })
    },
    categoryInput(categoryId) {
      for (let i = 0; i < this.categoryList.length; i++) {
        if (this.categoryList[i].value === categoryId) {
          this.childrenCategoryList = []
          this.form.secondaryCategoryId = ''

          if (this.categoryList[i].children.length > 0) {
            for (let p = 0; p < this.categoryList[i].children.length; p++) {
              if (p === 0) {
                this.form.secondaryCategoryId = this.categoryList[i].children[p].id
              }
              this.childrenCategoryList.push({
                label: this.categoryList[i].children[p].name,
                value: this.categoryList[i].children[p].id
              })
            }
          }
        }
      }
    },
    // 表单操作
    submitForm(formName) {
      if (this.form.title === '') {
        this.$message.warning('请填写文章标题')
        return
      }
      if (this.form.contentHtml === '') {
        this.$message.warning('请填写文章内容')
        return
      }
      if (this.form.categoryId === '') {
        this.$message.warning('请选择主分类')
        return
      }
      if (this.form.description === '') {
        this.$message.warning('请填写内容简介')
        return
      }
      if (this.form.mainImgUrl === '') {
        this.$message.warning('请上传主图')
        return
      }

      if (this.$route.params.id != null) {
        this.submit(true)
      } else {
        this.submit(false)
      }
    },
    submit(isUpdate) {
      const data = this.form

      this.$axios({
        method: 'post',
        url: '/article/save',
        data: data
      }).then((rsp) => {
        this.$message(rsp.message)
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style>
  .common-width {
    width: 500px;
  }
</style>
