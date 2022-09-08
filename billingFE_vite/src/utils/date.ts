const yesterday = date => {
  const d = date ? new Date(date) : new Date()
  return getDate(new Date(d.getTime() - 24 * 60 * 60 * 1000))
}

const lastWeek = date => {
  return getDate(new Date(new Date(date).getTime() - 24 * 60 * 60 * 1000 * 6))
}

function getDate(date) {
  const dd = new Date(date)
  dd.setDate(dd.getDate())
  const y = dd.getFullYear()
  const m =
    dd.getMonth() + 1 < 10 ? '0' + (dd.getMonth() + 1) : dd.getMonth() + 1
  const d = dd.getDate() < 10 ? '0' + dd.getDate() : dd.getDate()
  return y + '-' + m + '-' + d
}

export { getDate, yesterday, lastWeek }
