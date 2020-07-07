import axios from '../axios'
/* 
 * 二维码信息模块
 */

// 读取二维码文件，并保存
export const save = (data) => {
    return axios({
        url: '/qrcode/save',
        method: 'post',
        data,
        headers: {
            'Content-Type': 'multipart/form-data'
          }
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/qrcode/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/qrcode/findPage',
        method: 'post',
        data
    })
}
