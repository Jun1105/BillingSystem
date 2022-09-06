import { ElLoading } from 'element-plus'

const defaultOption = {
  lock: true,
  text: '正在加载',
  background: 'rgba(0, 0, 0, 0.7)'
}

const loading = (isLoading, options = defaultOption) => {
  if (isLoading) {
    ElLoading.service({
      ...options
    })
  } else {
    ElLoading.service({
      ...options
    }).close()
  }
}
export default loading
