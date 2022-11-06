import request from '@/utils/request'

/**
 * 上传图片接口
 */
export function upload(parameter) {
  const form = new FormData()
  form.append('file', parameter)
  return request({
    url: '/oss/upload',
    method: 'POST',
    data: form
  })
}
