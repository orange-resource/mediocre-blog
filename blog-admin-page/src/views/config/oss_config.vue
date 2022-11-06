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

            <el-form-item label="前缀域名" required>
              <el-input v-model="form.domainName" class="common-width"/>
            </el-form-item>

            <el-form-item label="endpoint" required>
              <el-input v-model="form.endpoint" class="common-width"/>
            </el-form-item>

            <el-form-item label="keyId" required>
              <el-input v-model="form.keyId" class="common-width"/>
            </el-form-item>

            <el-form-item label="keySecret" required>
              <el-input v-model="form.keySecret" class="common-width"/>
            </el-form-item>

            <el-form-item label="bucketName" required>
              <el-input v-model="form.bucketName" class="common-width"/>
            </el-form-item>

            <el-form-item label="region" required>
              <el-input v-model="form.region" class="common-width"/>
            </el-form-item>

            <el-form-item label="目录路径" required>
              <el-input v-model="form.catalogue" class="common-width"/>
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
        domainName: '',
        endpoint: '',
        keyId: '',
        keySecret: '',
        bucketName: '',
        catalogue: '',
        region: ''
      }
    }
  },
  mounted() {
    this.$axios.post('/oss/get').then((rsp) => {
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
      if (this.form.domainName === '') {
        this.$message.warning('请填写前缀域名')
        return
      }
      if (this.form.endpoint === '') {
        this.$message.warning('请填写endpoint')
        return
      }
      if (this.form.keyId === '') {
        this.$message.warning('请填写keyId')
        return
      }
      if (this.form.keySecret === '') {
        this.$message.warning('请填写keySecret')
        return
      }
      if (this.form.bucketName === '') {
        this.$message.warning('请填写bucketName')
        return
      }
      if (this.form.region === '') {
        this.$message.warning('请填写region')
        return
      }
      if (this.form.catalogue === '') {
        this.$message.warning('请填写目录路径')
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
        url: '/oss/save',
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
