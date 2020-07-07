import axios from '../axios'

/* 
 * 系统
 */
export const save = data => {
    return axios({
        url: '/englishuser/save',
        method: 'post',
        data
    })
}

export const updatesave = data => {
    return axios({
        url: '/englishuser/updatesave',
        method: 'post',
        data
    })
}

export const remarksave = data => {
    return axios({
        url: '/englishuser/remarksave',
        method: 'post',
        data
    })
}