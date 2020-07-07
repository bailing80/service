/* 
 * 二维码管理
 */

// 读取二维码文件，并保存
export function save()
{
  let dataQrcode= 
  {
    "code": 200,
    "msg": null,
    "data": {}
  }

    return {
      url: '/qrcode/save',         
      type: 'post',  
      data: {
      "code": 200,
      "msg": null,
      "data":dataQrcode
      }   
  }
}
// 删除
export function batchDelete() {
  return {
    url: 'qrcode/delete',
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
    url: 'qrcode/findPage',
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
   //obj.qrcodetext = '../assets/logo.png' + index
    obj.createtime = 'createtime' + index
    
    content.push(obj)
  }
  return content
}