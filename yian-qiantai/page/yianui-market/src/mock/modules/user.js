/* 
 * 用户管理模块
 */

// 保存
export function save() {
  return {
    url: 'user/save',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 批量删除
export function batchDelete() {
  return {
    url: 'user/delete',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 分页查询
export function findPage(params) {
  let findPageData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  let pageNum = 1
  let pageSize = 8
  if(params !== null) {
    // pageNum = params.pageNum
  }
  if(params !== null) {
    // pageSize = params.pageSize
  }
  let content = this.getContent(pageNum, pageSize)
  findPageData.data.pageNum = pageNum
  findPageData.data.pageSize = pageSize
  findPageData.data.totalSize = 50
  findPageData.data.content = content
  return {
    url: 'user/findPage',
    type: 'post',
    data: findPageData
  }
}
export function getContent(pageNum, pageSize) {
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.name = 'yian' + index
    //obj.password = '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d'
    //obj.salt = 'YzcmCZNvbXocrsz9dm8e'
    //obj.email = 'yian' + index +'@qq.com'
    //obj.mobile = '18688982323'
    obj.status = 1
    obj.deptId = 12
    obj.deptName = '技术部'
    obj.status = 1
    if(i % 2 === 0) {
      obj.deptId = 13
      obj.deptName = '市场部'
    }
    obj.createBy= 'admin'
    obj.createTime= '2018-08-14 11:11:11'
    obj.createBy= 'admin'
    obj.createTime= '2018-09-14 12:12:12'
    content.push(obj)
  }
  return content
}
// 查找用户的菜单权限标识集合
export function findPermissions() {
  let permsData = {
    "code": 200,
    "msg": null,
    "data": [
      null,
      "sys:user:view",
      "sys:menu:delete",
      "sys:dept:edit",
      "sys:dict:edit",
      "sys:dict:delete",
      "sys:menu:add",
      "sys:user:add",
      "sys:log:view",
      "sys:dept:delete",
      "sys:role:edit",
      "sys:role:view",
      "sys:dict:view",
      "sys:user:edit",
      "sys:user:delete",
      "sys:dept:view",
      "sys:dept:add",
      "sys:role:delete",
      "sys:menu:view",
      "sys:menu:edit",
      "sys:dict:add",
      "sys:role:add",
      "sys:mark:view",
      "sys:mark:add",
      "sys:mark:edit",
      "sys:mark:delete",
      "sys:mark:leadout",
      "sys:mark:leadin",
      "sys:mark:getSalesNames",
      "sys:mark:getHospitalNames",
      "sys:mark:nameDuplicateCheck",
      "sys:mark:telDuplicateCheck",
      "sys:exdel:view",
      "sys:exdel:add",
      "sys:exdel:leadin",
      "sys:exdel:edit",
      "sys:exdel:delete",
      "sys:exdel:leadout",
      "sys:exdel:query",
      "sys:exdel:export",
      "sys:qrcode:view",
      "sys:qrcode:add",
      "sys:qrcode:delete",
      "sys:qrcode:edit",
      "sys:hospital:view",
      "sys:hospital:add",
      "sys:hospital:delete",
      "sys:hospital:edit",
      "sys:hospital:importHospitalInfo",
      "sys:hospital:exportHospitalInfo",
      "sys:sales:view",
      "sys:sales:add",
      "sys:sales:delete",
      "sys:sales:edit",
      "sys:sales:importSalesInfo",
      "sys:sales:exportSalesInfo",
      "sys:cardpersonal:add",
      "sys:cardpersonal:view",
      "sys:cardpersonal:edit",
      "sys:cardpersonal:delete",
      "sys:cardpersonal:exportExcel",
      "sys:cardpersonal:importExcel",
      "sys:cardpersonal:getDetails",
      "sys:cardhospital:add",
      "sys:cardhospital:view",
      "sys:cardhospital:edit",
      "sys:cardhospital:delete",
      "sys:cardhospital:exportExcel",
      "sys:cardhospital:importExcel",
      "sys:cardhospital:getDetails"
    ]
  }
  return {
    url: 'user/findPermissions',
    type: 'get',
    data: permsData
  }
}