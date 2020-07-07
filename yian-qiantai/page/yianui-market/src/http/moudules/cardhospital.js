import axios from '../axios'
/* 
 * 电子保修卡-医院客户信息管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/cardhospital/save',
        method: 'post',
        data
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/cardhospital/delete',
        method: 'post',
        data
    })
}
//批量导出
export const batchExport = (data) => {
    return axios({
        url: '/cardhospital/batchExport',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/cardhospital/findPage',
        method: 'post',
        data
    })
}
// 查看详情
export const getDetails = (data) => {
    return axios({
        url: '/cardhospital/getDetails',
        method: 'post',
        data
    })
}
//导出
export const exportExcel = (data) => {
    return axios({   
        url: '/cardhospital/exportExcel',
        method: 'post', 
        data
    })
}
//导入
export const importExcel =(data) => {
    return axios({   
        url: '/cardhospital/importExcel',
        method: 'post', 
        data :data,
        headers: {
            'Content-Type': 'multipart/form-data'
          }
    })

}
