const yesterday = date => {
  const d = date ? new Date(date) : new Date()
  return getDate(new Date(d.getTime() - 24 * 60 * 60 * 1000))
}

const sevenBefore = date => {
  return getDate(new Date(new Date(date).getTime() - 24 * 60 * 60 * 1000 * 6))
}

const sevenAfter = date => {
  return getDate(new Date(new Date(date).getTime() + 24 * 60 * 60 * 1000 * 6))
}

//时间格式化
function getDate(date) {
  const dd = new Date(date)
  dd.setDate(dd.getDate())
  const y = dd.getFullYear()
  const m =
    dd.getMonth() + 1 < 10 ? '0' + (dd.getMonth() + 1) : dd.getMonth() + 1
  const d = dd.getDate() < 10 ? '0' + dd.getDate() : dd.getDate()
  return y + '-' + m + '-' + d
}

/**
 * 获取前n周 周一~周日时间
 * @date 2022-09-08
 * @param {int} n 前n周
 * @returns {Array}
 */
function getOneWeek(n) {
  const now = new Date()
  const day = new Date().getDay()
  const lastSun = now.getTime() - 24 * 60 * 60 * 1000 * day
  const lastMon = lastSun - 24 * 60 * 60 * 1000 * 6
  const sun = lastSun - 24 * 60 * 60 * 1000 * 7 * (n - 1)
  const mon = lastMon - 24 * 60 * 60 * 1000 * 7 * (n - 1)
  return [getDate(mon), getDate(sun)]
}

/**
 * 获取本周日~n周前周一
 * @date 2022-09-08
 * @param {int} n
 * @returns {Array}
 */
function getWeek(n) {
  const now = new Date()
  const day = new Date().getDay()
  const lastSun = now.getTime() + 24 * 60 * 60 * 1000 * (7 - day)
  const lastMon = lastSun - 24 * 60 * 60 * 1000 * 6
  const mon = lastMon - 24 * 60 * 60 * 1000 * 7 * (n - 1)
  return [getDate(mon), getDate(lastSun)]
}

/**
 * 获取月初到今日日期
 * @date 2022-09-09
 * @returns {Array}
 */
function getCurrentMonth() {
  const dd = new Date()
  const y = dd.getFullYear()
  const m =
    dd.getMonth() + 1 < 10 ? '0' + (dd.getMonth() + 1) : dd.getMonth() + 1
  return [`${y}-${m}-01`, getDate(dd)]
}

/**
 * 获取上月日期
 * @date 2022-09-09
 * @returns {Array}
 */
function getLastMonth() {
  const dd = new Date()
  const m = dd.getMonth()
  let y = dd.getFullYear()
  y = m === 0 ? y - 1 : y

  //获取本月天数
  const day = new Date(y, m, 0).getDate()
  const month = m === 0 ? '12' : m < 10 ? '0' + m : m + ''
  const startDate = `${y}-${month}-01`
  const endDate = `${y}-${month}-${day}`
  return [startDate, endDate]
}

export {
  getDate,
  yesterday,
  sevenBefore,
  getOneWeek,
  getWeek,
  sevenAfter,
  getCurrentMonth,
  getLastMonth
}
