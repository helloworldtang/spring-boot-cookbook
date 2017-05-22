DAO 层：数据访问层，与底层 MySQL、Oracle、Hbase 进行数据交互。


异常处理策略及日志规约：   
在 DAO 层，产生的异常类型有很多，无法用细粒度的异常进行 catch，使用 catch(Exception e)方式，并 throw new DAOException(e)，不需要打印
日志，因为日志在 Manager/Service 层一定需要捕获并打到日志文件中去，如果同台服务器再打日志，浪费性能和存储。



http://localhost/flyway

```json
[
{
"migrations": [
{
"checksum": "",
"description": "<< Flyway Baseline >>",
"executionTime": 0,
"installedOn": 1492879201000,
"script": "<< Flyway Baseline >>",
"state": "SUCCESS",
"type": "BASELINE",
"version": "1"
},
{
"checksum": 948899793,
"description": "txDemo",
"executionTime": 95,
"installedOn": 1492879201000,
"script": "V2__txDemo.sql",
"state": "SUCCESS",
"type": "SQL",
"version": "2"
},
{
"checksum": -887012155,
"description": "security",
"executionTime": 289,
"installedOn": 1492879201000,
"script": "V3__security.sql",
"state": "SUCCESS",
"type": "SQL",
"version": "3"
}
],
"name": "flyway"
}
```