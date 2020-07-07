import axios from '../axios'

/* 
 * 电子保修卡-个人客户信息系统
 */

// 保存

export const sendMessage = (data) => {
    return axios({
      url: '/cardpersonal/sendMessage',
      method: 'post',
      data
    })
}
//找序列号
export const findXuliehao = (data) => {
  return axios({   
      url: '/cardpersonal/findXuliehao',
      method: 'post', 
      data
  })
}

export const save = (data) => {
  return axios({
    url: 'cardpersonal/save',
    method: 'post',
    data
  })
}
export const findByLable = (data) => {
  return axios({
    url: 'cardproduct/findByLable',
    method: 'post',
    data
  })
}
// 查看详情
export const getDetails = (data) => {
  return axios({
      url: '/cardpersonal/getDetails',
      method: 'post',
      data
  })
}
//导出
export const exportExcel = (data) => {
  return axios({   
      url: '/cardpersonal/exportExcel',
      method: 'post', 
      data
  })
}
//导入
export const importExcel =(data) => {
  return axios({   
      url: '/cardpersonal/importExcel',
      method: 'post', 
      data :data,
      headers: {
          'Content-Type': 'multipart/form-data'
        }
  })

}
