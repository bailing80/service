/* 
 * 客户信息系统
 */

// 保存
export function save()
{
  return {
    url: 'mark/save',
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
    url: 'mark/delete',
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
    url: 'mark/findPage',
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
    obj.tel = 'tel' + index
    obj.hospital = 'hospital' + index
    obj.departmentoffices = 'departmentoffices' + index
    obj.address = 'address' + index    
    obj.createtime = 'createtime' + index
    
   // obj.createBy= 'admin'
    //obj.createTime= '2018-08-14 11:11:11'
    //obj.createBy= 'admin'
   // obj.createTime= '2018-08-14 11:11:11'

    content.push(obj)
  }
  return content
}

//信息导出
export function leadgGout()
{
  let leaddGout= 
  {
    "code": 200,
    "msg": null,
    "data": {}
  }
  //let content= []
  //for(let i=0; i<content.length; i++) {
  //    let parent = content[i]
  //    let obj = {}
  //    obj.id = (i + 1)
  //    obj.name = parent.name
  //    obj.te = parent.tel
  //   content.push(obj)
  //}
  //leaddGout.data = content
    return {
      url: '/mark/leadGout',         
      type: 'post',  
      data: {
      "code": 200,
      "msg": null,
      "data":leaddGout
      }   
  }
}

//信息导出
export function leadGin()
{
  let leaddGin= 
  {
    "code": 200,
    "msg": null,
    "data": {}
  }

    return {
      url: '/mark/leadgin',         
      type: 'post',  
      data: {
      "code": 200,
      "msg": null,
      "data":leaddGin
      }   
  }
}