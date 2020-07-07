/**
 * 全局常量、方法封装模块
 * 通过原型挂载到Vue属性
 * 通过 this.Global 调用
 */

// 后台管理系统服务器地址
//export const baseUrl = 'http://localhost:9001'
//export const baseUrl = 'http://172.14.10.21:9001'
//export const baseUrl = 'http://39.105.157.251:9001'
export const baseUrl = 'http://124.207.242.89:9001'//生产
//export const baseUrl = 'http://118.190.246.207:9001'


// 系统数据备份还原服务器地址
//export const backupBaseUrl = 'http://localhost:9001'
//export const backupBaseUrl = 'http://39.105.157.251:9001'
//export const backupBaseUrl = 'http://172.14.10.21:9001'
export const backupBaseUrl = 'http://124.207.242.89:9001'//生产
//export const backupBaseUrl = 'http://118.190.246.207:9001'

export default {
    baseUrl,backupBaseUrl
}  