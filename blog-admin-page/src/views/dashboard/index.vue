<template>
  <div id="app">

    <el-row type="flex" style="padding-top: 10px">
      <el-col :span="2"/>
      <el-col :span="20">
        <el-card shadow="always" style="text-align: center">
          <i class="el-icon-star-on"/>
          软件数量：{{ softCount }}
          <i class="el-icon-star-on"/>
        </el-card>
      </el-col>
      <el-col :span="2"/>
    </el-row>

    <el-row type="flex" style="padding-top: 10px">
      <el-col :span="2"/>
      <el-col :span="20">
        <el-card shadow="always" style="text-align: center">
          <i class="el-icon-star-on"/>
          卡密数量：{{ cardCount }}
          <i class="el-icon-star-on"/>
        </el-card>
      </el-col>
      <el-col :span="2"/>
    </el-row>

    <el-row type="flex" style="padding-top: 10px">
      <el-col :span="2"/>
      <el-col :span="20">
        <el-card shadow="always" style="text-align: center">
          <i class="el-icon-star-on"/>
          用户数量：{{ accountCount }}
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
      activeNames: ['1'],
      softCount: 0,
      cardCount: 0,
      accountCount: 0
    }
  },
  mounted() {
  },
  methods: {
  }
}
</script>
