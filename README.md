### SpringBootCookbook

druid  
http://localhost:9999/druid/index.html  

根据返回值中的location来确定是api json的path  
http://localhost:9999/swagger-resources  
http://localhost:9999/swagger-ui.html

//验证用的   
http://localhost:9999/swagger-resources/configuration/ui  
http://localhost:9999/swagger-resources/configuration/security


mvn clean install -Dmaven.test.skip=true      


todo:

OpenAPI平台
http.requireChannel().anyRequest().requireSecure().and()
让Connector跳转时如果和server.port相同会报冲突
https也使用80端口,就是这种https://www.baidu.com/