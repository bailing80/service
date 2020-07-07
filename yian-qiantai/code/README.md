### 软件架构
#### 后端架构
##### 开发环境
- IDE : eclipse 4.x
- JDK : JDK1.8.x
- Maven : Maven 3.5.x
- MySQL: MySQL 5.7.x
##### 技术选型
- 核心框架：Spring Boot 2.x
- 服务治理：Spring Cloud Finchley
- 安全框架：Spring Security 5.x
- 视图框架：Spring MVC 5.x
- 持久层框架：MyBatis 3.x
- 数据库连接池：Druid 1.x
- 日志管理：SLF4J、Log4j
##### 项目结构
- kitty-common： 公共代码模块，主要放置一些工具类
- kitty-core： 核心代码模块，主要封装公共业务模块
- kitty-admin： 后台管理模块，包含用户、角色、菜单管理等
- kitty-backup： 系统数据备份备份模块，可选择独立部署
- kitty-monitor： 系统监控服务端，监控Spring Boot服务模块
#### 前端架构
##### 开发环境
- IDE : VS Code 1.27
- NODE: Node 8.9.x
- NPM : NPM 6.4.x
##### 技术选型
- 前端框架：Vue 2.x
- 页面组件：Element 2.x
- 状态管理：Vuex 2.x
- 后台交互：axios 0.18.x
- 图标使用：Font Awesome 4.x
##### 项目结构
yian-ui
- assets： 图标、字体、国际化信息等静态信息
- components： 组件库，对常用组件进行封装
- http： 后台交互模块，统一后台接口请求API
- i18n： 国际化模块，使用Vue i18n进行国际化
- mock： Mock模块，模拟接口调用并返回定制数据
- permission： 权限控制模块，处理权限认证逻辑
- router： 路由管理模块，负责页面各种路由配置
- store： 状态管理模块，提供组件间状态共享
- utils： 工具模块，提供一些通用的工具方法
- views： 页面模块，主要放置各种页面视图组件
#### 前端安装
1. 编译代码
   进入项目根目录，执行 npm install, 下载和安装项目相关依赖包。
2. 启动系统
    执行 npm run dev 命令，启动项目，通过 http://localhost:8090 访问。
3. 项目打包
    执行 npm run build 命令，启动打包，完成之后会生成 dist 目录。