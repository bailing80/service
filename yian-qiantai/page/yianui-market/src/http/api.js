/* 
 * 接口统一集成模块
 */
import * as login from './moudules/login'
import * as user from './moudules/user'
import * as dept from './moudules/dept'
import * as role from './moudules/role'
import * as menu from './moudules/menu'
import * as dict from './moudules/dict'
import * as log from './moudules/log'
import * as mark from './moudules/mark'
import * as exdel from './moudules/exdel'
import * as qrcode from './moudules/qrcode'
import * as cardpersonal from './moudules/cardpersonal'
import * as cardhospital from './moudules/cardhospital'
import * as englishuser from './moudules/englishuser'




// 默认全部导出
export default {
    login,
    user,
    dept,
    role,
    menu,
    dict,
    log,
    mark,
    exdel,
    qrcode,
    cardpersonal,
    cardhospital,
    englishuser,
    
}