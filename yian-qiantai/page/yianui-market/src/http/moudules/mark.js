import axios from '../axios'
/* 
 * 字典管理模块
 */

// 保存
export const save = (data) => {
    return axios({
        url: '/mark/save',
        method: 'post',
        data
    })
}
// 删除
export const batchDelete = (data) => {
    return axios({
        url: '/mark/delete',
        method: 'post',
        data
    })
}
// 分页查询
export const findPage = (data) => {
    return axios({
        url: '/mark/findPage',
        method: 'post',
        data
    })
}

export const leadgGout = (data) => {
    return axios({   
        url: '/mark/leadGout',
        method: 'post', 
        data
    })
}

export const leadGin =(data) => {
    return axios({   
        url: '/mark/leadgin',
        method: 'post', 
        data :data,
        headers: {
            'Content-Type': 'multipart/form-data'
          }
    })

}
