<template>
  <div id="app">

    <el-row type="flex" style="padding-top: 10px">
      <el-col :span="2"/>
      <el-col :span="20">
        <el-card shadow="always" style="text-align: center">
          <i class="el-icon-star-on"/>
          当前全部文章数量：{{ articleCount }}
          <i class="el-icon-star-on"/>
        </el-card>
      </el-col>
      <el-col :span="2"/>
    </el-row>

  </div>
</template>

<script>
export default {
  data() {
    return {
      articleCount: 0
    }
  },
  mounted() {
    this.$axios({
      method: 'post',
      url: '/article/queryCount'
    }).then((rsp) => {
      this.articleCount = rsp.data.count
    })
  },
  methods: {
  }
}
</script>
