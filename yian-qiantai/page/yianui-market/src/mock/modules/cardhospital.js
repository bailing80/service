/* 
 * 电子保修卡-医院客户信息系统
 */

// 保存
export function save()
{
  return {
    url: 'cardhospital/save',
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
    url: 'cardhospital/delete',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 批量导出
export function batchExport() {
  return {
    url: 'cardhospital/batchExport',
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
    url: 'cardhospital/findPage',
    type: 'post',
    data: findPageData
  }
}
// 查看详情
export function getDetails() {
  return {
    url: 'cardhospital/getDetails',
    type: 'post',
    data: {}
  }
}
export function getContent(pageNum, pageSize) {
  let content = []
  for(let i=0; i<pageSize; i++) {
    let obj = {}
    let index = ((pageNum - 1) * pageSize) + i + 1
    obj.id = index
    obj.name = 'name' + index
    obj.telephone = 'telephone' + index
    obj.homephone = 'homephone' + index
    obj.height = 'height' + index
    obj.weight = 'weight' + index    
    obj.createtime = 'createtime' + index
    content.push(obj)
  }
  return content
}

//信息导出
export function exportExcel()
{
  let leaddGout= 
  {
    "code": 200,
    "msg": null,
    "data": {}
  }
    return {
      url: '/cardhospital/exportExcel',         
      type: 'post',  
      data: {
      "code": 200,
      "msg": null,
      "data":leaddGout
      }   
  }
}

//信息导入
export function importExcel()
{
  let leaddGin= 
  {
    "code": 200,
    "msg": null,
    "data": {}
  }

    return {
      url: '/cardhospital/importExcel',         
      type: 'post',  
      data: {
      "code": 200,
      "msg": null,
      "data":leaddGin
      }   
  }
}