### 包含功能
1.spring cache     
  spring 提供的缓存功能       
2.spring metrics     
  spring 提供的服务监控功能     
3.xss 处理       
  get 方式xss 过滤器   
  涉及到的文件：   
  filter/CrosXssFilter   
  filter/XssHttpServletRequestWrapper   
  post 方式处理 requestbody 中xss内容    
  涉及到文件：    
  config/MessageConvertersConfig
4.websocket   
  官网地址：https://docs.spring.io/spring-framework/docs/6.0.6/reference/html/web-reactive.html#webflux-websocket     
  涉及文件：    
  websocket/*    
  static/websocket.html   
5.ai 转码：   
  lib 目录下boilerpipe是提供该服务的依赖
6.通过spring 使用vault

