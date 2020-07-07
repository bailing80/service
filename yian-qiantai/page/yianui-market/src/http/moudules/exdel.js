import axios from '../axios'
/* 
 * 快递信息模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/exdel/save',
        method: 'post',
        data
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/exdel/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/exdel/findPage',
        method: 'post',
        data
    })
}
//查询快递状态
export const queryExpInfo = (data) => {
    return axios({
        url: '/exdel/queryExpInfo',
        method: 'post',
        data
    })
}
//礼品信息导入
export const leadin = (data) => {
    return axios({   
        url: '/exdel/leadin',
        method: 'post',
        data :data,
        headers: {
            'Content-Type': 'multipart/form-data'
          }
    })
}
//客户信息和快递信息一起导出
export const leadout = (data) => {
    return axios({   
        url: '/exdel/leadout',
        method: 'post', 
        data
    })
}
