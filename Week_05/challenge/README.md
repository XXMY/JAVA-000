### 挑战
##### 4.1 将网关的 frontend/backend/filter/router 线程池都改造成 Spring 配置方式
##### 4.2 基于 AOP 改造 Netty 网关，filter 和 router 使用 AOP 方式实现
##### 4.3 基于前述改造，将网关请求前后端分离，中级使用 JMS 传递消息
##### 4.4 尝试使用 ByteBuddy 实现一个简单的基于类的 AOP
##### 4.5 尝试使用 ByteBuddy 与 Instrument 实现一个简单 JavaAgent 实现无侵入下的 AOP