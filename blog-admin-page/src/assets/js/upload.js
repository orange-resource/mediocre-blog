import { upload } from '../../api/upload/upload'

/**
 * 后台请求返回转成前端自己的格式码 通用性,维护性考虑
 */
export function uploadFile (file, isCompress, isAddWatermark) {
  return new Promise((resolve, reject) => {
    upload(file, isCompress, isAddWatermark).then((rsp) => {
      if (rsp.code !== undefined) {
        if (rsp.code === 1001) {
          resolve({
            code: 200,
            url: rsp.data.url,
            message: rsp.message,
            data: rsp.data
          })
        } else {
          resolve({
            code: parseInt(rsp.code),
            message: rsp.message
          })
        }
      }
      resolve({
        code: -1,
        message: '图片上传网络错误，请刷新试试'
      })
    }).catch((error) => {
      reject(error)
    })
  })
}

// 获取上传图片大小
export function getImgSize (file) {
  return new Promise((resolve, reject) => {
    var reader = new FileReader()
    reader.onload = (theFile) => {
      var image = new Image()
      image.onload = () => {
        resolve(image)
      }
      image.src = theFile.target.result
    }
    reader.readAsDataURL(file)
  })
}
