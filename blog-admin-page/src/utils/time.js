
const time = {
  timeStampDate: function(data) {
  // 不传参就生成当前时间的13位时间戳
    if (data == null) {
      return ''
    } else if (data.time == null) {
      return ''
    }

    var timeLength = data.time.toString().length

    var date

    // 为13位时间戳的时候怎么办
    if (timeLength === 13) {
      date = new Date(data.time)// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
    } else if (timeLength === 10) {
      date = new Date(data.time * 1000)// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
    }

    const Y = date.getFullYear() + '-'
    const M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-'
    const D = date.getDate() + ' '
    const h = date.getHours() + ':'
    const m = date.getMinutes() + ':'
    const s = date.getSeconds()

    return Y + M + D + h + m + s
  },
  timeStampDateSpecial: function(data) {
    if (data == null) {
      return ''
    } else if (data.time == null) {
      return ''
    }

    const dateee = new Date(data.time).toJSON()
    return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '')
  }
}

module.exports = time
