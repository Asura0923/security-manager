# security-manager
实现了用户、角色、组、资源的相关管理功能
- 用户可以有多个角色
    - 角色可以拥有多个资源的访问权限
- 用户可以属于多个组
    - 组可以拥有多个资源的访问权限
- 用户可以拥有多个资源
- 访问权限控制到按钮级别

# 开源框架
- AdminLTE + jQuery + Freemarker
- SpringBoot + Spring Security + MyBatis Plus + Jackson + Hutool Tools

# 数据库
- 默认使用h2内存数据库，用于测试
- 支持mysql 5.x数据库无缝切换

# 环境要求
- JDK 8+
- Maven 3.x+
- MySql 5.x (可以使用h2内存数据库)

# 项目编译
1. 下载项目，然后进入项目目录 `security-manager`
2. 在当前目录打开 `CMD` 执行 `install.bat` 或者直接执行语句 `mvn clean install -U -e`

# 启动配置
1. 默认不需要任何配置，直接启动项目即可，可以自定义启动端口

    ``` java -jar secuity-manager-1.0.0.jar ```
    
    ``` java -jar security-manager-1.0.0.jar --server.port=10000 ```
    
2. 如果使用 mysql 数据库，配置方法如下
    1. 修改application.properties里的datasource部分配置，注释掉 `datasource h2` 的配置，然后开启 `datasource mysql` 的配置
    2. 将 `mysql-schema.sql` 文件内容，在mysql中执行，进行表和数据的初始化；也可以直接更改 `spring.datasource.initialization-mode` 的属性值为 `always` ，但注意第二次启动项目时要改回原值，否则数据库会被清空
    3. 修改 `spring.datasource.url` `spring.datasource.username` `spring.datasource.password` 三处的配置为你自己mysql的配置
    
# 二次开发注意
- SpringBoot 相关配置应放到 `sunyu.demo.security.manager.config` 目录下
- Controller 相关类信息放到 `sunyu.demo.security.manager.controller` 目录下
- MyBatis Mapper 相关信息放到 `sunyu.demo.security.manager.mapper` 目录下
- PoJo 放到 `sunyu.demo.security.manager.pojo` 目录下
- Service和实现类放到 `sunyu.demo.security.manager.service` `sunyu.demo.security.manager.service.impl` 目录下
- SecurityManagerApplication 是项目主启动类
- MyBatis 映射文件 放到 `resources/mapping` 下，后缀名必须 `.xml`
- Web 静态文件必须放到 `resources/static` 下，否则会被权限拦截
- 页面文件放到 `resources/templates` 下，需要使用 `Freemarker` 模板技术

# 项目后门
在 `application.properties` 文件里，有段 `security` 配置，这里明文标识了整个项目的超级管理员登录账户与密码，请妥善保管，因为这个账户是最大权限账户
