1、在浏览器请求：      
http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://baidu.com   
2、根据以上配置,输入用户名/密码,点同意,获取返回的授权许可码   

3、在弹出的OAuth Approval页面中       
scope.openid选项中选择"approve"      
返回的redirect_uri会多返回一个参数code，即授权许可码   
https://www.baidu.com/?code=Zv1kwD    

4、
拿到code以后，就可以调用   
POST/GET http://acme:acme-secret@localhost:8080/uaa/oauth/token?redirect_uri=http://baidu.com&grant_type=authorization_code&code=tdBO1v     
或
Post请求：
http://localhost:9999/uaa/oauth/token?redirect_uri=http://baidu.com&grant_type=authorization_code&code=tdBO1v  
参数code的值来自第一次请求，code只能使用一次   
使用postman发送请求，   
Authorization tab页，        
Type选“Basic Auth”，      
Username填acme,      
Password填acme-secret   
上面两个值来自application.properties中的配置

返回值示例：   
{    
  "access_token": "df3833c8-5407-4a7c-9a8a-7dd11112dc8b",    
  "token_type": "bearer",    
  "refresh_token": "c7c573a3-6ff4-41ca-b04b-64cb2fab020b",    
  "expires_in": 42927,    
  "scope": "openid"    
}        

5、请求资源API   
GET   
http://localhost:9999/uaa/user    
Header中传参数     
参数名：Authorization     
参数值：bearer  df3833c8-5407-4a7c-9a8a-7dd11112dc8b

Authorization的值来自上面请求的值







