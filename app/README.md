Web层:  
承担了三个职责：  
开放接口层的职责：可直接封装 Service 方法暴露成 RPC 接口；通过 Web 封装成 http 接口；进行网
关安全控制、流量控制等。       
终端显示层的职责：各个端的模板渲染并执行显示的层。当前主要是 velocity 渲染，JS 渲染，JSP
渲染，移动端展示等。     
Web 层的职责：主要是对访问控制进行转发，各类基本参数校验，或者不复用的业务简单处理等


异常处理策略及日志规约：  
绝不应该继续往上抛异常，  
因为已经处于顶层，无继续处理异常的方式，如果意识到这个异常将导致页面无法正常渲染，
那么就应该直接跳转到友好错误页面，加上友好的错误提示信息。开放接口层要将异常处理成
错误码和错误信息方式返回


库版本号命名方式：主版本号.次版本号.修订号    
1） 主版本号：当做了不兼容的 API 修改，或者增加了能改变产品方向的新功能。    
2） 次版本号：当做了向下兼容的功能性新增（新增类、接口等）。    
3） 修订号：修复 bug，没有修改方法签名的功能加强，保持 API 兼容性。    
说明：    
注意：    
起始版本号必须为：1.0.0，而不是 0.0.1 正式发布的类库必须先去中央仓库进行查证，使版本号要有延续性，正式版本号不允许覆盖升级。
如当前版本：1.3.3，那么下一个合理的版本号：1.3.4 或 1.4.0 或 2.0.0



优化带宽ETag:     
http.headers().cacheControl().disable();  
第二次请求是要带上第一次请求的ETag值，注意ETag是有双引号包裹的      
如果没有变化，第二次请求服务器不会返回值并且HttpStatus会变为304(没有变化)    



Service 层：相对具体的业务逻辑服务层。

一个Service涉及到复杂算法时，如果通过DAO层导致逻辑不清晰  
可通过manager层来解耦    
相关算法内聚在manager层   


异常处理策略及日志规约：  
在 Service 层出现异常时，必须记录出错日志到磁盘，尽可能带上参数信息，相当于保护案发现场。



DAO 层：数据访问层，与底层 MySQL、Oracle、Hbase 进行数据交互。


异常处理策略及日志规约：   
在 DAO 层，产生的异常类型有很多，无法用细粒度的异常进行 catch，使用 catch(Exception e)方式，并 throw new DAOException(e)，不需要打印
日志，因为日志在 Manager/Service 层一定需要捕获并打到日志文件中去，如果同台服务器再打日志，浪费性能和存储。



http://localhost/flyway



通用业务处理层，它有如下特征：
1） 对第三方平台封装的层，预处理返回结果及转化异常信息；
2） 对 Service 层通用能力的下沉，如缓存方案、中间件通用处理；
3） 与 DAO 层交互，对多个 DAO 的组合复用。
放在DAO层不合适，或不能通过简单的DAO层处理的相关逻辑


异常处理策略及日志规约：   
如果 Manager 层与 Service 同机部署，日志方式与 DAO层处理一致，如果是单独部署，则采用与 Service 一致的处理方式。



分层领域模型规约：  
 DO（Data Object）：与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。  
 DTO（Data Transfer Object）：数据传输对象，Service 和 Manager 向外传输的对象。  
 BO（Business Object）：业务对象。可以由 Service 层输出的封装业务逻辑的对象。  
 Query：数据查询对象，各层接收上层的查询请求。注：超过 2 个参数的查询封装，禁止使用 Map 类来传输。   
 VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。  


##ssl:     
keytool -genkey -alias springboot-cookbook -keyalg RSA -keystore ./tomcat.keystore   
Enter keystore password:   
Re-enter new password:   
What is your first and last name?   
  [Unknown]:  springboot-cookbook   
What is the name of your organizational unit?   
  [Unknown]:  6kesong   
What is the name of your organization?   
  [Unknown]:  6kesong   
What is the name of your City or Locality?   
  [Unknown]:  sh   
What is the name of your State or Province?   
  [Unknown]:  sh   
What is the two-letter country code for this unit?   
  [Unknown]:  cn   
Is CN=springboot-cookbook, OU=6kesong, O=6kesong, L=sh, ST=sh, C=cn correct?   
  [no]:  yes   
Enter key password for <springboot-cookbook>   
        (RETURN if same as keystore password):   
Re-enter new password:   