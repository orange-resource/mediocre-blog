<template>
  <div>
    <div class="upload">
      <el-upload
        ref="upload"
        :show-file-list="false"
        :http-request="uploadImg"
        :before-upload="beforeAvatarUpload"
        class="avatar-uploader"
        action="">
        <img v-if="url" :src="url" alt="图片跑丢了" class="avatar">
        <div v-else class="avatar-uploader-interior">
          <svg-icon class="avatar-uploader-icon" icon-class="uploadImage"/>
          <span class="avatar-uploader-font">点击上传</span>
        </div>
      </el-upload>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
import { uploadFile } from '@/assets/js/upload'

export default {
  name: 'UploadImage',
  props: {
    url: {
      required: true,
      type: String
    }
  },
  data() {
    return {
    }
  },
  methods: {
    beforeAvatarUpload(file) {
      // const isJPG =
      //   file.type === 'image/jpeg' ||
      //   file.type === 'image/jpg' ||
      //   file.type === 'image/png'

      const isSize = file.size / 1024 / 1024 < 2

      // if (!isJPG) {
      //   this.$message.error('上传图片只能是 JPG,PNG 格式')
      //   return false
      // }
      if (!isSize) {
        this.$message.error('上传图片大小不能超过 2MB')
        return false
      }

      return true
    },
    uploadImg(img) {
      uploadFile(img.file).then(rsp => {
        if (rsp.code === 200) {
          this.$emit('update:url', rsp.url)
          this.$emit('onUrl', rsp.url)
        } else {
          this.$message.error(rsp.message)
        }
      }).catch(() => {
        this.$message.error('图片上传网络错误，请刷新试试')
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .upload {
    /*margin-top: 12px*/
    /*margin-bottom: 32px*/
    width: 120px;
    height: 120px;
    border: 1px solid #E5E7EC;
    border-radius: 8px;
    margin-bottom: 30px;

    .avatar {
      width: 118px;
      height: 118px;
      display: block;
      border-radius: 8px;
    }

    .avatar-uploader-interior {
      width: 120px;
      height: 120px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      .avatar-uploader-icon {
        width: 30px;
        height: 30px;
      }

      .avatar-uploader-font {
        margin-top: 6px;
        font-size: 14px;
        font-family: SourceHanSansSC-Normal;
        font-weight: 400;
        color: rgba(149,149,149,1);
      }
    }
  }
</style>
