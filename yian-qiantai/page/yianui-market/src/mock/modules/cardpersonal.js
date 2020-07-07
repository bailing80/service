/* 
 * 电子保修卡-个人客户信息系统
 */

// 保存

export function save()
{
  return {
    url: '/cardpersonal/save',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}



export function findXuliehao()
{
return {
  url: '/cardpersonal/findXuliehao',
  type: 'post',
  data: {
    "code": 200,
    "msg": null,
    "data": {}
  }
}
}

export function findByLable()
{
  return {
    url: '/cardproduct/findByLable',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": {}
    }
  }
}
// 验证码

export function sendMessage(data)
{
  return {
    url: '/cardpersonal/sendMessage',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 删除
export const batchDelete = (data) => {
  return axios({
      url: '/cardpersonal/delete',
      method: 'post',
      data
  })
}
//批量导出
export const batchExport = (data) => {
  return axios({
      url: '/cardpersonal/batchExport',
      method: 'post',
      data
  })
}
// 分页查询
export const findPage = (data) => {
  return axios({
      url: '/cardpersonal/findPage',
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
