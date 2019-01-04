### SpringBootCookbook

druid  
http://localhost:9999/druid/index.html  

根据返回值中的location来确定是api json的path  
http://localhost:9999/swagger-resources  
http://localhost:9999/swagger-ui.html

//验证用的   
http://localhost:9999/swagger-resources/configuration/ui  
http://localhost:9999/swagger-resources/configuration/security


```bash
mvn clean install -Dmaven.test.skip=true      
```

todo:    
OpnApi     
`http.requireChannel().anyRequest().requireSecure().and()`
让Connector跳转时如果和server.port相同会报冲突
https也使用80端口,就是这种https://www.alipay.com/



![微信扫描二维码，关注我的公众号](https://user-images.githubusercontent.com/13701989/50696841-8c288b80-107b-11e9-9fbf-2b9e20adc166.jpg)
 <center>微信扫描二维码，关注我的公众号</center>
