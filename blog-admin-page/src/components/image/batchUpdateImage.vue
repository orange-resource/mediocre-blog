<template>
  <div class="upload">
    <upload-image-list :list="list">
      <el-upload
        ref="refImageUploadImage"
        :show-file-list="false"
        :http-request="function(img) {return uploadFavorImage(img, list)}"
        :before-upload="function(file) {return beforeAvatarUpload(file, list)}"
        multiple
        action=""
        list-type="picture-card">
        <i class="el-icon-plus"/>
      </el-upload>
    </upload-image-list>
  </div>
</template>

<script type="text/ecmascript-6">
import { uploadFile } from '@/assets/js/upload'

export default {
  name: 'BatchUpdateImage',
  props: {
    // eslint-disable-next-line vue/require-default-prop
    list: {
      type: Array
    },
    uploadNumber: {
      type: Number,
      default: 5
    }
  },
  data() {
    return {
    }
  },
  methods: {
    uploadFavorImage(img, imageUrls) {
      uploadFile(img.file).then(rsp => {
        if (rsp.code === 200) {
          imageUrls.push(rsp.url)
        } else {
          this.$message.error(rsp.message)
        }
      }).catch(() => {
        this.$message.error('图片上传网络错误，请刷新试试')
      })
    },
    beforeAvatarUpload(file, imageUrls) {
      return new Promise((resolve, reject) => {
        if (imageUrls.length >= this.uploadNumber) {
          this.$message.error('上传图片已上传了' + this.uploadNumber + '张')
          reject(new Error('上传图片已上传了' + this.uploadNumber + '张'))
        }

        // const isJPG =
        //   file.type === 'image/jpeg' ||
        //   file.type === 'image/jpg' ||
        //   file.type === 'image/png'
        const isLt2M = file.size / 1024 / 1024 < 2
        // if (!isJPG) {
        //   this.$message.error('上传图片只能是 JPG,PNG 格式!')
        //   reject(new Error('上传图片只能是 JPG,PNG 格式!'))
        // }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!')
          reject(new Error('上传图片大小不能超过 2MB!'))
        }

        resolve()
      })
    }
  }
}
</script>

<style lang="scss" scoped>
  .upload {
    margin-bottom: 30px;
  }
</style>
