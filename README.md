# Bus Admin（Finchley.SR1）


#### 简介

> 基于Eureka服务治理的spring cloud快速开发框架。

#### 框架支持

- Eureka服务治理
- Feign服务消费
- Zuul服务网关1
- Hystrix熔断器
- Turbine熔断器聚合监控
- Swagger2接口API
- OAuth2身份认证
- Spring Cloud Config服务配置
- Spring Boot Admin应用监控
- Spring Cloud Sleuth链路跟踪
- Spring Cloud Bus消息总线（未完成）
- UPMS通用权限管理（未完成）


#### 后端目录结构说明
``` lua
bus-admin
├── bus-center -- 服务注册中心
├── bus-common -- 公共模块
├── bus-auth -- 授权服务中心 
├── bus-config -- 配置服务中心
├── bus-gateway -- 网关服务中心
├── bus-monitor -- 监测与管理服务中心
├── bus-modules -- 公共服务模块
    ├── bus-example-service -- DEMO实例 
    └── bus-upms-service -- 通用权限管理
```
