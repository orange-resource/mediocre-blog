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

            <el-form-item label="网站logo(ico)" required>
              <single-upload-image :url.sync="form.logoUrl"/>
            </el-form-item>

            <el-form-item label="网站大logo" required>
              <single-upload-image :url.sync="form.bigLogoUrl"/>
            </el-form-item>

            <el-form-item label="网站标题" required>
              <el-input v-model="form.title" class="common-width"/>
            </el-form-item>

            <el-form-item label="SEO关键词" required>
              <el-input v-model="form.seoKeywords" rows="4" type="textarea" class="common-width"/>
            </el-form-item>

            <el-form-item label="SEO描述" required>
              <el-input
                v-model="form.seoDescription"
                rows="6"
                type="textarea"
                class="common-width"/>
            </el-form-item>

            <el-form-item label="网站底部信息" required>
              <el-input v-model="form.footer" rows="6" type="textarea" class="common-width"/>
            </el-form-item>

            <el-form-item label="页面颜色" required>
              <el-radio v-model="form.pageGrayMode" label="0">正常颜色</el-radio>
              <el-radio v-model="form.pageGrayMode" label="1">灰色</el-radio>
            </el-form-item>

            <el-form-item label="页面通用页头Html" required>
              <el-input v-model="form.commonHeadHtml" rows="6" type="textarea" class="common-width"/>
            </el-form-item>

            <el-form-item label="页面通用页脚Html" required>
              <el-input v-model="form.commonFooterHtml" rows="6" type="textarea" class="common-width"/>
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
        logoUrl: '',
        bigLogoUrl: '',
        title: '',
        seoKeywords: '',
        seoDescription: '',
        footer: '',
        pageGrayMode: '0',
        commonHeadHtml: '',
        commonFooterHtml: ''
      }
    }
  },
  mounted() {
    this.$axios.post('/system/get').then((rsp) => {
      if (rsp.code === 200) {
        if (rsp.data.detail !== null) {
          this.form = rsp.data.detail
          this.form.pageGrayMode = this.form.pageGrayMode.toString()
        }
        this.isCreate = 1
        this.formButtonName = '立即保存'
      }
    })
  },
  methods: {
    // 表单操作
    submitForm(formName) {
      if (this.form.logoUrl === '') {
        this.$message.warning('请上传logo')
        return
      }
      if (this.form.bigLogoUrl === '') {
        this.$message.warning('请上传大logo')
        return
      }
      if (this.form.title === '') {
        this.$message.warning('请填写网站标题')
        return
      }
      if (this.form.seoKeywords === '') {
        this.$message.warning('请填写网站关键词')
        return
      }
      if (this.form.seoDescription === '') {
        this.$message.warning('请填写网站描述')
        return
      }
      if (this.form.footer === '') {
        this.$message.warning('请填写网站底部信息')
        return
      }
      if (this.form.pageGrayMode === '') {
        this.$message.warning('请填写页面颜色')
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
        url: '/system/save',
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
