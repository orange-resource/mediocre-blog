<template>
  <div>
    <div class="upload">
      <el-upload
        ref="upload"
        :show-file-list="false"
        :http-request="uploadVideo"
        :before-upload="beforeAvatarUpload"
        class="avatar-uploader"
        action="">
        <div v-if="url">
          <video :src="url" class="avatar" controls="controls">
            您的浏览器不支持视频播放
          </video>
          <div slot="trigger" class="retry-font">重新上传</div>
        </div>
        <div v-else class="avatar-uploader-interior">
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
  methods: {
    beforeAvatarUpload(file) {
      const isJPG =
        file.type === 'video/mp4'
      const isSize = file.size / 1024 / 1024 < 20

      if (!isJPG) {
        this.$message.error('上传视频只能是 mp4 格式')
        return false
      }
      if (!isSize) {
        this.$message.error('上传视频大小不能超过 20MB')
        return false
      }

      return true
    },
    uploadVideo(video) {
      uploadFile(video.file).then(rsp => {
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
    margin-top: 12px;
    margin-bottom: 32px;
    width: 300px;
    height: 250px;
    border: 1px solid #E5E7EC;
    border-radius: 8px;

    position: relative;

    .retry-font {
      position: absolute;
      bottom: 0;
      width: 100%;
      text-align: center;
      font-size: 14px;
      user-select: none;
      cursor: pointer;
    }

    .avatar {
      width: 100%;
      border-radius: 8px;
      height: 210px;
      outline: none;
    }

    .avatar-uploader-interior {
      width: 300px;
      height: 250px;
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
