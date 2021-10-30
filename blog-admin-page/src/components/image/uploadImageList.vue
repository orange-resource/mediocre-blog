<template>
  <div>
    <ul class="image-list">
      <transition v-for="(item,index) in list" :key="'uploadImageList' + index" name="el-zoom-in-top">
        <li class="image-card arrange">
          <img :src="item">
          <i class="el-icon-close close" @click="deleteImage(item)"/>
        </li>
      </transition>
      <li class="arrange" style="float: left;">
        <slot/>
      </li>
      <div style="clear: both"/>
    </ul>
  </div>
</template>

<script type="text/ecmascript-6">
export default {
  name: 'ImageList',
  props: ['list'],
  methods: {
    deleteImage(url) {
      const imgList = this.list
      for (let i = 0; i < imgList.length; i++) {
        if (url === imgList[i]) {
          imgList.splice(i, 1)
          break
        }
      }
    }
  }
}
</script>

<style lang="scss" scoped>
  .image-list {
    list-style: none;
  }

  .image-card {
      border: 1px solid #c0ccda;
      border-radius: 6px;
      position: relative;
      width: 148px;
      height: 148px;
      float: left;
      img {
        width: 100%;
        height: 100%;
        cursor: pointer;
        border-radius: 6px;
      }
      .close {
        position: absolute;
        right: 3px;
        top: 3px;
        font-size: 20px;
        font-weight: bold;
        cursor: pointer;
        color: #fff;
        background-color: #303133;
        border-radius: 50%;
      }
    }
    .image-card:hover {
      border: 1px dashed #409EFF;
    }

  .arrange {
    margin-left: 5px;
    margin-top: 5px;
    &:nth-child(-n + 1) {
      margin-left: 0;
    }
  }

  /deep/ .el-upload {
    width: 150px;
  }
</style>
