import request from '@/utils/request'

/**
 * 上传图片接口
 */
export function upload(parameter, isCompress, isAddWatermark) {
  const form = new FormData()
  form.append('file', parameter)
  form.append('compress', isCompress ? 1 : 2)
  form.append('addWatermark', isAddWatermark ? 1 : 2)
  return request({
    url: '/upload/pic',
    method: 'POST',
    data: form
  })
}
