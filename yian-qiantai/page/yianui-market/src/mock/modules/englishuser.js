/* 
 * 电子保修卡-个人客户信息系统
 */

// 保存

export function save()
{
  return {
    url: '/englishuser/updatesave',
    type: 'post',
    data: {
      "code": 200,
      "msg": null,
      "data": 1
    }
  }
}



