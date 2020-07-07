/* 
 * 系统登录模块
 */

// 登录接口
export function login() {
  const loginData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  return {
    url: 'login',
    type: 'post',
    data: loginData
  }
}

export const srcmarket = (data) => {
  const logoutData = {
    "code": 200,
    "msg": null,
    "data": {
    }
  }
  return {
    url: 'srcmarketjsp',
    type: 'get',
    data: logoutData
  }
}

export const hospital = (data) => {
  const hospitalData = {
    "code": 200,
    "msg": null,
    "data": {}
  }
  return {
    url: 'findhospital',
    type: 'post',
    data: hospitalData
  }
}
//从数据库获取所有销售人员名称
export function getSalesNames()
{
  let salesnames= 
  {
    "code": 200,
    "msg": null,
    "data": {}
  }

    return {
      url: 'getSalesNames',         
      type: 'post',  
      data: {
      "code": 200,
      "msg": null,
      "data":salesnames
      }   
    }
}

