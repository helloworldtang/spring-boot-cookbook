  
分层领域模型规约：  
 DO（Data Object）：与数据库表结构一一对应，通过 DAO 层向上传输数据源对象。  
 DTO（Data Transfer Object）：数据传输对象，Service 和 Manager 向外传输的对象。  
 BO（Business Object）：业务对象。可以由 Service 层输出的封装业务逻辑的对象。  
 Query：数据查询对象，各层接收上层的查询请求。注：超过 2 个参数的查询封装，禁止使用 Map 类来传输。   
 VO（View Object）：显示层对象，通常是 Web 向模板渲染引擎层传输的对象。    
