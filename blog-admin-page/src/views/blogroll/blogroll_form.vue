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
            label-width="100px"
            class="demo-ruleForm">

            <el-form-item label="链接名称" required>
              <el-input v-model="form.name" class="common-width"/>
            </el-form-item>

            <el-form-item label="跳转链接" required>
              <el-input v-model="form.openUrl" class="common-width"/>
            </el-form-item>

            <el-form-item label="排序" required>
              <el-input-number v-model="form.sort" :min="1" :max="10000"/>
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

      // 表单配置
      form: {
        name: '',
        openUrl: '',
        sort: 100
      }

    }
  },
  mounted() {
    if (this.$route.params.id != null) {
      this.form = this.$route.params.row
      delete this.form.createAt
      delete this.form.updateAt
      this.formButtonName = '立即保存'
    }
  },
  methods: {
    // 上一页
    openExpress() {
      this.$router.push({
        name: 'BlogrollList'
      })
    },
    // 表单操作
    submitForm(formName) {
      if (this.form.name === '') {
        this.$message.warning('链接名称不能为空')
        return
      }
      if (this.form.openUrl === '') {
        this.$message.warning('跳转链接不能为空')
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

      let url = ''
      if (isUpdate) {
        url = '/blogroll/update'
      } else {
        url = '/blogroll/create'
      }

      this.$axios({
        method: 'post',
        url: url,
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
