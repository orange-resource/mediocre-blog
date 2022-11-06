<template>

  <div id="app">
    <el-row :gutter="0">

      <el-col :span="24">

        <el-card v-show="searchWorkspace == false" shadow="always" style="text-align: center">
          <i class="el-icon-upload"/>
          <span> 操作</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
            展示
          </el-button>
        </el-card>

        <el-card v-show="searchWorkspace == true" class="box-card" shadow="always">
          <div slot="header" class="clearfix">
            <i class="el-icon-upload"/>
            <span> 操作</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
              收起
            </el-button>
          </div>

          <el-form
            ref="form"
            :model="form"
            :status-icon="true"
            label-width="108px"
            class="demo-ruleForm">

            <el-form-item label="频道名称" required>
              <el-input v-model="form.title" class="common-width"/>
            </el-form-item>

            <el-form-item label="link(域名)" required>
              <el-input v-model="form.link" class="common-width"/>
            </el-form-item>

            <el-form-item label="作者" required>
              <el-input v-model="form.author" class="common-width"/>
            </el-form-item>

            <el-form-item label="频道的描述" required>
              <el-input v-model="form.description" rows="6" type="textarea" class="common-width"/>
            </el-form-item>

            <el-form-item label="language">
              <el-input v-model="form.language" class="common-width"/>
            </el-form-item>

            <el-form-item label="管理员联系邮箱">
              <el-input v-model="form.webMaster" class="common-width"/>
            </el-form-item>

            <el-form-item label="文章编辑人员联系邮箱">
              <el-input v-model="form.managingEditor" class="common-width"/>
            </el-form-item>

            <el-form-item label="copyright">
              <el-input v-model="form.copyright" class="common-width"/>
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

      isCreate: 0,

      // 表单配置
      form: {
        title: '',
        link: '',
        description: '',
        language: '',
        author: '',
        copyright: '',
        webMaster: '',
        managingEditor: ''
      }
    }
  },
  mounted() {
    this.$axios.post('/rss/config/get').then((rsp) => {
      if (rsp.code === 200) {
        if (rsp.data.detail !== null) {
          this.form = rsp.data.detail
        }
        this.isCreate = 1
        this.formButtonName = '立即保存'
      }
    })
  },
  methods: {
    // 表单操作
    submitForm(formName) {
      if (this.form.title === '') {
        this.$message.warning('请填写频道名称')
        return
      }
      if (this.form.link === '') {
        this.$message.warning('请填写web域名')
        return
      }
      if (this.form.description === '') {
        this.$message.warning('请填写频道的描述')
        return
      }
      if (this.form.author === '') {
        this.$message.warning('请填写作者')
        return
      }

      if (this.isCreate === 1) {
        this.submit(true)
      } else {
        this.submit(false)
      }
    },
    submit(isUpdate) {
      const data = this.form
      this.$axios({
        method: 'post',
        url: '/rss/config/save',
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
