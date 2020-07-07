/* 
 * 快递信息系统
 */

// 保存
export function save()
{
  return {
    url: 'exdel/save',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 查询快递状态
export function queryExpInfo()
{
  return {
    url: 'exdel/queryExpInfo',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
// 删除
export function batchDelete() {
  return {
    url: 'exdel/delete',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}
//礼品快递信息导入
export function leadin()
{
  let giftData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  return {
    url: 'exdel/leadin',
    type: 'post',
    data: giftData
  }
}

//客户和快递信息一起导出
export function leadout()
{
  let leadout= 
  {
    "code": 200,
    "msg": null,
    "data": {}
  }
    return {
      url: '/exdel/leadout',         
      type: 'post',  
      data: {
      "code": 200,
      "msg": null,
      "data":leadout
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
    url: 'exdel/findPage',
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
    obj.name = 'name' + index
    obj.telephone = 'telephone' + index
    obj.toLocation = 'toLocation' + index     
    obj.fromLocation = 'fromLocation' + index
    obj.expressName = 'expressName' + index
    obj.expressNumber = 'expressNumber' + index    
    obj.giftSend = 'giftSend' + index
    obj.sparam = 'sparam' + index
    obj.sign = 'sign' + index
    obj.createtime = 'createtime' + index
    
    content.push(obj)
  }
  return content
}