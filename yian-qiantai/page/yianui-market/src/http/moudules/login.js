import axios from '../axios'

/* 
 * 系统
 */
export const login = data => {
    return axios({
        url: 'login',
        method: 'post',
        data
    })
}

export const srcmarket = (data) => {
    return axios({   
        url: 'srcmarketjsp',
        method: 'get', 
        data
    })
}

export const hospital = (data) => {
    return axios({   
        url: 'findhospital',
        method: 'post', 
        data
    })
}
//从数据库获取所有销售人员名称
export const getSalesNames = (data) => {
    return axios({
        url: 'getSalesNames',
        method: 'post',
        data
    })
}