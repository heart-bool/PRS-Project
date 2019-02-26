# prs-project
##介绍
    闲来无事玩玩spring cloud. 项目包括微服务的一些基本组件, 用的都是Spring Cloud官方提供的.
    项目目前处于开发初期, 除了一些基本组件的开发, 没有深入到业务(我也不晓得要做个啥).

##结构说明
    - prs-project: content root 项目根节点, 一些公用的Maven配置, 包括版本号、资源打包等
    +    -- prs-base: 基础组件的存放目录. 微服务的基础组件, 注册中心、配置中心、APIGateway等等
          +  --- admin-server: 服务监控应用
          +  --- auth-server: 鉴权(目前没有实现, Spring Cloud Gateway 暂时没有对Oauth2做支持)及用户登录
          +  --- config-server: 配置中心
          +  --- gateway-server: 网关APIGateway
          +  --- registry-server: 注册中心
    +    -- prs-common: 公用组件目录. 目前添加的有swagger在线文档组件、web应用的全局AOP组件、公共实体包/工具类等
          +  --- prs-aop: web应用的全局AOP组件
          +  --- prs-cache: 缓存组件
          +  --- prs-domain: 公共实体包
          +  --- prs-swagger: swagger在线文档组件
          +  --- prs-tools: 工具类
    +    —— prs-service: 业务WEB应用模块. 这里就是核心业务实现的地方了, 实际项目中微服务拆分后存放的位置. 下面有两个示例项目
          +  --- prs-message: 消息服务, 短信/邮件等
          +  --- prs-user: 用户服务
    +    -- prs-config: 公用配置文件
          +  --- bootstrap-config.yml: Spring Cloud Config 配置中心的公用配置文件
          +  --- bootstrap-datasource.yml: 数据库公用配置文件
          +  --- bootstrap-public.yml: 注册中心和一些全局配置
        
        
##运行方式
###1. clone 代码
        git clone https://github.com/heart-bool/prs-config-repo
        git clone https://github.com/heart-bool/prs-project
        
        修改该标签下的路径为你当前 ../prs-config/resources 的绝对路径
        <project.config.resources.dir>/Users/bool/Project/java/prs/prs-project/prs-config/resources</project.config.resources.dir>
        
###2. 设置环境变量, 包含开发环境和测试环境的环境变量、各微服务的名称host文件的映射等. 配置如下:
        export PROFILES_ACTIVE="dev"
        your ip message eureka-server eureka-server-1 eureka-server-2 admin-server config-server gateway-server user auth

        注意: 
        说明一下为啥要配置各微服务的名称host文件的映射(开发环境). 因为最终会采用Docker部署, 而Docker容器的IP不固定性, 就采用了服务名称的方式
        目前容器部署还没有配置, 后期会研究一下K8S.        
###3. 配置MySQL
        MySQL 本身的配置这里就不说了, 主要说一下目前项目的 MySQL 配置.
        bootstrap-datasource.yml 文件中已经对数据库基本的公共配置进行了提取. 包括mybatis的配置

###4. 配置Redis
        正常的spring-boot-data-redis 的配置
        
###5. 运行
        运行各自的 *Application.java
        registry-server ---> config-server ---> admin-server（可选） ---> 各微服务 ---> gateway-server（默认8080端口）

###注意
        gateway-server 目前没有加token的拦截, 在启动admin-server之后直接启动auth-server也可以
###相关地址
        registry-server:8911
        admin-server:8921
        config-server:8931
        auth-server:9102
        gateway-server:8080
        swagger在线文档地址: gateway-server:8080/swagger-ui.html
        
        
                