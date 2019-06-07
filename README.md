# Parking Guidance Information System（城市停车诱导系统）
## 智能交通诱导系统


# 运行方式一：
    运行 micro-service-provider-static 下的 StaticProviderApplication

# 运行方式二：
    1，mvn clean install -DskipTests
    2，mvn spring-boot:run

# 接口地址：http://localhost:8081/swagger-ui.html


# 发布：scp ROOT.war root@139.198.2.158:/home/tomcat/webapps
# 发布后的接口地址：http://139.198.2.158:8081/swagger-ui.html






# 第一部分：运行 micro-service-provider-static
    1，在项目根下：mvn clean install -DskipTests
    2，cd micro-service-provider/micro-service-provider-static/
    3，mvn spring-boot:run（需要将 application.yml 中的 additional-paths: target/classes/ 注释打开）

# 第二部分：RESTful api 接口
    1，地址：http://localhost:8081/swagger-ui.html



> 项目规划：

| 项目名称                          | 端口   | 描述                    | URL                      |
| ----------------------------  | ------ | ---------------------- | ------------------------ |
| micro-service-discovery       | 8761   | 服务注册中心              | http://discovery:8761   |
| micro-service-provider-static | 8081   | 配置中心                 | http://monitor:8888     |
| micro-service-monitor         | 7777   | 服务监控                 | http://monitor:7777      |
| micro-service-provider        | 8081   | 停车场服务提供者           | http://localhost:8081/1 |



> 运行步骤：
> 1，项目根下运行：mvn clean package -DskipTests
> 2，启动：micro-service-discovery
> 3，启动：micro-service-provider-static

部署：
scp micro-service-provider/micro-service-provider-static/target/ROOT.war root@47.100.219.142:/home/pgis/tomcat/webapps


> 环境准备：


| 工具   | 版本或描述                  |
| ----- | --------------------------|
| JDK   | 1.9                       |
| IDE   | IntelliJ IDEA Community   |
| Maven | Apache Maven 3.5.0        |
| Docker| Machine、Compose          |


> 本机配置：

| hosts   |                         |
| -----   | ------------------------|
| 127.0.0.1| config discovery monitor gateway park menu user feign ribbon       |




SpringCloud 微服务架构

技术选型：
    开发语言：Java 8
    开发环境：IntelliJ IDEA Community（推荐）
    热启动：devtools + LiveReload
    基本技术：Spring Boot / Spring Cloud
    ORM：JPA
    简化代码：Lombok
    视图层技术：FreeMarker
    服务端缓存：EhCache（可选）
    日志框架：Logback + ELK
    日志预警：Logstash

微服务方面：
    服务注册和服务发现：Eureka
    服务健康检查：Eureka
    配置管理：Eureka、Archaius
    集群容错：Hystrix
    计数监控：Codahale-metrics、Java-statsd-client、Hystrix-dashboard、Turbine、Statsd、Graphite、Grafana
    服务路由：Load Balancer、Ribbon
    服务通信：Retrofit、AsyncHttpClient（不选择okhttp，是因为okhttp性能比较差）
    Restful接口和文档输出：Swagger2 + Jsonql
    消息队列：RabbitMQ
    分布式锁：Redis + Eureka
    本地缓存：Guava Cache
    链路跟踪：Zipkin、Brave
    安全鉴权：Auth2、OpenId
    自动化构建与部署：GitHub + Jenkins + Docker + k8s
    数据库、集群、读写分离：PostgreSQL 9.6 + PGPool-II + HA
    缓存集群：Redis 3.0+
    日志存储、历史数据：MongoDB




# 第二部分：前端

## 运行步骤：

``` bash
npm install --registry=https://registry.npm.taobao.org
npm run dev
npm run build
```



菜单 png 图标的颜色值：#20A0FF，尺寸：18 x 18


