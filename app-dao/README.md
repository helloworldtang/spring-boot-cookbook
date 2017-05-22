操作DB do的原子单位

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